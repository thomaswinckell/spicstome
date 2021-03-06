package com.spicstome.client.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.form.FormUtils;
import com.spicstome.client.ui.form.UserForm;
import com.spicstome.client.ui.widget.Crumb;

public class UserEditViewImpl extends UserViewImpl  implements UserEditView {

	UserForm userForm;
	
	public UserEditViewImpl () {
		
		super();
		
		addCrumb(new Crumb("Gestion des utilisateurs") {
			@Override
			public void onClickCrumb() {			
				goTo(new UsersManagementPlace());
			}
		});
		
		addCrumb(new Crumb("Ajout/Edition d'utilisateur"){
			@Override
			public void onClickCrumb() {
			}
		});
		
		userForm = new UserForm(new AsyncCallback<Long> () {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			@Override
			public void onSuccess(Long idUser) {
				goTo(new UsersManagementPlace());
			}
		});
		
		userForm.setWidth(500);
		userForm.setLayoutAlign(Alignment.CENTER);
		mainPanel.addMember(userForm);
	}
	
	public void setUserDTO(UserDTO userDTO) {
		
		FormUtils.Mode mode;
		
		if (userDTO == null) {
			mode = FormUtils.Mode.NEW;
		} else {
			mode = FormUtils.Mode.EDIT;
		}
		
		userForm.setUserDTO(userDTO, mode);
	}
}
