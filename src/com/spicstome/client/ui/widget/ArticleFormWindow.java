package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;

public class ArticleFormWindow extends PecsFormWindow{

	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	CheckboxItem checkBoxFavorite = new CheckboxItem("favorite","Favoris");
	
	DynamicForm articleForm = new DynamicForm();
	
	public ArticleFormWindow()
	{
		super();

	
		setTitle("Article edit");
		
		
		
		radioGroupType.setValueMap("Noun", "Verb");
		radioGroupType.setTitle("Word type");

		radioGroupGroup.setValueMap("1st group", "2nd group","3rd group","other");
		radioGroupGroup.setTitle("Group");
		
		articleForm.setFields(checkBoxFavorite,radioGroupType,radioGroupGroup);
		
		verticalLayout.addMember(articleForm);
	}
}
