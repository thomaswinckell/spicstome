package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumManagementView;
import com.spicstome.client.ui.UserViewImpl;


public class AlbumManagementActivity extends UserActivity implements AlbumManagementView.Presenter {

	public AlbumManagementActivity(AlbumManagementPlace place, ClientFactory clientFactory) {

		super(place, clientFactory,(UserViewImpl)clientFactory.getAlbumManagementView());
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

		
		super.start(containerWidget, eventBus);
				
		SpicsToMeServices.Util.getInstance().getReferentConnected(new AsyncCallback<ReferentDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(ReferentDTO result) {
				if(result!=null) 
				{
					clientFactory.getAlbumManagementView().setAlbums(result.getStudents());
				}		
			}
		});
	}

	@Override
	public void copy(AlbumDTO album) {
		SpicsToMeServices.Util.getInstance().copyAlbum(album, new AsyncCallback<AlbumDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(AlbumDTO result) {
				// TODO Auto-generated method stub
				
			}
		});		
	}
}
