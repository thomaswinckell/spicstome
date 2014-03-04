package com.spicstome.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.MailListDTO;

public interface ReceivedMailsView extends IsWidget{

	void setReceivedMails(MailListDTO mails);
	
	public interface Presenter 
	{
		
	}
}
