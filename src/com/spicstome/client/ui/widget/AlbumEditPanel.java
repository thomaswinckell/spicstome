package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class AlbumEditPanel extends AlbumPanel{

	ActionPanel actionFoldersPanel;
	ImageTileGrid articlesGrid;
	

	IconButton buttonImportBase = new IconButton("Import from general base");
	IconButton buttonImportAlbum = new IconButton("Import from other album");
	IconButton buttonImportComputer = new IconButton("Import from PC");
	
	HLayout importPanel = new HLayout();
	
	VLayout articleVerticalPanel = new VLayout();
	
	
	VLayout detailLayout = new VLayout();
	HLayout detailContentLayout = new HLayout();
	
	Label titleDetail = new Label("Details");

	Img imgDetail = new Img();
	IconButton buttonFavorite = new IconButton("");
	private DynamicForm formDetail = new DynamicForm();
	
	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	CheckboxItem checkBoxFavorite = new CheckboxItem("favorite","Favoris");
	TextItem nameDetail = new TextItem("name","Titre");
	ComboBoxItem comboBoxOwner = new ComboBoxItem("owner","Album de");

	private DynamicForm formOwner = new DynamicForm();
	
	
	public AlbumEditPanel() {
		super();
		
		treeGrid.setCanEdit(true); 
		
	    treeGrid.setCanReorderRecords(true);  
	    treeGrid.setCanAcceptDroppedRecords(true); 
	    treeGrid.setCanReparentNodes(true);
        
        treeGrid.setShowOpenIcons(false);  
        treeGrid.setDropIconSuffix("into");  

        /*
	    treeGrid.addFolderDropHandler(new FolderDropHandler() {
	    	  @Override
	    	  public void onFolderDrop(FolderDropEvent folderDropEvent) {
	    		  
	    		  
	    		  
	    		  Canvas src = folderDropEvent.getSourceWidget();
	    		  folderDropEvent.getNodes()[0].setAttribute("title", "prout");
	    	      //System.out.println("drop into folder"+src.);
	    	      
	    	  }
	    	});*/
	    
	    treeGrid.addDropHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {
				
				System.out.println("drop ");
				System.out.println(event.getSource());
				if(event.getSource() instanceof ImageRecord)
				{
					System.out.println("drop ImageRecord");
				}
				
			}
		});
	    
	    comboBoxOwner.setValueMap("Albert","Jean","Robert");
	    comboBoxOwner.setValue("Albert");
	    formOwner.setFields(comboBoxOwner);
	    titleLayout.addMember(formOwner);
	    
	    actionFoldersPanel = new ActionPanel() {
			
			@Override
			public void onVisualize() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onEdit() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNew() {
				
				if(selectFolderNode!=null)
				{
					tree.add(new AlbumTreeNode("42", selectFolderNode.getAttribute("id_folder"), "Foo","tout.png"), selectFolderNode);
					treeGrid.setData(tree);
					
					treeGrid.getData().openAll();
				}
				
			}
		};
		
		buttonImportBase.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				 final Window winModal = new Window();
		            winModal.setWidth(1000);
		            winModal.setHeight(600);
		            winModal.setTitle("Import image from other album");
		            winModal.setShowMinimizeButton(false);
		            winModal.setIsModal(true);
		            winModal.setShowModalMask(true);
		            winModal.centerInPage();
		            
		            AlbumBookPanel albmBook =  new AlbumBookPanel();
		            albmBook.setAlbum(new Album());
		            winModal.addItem(albmBook);
		            
		            HLayout hLayout = new HLayout();
		            
		            
		            
		            IconButton importButton = new IconButton("Importer");
		            importButton.setIcon("check.png");
		            importButton.setIconSize(64);
		            
		            importButton.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							
							
									winModal.destroy();
							
						}
					});
		            
		            DropZone dropZone = new DropZone(50);
		            hLayout.addMember(dropZone);
		            hLayout.addMember(importButton);
		            
		            winModal.addItem(hLayout);
		            winModal.show();
				
			}
		});
	    
	   
	    
	    verticalLayout.addMember(actionFoldersPanel);
	    
	    verticalLayout.setStyleName("album");
	    detailLayout.setStyleName("album");
	    articleVerticalPanel.setStyleName("album");
	    
	    
	    articlesGrid = new ImageTileGrid(Mode.DRAG_AND_DROP,70,70,50){

			@Override
			public void OnSelectChanged(ImageRecord object) {
		
				
				nameDetail.setValue(object.getAttribute(ImageRecord.PICTURE_NAME));
				imgDetail.setSrc(object.getAttribute(ImageRecord.PICTURE_PATH));
				Update();
			}
	    	
	    };
	    
	   
	    
	    
	    titleDetail.setHeight(20);
	    nameDetail.setHeight(20);
	   
	    
	    imgDetail.setSize(200);
	    imgDetail.setLayoutAlign(VerticalAlignment.TOP);
	    
	    buttonFavorite.setIcon("favorite_on.png");
	    buttonFavorite.setIconSize(50);
	    
	   
	    
	    
	    radioGroupType.setValueMap("Noun", "Verb");
	    radioGroupType.setTitle("Word type");
	    
	    radioGroupGroup.setValueMap("1st group", "2nd group","3rd group","other");
	    radioGroupGroup.setTitle("Group");
	    
	    formDetail.setFields(nameDetail,checkBoxFavorite,radioGroupType,radioGroupGroup);
	   
	    
	    
	   
	    detailContentLayout.addMember(formDetail);
	    detailContentLayout.addMember(imgDetail);
	    
	    detailLayout.addMember(detailContentLayout);
	    

	    
	    
	    
	    detailLayout.setHeight(350);
	    detailLayout.setWidth(500);

	    int iconsize=32;
	    
	   
	    
	    buttonImportBase.setIcon("import_general_base.png");
	    buttonImportBase.setIconSize(iconsize);
	    buttonImportBase.setOrientation("vertical");
	    buttonImportBase.setMargin(5);
	    
	    buttonImportAlbum.setIcon("import_other_album.png");
	    buttonImportAlbum.setIconSize(iconsize);
	    buttonImportAlbum.setOrientation("vertical");
	    buttonImportAlbum.setMargin(5);

	    buttonImportComputer.setIcon("import_computer.png");
	    buttonImportComputer.setIconSize(iconsize);
	    buttonImportComputer.setOrientation("vertical");
	    buttonImportComputer.setMargin(5);
	    
	    importPanel.addMember(buttonImportBase);
	    importPanel.addMember(buttonImportAlbum);
	    importPanel.addMember(buttonImportComputer);
	    
	    importPanel.setHeight(68);
	   
	    
	    
	    articleVerticalPanel.addMember(importPanel);
	    
	    articleVerticalPanel.setHeight(350);
	    
	    horizontalLayout.addMember(articleVerticalPanel);
	    horizontalLayout.addMember(detailLayout);
	    
	    Update();
	    
	}
	
	public void Update()
	{
		
		detailContentLayout.setVisible(articlesGrid.getSelectedRecord()!=null);
		
		importPanel.setVisible(selectFolderId!=-1);
		actionFoldersPanel.setActionVisible(selectFolderId!=-1);
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
			switch(selectFolderId)
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
