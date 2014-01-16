package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AddUserView;
import com.spicstome.client.ui.LoginView;
import com.spicstome.client.ui.UserViewImpl;

public class AddUserActivity extends UserActivity {
	
	AddUserView view;

	public AddUserActivity(AddUserPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getAddUserView());
		
		view = clientFactory.getAddUserView();
		
		if (place.getIdUser() != null) {
		
			SpicsToMeServices.Util.getInstance().getStudent(Long.parseLong(place.getIdUser()), new AsyncCallback<StudentDTO> () {
	
				@Override
				public void onFailure(Throwable caught) {
					System.out.println(caught);
				}
	
				@Override
				public void onSuccess(StudentDTO student) {
					
					view.setUserDTO(student);
				}			
			});
		} else {
			view.setUserDTO(null);
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		super.start(containerWidget, eventBus);
	}
}