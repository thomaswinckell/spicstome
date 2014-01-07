package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.ui.UserViewLayout;

public class AlbumActivity extends UserActivity{

	public AlbumActivity(AlbumPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewLayout)clientFactory.getAlbumView());

		//clientFactory.getAlbumView().setAlbum(new AlbumDTO());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
