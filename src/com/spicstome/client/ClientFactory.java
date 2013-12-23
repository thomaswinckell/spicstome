package com.spicstome.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.spicstome.client.ui.AlbumManagementView;
import com.spicstome.client.ui.AlbumManagementViewImpl;
import com.spicstome.client.ui.AlbumView;
import com.spicstome.client.ui.AlbumViewImpl;
import com.spicstome.client.ui.MailView;
import com.spicstome.client.ui.MailViewImpl;
import com.spicstome.client.ui.MainMenuView;
import com.spicstome.client.ui.MainMenuViewImpl;
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
	private static final MainMenuView mainMenuView = new MainMenuViewImpl();
	private static final AlbumManagementView albumManagementView = new AlbumManagementViewImpl();
	private static final AlbumView albumView = new AlbumViewImpl();
	private static final MailView mailView = new MailViewImpl();

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

	public MainMenuView getMainMenuView()
	{
		return mainMenuView;
	}
	public AlbumManagementView getAlbumManagementView()
	{
		return albumManagementView;
	}
	public AlbumView getAlbumView()
	{
		return albumView;
	}
	
	public MailView getMailView()
	{
		return mailView;
	}
}
