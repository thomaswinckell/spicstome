package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.MailListDTO;

public interface ReceivedMailsView extends IsWidget{

	void setReceivedMails(MailListDTO mails);
	
	public interface Presenter 
	{
		
	}
}
