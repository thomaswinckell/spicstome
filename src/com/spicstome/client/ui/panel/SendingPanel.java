package com.spicstome.client.ui.panel;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class SendingPanel extends HLayout{

	IconButton img = new IconButton("");
	int iconsize = 60;
	
	public SendingPanel()
	{
		setWidth100();
		setHeight(80);
		
		setStyleName("bloc");

		img.setIcon("mail.png");
		img.setIconSize(iconsize);
		img.setPrompt("Envoyer le mail");
		
		img.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
	
				onSend();
			}
		});

		addMember(img);

	}
	
	public abstract void onSend();

}
