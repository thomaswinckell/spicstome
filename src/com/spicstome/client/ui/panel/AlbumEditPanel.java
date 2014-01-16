package com.spicstome.client.ui.panel;

import java.util.ArrayList;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.ui.form.ArticleFormWindow;
import com.spicstome.client.ui.form.FolderFormWindow;
import com.spicstome.client.ui.picker.ArticlePickerWindow;
import com.spicstome.client.ui.picker.FolderPickerWindow;
import com.spicstome.client.ui.widget.FolderTree;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public abstract class AlbumEditPanel extends AlbumPanel{

	ActionPanel actionFoldersPanel;
	ActionPanel actionArticlePanel;
	ImageTileGrid articlesGrid;
	

	VLayout articleVerticalPanel = new VLayout();

	public ComboBoxItem comboBoxOwner = new ComboBoxItem("owner","Album de");
	DynamicForm formOwner = new DynamicForm();
	
	
	public AlbumEditPanel() {
		super();
		
		folderTree.AllowReorder();
	    
	    comboBoxOwner.setValueMap("Albert","Jean","Robert");
	    comboBoxOwner.setValue("Albert");
	    formOwner.setFields(comboBoxOwner);
	    titleLayout.addMember(formOwner);
	    
	    actionArticlePanel = new ActionPanel(true,true,false,true,true)
	    {

			@Override
			public void onNew() {
				ArticleFormWindow articleFormWindow = new ArticleFormWindow(ArticleFormWindow.Mode.NEW,null){
					@Override
					public void onDestroy()
					{
						/*
						ArticleDTO artcileDTO = new ArticleDTO((long)-1,
								form.getValueAsString("name"),
								0,
								album.getFolder(),
								new ImageDTO(),
								new HashSet<LogDTO>());
						
						album.getFolder().getContent().add(artcileDTO);*/
						onSaveArticle();
					}
				};
				articleFormWindow.show();

			}
			
		
			
			@Override
			public void onEdit() {
				
				ArticleDTO article = getSelectedArticle();
				ArticleFormWindow articleFormWindow = new ArticleFormWindow(ArticleFormWindow.Mode.EDIT,article);
				articleFormWindow.show();
				
			}
			
			@Override
			public void onImport()
			{
				ArticlePickerWindow win = new ArticlePickerWindow(new ArrayList<AlbumDTO>()){
					@Override
					public void onDestroy()
					{
						// todo traitement 
						//System.out.println(win.book.selectedImage.toString());
						articlesGrid.addItem(book.selectedImage);
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
							
							//suppression métier
							onDeleteArticle(articleDTO);
							
							//suppression graphique
							articlesGrid.removeData(articlesGrid.getSelectedItem());
							
							//mis à jour graphique
							Update();
						}
					}
				});
				
			}
		};
	    
	    actionFoldersPanel = new ActionPanel(true,true,false,true,true) {

			@Override
			public void onEdit() {
				FolderDTO folder = getSelectedFolder();
				FolderFormWindow folderFormWindow = new FolderFormWindow(FolderFormWindow.Mode.EDIT,folder);
				folderFormWindow.show();
			}

			@Override
			public void onNew() {
				FolderFormWindow folderFormWindow = new FolderFormWindow(FolderFormWindow.Mode.NEW,null){
					@Override
					public void onDestroy()
					{
						onSaveFolder();
					}};

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
			public void onImport()
			{
				FolderPickerWindow win = new FolderPickerWindow(new ArrayList<AlbumDTO>()){
					@Override
					public void onDestroy()
					{
						// todo traitement 
						//System.out.println(win.book.selectedImage.toString());
						
						folderTree.tree.add(new FolderTree.AlbumTreeNode("42",
								folderTree.selectFolderNode.getAttribute("id_folder"), 
								albumPanel.folderTree.selectFolderNode.getAttribute("title"),
								albumPanel.folderTree.selectFolderNode.getAttribute("icon"),null), 
								folderTree.selectFolderNode);
						folderTree.treeGrid.setData(folderTree.tree);
						
						folderTree.treeGrid.getData().openAll();
					}
				};			 
				win.show();
			}

			@Override
			public void onDelete() {
				
				FolderDTO folderDTO = getSelectedFolder();
				
				onDeleteFolder(folderDTO);
				
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
	
	public ArticleDTO getSelectedArticle()
	{
		return (ArticleDTO)((ImageRecord)(articlesGrid.getSelectedRecord())).getAttributeAsObject(ImageRecord.DATA);
	}
	
	public FolderDTO getSelectedFolder()
	{
		return (FolderDTO)folderTree.selectFolderNode.getAttributeAsObject("data");
	}
	
	public void Update()
	{
		actionArticlePanel.setHiddenActionVisible(articlesGrid.getSelectedRecord()!=null);
		actionArticlePanel.setVisible(folderTree.selectFolderId!=-1);
		actionFoldersPanel.setHiddenActionVisible(folderTree.selectFolderId!=-1);
	}
	
	@Override
	public void setAlbum(AlbumDTO album)
	{
		super.setAlbum(album);
		Update();
	}
	
	@Override
	public boolean onFolderClick(NodeClickEvent event)
	{
		boolean changed = super.onFolderClick(event);

		
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		if(changed)
		{
			
			FolderDTO folder = getSelectedFolder();
			
			
			for(PecsDTO pecsDTO:folder.getContent())
			{
				if(pecsDTO instanceof ArticleDTO)
				{
					articles.add(new ImageRecord((ArticleDTO)pecsDTO));
				}
					
			}
			
			articlesGrid.setItems(articles);
			articleVerticalPanel.addMember(articlesGrid,0);
			
			Update();
			
			return true;
		}
		
		return false;
		
		
	}
	
	public abstract void onSaveArticle();
	public abstract void onSaveFolder();
	public abstract void onDeleteArticle(ArticleDTO a);
	public abstract void onDeleteFolder(FolderDTO f);

}
