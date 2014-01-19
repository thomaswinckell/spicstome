package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class StudentsEditForm extends DynamicForm {
	
	private SelectItem selectItem;
	private ButtonItem editButtonItem, deleteButtonItem;
	private LinkedHashMap<String, String> valueMap, imagesValueMap;
	private List<StudentDTO> students;
	
	public StudentsEditForm() {
		selectItem = new SelectItem("students", "Etudiants");
		editButtonItem = new ButtonItem("btn_edit_student", "Editer");
		deleteButtonItem = new ButtonItem("btn_delete_student", "Supprimer");
		
		valueMap = new LinkedHashMap<String, String>();
		imagesValueMap = new LinkedHashMap<String, String>();
		
		selectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
		
		setFields(selectItem, editButtonItem, deleteButtonItem);
	}
	
	private void updateSelectItem() {
		
		selectItem.clearValue();
		valueMap.clear();
		imagesValueMap.clear();
		
		String firstStudentId = null;
		for(StudentDTO student : students) {
			
			if (firstStudentId == null)
				firstStudentId = student.getId().toString();
				
			valueMap.put(student.getId().toString(), student.getFirstName()+" "+student.getName());
			imagesValueMap.put(student.getId().toString(), student.getImage().getFilename());
		}
		
		if (firstStudentId != null) {
			selectItem.setDefaultValue(firstStudentId);
			selectItem.enable();
			editButtonItem.enable();
			deleteButtonItem.enable();
		}
		else {
			selectItem.setDefaultValue("Aucun");
			selectItem.disable();
			editButtonItem.disable();
			deleteButtonItem.disable();
		}
		
		selectItem.setValueMap(valueMap);
		selectItem.setValueIcons(imagesValueMap);
	}
	
	public void setStudents (List<StudentDTO> mStudents, final UsersManagementViewImpl view) {
		
		students = mStudents;
		
		updateSelectItem();
		
		editButtonItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Long idStudent = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idStudent.toString()));
			}				
		});
		
		deleteButtonItem.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				SC.ask("Confirmation de suppression", "&Ecirc;tes-vous s&ucirc;r(e) de vouloir supprimer cet utilisateur ?", 
					new BooleanCallback() {
					@Override
					public void execute(Boolean confirm) {
						if (confirm) {

							final Long idStudent = Long.parseLong(selectItem.getValueAsString());

							SpicsToMeServices.Util.getInstance().deleteUser(idStudent, new AsyncCallback<Boolean> () {

								@Override
								public void onFailure(Throwable caught) {
									System.out.println(caught);
								}

								@Override
								public void onSuccess(Boolean result) {
									for(StudentDTO student : students) {
										if (student.getId() == idStudent) {
											students.remove(student);
											break;
										}
									}
									updateSelectItem();
								}					
							});
						}
					}
				});
			}				
		});
	}
}
