package com.spicstome.client.ui.widget;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FileItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class ImageDescriptionFormWindow extends Window{

	VLayout verticalLayout = new VLayout();
	DynamicForm baseForm = new DynamicForm();
	TextItem nameDetail = new TextItem("name");
	Img imgDetail = new Img();
	FileItem fileItem = new FileItem();
	
	public enum Mode{NEW, EDIT}
	
	public ImageDescriptionFormWindow(Mode mode) 
	{
		super();

		setWidth(500);
		setHeight(500);

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
		
		baseForm.setFields(nameDetail,fileItem);

		verticalLayout.addMember(imgDetail);
		verticalLayout.addMember(baseForm);
		
		addItem(verticalLayout);
				
		
	}
}
