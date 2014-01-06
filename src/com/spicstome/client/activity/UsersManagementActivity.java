
package com.spicstome.client.activity;

import com.spicstome.client.ClientFactory;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.UserViewLayout;
import com.spicstome.client.ui.UsersManagementView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class UsersManagementActivity extends UserActivity {

	public UsersManagementActivity(UsersManagementPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewLayout)clientFactory.getUsersManagementView());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		super.start(containerWidget, eventBus);
	}
}
