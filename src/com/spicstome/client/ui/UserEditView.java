package com.spicstome.client.ui;


import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.UserDTO;

public interface UserEditView extends IsWidget {
	
	public void setUserDTO(UserDTO userDTO);
}
