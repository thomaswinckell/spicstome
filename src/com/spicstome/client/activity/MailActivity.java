package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.ui.AlbumManagementView;
import com.spicstome.client.ui.UserViewImpl;

/**
 * activity which allow to cchoice between " create" or "read" a mail.
 * 
 *
 */
public class MailActivity extends UserActivity implements AlbumManagementView.Presenter {

	ReferentDTO referent;
	
	public MailActivity(MailPlace place, ClientFactory clientFactory) {

		super(place, clientFactory,(UserViewImpl)clientFactory.getMailView());
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		super.start(containerWidget, eventBus);
		
		userView.setIsLoading(false);

	}

}
