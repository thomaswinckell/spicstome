package com.spicstome.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.LoginPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UserView;
import com.spicstome.client.ui.UserViewImpl;

/**
 * Activity which contain a top view with the connected user and a breadcrumb
 */
public class UserActivity extends AbstractActivity implements UserView.Presenter{

	protected UserViewImpl userView;
	protected ClientFactory clientFactory;
	
	public UserActivity(Place place, ClientFactory clientFactory,UserViewImpl userView) 
	{
		this.clientFactory = clientFactory;
		this.userView = userView;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		
		userView.setIsLoading(true);
		
		Scheduler.get().scheduleFinally(new ScheduledCommand() {
			public void execute() {
				SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {
					@Override
					public void onSuccess(UserDTO user) {
						
						if(user!=null) {
							userView.setUser(user);

						} else {
							goTo(new LoginPlace());
						}

					}
					@Override
					public void onFailure(Throwable caught) {}
				});
			}
		});
		
		panel.setWidget(userView.asWidget());
		userView.setPresenter(this);
		userView.setUser(null);
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
		
	}

}
