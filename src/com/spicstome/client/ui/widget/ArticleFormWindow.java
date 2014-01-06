package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;

public class ArticleFormWindow extends ImageDescriptionFormWindow{


	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	CheckboxItem checkBoxFavorite = new CheckboxItem("favorite","Favoris");
	
	DynamicForm articleForm = new DynamicForm();
	
	public ArticleFormWindow(Mode mode)
	{
		super(mode);
		
		if(mode==Mode.NEW)
			setTitle("Création d'un nouvel article");
		else if(mode==Mode.EDIT)
			setTitle("Edition d'un article");


		radioGroupType.setValueMap("Noun", "Verb");
		radioGroupType.setTitle("Word type");

		radioGroupGroup.setValueMap("1st group", "2nd group","3rd group","other");
		radioGroupGroup.setTitle("Group");
		
		articleForm.setFields(checkBoxFavorite,radioGroupType,radioGroupGroup);
		
		verticalLayout.addMember(articleForm);
	}
}
