package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumView;
import com.spicstome.client.ui.UserViewImpl;

/**
 * Activity which loads and display an album with a given id
 * 1 => general album
 * 2=> example album
 */

public class AlbumActivity extends UserActivity{

	//album View
	AlbumView albumView;
	
	public AlbumActivity(AlbumPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getAlbumView());

		this.albumView = clientFactory.getAlbumView();

		//general and example case
		if(place.idAlbum==1 || place.idAlbum==2)
		{
			SpicsToMeServices.Util.getInstance().getAlbum(place.idAlbum, new AsyncCallback<AlbumDTO>() {
				@Override
				public void onSuccess(AlbumDTO result) {
					
					StudentDTO falseStudent = new StudentDTO((long)-1);
					falseStudent.setAlbum(result);
					
					albumView.setStudent(falseStudent);
					userView.setIsLoading(false);
				}

				@Override
				public void onFailure(Throwable caught) {}
			});
		}
		else
		{
			//student case
			SpicsToMeServices.Util.getInstance().getAlbumOwner(place.idAlbum, new AsyncCallback<StudentDTO>() {
				@Override
				public void onSuccess(StudentDTO result) {
					
					albumView.setStudent(result);
					userView.setIsLoading(false);
				}
				@Override
				public void onFailure(Throwable caught) {}			
			});	
		}
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
