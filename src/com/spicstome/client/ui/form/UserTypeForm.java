package com.spicstome.client.ui.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;

public class UserTypeForm extends VLayout {
	
	private FormUtils.Mode mode;
	
	private SelectItem userTypeSelectItem;
	private UserDTO userDTO;
	private LinkedStudentsForm linkedStudentsForm = null;
	private DynamicForm form;

	public UserTypeForm () {
		
		super();
		
		form = new DynamicForm();
        
        // Selecting the user type        
        userTypeSelectItem = new SelectItem("user_type", "Type d'utilisateur");
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();  
        valueMap.put("student", "Etudiant");
        valueMap.put("teacher", "Professeur");
        valueMap.put("referent", "R&eacute;f&eacute;rent");
        userTypeSelectItem.setValueMap(valueMap);
        userTypeSelectItem.setRequired(true);
        
        userTypeSelectItem.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (event.getValue().equals("teacher") || event.getValue().equals("referent")) {
					setTeacherOrReferentMode();
				} else {
					setStudentMode();
				}
			}
        });
        
        userTypeSelectItem.setTop(10);
        
        form.setFields(userTypeSelectItem);
        
        addMember(form);
        
        linkedStudentsForm = new LinkedStudentsForm();
		
		addMember(linkedStudentsForm);
	}
	
	public void setUserDTO(UserDTO userDTO, FormUtils.Mode mode) {
		
		this.userDTO = userDTO;
		this.mode = mode;
		
		if (mode == FormUtils.Mode.NEW) {
	        userTypeSelectItem.setValue("student");
	        userTypeSelectItem.enable();
	        setStudentMode();
		} else {
			userTypeSelectItem.disable();
	    	if (userDTO instanceof StudentDTO) {
	    		userTypeSelectItem.setValue("student");
	    		setStudentMode();
	    	} else {
	    		if (userDTO instanceof TeacherDTO)
		    		userTypeSelectItem.setValue("teacher");
		    	else
		    		userTypeSelectItem.setValue("referent");
		    	
	    		setTeacherOrReferentMode();
	    	}
	    }
	}
	
	public void setTeacherOrReferentMode() {
		
		ArrayList<StudentDTO> students = new ArrayList<StudentDTO>();
        
		if (mode == FormUtils.Mode.EDIT) {
	        if (userDTO instanceof TeacherDTO)
	        	students = ((TeacherDTO) userDTO).getStudents();
	        else /* we assume userDTO is an instance of ReferentDTO if it's not an instance of TeacherDTO */
	        	students = ((ReferentDTO) userDTO).getStudents();
		}
		
        linkedStudentsForm.setLinkedStudents(students, mode);
        
        linkedStudentsForm.show();
	}
	
	public void setStudentMode() {
		linkedStudentsForm.hide();
	}
	
	public UserDTO getUserDTO() {
		if (userTypeSelectItem.getValueAsString().equals("student")) {
			if (mode == FormUtils.Mode.NEW) {
				return new StudentDTO((long) -1, null, null, null, null, null, null, new ImageDTO((long) -1, 
						null), null, new HashSet<LogDTO>());
			} else {
				
				return userDTO;
				
				/*return new StudentDTO((long) -1, userDTO.getSubscriptionDate(), null, 
						userDTO.getName(), userDTO.getEmail(), userDTO.getLogin(), 
						userDTO.getPassword(), userDTO.getImage(), ((StudentDTO) userDTO).getAlbum(), 
						((StudentDTO) userDTO).getLogs(), ((StudentDTO) userDTO).getReferents(), 
						((StudentDTO) userDTO).getTeachers());*/
			}
		} else if (userTypeSelectItem.getValueAsString().equals("teacher")){
			if (mode == FormUtils.Mode.NEW) {
				return new TeacherDTO((long) -1, null, null, null, null, null, null, new ImageDTO((long) -1, 
						null), linkedStudentsForm.getLinkedStudents());
			} else {
				
				((TeacherDTO) userDTO).setStudents(linkedStudentsForm.getLinkedStudents());
				
				return userDTO;
				
				/*return new TeacherDTO((long) -1, userDTO.getSubscriptionDate(), userDTO.getFirstName(), 
						userDTO.getName(), userDTO.getEmail(), userDTO.getLogin(), 
						userDTO.getPassword(), userDTO.getImage(), linkedStudentsForm.getLinkedStudents());*/
			}
		} else {
			if (mode == FormUtils.Mode.NEW) {
				return new ReferentDTO((long) -1, null, null, null, null, null, null, new ImageDTO((long) -1, 
						null), linkedStudentsForm.getLinkedStudents());
			} else {
				
				((ReferentDTO) userDTO).setStudents(linkedStudentsForm.getLinkedStudents());
				
				return userDTO;
				
				/*return new ReferentDTO((long) -1, userDTO.getSubscriptionDate(), userDTO.getFirstName(), 
						userDTO.getName(), userDTO.getEmail(), userDTO.getLogin(), 
						userDTO.getPassword(), userDTO.getImage(), linkedStudentsForm.getLinkedStudents());*/
			}
		}
	}
}
