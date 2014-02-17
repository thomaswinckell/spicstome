package com.spicstome.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.spicstome.client.ui.AddUserView;
import com.spicstome.client.ui.AddUserViewImpl;
import com.spicstome.client.ui.AlbumEditView;
import com.spicstome.client.ui.AlbumEditViewImpl;
import com.spicstome.client.ui.AlbumManagementView;
import com.spicstome.client.ui.AlbumManagementViewImpl;
import com.spicstome.client.ui.AlbumView;
import com.spicstome.client.ui.AlbumViewImpl;
import com.spicstome.client.ui.LogoutView;
import com.spicstome.client.ui.LogoutViewImpl;
import com.spicstome.client.ui.MailView;
import com.spicstome.client.ui.MailViewImpl;
import com.spicstome.client.ui.NewMailView;
import com.spicstome.client.ui.NewMailViewImpl;
import com.spicstome.client.ui.MainMenuView;
import com.spicstome.client.ui.MainMenuViewImpl;
import com.spicstome.client.ui.LoginView;
import com.spicstome.client.ui.LoginViewImpl;
import com.spicstome.client.ui.UsersManagementView;
import com.spicstome.client.ui.UsersManagementViewImpl;


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
	private static final LogoutView logoutView = new LogoutViewImpl();
	private static final MainMenuView mainMenuView = new MainMenuViewImpl();
	private static final AlbumManagementView albumManagementView = new AlbumManagementViewImpl();
	private static final AlbumView albumView = new AlbumViewImpl();
	private static final AlbumEditView albumEditView = new AlbumEditViewImpl();
	private static final NewMailView newMailView = new NewMailViewImpl();
	private static final UsersManagementView usersManagementView = new UsersManagementViewImpl();
	private static final AddUserView addUserView = new AddUserViewImpl();
	private static final MailView mailView = new MailViewImpl();

	public SimpleEventBus getEventBus() {
		return eventBus;
	}

	public LoginView getLoginView() {
		return loginView;
	}
	
	public LogoutView getLogoutView() {
		return logoutView;
	}

	public PlaceController getPlaceController() {
		return placeController;
	}

	public MainMenuView getMainMenuView() {
		return mainMenuView;
	}
	
	public AlbumManagementView getAlbumManagementView() {
		return albumManagementView;
	}
	
	public AlbumView getAlbumView() {
		return albumView;
	}
	
	public AlbumEditView getAlbumEditView() {
		return albumEditView;
	}
	
	public NewMailView getNewMailView() {
		return newMailView;
	}
	
	public MailView getMailView() {
		return mailView;
	}
	
	public UsersManagementView getUsersManagementView() {
		return usersManagementView;
	}
	
	public AddUserView getAddUserView() {
		return addUserView;
	}
}
