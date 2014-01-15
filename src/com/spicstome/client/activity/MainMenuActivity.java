package com.spicstome.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.ui.UserViewImpl;

public class MainMenuActivity extends UserActivity{

	public MainMenuActivity(MainMenuPlace place, ClientFactory clientFactory) {
		super(place,clientFactory,(UserViewImpl)clientFactory.getMainMenuView());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		super.start(containerWidget, eventBus);
		
	}

	@Override
	public void goTo(Place place) {
		
		clientFactory.getPlaceController().goTo(place);
		
	}
}