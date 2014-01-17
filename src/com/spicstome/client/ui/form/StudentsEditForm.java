package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class StudentsEditForm extends DynamicForm {
	
	SelectItem selectItem;
	ButtonItem buttonItem;
	LinkedHashMap<String, String> valueMap, imagesValueMap;
	
	public StudentsEditForm() {
		selectItem = new SelectItem("students", "Etudiants");
		buttonItem = new ButtonItem("btn_edit_student", "Editer");
		
		valueMap = new LinkedHashMap<String, String>();
		imagesValueMap = new LinkedHashMap<String, String>();
		
		selectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
		
		setFields(selectItem, buttonItem);
	}
	
	public void setStudents (List<StudentDTO> students, final UsersManagementViewImpl view) {
		String firstStudentId = null;
		for(StudentDTO student : students) {
			
			if (firstStudentId == null)
				firstStudentId = student.getId().toString();
				
			valueMap.put(student.getId().toString(), student.getFirstName()+" "+student.getName());
			imagesValueMap.put(student.getId().toString(), student.getImage().getFilename());
		}
		
		if (firstStudentId != null) {
			selectItem.setDefaultValue(firstStudentId);
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
				
				Long idStudent = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idStudent.toString()));
			}				
		});
	}
}
