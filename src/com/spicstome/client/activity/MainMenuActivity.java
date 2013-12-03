package com.spicstome.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.ui.MainMenuView;
import com.spicstome.client.ui.UserView;

public class MainMenuActivity extends UserActivity implements
MainMenuView.Presenter{



	public MainMenuActivity(MainMenuPlace place, ClientFactory clientFactory) {
		super(place,clientFactory);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		MainMenuView mainmenu = clientFactory.getMainMenuView();
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