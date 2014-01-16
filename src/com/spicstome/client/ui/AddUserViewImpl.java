package com.spicstome.client.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.form.FormUtils;
import com.spicstome.client.ui.form.UserForm;
import com.spicstome.client.ui.widget.Crumb;

public class AddUserViewImpl extends UserViewImpl  implements AddUserView {

	UserForm userForm;
	
	public AddUserViewImpl () {
		
		super();
		
		/*addCrumb(new Crumb("Ajout d'un utilisateur"){
			@Override
			public void onClickCrumb() {			
				goTo(new AddUserPlace(null));
			}
		});*/
		
		userForm = new UserForm(mainPanel, new AsyncCallback<Long> () {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			@Override
			public void onSuccess(Long idUser) {
				goTo(new UsersManagementPlace());
			}
		});
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
