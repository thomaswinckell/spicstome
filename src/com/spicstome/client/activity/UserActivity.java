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
		
		Scheduler.get().scheduleFinally(new ScheduledCommand() {
			public void execute() {
				SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {
					@Override
					public void onSuccess(UserDTO user) {
						
						if(user!=null) {
							userView.setName(user.getLogin());

						} else {
							goTo(new LoginPlace());
						}

					}
					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught);
					}
				});
			}
		});
		
		panel.setWidget(userView.asWidget());
		userView.setPresenter(this);
		userView.setName("?");
	}

	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
		
	}

}
