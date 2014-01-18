package com.spicstome.client.ui.form;

import java.util.HashSet;

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
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.PecsDTO;

public class FolderFormWindow extends Window{

	VLayout verticalLayout = new VLayout();
	DynamicForm form = new DynamicForm();
	
	IconButton buttonValidate = new IconButton("");
	
	public FolderDTO folder;
	
	TextItem nameDetail = new TextItem("name");
	Img imgDetail = new Img();
	FileItem fileItem = new FileItem();
	
	public enum Mode{NEW, EDIT}
	
	public FolderFormWindow(Mode mode,FolderDTO folderDTO,FolderDTO parent) 
	{
		super();

		setWidth(500);
		setHeight(300);

		if(mode==Mode.NEW)
		{
			setTitle("Création d'un nouveau dossier");
			
			this.folder = new FolderDTO((long)-1,
					"Nouveau dossier",
					0,
					parent,
					new ImageDTO((long)-1,"default_folder.png"),
					new HashSet<PecsDTO>());
		}
			
		else if(mode==Mode.EDIT)
		{
			setTitle("Edition d'un dossier");		
			this.folder=folderDTO;
		}
		
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
		buttonValidate.setLayoutAlign(Alignment.CENTER);
		buttonValidate.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				folder.setName(nameDetail.getValueAsString());
				
				destroy();
				
			}
		});
		
		nameDetail.setValue(this.folder.getName());

		verticalLayout.addMember(imgDetail);
		verticalLayout.addMember(form);
		verticalLayout.addMember(buttonValidate);
		
		addItem(verticalLayout);
		
	
			
		
	}
}
