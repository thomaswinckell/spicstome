package com.spicstome.client.ui.form;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FileItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class ArticleFormWindow extends Window{
	
	VLayout verticalLayout = new VLayout();

	TextItem nameDetail = new TextItem("name");
	Img imgDetail = new Img();
	FileItem fileItem = new FileItem();
	
	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	CheckboxItem checkBoxFavorite = new CheckboxItem("favorite","Favoris");
	
	DynamicForm form = new DynamicForm();
	
	public enum Mode{NEW, EDIT}
	
	public ArticleFormWindow(Mode mode)
	{
		setWidth(500);
		setHeight(500);

		if(mode==Mode.NEW)
			setTitle("Création d'un nouvel article");
		else if(mode==Mode.EDIT)
			setTitle("Edition d'un article");
		
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
		
		radioGroupType.setValueMap("Noun", "Verb");
		radioGroupType.setTitle("Word type");

		radioGroupGroup.setValueMap("1st group", "2nd group","3rd group","other");
		radioGroupGroup.setTitle("Group");
		
		form.setFields(nameDetail,fileItem,checkBoxFavorite,radioGroupType,radioGroupGroup);
		
		verticalLayout.addMember(imgDetail);
		verticalLayout.addMember(form);
		
		addItem(verticalLayout);
	}
}
