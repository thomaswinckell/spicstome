package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class TeachersEditForm extends DynamicForm {
	
	private SelectItem selectItem;
	private ButtonItem editButtonItem, deleteButtonItem;
	private LinkedHashMap<String, String> valueMap, imagesValueMap;
	private List<TeacherDTO> teachers;
	
	public TeachersEditForm() {
		selectItem = new SelectItem("teachers", "Enseignants");
		editButtonItem = new ButtonItem("btn_edit_teachers", "Editer");
		deleteButtonItem = new ButtonItem("btn_delete_teachers", "Supprimer");
		
		valueMap = new LinkedHashMap<String, String>();
		imagesValueMap = new LinkedHashMap<String, String>();
		
		selectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
		
		setFields(selectItem, editButtonItem, deleteButtonItem);
	}
	
	private void updateSelectItem() {
		
		selectItem.clearValue();
		valueMap.clear();
		imagesValueMap.clear();
		
		String firstTeacherId = null;
		for(TeacherDTO teacher : teachers) {
			
			if (firstTeacherId == null)
				firstTeacherId = teacher.getId().toString();
				
			valueMap.put(teacher.getId().toString(), teacher.getFirstName()+" "+teacher.getName());
			imagesValueMap.put(teacher.getId().toString(), teacher.getImage().getFilename());
		}
		
		if (firstTeacherId != null) {
			selectItem.setDefaultValue(firstTeacherId);
			selectItem.enable();
			editButtonItem.enable();
			deleteButtonItem.enable();
		} else {
			selectItem.setDefaultValue("Aucun");
			selectItem.disable();
			editButtonItem.disable();
			deleteButtonItem.disable();
		}
		
		selectItem.setValueMap(valueMap);
		selectItem.setValueIcons(imagesValueMap);
	}
	
	public void setTeachers (List<TeacherDTO> mTeachers, final UsersManagementViewImpl view) {
		
		teachers = mTeachers;
		
		updateSelectItem();
		
		editButtonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			@Override
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				
				Long idTeacher = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idTeacher.toString()));
			}				
		});
		
		deleteButtonItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				final Long idTeacher = Long.parseLong(selectItem.getValueAsString());
				
				SpicsToMeServices.Util.getInstance().deleteUser(idTeacher, new AsyncCallback<Boolean> () {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught);
					}

					@Override
					public void onSuccess(Boolean result) {
						for(TeacherDTO teacher : teachers) {
							if (teacher.getId() == idTeacher) {
								teachers.remove(teacher);
								break;
							}
						}
						updateSelectItem();
					}					
				});
			}				
		});
	}
}
