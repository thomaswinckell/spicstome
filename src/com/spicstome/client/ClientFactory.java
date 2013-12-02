package com.spicstome.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.spicstome.client.ui.GoodbyeView;
import com.spicstome.client.ui.GoodbyeViewImpl;
import com.spicstome.client.ui.LoginView;
import com.spicstome.client.ui.LoginViewImpl;


/**
 * 
 * @author Maxime
 * 
 * ClientFactory provide all the view of the application and contain 
 * the PlaceController which will map the places during the execution
 *
 */

public class ClientFactory 
{
	private static final SimpleEventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final LoginView loginView = new LoginViewImpl();
	private static final GoodbyeView goodbyeView = new GoodbyeViewImpl();


	public SimpleEventBus getEventBus()
	{
		return eventBus;
	}


	public LoginView getLoginView()
	{
		return loginView;
	}

	public PlaceController getPlaceController()
	{
		return placeController;
	}

	public GoodbyeView getGoodbyeView()
	{
		return goodbyeView;
	}
}
