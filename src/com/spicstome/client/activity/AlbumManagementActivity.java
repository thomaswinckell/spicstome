package com.spicstome.client.activity;


import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UserViewLayout;


public class AlbumManagementActivity extends UserActivity {

	public AlbumManagementActivity(AlbumManagementPlace place, ClientFactory clientFactory) {

		super(place, clientFactory,(UserViewLayout)clientFactory.getAlbumManagementView());
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		
		super.start(containerWidget, eventBus);
				
		SpicsToMeServices.Util.getInstance().getReferentAlbums(new AsyncCallback<List<AlbumDTO>>() {
			@Override
			public void onSuccess(List<AlbumDTO> result) {
				if(result!=null) {
					clientFactory.getAlbumManagementView().setAlbum(result);
				}
				
			}
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}
			
		});
	}


}
