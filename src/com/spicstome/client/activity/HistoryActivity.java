package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.place.HistoryPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.AlbumView;
import com.spicstome.client.ui.HistoryView;
import com.spicstome.client.ui.UserViewImpl;

public class HistoryActivity extends UserActivity{

	

	HistoryView historyView;
	
	public HistoryActivity(HistoryPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getHistoryView());

		this.historyView = clientFactory.getHistoryView();

		
		
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
