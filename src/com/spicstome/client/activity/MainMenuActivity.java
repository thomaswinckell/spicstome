package com.spicstome.client.activity;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.MainMenuView;
import com.spicstome.client.ui.UserViewImpl;
import com.spicstome.client.ui.UserViewImpl.userType;

/**
 * MainMenu : depending of the current user, the menu allow different action
 */
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
					if(result.getId()==1)
						mainView.init(userType.ADMIN);
					else
						mainView.init(userType.REFERENT);
				}
				else if(result instanceof TeacherDTO)
				{
					mainView.init(userType.TEACHER);
				}
				else if(result instanceof StudentDTO)
				{
					mainView.init(userType.STUDENT);
				}
					
				userView.setIsLoading(false);
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