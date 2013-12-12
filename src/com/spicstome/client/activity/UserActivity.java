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
import com.spicstome.client.ui.composite.UserViewComposite;

public class UserActivity extends AbstractActivity implements UserView.Presenter{

	protected UserViewComposite userView;
	protected ClientFactory clientFactory;
	
	public UserActivity(Place place, ClientFactory clientFactory,UserViewComposite userView) {
		this.clientFactory = clientFactory;
		
		this.userView = userView;
		
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
		
		panel.setWidget(userView.asWidget());
		userView.setPresenter(this);
		
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
		
	}

}
