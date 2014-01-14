package com.spicstome.client.ui.form;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FileItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class FolderFormWindow extends Window{

	VLayout verticalLayout = new VLayout();
	DynamicForm form = new DynamicForm();
	
	IconButton buttonValidate = new IconButton("");
	
	TextItem nameDetail = new TextItem("name");
	Img imgDetail = new Img();
	FileItem fileItem = new FileItem();
	
	public enum Mode{NEW, EDIT}
	
	public FolderFormWindow(Mode mode) 
	{
		super();

		setWidth(500);
		setHeight(300);

		if(mode==Mode.NEW)
			setTitle("Création d'un nouveau dossier");
		else if(mode==Mode.EDIT)
			setTitle("Edition d'un dossier");
		
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();

		setDismissOnOutsideClick(true);
		

		imgDetail.setSize(150);
		imgDetail.setLayoutAlign(Alignment.RIGHT);

		nameDetail.setHeight(20);    
		nameDetail.setTitle("Nom");
		
		fileItem.setTitle("Image");
		
		form.setFields(nameDetail,fileItem);
		
		buttonValidate.setIconSize(42);
		buttonValidate.setIcon("check.png");
		buttonValidate.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				destroy();
				
			}
		});
		

		verticalLayout.addMember(imgDetail);
		verticalLayout.addMember(form);
		verticalLayout.addMember(buttonValidate);
		
		addItem(verticalLayout);
				
		
	}
}
