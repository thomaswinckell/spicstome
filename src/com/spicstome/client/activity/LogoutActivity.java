package com.spicstome.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.LoginPlace;
import com.spicstome.client.place.LogoutPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.LogoutView;

public class LogoutActivity extends AbstractActivity implements
LogoutView.Presenter {

		private ClientFactory clientFactory;


		public LogoutActivity(LogoutPlace place, ClientFactory clientFactory) {
			this.clientFactory = clientFactory;
		}

		/**
		 * Invoked by the ActivityManager to start a new Activity
		 */
		@Override
		public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
			Scheduler.get().scheduleFinally(new ScheduledCommand() {
				public void execute() {
					SpicsToMeServices.Util.getInstance().disconnectCurrentUser(new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean result) {
							// TODO Auto-generated method stub
							goTo(new LoginPlace());
						}
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							System.out.println(caught);
						}
					});
				}
			});			
		}

		/**
		 * Navigate to a new Place in the browser
		 */
		public void goTo(Place place) {
			clientFactory.getPlaceController().goTo(place);
		}
}
