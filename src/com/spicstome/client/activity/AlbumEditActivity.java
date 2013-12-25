package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.UserViewLayout;

public class AlbumEditActivity extends UserActivity{

	public AlbumEditActivity(AlbumEditPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewLayout)clientFactory.getAlbumEditView());

		clientFactory.getAlbumEditView().setAlbum(new Album());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
