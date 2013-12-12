package com.spicstome.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.User;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.place.LoginPlace;
import com.spicstome.client.ui.LoginView;

/**
 * 
 * @author Maxime
 * 
 * LoginActivity , handle the login part of the application
 *
 */

public class LoginActivity extends AbstractActivity implements
		LoginView.Presenter {
	
	// Used to obtain views, eventBus, placeController

	private ClientFactory clientFactory;


	public LoginActivity(LoginPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		LoginView loginView = clientFactory.getLoginView();
		loginView.setPresenter(this);
		containerWidget.setWidget(loginView.asWidget());
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void login(String login, String password) {
		
		
		SpicsToMeServices.Util.getInstance().Login(login, password, new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				/*
				if(user!=null) 
				{
					goTo(new MainMenuPlace());
				}	
				else
					clientFactory.getLoginView().setWrongLogin();

				*/
				goTo(new MainMenuPlace());
				
			}
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}
		});
	}
}
