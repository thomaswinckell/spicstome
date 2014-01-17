package com.spicstome.client.ui.form;

import java.util.HashSet;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FileItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;

public class ArticleFormWindow extends Window{
	
	VLayout verticalLayout = new VLayout();
	
	IconButton buttonValidate = new IconButton("");

	TextItem nameDetail = new TextItem("name");
	Img imgDetail = new Img();
	FileItem fileItem = new FileItem();
	
	public ArticleDTO article;
	
	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	CheckboxItem checkBoxFavorite = new CheckboxItem("favorite","Favoris");
	
	public DynamicForm form = new DynamicForm();
	
	public enum Mode{NEW, EDIT}
	
	public ArticleFormWindow(Mode mode,final ArticleDTO articleDTO,FolderDTO parent)
	{
		setWidth(500);
		setHeight(500);

		
		
		if(mode==Mode.NEW)
		{
			setTitle("Création d'un nouvel article");
			
			this.article = new ArticleDTO((long)-1,
					"nom de l'article",
					0,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					new HashSet<LogDTO>());
		}
		else if(mode==Mode.EDIT)
		{
			setTitle("Edition d'un article");
			
			this.article=articleDTO;
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
		
		radioGroupType.setValueMap("Noun", "Verb");
		radioGroupType.setTitle("Word type");

		radioGroupGroup.setValueMap("1st group", "2nd group","3rd group","other");
		radioGroupGroup.setTitle("Group");
		
		form.setFields(nameDetail,fileItem,checkBoxFavorite,radioGroupType,radioGroupGroup);
		
		buttonValidate.setIconSize(42);
		buttonValidate.setIcon("check.png");
		buttonValidate.setLayoutAlign(Alignment.CENTER);
		buttonValidate.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				article.setName(nameDetail.getValueAsString());
				destroy();
				
			}
		});
		
		nameDetail.setValue(article.getName());
		
		verticalLayout.addMember(imgDetail);
		verticalLayout.addMember(form);
		verticalLayout.addMember(buttonValidate);
		
		addItem(verticalLayout);
		
		
	
			
	}
	
}
