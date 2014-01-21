package com.spicstome.client.ui.form;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.HoverCustomizer;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class TeachersEditForm extends HLayout {
	
	private DynamicForm form;
	private ComboBoxItem selectItem;
	private IconButton editButton, deleteButton;
	private LinkedHashMap<String, String> valueMap;
	private List<TeacherDTO> teachers;
	
	public TeachersEditForm() {
		
		super();
		
		setWidth("500px");
		setMargin(20);
		setLayoutAlign(Alignment.CENTER);
		
		form = new DynamicForm();
		form.setMargin(10);
		
		selectItem = new ComboBoxItem("teachers", "<b>Enseignants</b>");
		selectItem.setWidth(320);
		
		ListGrid pickListProperties = new ListGrid();  
        pickListProperties.setCellHeight(50);  
        pickListProperties.setCanHover(true);  
        pickListProperties.setShowHover(true);  
        pickListProperties.setCellFormatter(new CellFormatter() {  
            @Override  
            public String format(Object value, ListGridRecord record, int rowNum, int colNum) {
            	TeacherDTO teacher = teachers.get(rowNum);  
                String styleStr = "white-space:nowrap;overflow:hidden;";  
                String retStr = "<table>" +  
                        "<tr>" +
                        "<td align='right'><span style='" + styleStr + "width:50px;float:left;'>" +
                    		"<img src='images/"+ FormUtils.UPLOAD_IMAGE_PATH + teacher.getImage().getFilename() + "' height='40px' width='40px'>" +
                    	"<span></td>" +  
                		"<td ><span style='" + styleStr + "width:170px;float:right;font-weight:bold;font-family:arial;font-size:18px;'>" + teacher.getFirstName() + " " + teacher.getName() + "<span></td>" +  
                        "</tr></table>";  
                return retStr;  
  
            }  
        });
        
        pickListProperties.setHoverCustomizer(new HoverCustomizer() {  
            @Override  
            public String hoverHTML(Object value, ListGridRecord record, int rowNum, int colNum) { 
            	TeacherDTO teacher = teachers.get(rowNum);           	
            	String styleStr = "white-space:nowrap;overflow:hidden;";  
                String retStr = "<table>" +  
                        "<tr>" +
                		"<td align='center'><span style='" + styleStr + "width:200px;font-weight:bold;font-family:arial;font-size:25px;'>" + teacher.getFirstName() + " " + teacher.getName() + "<span></td>" +
                        "</tr><tr>" +
                		"<td align='center'><span style='" + styleStr + "width:200px;'>" +
                    		"<img src='images/"+ FormUtils.UPLOAD_IMAGE_PATH + teacher.getImage().getFilename() + "' max-width='200px'>" +
                    	"<span></td>" +   
                        "</tr></table>";  
                return retStr;  
            }  
        });
  
        selectItem.setPickListProperties(pickListProperties);
        
        selectItem.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				event.cancel();
			}        	
        });
		
		form.setFields(selectItem);
		
		editButton = new IconButton("");
		editButton.setIcon("edit.png");
		editButton.setIconSize(40);
		editButton.setTop("-30px");
		deleteButton = new IconButton("");
		deleteButton.setIcon("delete.png");
		deleteButton.setIconSize(40);
		
		valueMap = new LinkedHashMap<String, String>();
		
		addMember(form);
		addMember(editButton);
		addMember(deleteButton);
	}
	
	private void updateSelectItem() {
		
		selectItem.clearValue();
		valueMap.clear();
		
		String firstTeacherId = null;
		for(TeacherDTO teacher : teachers) {
			
			if (firstTeacherId == null)
				firstTeacherId = teacher.getId().toString();
				
			valueMap.put(teacher.getId().toString(), teacher.getFirstName()+" "+teacher.getName());
		}
		
		if (firstTeacherId != null) {
			selectItem.setDefaultValue(firstTeacherId);
			selectItem.enable();
			editButton.enable();
			deleteButton.enable();
		} else {
			selectItem.setDefaultValue("Aucun");
			selectItem.disable();
			editButton.disable();
			deleteButton.disable();
		}
		
		selectItem.setValueMap(valueMap);
	}
	
	public void setTeachers (List<TeacherDTO> mTeachers, final UsersManagementViewImpl view) {
		
		teachers = mTeachers;
		
		updateSelectItem();
		
		editButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				Long idTeacher = Long.parseLong(selectItem.getValueAsString());
				
				view.goTo(new AddUserPlace(idTeacher.toString()));
			}				
		});
		
		deleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				SC.ask("Confirmation de suppression", "&Ecirc;tes-vous s&ucirc;r(e) de vouloir supprimer cet utilisateur ?", 
						new BooleanCallback() {
					@Override
					public void execute(Boolean confirm) {
						if (confirm) {

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
					}
				});
			}				
		});
	}
}
