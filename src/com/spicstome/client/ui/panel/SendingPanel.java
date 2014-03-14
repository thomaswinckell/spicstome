package com.spicstome.client.ui.panel;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * 
 * This panel allow to click on a "sending" button, "listen" or "expand"
 *
 */
public abstract class SendingPanel extends HLayout{

	IconButton trashButton = new IconButton("");
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

		sendingButton.setIcon("sendmail.png");
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
		
		trashButton.setIcon("trash.png");
		trashButton.setIconSize(iconsize);
		trashButton.setPrompt("Annuler");
		trashButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				onTrash();
			}
		});

		addMember(sendingButton);
		addMember(speakButton);
		addMember(trashButton);
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
	public abstract void onTrash();

}
