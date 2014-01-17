package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class TeachersEditForm extends DynamicForm {
	
	SelectItem selectItem;
	ButtonItem buttonItem;
	LinkedHashMap<String, String> valueMap, imagesValueMap;
	
	public TeachersEditForm() {
		selectItem = new SelectItem("teachers", "Enseignants");
		buttonItem = new ButtonItem("btn_edit_teachers", "Editer");
		
		valueMap = new LinkedHashMap<String, String>();
		imagesValueMap = new LinkedHashMap<String, String>();
		
		selectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
		
		setFields(selectItem, buttonItem);
	}
	
	public void setTeachers (List<TeacherDTO> teachers, final UsersManagementViewImpl view) {
		String firstTeacherId = null;
		for(TeacherDTO teacher : teachers) {
			
			if (firstTeacherId == null)
				firstTeacherId = teacher.getId().toString();
				
			valueMap.put(teacher.getId().toString(), teacher.getFirstName()+" "+teacher.getName());
			imagesValueMap.put(teacher.getId().toString(), teacher.getImage().getFilename());
		}
		
		if (firstTeacherId != null) {
			selectItem.setDefaultValue(firstTeacherId);
			buttonItem.enable();
		}
		else {
			selectItem.setDefaultValue("Aucun");
			selectItem.disable();
			buttonItem.disable();
		}
		
		selectItem.setValueMap(valueMap);
		selectItem.setValueIcons(imagesValueMap);
		
		buttonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				
				Long idTeacher = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idTeacher.toString()));
			}				
		});
	}
}
