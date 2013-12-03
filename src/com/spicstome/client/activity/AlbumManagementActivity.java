package com.spicstome.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.AlbumManagementPlace;

import com.spicstome.client.ui.AlbumManagementView;

import com.spicstome.client.ui.UserView;


public class AlbumManagementActivity extends UserActivity implements AlbumManagementView.Presenter {

	public AlbumManagementActivity(AlbumManagementPlace place, ClientFactory clientFactory) {

		super(place, clientFactory);
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		AlbumManagementView mainmenu = clientFactory.getAlbumManagementView();
		userView = (UserView) mainmenu;
		containerWidget.setWidget(userView.asWidget());
		mainmenu.setPresenter(this);
		
		super.start(containerWidget, eventBus);
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
		
	}
	
	

}


/*
public class AlbumManagementActivity extends AbstractActivity implements AlbumManagementView.Presenter
{

	private ClientFactory clientFactory;


	public AlbumManagementActivity(AlbumManagementPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		AlbumManagementView loginView = clientFactory.getAlbumManagementView();
		loginView.setPresenter(this);
		containerWidget.setWidget(loginView.asWidget());
	}

	
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	
	
}
*/