package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.DropCompleteEvent;
import com.smartgwt.client.widgets.events.DropCompleteHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.FolderDropEvent;
import com.smartgwt.client.widgets.tree.events.FolderDropHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.FolderTree.AlbumTreeNode;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;
import com.spicstome.client.ui.window.FolderFormWindow;
import com.spicstome.client.ui.window.FolderPickerWindow;
import com.spicstome.client.ui.window.WordFormWindow;
import com.spicstome.client.ui.window.WordPickerWindow;

public abstract class AlbumEditPanel extends AlbumPanel{

	ActionPanel actionFoldersPanel;
	ActionPanel actionWordsPanel;
	ImageTileGrid wordsGrid;
	List<StudentDTO> allStudents;

	VLayout wordVerticalPanel = new VLayout();

	VLayout emptyLayout = new VLayout();
	Label emptyLabel = new Label();
	Img imgHere = new Img("drophere.gif");
	
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
				
				/* set the order of all folder recursively */
				ReorderFolder(f);
				
				/*save business data*/
				onReorderFolder(f.getFolderDTO());
			}
		});

	    
	    actionWordsPanel = new ActionPanel(40,true,true,false,true,false,true,true)
	    {

			@Override
			public void onNew() {
				
				FolderDTO parent = getSelectedFolder();
				
				WordFormWindow wordFormWindow = new WordFormWindow(WordFormWindow.Mode.NEW,null,parent){
					@Override
					public void onDestroy()
					{
					
						/* getting the new article */
						WordDTO a = this.word;
						
						/* saving business data */
						onSaveWord(a);
						
					}
				};
				wordFormWindow.show();

			}
			
			@Override
			public void onEdit() {
				
				FolderDTO parent = getSelectedFolder();
				WordDTO article = getSelectedWord();
				WordFormWindow articleFormWindow = new WordFormWindow(WordFormWindow.Mode.EDIT,article,parent){
					@Override
					public void onDestroy()
					{	
						WordDTO a= this.word;	
						
						/* save business data */
						onUpdateWord(a);
						
					}
				};
				
				articleFormWindow.show();
				
			}
			
			@Override
			public void onImport()
			{
				WordPickerWindow win = new WordPickerWindow(allStudents){
					@Override
					public void onDestroy()
					{
						/* getting original article to import */
						WordDTO article = (WordDTO)book.selectedImage.getAttributeAsObject(ImageRecord.DATA);
						
						/* creating a copy of the article with out folder parent */

						onCopyWord(article, getSelectedFolder());
					
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
							
							WordDTO articleDTO = getSelectedWord();
							
							/* delete business data */
							onDeleteWord(articleDTO);

						}
					}
				});
				
			}
			
			@Override
			public void onMove()
			{
				/* the student is not upto date */
				final WordDTO article = getSelectedWord();
				List<StudentDTO> set = new ArrayList<StudentDTO>();
				set.add(student);
				
				FolderPickerWindow win = new FolderPickerWindow(set,FolderPickerWindow.Type.MOVE){
					@Override
					public void onDestroy()
					{
						/* getting original article to import */
						FolderDTO folder = (FolderDTO)albumPanel.getSelectedFolder();
						
						onMoveWord(article, folder);

					}
				};			 
				win.show();
			}
		};
	    
	    actionFoldersPanel = new ActionPanel(40,true,true,false,true,false,false,true) {

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
	    
	    verticalLayout.setStyleName("bloc");
	    wordVerticalPanel.setStyleName("bloc");
	    
	    wordsGrid = new ImageTileGrid(Mode.DRAG_AND_DROP,150,150,100){
	    	
	    	@Override
			public void OnSelectChanged(ImageRecord object) {

				UpdateActionPanels();
			}
	    	
	    	@Override
			public void OnDropOrReorder(WordDTO article)
	    	{

	    		RecordList list = wordsGrid.getDataAsRecordList();
	    		
	    		
	    		for(int i=0;i<list.getLength();i++)
	    		{
	    			WordDTO a = (WordDTO)((ImageRecord)(list.get(i))).getAttributeAsObject(ImageRecord.DATA);  			
	    			a.setOrder(i);
	    		
	    			onReorderWord(a);
	    		}
	    	}
	    	
	    };
	    
	    emptyLabel.setContents("C'est vide ici ! Vous pouvez ajouter ou importer des mots depuis "+
	    "d'autre albums ou depuis votre ordinateur");
	    emptyLabel.setStyleName("title");
	    emptyLabel.setMargin(50);
	    emptyLayout.addMember(emptyLabel);
	    imgHere.setSize(60);
		imgHere.setPrompt("Ajouter ou importer des mots grâce aux icones ci-dessous");
	    emptyLayout.addMember(imgHere);
	    
	    wordVerticalPanel.addMember(emptyLayout);
	    wordVerticalPanel.addMember(wordsGrid);
	    wordVerticalPanel.addMember(actionWordsPanel);
	       
	    verticalLayout.setHeight(450);
	    wordVerticalPanel.setHeight(450);
	    
	    
	   
	    horizontalLayout.addMember(wordVerticalPanel);

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
			
			ReorderFolder((AlbumTreeNode)childNode);
			
			order++;
		}
	}
	
	public void removeFolderFromTree(FolderDTO folderDTO)
	{
		folderTree.tree.remove(folderTree.selectFolderNode);
		folderTree.treeGrid.setData(folderTree.tree);		
		folderTree.treeGrid.getData().openAll();
		
		wordsGrid.deselectAllRecords();	
		folderTree.selectFolderNode=null;
		folderTree.treeGrid.deselectAllRecords();
		
		wordsGrid.clearItems();
		UpdateActionPanels();
		UpdateEmptyFolder();
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
	
	public void updateWordIntoGrid(FolderDTO folder)
	{		
		ArrayList<ImageRecord> words = new ArrayList<ImageRecord>();
		
		for(PecsDTO pecsDTO:folder.getContent())
		{
			if(pecsDTO instanceof WordDTO)
			{
				words.add(new ImageRecord((WordDTO)pecsDTO));
			}
				
		}
		
		wordsGrid.setItems(words);
		
		folderTree.selectFolderNode.setFolderDTO(folder);
		
		UpdateActionPanels();
		UpdateEmptyFolder();
		
	}
	
	
	
	public void insertWordIntoGrid(WordDTO wordDTO)
	{
			wordsGrid.addItem(new ImageRecord(wordDTO));
			UpdateEmptyFolder();
	}
	
	public void removeWordFromGrid(WordDTO wordDTO)
	{
		wordsGrid.removeItem((ImageRecord)wordsGrid.getSelectedRecord());
		wordsGrid.deselectAllRecords();	
		UpdateActionPanels();
		UpdateEmptyFolder();
	}
	
	public WordDTO getSelectedWord()
	{
		return (WordDTO)((ImageRecord)(wordsGrid.getSelectedRecord())).getAttributeAsObject(ImageRecord.DATA);
	}
	
	public int getWordCountInFolder()
	{
		int n=0;
		for(PecsDTO pecsDTO:getSelectedFolder().getContent())
		{
			if(pecsDTO instanceof WordDTO)
				n++;		
		}
		
		return n;
	}
	
	public void UpdateEmptyFolder()
	{
		boolean empty = (folderTree.selectFolderNode!=null) && (getWordCountInFolder()==0);
		
		emptyLayout.setVisible(empty);
		wordsGrid.setVisible(!empty);
		
	}
	
	public void UpdateActionPanels()
	{
		actionWordsPanel.setHiddenActionVisible(wordsGrid.getSelectedRecord()!=null);
		actionWordsPanel.setVisible(folderTree.selectFolderNode!=null);
		actionFoldersPanel.setHiddenActionVisible(folderTree.selectFolderNode!=null);
	}

	@Override
	public void setStudent(StudentDTO student)
	{
		super.setStudent(student);
		
		wordsGrid.clearItems();
		UpdateActionPanels();
		UpdateEmptyFolder();
	}
	
	
	public void updateAlbum(AlbumDTO album)
	{
		student.setAlbum(album);
		super.setStudent(student);
	}
	
	
	
	public void setAllStudents(List<StudentDTO> list)
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
	
	
	
	
	public abstract void onSaveWord(WordDTO wordDTO);
	public abstract void onSaveFolder(FolderDTO folderDTO);
	public abstract void onDeleteWord(WordDTO wordDTO);
	public abstract void onDeleteFolder(FolderDTO f);
	public abstract void onMoveFolder(FolderDTO child,FolderDTO parent);
	public abstract void onMoveWord(WordDTO child,FolderDTO parent);
	public abstract void onUpdateFolder(FolderDTO folderDTO);
	public abstract void onUpdateWord(WordDTO wordDTO);
	public abstract void onLoadFolder(FolderDTO folder);
	public abstract void onReorderWord(WordDTO word);
	public abstract void onReorderFolder(FolderDTO folder);
	public abstract void onCopyFolder(FolderDTO folder,FolderDTO parent);
	public abstract void onCopyWord(WordDTO word,FolderDTO parent);

}
