package com.spicstome.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;

public interface NewMailView extends IsWidget{

	void setStudent(StudentDTO owner);
	void setIsStudent(boolean b);
	void setRecipients(List<UserDTO> recipients);
	void init();
	
	public interface Presenter 
	{
		void saveLog(LogDTO log);
	}
}
