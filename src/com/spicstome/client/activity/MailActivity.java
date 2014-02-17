package com.spicstome.client.activity;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.MailView;
import com.spicstome.client.ui.UserViewImpl;

public class MailActivity extends UserActivity{
	
	MailView mailview;
	
	public MailActivity(MailPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getMailView());		
		
		mailview = clientFactory.getMailView();
		
		SpicsToMeServices.Util.getInstance().getAlbum(1, new AsyncCallback<AlbumDTO>() {
			@Override
			public void onSuccess(AlbumDTO result) {
				
				StudentDTO falseStudent = new StudentDTO((long)-1);
				falseStudent.setAlbum(result);
				
				mailview.setStudent(falseStudent);
			}

			@Override
			public void onFailure(Throwable caught) {}
		});
		
		SpicsToMeServices.Util.getInstance().getEverybody(new AsyncCallback<List<UserDTO>>() {
			@Override
			public void onSuccess(List<UserDTO> result) {
				
				mailview.setRecipients(result);
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
