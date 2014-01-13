package com.spicstome.client.activity;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumView;
import com.spicstome.client.ui.UserViewLayout;

public class AlbumActivity extends UserActivity{

	
	AlbumDTO album;
	AlbumView albumView;
	
	public AlbumActivity(AlbumPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewLayout)clientFactory.getAlbumView());

		this.albumView = clientFactory.getAlbumView();

		SpicsToMeServices.Util.getInstance().getAlbum(place.idAlbum, new AsyncCallback<AlbumDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(AlbumDTO result) {
				album=result;
				albumView.setAlbum(album);
				
				SpicsToMeServices.Util.getInstance().getFoldersAlbum(album.getId(), new AsyncCallback<List<FolderDTO>>() {

					@Override
					public void onSuccess(List<FolderDTO> result) {
						albumView.setFolders(result);	
					}
					@Override
					public void onFailure(Throwable caught) {}	
				});
				
			}
		});
		
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
