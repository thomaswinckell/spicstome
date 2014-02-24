package com.spicstome.client.activity;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumManagementView;
import com.spicstome.client.ui.UserViewImpl;


public class AlbumManagementActivity extends UserActivity implements AlbumManagementView.Presenter {

	ReferentDTO referent;
	AlbumManagementView managementView;
	
	public AlbumManagementActivity(AlbumManagementPlace place, ClientFactory clientFactory) {

		super(place, clientFactory,(UserViewImpl)clientFactory.getAlbumManagementView());
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		
		
		super.start(containerWidget, eventBus);
		
		managementView = clientFactory.getAlbumManagementView();
		
		
		
		
				
		
		SpicsToMeServices.Util.getInstance().getCurrentUser( new AsyncCallback<UserDTO>() {
			
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(UserDTO result) {

				if(result instanceof ReferentDTO)
				{
					ReferentDTO referent = (ReferentDTO) result;
					managementView.init(true);
					managementView.insertStudentAlbum(referent.getStudents());
					
				}
				else
				{
					managementView.init(false);
				}
				
				SpicsToMeServices.Util.getInstance().getGeneralAndExampleAlbum(new AsyncCallback<List<AlbumDTO>>() {

					@Override
					public void onFailure(Throwable caught) {}

					@Override
					public void onSuccess(List<AlbumDTO> result) {
				
						managementView.insertMainAlbum(result);

					}
				});
				
			}			
		});	

	}

}
