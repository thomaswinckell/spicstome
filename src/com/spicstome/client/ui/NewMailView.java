package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.ui.UserViewImpl.userType;

public interface NewMailView extends IsWidget{

	void setStudent(StudentDTO owner);
	void setRecipients(List<UserDTO> recipients);
	void init(userType type);
	void initComponents();
	
	public interface Presenter 
	{
		void saveLog(LogDTO log);
		void sendMail(String emailReceiver, ArrayList<WordDTO> words, ArrayList<String> correctedWords);
	}
}
