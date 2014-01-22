package com.spicstome.client.ui.form;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UsersManagementViewImpl;

public class StudentsEditForm extends HLayout {
	
	private DynamicForm form;
	private UserComboBoxItem selectItem;
	private IconButton editButton, deleteButton;
	
	public StudentsEditForm() {
		
		super();
		
		setWidth("500px");
		setMargin(20);
		setLayoutAlign(Alignment.CENTER);
		
		form = new DynamicForm();
		form.setMargin(10);
		
		selectItem = new UserComboBoxItem("students", "<b>Etudiants</b>");
		
		form.setFields(selectItem);
		
		editButton = new IconButton("");
		editButton.setIcon("edit.png");
		editButton.setIconSize(40);
		editButton.setTop("-30px");
		deleteButton = new IconButton("");
		deleteButton.setIcon("delete.png");
		deleteButton.setIconSize(40);
		
		addMember(form);
		addMember(editButton);
		addMember(deleteButton);
	}
	
	public void setStudents (List<UserDTO> mStudents, final UsersManagementViewImpl view) {
		
		selectItem.setUsers(mStudents);
		
		if (selectItem.isEmpty()) {
			editButton.disable();
			deleteButton.disable();
		} else {
			editButton.enable();
			deleteButton.enable();
		}
		
		editButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				String val = selectItem.getValueAsString();
				
				if ((val == null) || val.isEmpty()){
					SC.warn("Veuillez s&eacute;lectionner un r&eacute;f&eacute;rent.");
				} else {				
					Long idStudent = Long.parseLong(val);
					
					view.goTo(new AddUserPlace(idStudent.toString()));
				}
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

							final Long idStudent = Long.parseLong(selectItem.getValueAsString());

							SpicsToMeServices.Util.getInstance().deleteUser(idStudent, new AsyncCallback<Boolean> () {

								@Override
								public void onFailure(Throwable caught) {
									System.out.println(caught);
								}

								@Override
								public void onSuccess(Boolean result) {
									selectItem.removeUser(idStudent);
								}					
							});
						}
					}
				});
			}				
		});
	}
}
