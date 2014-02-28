package com.spicstome.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.place.NewMailPlace;
import com.spicstome.client.place.ReceivedMailsPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.NewMailView;
import com.spicstome.client.ui.ReceivedMailsView;
import com.spicstome.client.ui.UserViewImpl;

public class ReceivedMailsActivity extends UserActivity implements ReceivedMailsView.Presenter{

	ReceivedMailsView receivedMailsView;
	UserDTO user;
	
	public ReceivedMailsActivity(ReceivedMailsPlace place, ClientFactory clientFactory) {
		
		super(place, clientFactory,(UserViewImpl)clientFactory.getReceivedMailsView());		
		
		receivedMailsView = clientFactory.getReceivedMailsView();		
		
		SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(UserDTO result) {
				
				user = result;
				
				SpicsToMeServices.Util.getInstance().getMails(user, new AsyncCallback<ArrayList<MailDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						
					}

					@Override
					public void onSuccess(ArrayList<MailDTO> result) {
						receivedMailsView.setReceivedMails(result);
					}			
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
