package com.spicstome.client.ui.form;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.services.SpicsToMeServices;

public class LinkedStudentsForm {
	
	private SelectItem linkedStudentsSelectItem, nonLinkedStudentsSelectItem;
	private ButtonItem removeStudentButtonItem, addStudentButtonItem;
	
	private LinkedHashMap<String, String> linkedStudentsValueMap, nonLinkedStudentsValueMap;
	private LinkedHashMap<String, String> linkedStudentsImagesValueMap, nonLinkedStudentsImagesValueMap;
	
	public LinkedStudentsForm() {

        linkedStudentsValueMap = new LinkedHashMap<String, String>();
        linkedStudentsImagesValueMap = new LinkedHashMap<String, String>();
        
        removeStudentButtonItem = new ButtonItem("btn_unlink_student", "D&eacute;lier");
		
		linkedStudentsSelectItem = new SelectItem("linked_students", "Etudiants li&eacute;s");
        linkedStudentsSelectItem.setValueMap(linkedStudentsValueMap);
        linkedStudentsSelectItem.setValueIcons(linkedStudentsImagesValueMap);
	        
        linkedStudentsSelectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
        
        nonLinkedStudentsSelectItem = new SelectItem("non_linked_students", "Etudiants non li&eacute;s");
        nonLinkedStudentsSelectItem.setImageURLPrefix(FormUtils.UPLOAD_IMAGE_PATH);
              
        removeStudentButtonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
            	
            	String idStudent = linkedStudentsSelectItem.getValueAsString();
            	
            	// saving and removing the current student from the students list
            	String studentName = linkedStudentsValueMap.get(idStudent);
            	String studentImageFilename = linkedStudentsImagesValueMap.get(idStudent);	            	
            	linkedStudentsValueMap.remove(idStudent);
            	linkedStudentsImagesValueMap.remove(idStudent);
            	
            	// add current student to the add list
            	nonLinkedStudentsValueMap.put(idStudent, studentName);
            	nonLinkedStudentsImagesValueMap.put(idStudent, studentImageFilename);
            	
            	forceDataUpdate();
            	
            	nonLinkedStudentsSelectItem.enable();
            	addStudentButtonItem.enable();
            	nonLinkedStudentsSelectItem.setDefaultValue(idStudent);
            	
            	if (linkedStudentsValueMap.keySet().size() > 0) {		            	
            		linkedStudentsSelectItem.setDefaultValue(linkedStudentsValueMap.keySet().iterator().next());
            	} else {
            		linkedStudentsSelectItem.setDefaultValue("Aucun");
            		linkedStudentsSelectItem.disable();
	            	removeStudentButtonItem.disable();
            	}
            }
        });
        
        addStudentButtonItem = new ButtonItem("btn_link_student", "Lier");
        
        nonLinkedStudentsValueMap = new LinkedHashMap<String, String>();
		nonLinkedStudentsImagesValueMap = new LinkedHashMap<String, String>();
		
		addStudentButtonItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
            	String idStudent = nonLinkedStudentsSelectItem.getValueAsString();
            	
            	// saving and removing the current student from the add list
            	String studentName = nonLinkedStudentsValueMap.get(idStudent);
            	String studentImageFilename = nonLinkedStudentsImagesValueMap.get(idStudent);	            	
            	nonLinkedStudentsValueMap.remove(idStudent);
            	nonLinkedStudentsImagesValueMap.remove(idStudent);
            	
            	// add current student to the students list
            	linkedStudentsValueMap.put(idStudent, studentName);
            	linkedStudentsImagesValueMap.put(idStudent, studentImageFilename);
            	
            	forceDataUpdate();
            	
            	linkedStudentsSelectItem.enable();
            	removeStudentButtonItem.enable();
            	linkedStudentsSelectItem.setDefaultValue(idStudent);
            	
            	if (nonLinkedStudentsValueMap.keySet().size() > 0) {		            	
            		nonLinkedStudentsSelectItem.setDefaultValue(nonLinkedStudentsValueMap.keySet().iterator().next());
            	} else {
            		nonLinkedStudentsSelectItem.setDefaultValue("Aucun");
            		nonLinkedStudentsSelectItem.disable();
	            	addStudentButtonItem.disable();
            	}
            }
        });
	}

	public SelectItem getLinkedStudentsSelectItem() {
		return linkedStudentsSelectItem;
	}

	public SelectItem getNonLinkedStudentsSelectItem() {
		return nonLinkedStudentsSelectItem;
	}

	public ButtonItem getRemoveStudentButtonItem() {
		return removeStudentButtonItem;
	}

	public ButtonItem getAddStudentButtonItem() {
		return addStudentButtonItem;
	}
	
	private void forceDataUpdate() {
    	linkedStudentsSelectItem.clearValue();
    	nonLinkedStudentsSelectItem.clearValue();
    	linkedStudentsSelectItem.setValueMap(linkedStudentsValueMap);
    	linkedStudentsSelectItem.setValueIcons(linkedStudentsImagesValueMap);
    	nonLinkedStudentsSelectItem.setValueMap(nonLinkedStudentsValueMap);
    	nonLinkedStudentsSelectItem.setValueIcons(nonLinkedStudentsImagesValueMap);
	}
	
	public void setLinkedStudents(ArrayList<StudentDTO> students, FormUtils.Mode mode) {
		
        linkedStudentsValueMap.clear();
        linkedStudentsImagesValueMap.clear();
        nonLinkedStudentsValueMap.clear();
        nonLinkedStudentsImagesValueMap.clear();
		
		String firstStudentId = null;
        
	    if (mode == FormUtils.Mode.EDIT) {
	        
	        for(StudentDTO student : students) {
				if (firstStudentId == null)
					firstStudentId = student.getId().toString();
					
				linkedStudentsValueMap.put(student.getId().toString(), student.getFirstName()+" "+student.getName());
				linkedStudentsImagesValueMap.put(student.getId().toString(), student.getImage().getFilename());
			}
	    }
	    
	    if (firstStudentId == null) {
	        linkedStudentsSelectItem.setDefaultValue("Aucun");
	        linkedStudentsSelectItem.disable();
	        removeStudentButtonItem.disable();
        } else {
        	linkedStudentsSelectItem.setDefaultValue(firstStudentId);
	        linkedStudentsSelectItem.enable();
	        removeStudentButtonItem.enable();
        }
	    
	    SpicsToMeServices.Util.getInstance().getAllStudents(new AsyncCallback<List<StudentDTO>> () {
        	
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			@Override
			public void onSuccess(List<StudentDTO> students) {
				
				String firstStudentId = null;
				for(StudentDTO student : students) {
					
					if (!linkedStudentsValueMap.containsKey(student.getId().toString())) {					
						if (firstStudentId == null)
							firstStudentId = student.getId().toString();
							
						nonLinkedStudentsValueMap.put(student.getId().toString(), student.getFirstName()+" "+student.getName());
						nonLinkedStudentsImagesValueMap.put(student.getId().toString(), student.getImage().getFilename());
					}
				}
				
				nonLinkedStudentsSelectItem.setValueMap(nonLinkedStudentsValueMap);
				nonLinkedStudentsSelectItem.setValueIcons(nonLinkedStudentsImagesValueMap);
				
				forceDataUpdate();
				
				if (firstStudentId != null) {
					nonLinkedStudentsSelectItem.setDefaultValue(firstStudentId);
					addStudentButtonItem.enable();
				}
				else {
					nonLinkedStudentsSelectItem.setDefaultValue("Aucun");
					nonLinkedStudentsSelectItem.disable();
					addStudentButtonItem.disable();
				}
			}	    				
		});
	}
	
	public ArrayList<StudentDTO> getLinkedStudents() {
		ArrayList<StudentDTO> students = new ArrayList<StudentDTO>();
		
		for (Map.Entry<String, String> entry : linkedStudentsValueMap.entrySet()) {
			
		    students.add(new StudentDTO(Long.parseLong(entry.getKey())));
		}
		
		return students;
	}
}
