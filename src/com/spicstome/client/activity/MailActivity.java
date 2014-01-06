package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.ui.UserViewLayout;

public class MailActivity extends UserActivity{

	public MailActivity(MailPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewLayout)clientFactory.getMailView());		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
