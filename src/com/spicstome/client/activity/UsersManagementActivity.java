
package com.spicstome.client.activity;

import java.util.List;

import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UserViewImpl;
import com.spicstome.client.ui.UsersManagementView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class UsersManagementActivity extends UserActivity {
	
	UsersManagementView view;

	public UsersManagementActivity(UsersManagementPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getUsersManagementView());
		
		view = clientFactory.getUsersManagementView();
		
		SpicsToMeServices.Util.getInstance().getAllStudents(new AsyncCallback<List<StudentDTO>> () {
        	
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			@Override
			public void onSuccess(List<StudentDTO> students) {
				view.setStudents(students);
			}
		});
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		super.start(containerWidget, eventBus);
	}
}
