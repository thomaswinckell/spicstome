package com.spicstome.client.ui.form;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.services.SpicsToMeServices;

public class LinkedStudentsForm extends VLayout {
	
	private UserComboBoxItem linkedStudentsSelectItem, nonLinkedStudentsSelectItem;
	private IButton removeStudentButtonItem, addStudentButtonItem;
	private DynamicForm linkedStudentsForm, nonLinkedStudentsForm;
	
	public LinkedStudentsForm() {
		
		super();
		
		linkedStudentsForm = new DynamicForm();
		nonLinkedStudentsForm = new DynamicForm();
        
        removeStudentButtonItem = new IButton("D&eacute;lier");
		
		linkedStudentsSelectItem = new UserComboBoxItem("linked_students", "Etudiants li&eacute;s");
        
        nonLinkedStudentsSelectItem = new UserComboBoxItem("non_linked_students", "Etudiants non li&eacute;s");
              
        removeStudentButtonItem.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	
            	String val = linkedStudentsSelectItem.getValueAsString();
            	
            	if ((val == null) || val.isEmpty()){
					SC.warn("Veuillez s&eacute;lectionner un &eacute;tudiant.");
				} else {				
					// saving and removing the current student from the students list
	            	UserDTO userDTO = linkedStudentsSelectItem.removeUser(Long.parseLong(val));
	            	
	            	// add current student to the add list
	            	nonLinkedStudentsSelectItem.addUser(userDTO);
	            	addStudentButtonItem.enable();
	            	
	            	if (linkedStudentsSelectItem.isEmpty()) {		            	
	            		removeStudentButtonItem.disable();
	            	} else {
		            	removeStudentButtonItem.enable();
	            	}
				}
            }
        });
        
        addStudentButtonItem = new IButton("Lier");
		
		addStudentButtonItem.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	
            	String val = nonLinkedStudentsSelectItem.getValueAsString();
            	
            	if ((val == null) || val.isEmpty()){
					SC.warn("Veuillez s&eacute;lectionner un &eacute;tudiant.");
				} else {	
	            	// saving and removing the current student from the students list
	            	UserDTO userDTO = nonLinkedStudentsSelectItem.removeUser(Long.parseLong(val));
	            	
	            	// add current student to the add list
	            	linkedStudentsSelectItem.addUser(userDTO);
	            	removeStudentButtonItem.enable();
	            	
	            	if (nonLinkedStudentsSelectItem.isEmpty()) {		            	
	            		addStudentButtonItem.disable();
	            	} else {
	            		addStudentButtonItem.enable();
	            	}
				}
            }
        });
		
		linkedStudentsForm.setFields(linkedStudentsSelectItem);
		nonLinkedStudentsForm.setFields(nonLinkedStudentsSelectItem);
		
		removeStudentButtonItem.setLayoutAlign(Alignment.CENTER);
		addStudentButtonItem.setLayoutAlign(VerticalAlignment.CENTER);
		
		HLayout hLayout = new HLayout();
		hLayout.addMember(linkedStudentsForm);
		hLayout.addMember(removeStudentButtonItem);
		
		HLayout hLayout2 = new HLayout();
		hLayout2.addMember(nonLinkedStudentsForm);
		hLayout2.addMember(addStudentButtonItem);
		
		addMember(hLayout);
		addMember(hLayout2);
	}
	
	public void setLinkedStudents(List<StudentDTO> students, FormUtils.Mode mode) {
		
		List<UserDTO> users = new ArrayList<UserDTO>();
		for (StudentDTO student : students) {
			users.add((UserDTO) student);
		}
		linkedStudentsSelectItem.setUsers(users);
		
		if (linkedStudentsSelectItem.isEmpty()) {		            	
    		removeStudentButtonItem.disable();
    	} else {
        	removeStudentButtonItem.enable();
    	}
	    
	    SpicsToMeServices.Util.getInstance().getAllStudents(new AsyncCallback<List<StudentDTO>> () {
        	
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			@Override
			public void onSuccess(List<StudentDTO> students) {
				List<UserDTO> users = new ArrayList<UserDTO>();
				
				boolean isLinked = false;
				
				for (StudentDTO student : students) {
					
					for (UserDTO user : linkedStudentsSelectItem.getUsers())
						if (student.getId().longValue() == user.getId().longValue())
							isLinked = true;
							
					if (!isLinked)
						users.add((UserDTO) student);
					
					isLinked = false;
				}
				nonLinkedStudentsSelectItem.setUsers(users);
				
				if (nonLinkedStudentsSelectItem.isEmpty()) {		            	
            		addStudentButtonItem.disable();
            	} else {
            		addStudentButtonItem.enable();
            	}
			}	    				
		});
	}
	
	public ArrayList<StudentDTO> getLinkedStudents() {
		ArrayList<StudentDTO> students = new ArrayList<StudentDTO>();
		for (UserDTO user : linkedStudentsSelectItem.getUsers()) {
			students.add((StudentDTO) user);
		}		
		
		return students;
	}
}
