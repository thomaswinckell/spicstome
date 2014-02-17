package com.spicstome.client.activity;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.NewMailPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.NewMailView;
import com.spicstome.client.ui.UserViewImpl;

public class NewMailActivity extends UserActivity{
	
	NewMailView newMailview;
	
	public NewMailActivity(NewMailPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getNewMailView());		
		
		newMailview = clientFactory.getNewMailView();
		newMailview.init();
		
		SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(UserDTO result) {
				
				if(result instanceof StudentDTO)
				{
					newMailview.setStudent((StudentDTO)result);
					newMailview.setIsStudent(true);
				}
				else
				{
					SpicsToMeServices.Util.getInstance().getAlbum(1, new AsyncCallback<AlbumDTO>() {
						@Override
						public void onSuccess(AlbumDTO result) {
							
							StudentDTO falseStudent = new StudentDTO((long)-1);
							falseStudent.setAlbum(result);
							
							newMailview.setStudent(falseStudent);
							newMailview.setIsStudent(false);
						}

						@Override
						public void onFailure(Throwable caught) {}
					});
				}
				
			}
		});
		

		SpicsToMeServices.Util.getInstance().getEverybody(new AsyncCallback<List<UserDTO>>() {
			@Override
			public void onSuccess(List<UserDTO> result) {
				
				newMailview.setRecipients(result);
			}

			@Override
			public void onFailure(Throwable caught) {}
		});
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
