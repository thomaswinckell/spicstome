package com.spicstome.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.UserDTO;

public interface ReceivedMailsView extends IsWidget{

	void setUser(UserDTO owner);
	void setReceivedMails(List<MailDTO> mails);
	void init();
	
	public interface Presenter 
	{
		
	}
}
