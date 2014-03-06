package com.spicstome.client.ui.panel;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class SendingPanel extends HLayout{

	IconButton sendingButton = new IconButton("");
	protected IconButton speakButton = new IconButton("");
	IconButton expand = new IconButton("");
	
	int iconsize = 100;
	
	public SendingPanel(final MailPanel mailView)
	{
		
		setWidth100();

		
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
		
		expand.setIcon("expand.png");
		expand.setIconSize(iconsize);
		expand.setPrompt("Plein écran");
		expand.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				mailView.expand(true);
			}
		});

		addMember(sendingButton);
		addMember(speakButton);
		addMember(expand);

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
