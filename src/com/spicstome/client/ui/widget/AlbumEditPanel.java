package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

/*
 * Folder new 
 * 	on peut importer un dossier depuis base , example (on a son image , nom et tout les fils en copie)
 * 	 ou pc (on cherche l'image sur pc)
 * Folder edit
 * 	(on cherche l'image sur pc) , pas d'autre import car va ecrasé les fils
 * Article new
 * 	on peut importer une image (article!) depuis base , example ou pc
 * Article edit
 * 	on peut importer une image (article!) depuis base , example ou pc
 */

public class AlbumEditPanel extends AlbumPanel{

	ActionPanel actionFoldersPanel;
	ActionPanel actionArticlePanel;
	ImageTileGrid articlesGrid;
	

	VLayout articleVerticalPanel = new VLayout();

	ComboBoxItem comboBoxOwner = new ComboBoxItem("owner","Album de");
	DynamicForm formOwner = new DynamicForm();
	
	
	public AlbumEditPanel() {
		super();
		
		folderTree.AllowReorder();
	    
	    comboBoxOwner.setValueMap("Albert","Jean","Robert");
	    comboBoxOwner.setValue("Albert");
	    formOwner.setFields(comboBoxOwner);
	    titleLayout.addMember(formOwner);
	    
	    actionArticlePanel = new ActionPanel(false) {
			
	    	
	    
			@Override
			public void onVisualize() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onNew() {
				ArticleFormWindow articleFormWindow = new ArticleFormWindow();
				articleFormWindow.show();
				
				
			}
			
			@Override
			public void onEdit() {
				ArticleFormWindow articleFormWindow = new ArticleFormWindow();
				articleFormWindow.show();
				
			}

			@Override
			public void onDelete() {
				SC.confirm("Êtes vous sure de vouloir supprimer cet article ?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value != null && value) 
						{
							articlesGrid.removeData(articlesGrid.getSelectedItem());
							Update();
						}
					}
				});
				
			}
		};
	    
	    actionFoldersPanel = new ActionPanel(false) {
			
			@Override
			public void onVisualize() {
				
				
			}
			
			@Override
			public void onEdit() {
				PecsFormWindow folderFormWindow = new PecsFormWindow();
				folderFormWindow.show();
			}

			@Override
			public void onNew() {
				
				PecsFormWindow folderFormWindow = new PecsFormWindow();
				folderFormWindow.show();
				
				/*
				if(folderTree.selectFolderNode!=null)
				{
					folderTree.tree.add(new AlbumTreeNode("42", folderTree.selectFolderNode.getAttribute("id_folder"), "Foo","tout.png"), folderTree.selectFolderNode);
					folderTree.treeGrid.setData(folderTree.tree);
					
					folderTree.treeGrid.getData().openAll();
				}
				*/
				
			}

			@Override
			public void onDelete() {
				// TODO Auto-generated method stub
				
			}
		};
		
		
	    
	    verticalLayout.addMember(actionFoldersPanel);
	    
	    verticalLayout.setStyleName("album");
	    articleVerticalPanel.setStyleName("album");
	    
	    articlesGrid = new ImageTileGrid(Mode.DRAG_AND_DROP,70,70,50){
	    	
	    	@Override
			public void OnSelectChanged(ImageRecord object) {

				Update();
			}
	    	
	    };
	    

	    articleVerticalPanel.addMember(actionArticlePanel);
	   
	    
	    verticalLayout.setHeight(350);
	    articleVerticalPanel.setHeight(350);
	    
	    horizontalLayout.addMember(articleVerticalPanel);
	    
	    Update();
	    
	}
	
	public void Update()
	{
		actionArticlePanel.setActionVisible(articlesGrid.getSelectedRecord()!=null);
		actionArticlePanel.setVisible(folderTree.selectFolderId!=-1);
		actionFoldersPanel.setActionVisible(folderTree.selectFolderId!=-1);
	}
	
	@Override
	public void setAlbum(Album album)
	{
		super.setAlbum(album);
		
		articlesGrid.setItems(new ArrayList<ImageRecord>());
		
		Update();
	}
	
	@Override
	public boolean onFolderClick(NodeClickEvent event)
	{
		boolean changed = super.onFolderClick(event);

		
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		if(changed)
		{
			switch(folderTree.selectFolderId)
			{
			case 3:
				articles.add(new ImageRecord(0,"Je","albumlogo.png"));
				articles.add(new ImageRecord(0,"Tu","albumlogo.png"));
				articles.add(new ImageRecord(0,"Il","albumlogo.png"));
				articles.add(new ImageRecord(0,"Nous","albumlogo.png"));
				articles.add(new ImageRecord(0,"Vous","albumlogo.png"));
				articles.add(new ImageRecord(0,"Ils","albumlogo.png"));
				articles.add(new ImageRecord(0,"Moi","albumlogo.png"));
				articles.add(new ImageRecord(0,"Toi","albumlogo.png"));
				articles.add(new ImageRecord(0,"Autre","albumlogo.png"));
				articles.add(new ImageRecord(0,"Riri","albumlogo.png"));
				articles.add(new ImageRecord(0,"Fifi","albumlogo.png"));
				articles.add(new ImageRecord(0,"LouLou","albumlogo.png"));
				articles.add(new ImageRecord(0,"Toto","albumlogo.png"));
				articles.add(new ImageRecord(0,"titi","albumlogo.png"));
				articles.add(new ImageRecord(0,"tata","albumlogo.png"));
	
			
				break;
			case 4:
				articles.add(new ImageRecord(0,"Je","albumlogo.png"));
				articles.add(new ImageRecord(0,"Tu","albumlogo.png"));
			break;
			case 5:
				
			break;
			}
		
			
			
			
			articlesGrid.setItems(articles);
			articleVerticalPanel.addMember(articlesGrid,0);
			
			Update();
			
			return true;
		}
		
		return false;
		
		
	}

}
