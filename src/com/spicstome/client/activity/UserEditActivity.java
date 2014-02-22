package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.UserEditPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UserEditView;
import com.spicstome.client.ui.UserViewImpl;

public class UserEditActivity extends UserActivity {
	
	UserEditView view;

	public UserEditActivity(UserEditPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getAddUserView());
		
		view = clientFactory.getAddUserView();
		
		if (place.getIdUser() != null) {
		
			SpicsToMeServices.Util.getInstance().getUser(Long.parseLong(place.getIdUser()), new AsyncCallback<UserDTO> () {
	
				@Override
				public void onFailure(Throwable caught) {
					System.out.println(caught);
				}
	
				@Override
				public void onSuccess(UserDTO user) {
					
					view.setUserDTO(user);
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