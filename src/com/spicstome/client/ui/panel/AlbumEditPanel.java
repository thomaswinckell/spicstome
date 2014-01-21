package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.FolderDropEvent;
import com.smartgwt.client.widgets.tree.events.FolderDropHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.LogDTO;
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
	Set<StudentDTO> others;

	VLayout articleVerticalPanel = new VLayout();

	public ComboBoxItem comboBoxOwner = new ComboBoxItem("owner","Proprietaire");
	DynamicForm formOwner = new DynamicForm();
	
	
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
					
					/*
					AlbumTreeNode f = (AlbumTreeNode)(folderTree.tree.getRoot().getn);
					
					TreeNode[] childsNode = folderTree.tree.getChildren(f);
					
					for(TreeNode childNode:childsNode)
					{
						FolderDTO fo = ((AlbumTreeNode)(childNode)).getFolderDTO();
						System.out.println(fo.getName());
					}*/
				}
				else
				{
					SC.warn("Vos dossiers doivent être dans la racine");
					
					event.cancel();
				}

			}
		});

	    
	    comboBoxOwner.setValueMap("Albert","Jean","Robert");

	    formOwner.setFields(comboBoxOwner);
	    titleLayout.addMember(formOwner);
	    
	    actionArticlePanel = new ActionPanel(true,true,false,true,true)
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
				ArticlePickerWindow win = new ArticlePickerWindow(others){
					@Override
					public void onDestroy()
					{
						/* getting original article to import */
						ArticleDTO article = (ArticleDTO)book.selectedImage.getAttributeAsObject(ImageRecord.DATA);
						
						/* creating a copy of the article with out folder parent */
						
						ArticleDTO copyArticle = getCopyOfArticle(article, getSelectedFolder());
						
						/* saving business data */
						onSaveArticle(copyArticle);
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
		};
	    
	    actionFoldersPanel = new ActionPanel(true,true,false,true,true) {

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
					FolderPickerWindow win = new FolderPickerWindow(others){
						@Override
						public void onDestroy()
						{
							/* getting original article to import */
							FolderDTO folder = (FolderDTO)albumPanel.getSelectedFolder();
							
							onSaveFolder(getCopyOfFolder(folder,parent));
							

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
	public void setAlbum(AlbumDTO album)
	{
		super.setAlbum(album);
		articlesGrid.clearItems();
		UpdateActionPanels();
	}
	
	public void setOthersAlbum(Set<StudentDTO> list)
	{
		if(list.size()>0)
			this.others=list;
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
	
	@Override
	public void setOwnerName(String name)
	{
		super.setOwnerName(name);
		comboBoxOwner.setValue(name); 
	}
	
	public ArticleDTO getCopyOfArticle(ArticleDTO article,FolderDTO parent)
	{
		ImageDTO copyImage = new ImageDTO((long)-1, article.getImage().getFilename());
		ArticleDTO copyArticle = new ArticleDTO((long)-1,article.getName(),article.getOrder(),parent,copyImage,new HashSet<LogDTO>()) ;
	
		return copyArticle;
	}
	
	public FolderDTO getCopyOfFolder(FolderDTO folderDTO,FolderDTO parent)
	{
		FolderDTO copyFolder = new FolderDTO((long)-1,
				folderDTO.getName(),
				folderDTO.getOrder(),
				parent,
				new ImageDTO((long)-1,folderDTO.getImage().getFilename()),
				new ArrayList<PecsDTO>());
		
		
		for(PecsDTO pecs :folderDTO.getContent())
		{
			if(pecs instanceof ArticleDTO)
			{
				ArticleDTO copyArticle = getCopyOfArticle((ArticleDTO)pecs, copyFolder);
				copyFolder.getContent().add(copyArticle);
			}
			else
			{
				FolderDTO copyFolder2 = getCopyOfFolder((FolderDTO)pecs, copyFolder);
				copyFolder.getContent().add(copyFolder2);
			}
		}
		
		return copyFolder;
	}
	
	public abstract void onSaveArticle(ArticleDTO articleDTO);
	public abstract void onSaveFolder(FolderDTO folderDTO);
	public abstract void onDeleteArticle(ArticleDTO a);
	public abstract void onDeleteFolder(FolderDTO f);
	public abstract void onMoveFolder(FolderDTO child,FolderDTO parent);
	public abstract void onUpdateFolder(FolderDTO folderDTO);
	public abstract void onUpdateArticle(ArticleDTO articleDTO);
	public abstract void onLoadFolder(FolderDTO folder);
	public abstract void onReorderArticle(ArticleDTO article);

}
