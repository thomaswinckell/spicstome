package com.spicstome.client.ui.panel;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class SendingPanel extends HLayout{

	IconButton sendingButton = new IconButton("");
	protected IconButton speakButton = new IconButton("");
	
	int iconsize = 60;
	
	public SendingPanel()
	{
		setWidth100();
		setHeight(80);
		
		setStyleName("bloc");
		
		speakButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onSpeak();
			}			
		});
		
		speakButton.setIcon("sound.png");
		speakButton.setIconSize(iconsize);

		sendingButton.setIcon("mail.png");
		sendingButton.setIconSize(iconsize);
		sendingButton.setPrompt("Envoyer le mail");
		
		sendingButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
	
				onSend();
			}
		});

		addMember(sendingButton);
		addMember(speakButton);

	}
	
	public void init()
	{
		speakButton.setVisible(false);
	}
	
	public void setSpeakVisible(boolean b)
	{
		speakButton.setVisible(b);
	}
	
	public abstract void onSend();
	public abstract void onSpeak();

}
