package com.spicstome.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.MailDTO;

public interface ReceivedMailsView extends IsWidget{

	void setReceivedMails(List<MailDTO> mails);
	
	public interface Presenter 
	{
		
	}
}
