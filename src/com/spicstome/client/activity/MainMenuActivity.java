package com.spicstome.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.MainMenuView;
import com.spicstome.client.ui.UserViewImpl;

public class MainMenuActivity extends UserActivity{

	MainMenuView mainView;
	
	public MainMenuActivity(MainMenuPlace place, ClientFactory clientFactory) {
		super(place,clientFactory,(UserViewImpl)clientFactory.getMainMenuView());
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		super.start(containerWidget, eventBus);
		
		mainView=clientFactory.getMainMenuView();
		
		SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {
			
			@Override
			public void onSuccess(UserDTO result) {
			
				if(result instanceof ReferentDTO)
				{
					mainView.init(true);
				}
				else
				{
					mainView.init(false);
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {}
		});
	}

	@Override
	public void goTo(Place place) {
		
		clientFactory.getPlaceController().goTo(place);
		
	}
}