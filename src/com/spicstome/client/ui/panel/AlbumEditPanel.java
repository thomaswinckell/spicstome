package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.DropCompleteEvent;
import com.smartgwt.client.widgets.events.DropCompleteHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.FolderDropEvent;
import com.smartgwt.client.widgets.tree.events.FolderDropHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.form.ArticleFormWindow;
import com.spicstome.client.ui.form.FolderFormWindow;
import com.spicstome.client.ui.picker.ArticlePickerWindow;
import com.spicstome.client.ui.picker.FolderPickerWindow;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.FolderTree.AlbumTreeNode;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public abstract class AlbumEditPanel extends AlbumPanel{

	ActionPanel actionFoldersPanel;
	ActionPanel actionArticlePanel;
	ImageTileGrid articlesGrid;
	Set<StudentDTO> allStudents;

	VLayout articleVerticalPanel = new VLayout();

	
	
	public AlbumEditPanel() {
		super();
		
		folderTree.AllowReorder();
		
		folderTree.treeGrid.addFolderDropHandler(new FolderDropHandler() {

			@Override
			public void onFolderDrop(FolderDropEvent event) {

				if(event.getFolder()!=null && (event.getFolder() instanceof AlbumTreeNode))
				{	
					FolderDTO parent = ((AlbumTreeNode)(event.getFolder())).getFolderDTO();
					FolderDTO child = ((AlbumTreeNode)(event.getNodes()[0])).getFolderDTO();
					onMoveFolder(child,parent);
					
				}
				else
				{
					SC.warn("Vos dossiers doivent être dans la racine");
					
					event.cancel();
				}

			}
			
			
		});
		
		folderTree.treeGrid.addDropCompleteHandler(new DropCompleteHandler() {
			
			@Override
			public void onDropComplete(DropCompleteEvent event) {
				
				AlbumTreeNode f = (AlbumTreeNode)(folderTree.tree.getChildren(folderTree.tree.getRoot())[0]);
				
				ReorderFolder(f);
				
			}
		});

	    
	    actionArticlePanel = new ActionPanel(true,true,false,true,false,true,true)
	    {

			@Override
			public void onNew() {
				
				FolderDTO parent = getSelectedFolder();
				
				ArticleFormWindow articleFormWindow = new ArticleFormWindow(ArticleFormWindow.Mode.NEW,null,parent){
					@Override
					public void onDestroy()
					{
					
						/* getting the new article */
						ArticleDTO a = this.article;
						
						/* saving business data */
						onSaveArticle(a);
						
					}
				};
				articleFormWindow.show();

			}
			
			@Override
			public void onEdit() {
				
				FolderDTO parent = getSelectedFolder();
				ArticleDTO article = getSelectedArticle();
				ArticleFormWindow articleFormWindow = new ArticleFormWindow(ArticleFormWindow.Mode.EDIT,article,parent){
					@Override
					public void onDestroy()
					{	
						ArticleDTO a= this.article;	
						
						/* save business data */
						onUpdateArticle(a);
						
					}
				};
				
				articleFormWindow.show();
				
			}
			
			@Override
			public void onImport()
			{
				ArticlePickerWindow win = new ArticlePickerWindow(allStudents){
					@Override
					public void onDestroy()
					{
						/* getting original article to import */
						ArticleDTO article = (ArticleDTO)book.selectedImage.getAttributeAsObject(ImageRecord.DATA);
						
						/* creating a copy of the article with out folder parent */

						onCopyArticle(article, getSelectedFolder());
					
					}
				};			 
				win.show();
			}

			@Override
			public void onDelete() {
				
				SC.confirm("Êtes vous sure de vouloir supprimer cet article ?", new BooleanCallback() {
					
					public void execute(Boolean value) {
						if (value != null && value) 
						{
							
							ArticleDTO articleDTO = getSelectedArticle();
							
							/* delete business data */
							onDeleteArticle(articleDTO);

						}
					}
				});
				
			}
			
			@Override
			public void onMove()
			{
				/* the student is not upto date */
				final ArticleDTO article = getSelectedArticle();
				Set<StudentDTO> set = new HashSet<StudentDTO>();
				set.add(student);
				
				FolderPickerWindow win = new FolderPickerWindow(set,FolderPickerWindow.Type.MOVE){
					@Override
					public void onDestroy()
					{
						/* getting original article to import */
						FolderDTO folder = (FolderDTO)albumPanel.getSelectedFolder();
						
						onMoveArticle(article, folder);

					}
				};			 
				win.show();
			}
		};
	    
	    actionFoldersPanel = new ActionPanel(true,true,false,true,false,false,true) {

			@Override
			public void onEdit() {
				FolderDTO folder = getSelectedFolder();
				FolderFormWindow folderFormWindow = new FolderFormWindow(FolderFormWindow.Mode.EDIT,folder,null){
					@Override
					public void onDestroy()
					{	
						FolderDTO f= this.folder;	
						
						/* save business data */
						onUpdateFolder(f);
					}
				};
			
				folderFormWindow.show();
			}

			@Override
			public void onNew() {
				
				FolderDTO parent = getSelectedFolder();
				
				if(parent!=null)
				{
					FolderFormWindow folderFormWindow = new FolderFormWindow(FolderFormWindow.Mode.NEW,null,parent){
						@Override
						public void onDestroy()
						{	
							FolderDTO f= this.folder;	
							
							/* save business data */
							onSaveFolder(f);
						}};

					folderFormWindow.show();
				}
				else
				{
					SC.warn("Aucun dossier parent séléctionné");
				}

			}
			
			@Override
			public void onImport()
			{
				final FolderDTO parent = getSelectedFolder();
				
				if(parent!=null)
				{
					FolderPickerWindow win = new FolderPickerWindow(allStudents,FolderPickerWindow.Type.IMPORT){
						@Override
						public void onDestroy()
						{
							/* getting original article to import */
							FolderDTO folder = (FolderDTO)albumPanel.getSelectedFolder();
							
							onCopyFolder(folder, parent);
							

						}
					};			 
					win.show();
				}
				else
				{
					SC.warn("Vous devez selectionner un dossier de destination");
				}
			
			}

			@Override
			public void onDelete() {
				
				final FolderDTO folderDTO = getSelectedFolder();
				
				if(folderDTO.getFolder()!=null)
				{
					SC.confirm("Êtes vous sure de vouloir supprimer ce dossier ainsi que son contenu ?", new BooleanCallback() {
						
						public void execute(Boolean value) {
							if (value != null && value) 
							{
								onDeleteFolder(folderDTO);
							}
						}
					});
				
				}
				else
				{
					SC.warn("Vous ne pouvez pas supprimer la racine");
				}
				
				
			}
		};
		
		
	    
	    verticalLayout.addMember(actionFoldersPanel);
	    
	    verticalLayout.setStyleName("album");
	    articleVerticalPanel.setStyleName("album");
	    
	    articlesGrid = new ImageTileGrid(Mode.DRAG_AND_DROP,70,70,50){
	    	
	    	@Override
			public void OnSelectChanged(ImageRecord object) {

				UpdateActionPanels();
			}
	    	
	    	@Override
			public void OnReorder()
	    	{
	    		RecordList list = articlesGrid.getDataAsRecordList();
	    		
	    		
	    		for(int i=0;i<list.getLength();i++)
	    		{
	    			ArticleDTO a = (ArticleDTO)((ImageRecord)(list.get(i))).getAttributeAsObject(ImageRecord.DATA);  			
	    			a.setOrder(i);
	    		
	    			onReorderArticle(a);
	    		}
	    	}
	    	
	    };
	    
	    articleVerticalPanel.addMember(actionArticlePanel);
	       
	    verticalLayout.setHeight(350);
	    articleVerticalPanel.setHeight(350);
	    
	    horizontalLayout.addMember(articleVerticalPanel);

	    UpdateActionPanels();
	    
	}
	
	public void ReorderFolder(AlbumTreeNode f)
	{
		TreeNode[] childsNode = folderTree.tree.getChildren(f);
		
		int order = 0;
		for(TreeNode childNode:childsNode)
		{
			FolderDTO fo = ((AlbumTreeNode)(childNode)).getFolderDTO();
			System.out.println(fo.getName());
			
			fo.setOrder(order);
			
			onReorderFolder(fo);
			
			ReorderFolder((AlbumTreeNode)childNode);
			
			order++;
		}
	}
	
	public void removeFolderFromTree(FolderDTO folderDTO)
	{
		folderTree.tree.remove(folderTree.selectFolderNode);
		folderTree.treeGrid.setData(folderTree.tree);		
		folderTree.treeGrid.getData().openAll();
		
		articlesGrid.deselectAllRecords();	
		folderTree.selectFolderNode=null;
		folderTree.treeGrid.deselectAllRecords();
		
		articlesGrid.clearItems();
		UpdateActionPanels();
	}
	
	public void insertFolderIntoTree(FolderDTO folderDTO)
	{
		folderTree.tree.add(new AlbumTreeNode(folderDTO),folderTree.getFolderNodeWithId(folderDTO.getFolder().getId()));
		folderTree.treeGrid.setData(folderTree.tree);		
		folderTree.treeGrid.getData().openAll();
	}
	
	public void updateFolderIntoTree(FolderDTO folder)
	{
		folderTree.tree.getAllNodes(folderTree.selectFolderNode)[0].setAttribute("title",folder.getName());
		folderTree.tree.getAllNodes(folderTree.selectFolderNode)[0].setAttribute("icon","upload/"+folder.getImage().getFilename());
		folderTree.treeGrid.setData(folderTree.tree);		
		folderTree.treeGrid.getData().openAll();
	}
	
	public void updateArticleIntoGrid(FolderDTO folder)
	{		
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		for(PecsDTO pecsDTO:folder.getContent())
		{
			if(pecsDTO instanceof ArticleDTO)
			{
				articles.add(new ImageRecord((ArticleDTO)pecsDTO));
			}
				
		}
		
		articlesGrid.setItems(articles);
		articleVerticalPanel.addMember(articlesGrid,0);
		
		UpdateActionPanels();
		
	}
	
	
	
	public void insertArticleIntoGrid(ArticleDTO articleDTO)
	{
		if(articleDTO.getFolder().getId()==getSelectedFolder().getId())
			articlesGrid.addItem(new ImageRecord(articleDTO));
	}
	
	public void removeArticleFromGrid(ArticleDTO articleDTO)
	{
		articlesGrid.removeItem((ImageRecord)articlesGrid.getSelectedRecord());
		articlesGrid.deselectAllRecords();	
		UpdateActionPanels();
	}
	
	public ArticleDTO getSelectedArticle()
	{
		return (ArticleDTO)((ImageRecord)(articlesGrid.getSelectedRecord())).getAttributeAsObject(ImageRecord.DATA);
	}
	
	
	
	public void UpdateActionPanels()
	{
		actionArticlePanel.setHiddenActionVisible(articlesGrid.getSelectedRecord()!=null);
		actionArticlePanel.setVisible(folderTree.selectFolderNode!=null);
		actionFoldersPanel.setHiddenActionVisible(folderTree.selectFolderNode!=null);
	}

	@Override
	public void setStudent(StudentDTO student)
	{
		super.setStudent(student);
		
		articlesGrid.clearItems();
		UpdateActionPanels();
	}
	
	
	public void updateAlbum(AlbumDTO album)
	{
		student.setAlbum(album);
	}
	
	
	
	public void setAllStudents(Set<StudentDTO> list)
	{
		if(list.size()>0)
		{
			this.allStudents=list;
		}
			
		else
		{
		// cacher les boutons import
		}
		
		
	}
	
	

	@Override
	public void onFolderClick(NodeClickEvent event)
	{
		super.onFolderClick(event);

		onLoadFolder(getSelectedFolder());
	
	}
	
	
	
	
	public abstract void onSaveArticle(ArticleDTO articleDTO);
	public abstract void onSaveFolder(FolderDTO folderDTO);
	public abstract void onDeleteArticle(ArticleDTO a);
	public abstract void onDeleteFolder(FolderDTO f);
	public abstract void onMoveFolder(FolderDTO child,FolderDTO parent);
	public abstract void onMoveArticle(ArticleDTO child,FolderDTO parent);
	public abstract void onUpdateFolder(FolderDTO folderDTO);
	public abstract void onUpdateArticle(ArticleDTO articleDTO);
	public abstract void onLoadFolder(FolderDTO folder);
	public abstract void onReorderArticle(ArticleDTO article);
	public abstract void onReorderFolder(FolderDTO folder);
	public abstract void onCopyFolder(FolderDTO folder,FolderDTO parent);
	public abstract void onCopyArticle(ArticleDTO article,FolderDTO parent);

}
