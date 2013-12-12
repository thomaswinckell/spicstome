package com.spicstome.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.AlbumManagementPlace;

import com.spicstome.client.ui.composite.UserViewComposite;


public class AlbumManagementActivity extends UserActivity {

	public AlbumManagementActivity(AlbumManagementPlace place, ClientFactory clientFactory) {

		super(place, clientFactory,(UserViewComposite)clientFactory.getAlbumManagementView());
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		
		super.start(containerWidget, eventBus);
		
		//albumManagementView.setAlbum(list)
	}


}
