package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.FolderDropEvent;
import com.smartgwt.client.widgets.tree.events.FolderDropHandler;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class AlbumEditPanel extends AlbumPanel{

	ActionPanel actionFoldersPanel;
	ImageTileGrid articlesGrid;
	

	IconButton buttonImportBase = new IconButton("");
	IconButton buttonImportAlbum = new IconButton("");
	IconButton buttonImportComputer = new IconButton("");
	
	HLayout importPanel = new HLayout();
	
	VLayout articleVerticalPanel = new VLayout();
	
	VLayout detailLayout = new VLayout();
	VLayout detailContentLayout = new VLayout();
	
	Label titleDetail = new Label("Details :");
	Label nameLabelDetail = new Label("Title :");
	Label nameDetail = new Label("");
	Img imgDetail = new Img();
	private DynamicForm form = new DynamicForm();
	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	
	HLayout topLayout = new HLayout();
	VLayout recapLayout = new VLayout();
	
	public AlbumEditPanel(Album album) {
		super(album);
		
		treeGrid.setCanEdit(true); 
	    treeGrid.setCanReorderRecords(true);  
        treeGrid.setCanAcceptDroppedRecords(true); 
        treeGrid.setCanAcceptDrop(true);
	    
	    treeGrid.addFolderDropHandler(new FolderDropHandler() {
	    	  @Override
	    	  public void onFolderDrop(FolderDropEvent folderDropEvent) {
	    		  
	    		  Canvas src = folderDropEvent.getSourceWidget();
	    		  folderDropEvent.getNodes()[0].setAttribute("title", "prout");
	    	      //System.out.println("drop into folder"+src.);
	    	      
	    	  }
	    	});
	    
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
		            
		            winModal.addItem(new AlbumBookPanel(new Album()));
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
		
				
				nameDetail.setContents(object.getAttribute(ImageRecord.PICTURE_NAME));
				imgDetail.setSrc(object.getAttribute(ImageRecord.PICTURE_PATH));
				Update();
			}
	    	
	    };
	    
	    imgDetail.setSize(100);
	    
	    
	    titleDetail.setHeight(20);
	    nameDetail.setHeight(20);
	    nameLabelDetail.setHeight(20);
	    
	    recapLayout.addMember(titleDetail);
	    recapLayout.addMember(nameLabelDetail);
	    recapLayout.addMember(nameDetail);
	    
	    topLayout.addMember(recapLayout);
	    topLayout.addMember(imgDetail);
	    
	    radioGroupType.setValueMap("Noun", "Verb");
	    radioGroupType.setTitle("Word type");
	    
	    radioGroupGroup.setValueMap("1st group", "2nd group","3rd group","other");
	    radioGroupGroup.setTitle("Group");
	    
	    form.setFields(radioGroupType,radioGroupGroup);
	    
	    
	    detailContentLayout.addMember(topLayout);
	    detailContentLayout.addMember(form);
	    
	    detailLayout.addMember(detailContentLayout);
	    

	    
	    verticalLayout.setWidth(350);
	    verticalLayout.setHeight(350);
	    
	    detailLayout.setHeight(350);
	    detailLayout.setWidth(500);

	    int iconsize=64;
	    
	   
	    
	    buttonImportBase.setIcon("import_general_base.png");
	    buttonImportBase.setIconSize(iconsize);
	    
	    buttonImportAlbum.setIcon("import_other_album.png");
	    buttonImportAlbum.setIconSize(iconsize);

	    buttonImportComputer.setIcon("import_computer.png");
	    buttonImportComputer.setIconSize(iconsize);
	    
	    importPanel.addMember(buttonImportBase);
	    importPanel.addMember(buttonImportAlbum);
	    importPanel.addMember(buttonImportComputer);
	    
	    importPanel.setHeight(iconsize);
	    
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
