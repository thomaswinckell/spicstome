package com.spicstome.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.User;
import com.spicstome.client.ui.UserView;

public class UserActivity extends AbstractActivity{

	protected UserView userView;
	protected ClientFactory clientFactory;
	
	public UserActivity(Place place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		SpicsToMeServices.Util.getInstance().CurrentUser(new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {

				if(user!=null) 
				{
					userView.setName(user.getLogin());
				}	
			}
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}
		});
		
	}

}
