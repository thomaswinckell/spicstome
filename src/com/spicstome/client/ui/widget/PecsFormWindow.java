package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.shared.Album;

public class PecsFormWindow extends Window{

	VLayout verticalLayout = new VLayout();
	DynamicForm baseForm = new DynamicForm();
	TextItem nameDetail = new TextItem("name","Nom");
	Img imgDetail = new Img();
	
	IconButton buttonImportBase = new IconButton("Import from general base");
	IconButton buttonImportAlbum = new IconButton("Import from other album");
	IconButton buttonImportComputer = new IconButton("Import from PC");
	
	HLayout importPanel = new HLayout();

	public PecsFormWindow() 
	{
		super();

		setWidth(500);
		setHeight(500);

		setTitle("Edit Folder");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();

		setDismissOnOutsideClick(true);
		
		 
		   
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

		imgDetail.setSize(150);
		imgDetail.setLayoutAlign(Alignment.RIGHT);

		nameDetail.setHeight(20);    
		baseForm.setFields(nameDetail);

		verticalLayout.addMember(importPanel);
		verticalLayout.addMember(imgDetail);
		verticalLayout.addMember(baseForm);
		
		addItem(verticalLayout);
				
		buttonImportBase.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ArticleFolderPickerWindow win = new ArticleFolderPickerWindow(new ArrayList<Album>(), ArticleFolderPickerWindow.Mode.FOLDER){
					@Override
					public void onDestroy()
					{
						// todo traitement 
						//System.out.println(win.book.selectedImage.toString());
						//articlesGrid.addItem(book.selectedImage);
					}
				};			 
				win.show();
			}
		});

		buttonImportAlbum.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) 
			{
				ArticleFolderPickerWindow win = new ArticleFolderPickerWindow(new ArrayList<Album>(),ArticleFolderPickerWindow.Mode.FOLDER){
					@Override
					public void onDestroy()
					{
						// todo traitement 
						//System.out.println(win.book.selectedImage.toString());
						//articlesGrid.addItem(book.selectedImage);
					}
				};			 
				win.show();
			}
		});
	}
}
