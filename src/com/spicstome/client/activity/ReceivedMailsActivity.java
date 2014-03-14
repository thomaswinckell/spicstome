package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.MailListDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.ReceivedMailsPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.ReceivedMailsView;
import com.spicstome.client.ui.UserViewImpl;

/**
 * Inbox activity
 * allow to reply to a received mail
 */
public class ReceivedMailsActivity extends UserActivity implements ReceivedMailsView.Presenter{

	ReceivedMailsView receivedMailsView;
	UserDTO user;
	int maxNbValidMails, startPosition;
	boolean isDescDirection;
	
	public ReceivedMailsActivity(ReceivedMailsPlace place, ClientFactory clientFactory) {
		
		super(place, clientFactory,(UserViewImpl)clientFactory.getReceivedMailsView());		
		
		receivedMailsView = clientFactory.getReceivedMailsView();
		maxNbValidMails = place.maxNbValidMails;
		startPosition = place.startPosition;
		isDescDirection = place.isDescDirection;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		super.start(containerWidget, eventBus);
		
		SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(UserDTO result) {
				
				user = result;
				
				SpicsToMeServices.Util.getInstance().getMails(user, startPosition, isDescDirection, maxNbValidMails, new AsyncCallback<MailListDTO>()  {

					@Override
					public void onFailure(Throwable caught) { }

					@Override
					public void onSuccess(MailListDTO result) {
						receivedMailsView.setReceivedMails(result);
						userView.setIsLoading(false);
					}			
				});
			}
		});
	}
}
