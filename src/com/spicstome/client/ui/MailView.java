package com.spicstome.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;

public interface MailView extends IsWidget{

	void setStudent(StudentDTO owner);
	void setRecipients(List<UserDTO> recipients);
}
