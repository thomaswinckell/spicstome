package com.spicstome.client.ui.form;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;

public class UserTypeForm extends DynamicForm {
	
	private FormUtils.Mode mode;
	
	private SelectItem userTypeSelectItem;
	private UserDTO userDTO;
	private LinkedStudentsForm linkedStudentsForm = null;

	public UserTypeForm () {
		
		super();
        
        // Selecting the user type        
        userTypeSelectItem = new SelectItem("user_type", "Type d'utilisateur");
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();  
        valueMap.put("student", "Etudiant");
        valueMap.put("teacher", "Professeur");
        valueMap.put("referent", "R&eacute;f&eacute;rent");
        userTypeSelectItem.setValueMap(valueMap);
        userTypeSelectItem.setRequired(true);
        
        //setFields(userTypeSelectItem);
        
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
        
        linkedStudentsForm = new LinkedStudentsForm();
		
		setFields(userTypeSelectItem, linkedStudentsForm.getLinkedStudentsSelectItem(), 
				linkedStudentsForm.getRemoveStudentButtonItem(), 
				linkedStudentsForm.getNonLinkedStudentsSelectItem(), 
				linkedStudentsForm.getAddStudentButtonItem());
		
		linkedStudentsForm.getLinkedStudentsSelectItem().setVisible(false);
		linkedStudentsForm.getRemoveStudentButtonItem().setVisible(false);  
		linkedStudentsForm.getNonLinkedStudentsSelectItem().setVisible(false); 
		linkedStudentsForm.getAddStudentButtonItem().setVisible(false);
	}
	
	public void setUserDTO(UserDTO userDTO, FormUtils.Mode mode) {
		
		this.userDTO = userDTO;
		this.mode = mode;
		
		if (mode == FormUtils.Mode.NEW) {
	        userTypeSelectItem.setDefaultValue("student");
	        userTypeSelectItem.enable();
	        setStudentMode();
		} else {
	    	if (userDTO instanceof StudentDTO) {
	    		userTypeSelectItem.setDefaultValue("student");
	    		setStudentMode();
	    	} else {
	    		if (userDTO instanceof TeacherDTO)
		    		userTypeSelectItem.setDefaultValue("teacher");
		    	else
		    		userTypeSelectItem.setDefaultValue("referent");
		    	
	    		setTeacherOrReferentMode();
	    	}
	    }
	}
	
	public void setTeacherOrReferentMode() {
		
		Set<StudentDTO> students = new HashSet<StudentDTO>();
        
		if (mode == FormUtils.Mode.EDIT) {
	        if (userDTO instanceof TeacherDTO)
	        	students = ((TeacherDTO) userDTO).getStudents();
	        else /* we assume userDTO is an instance of ReferentDTO if it's not an instance of TeacherDTO */
	        	students = ((ReferentDTO) userDTO).getStudents();
		}
		
        linkedStudentsForm.setLinkedStudents(students, mode);
        
        linkedStudentsForm.getLinkedStudentsSelectItem().show(); 
		linkedStudentsForm.getRemoveStudentButtonItem().show(); 
		linkedStudentsForm.getNonLinkedStudentsSelectItem().show();  
		linkedStudentsForm.getAddStudentButtonItem().show();
	}
	
	public void setStudentMode() {
		linkedStudentsForm.getLinkedStudentsSelectItem().hide();
		linkedStudentsForm.getRemoveStudentButtonItem().hide();
		linkedStudentsForm.getNonLinkedStudentsSelectItem().hide();
		linkedStudentsForm.getAddStudentButtonItem().hide();
	}
	
	public UserDTO getUserDTO() {
		if (userTypeSelectItem.getValueAsString().equals("student")) {
			if (mode == FormUtils.Mode.NEW) {
				return new StudentDTO((long) -1, null, null, null, null, null, null, null, null, 
						new HashSet<LogDTO>());
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
				return new TeacherDTO((long) -1, null, null, null, null, null, null, null, 
						linkedStudentsForm.getLinkedStudents());
			} else {
				
				((TeacherDTO) userDTO).setStudents(linkedStudentsForm.getLinkedStudents());
				
				return userDTO;
				
				/*return new TeacherDTO((long) -1, userDTO.getSubscriptionDate(), userDTO.getFirstName(), 
						userDTO.getName(), userDTO.getEmail(), userDTO.getLogin(), 
						userDTO.getPassword(), userDTO.getImage(), linkedStudentsForm.getLinkedStudents());*/
			}
		} else {
			if (mode == FormUtils.Mode.NEW) {
				return new ReferentDTO((long) -1, null, null, null, null, null, null, null, 
						linkedStudentsForm.getLinkedStudents());
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
