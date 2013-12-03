package com.spicstome.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.User;
import com.spicstome.client.ui.MainMenuView;

public class MainMenuActivity extends AbstractActivity {
	private ClientFactory clientFactory;
	// Name that will be appended to "Good-bye, "

	private User currentUser;
	
	public MainMenuActivity(MainMenuPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		MainMenuView goodbyeView = clientFactory.getGoodbyeView();
		
		System.out.println("start mainmenu");
		
		SpicsToMeServices.Util.getInstance().CurrentUser( new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
					
				if(user!=null) 
				{		
					currentUser = user;
					clientFactory.getGoodbyeView().setName(currentUser.getLogin());
				}
				
			}
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}
		});
		
		
		containerWidget.setWidget(goodbyeView.asWidget());
		System.out.println("start terminé");
	}
	
	
}