package com.spicstome.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.HistoryManagementPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.HistoryManagementView;
import com.spicstome.client.ui.UserViewImpl;


public class HistoryManagementActivity extends UserActivity implements HistoryManagementView.Presenter {

	
	HistoryManagementView historyView;
	
	public HistoryManagementActivity(HistoryManagementPlace place, ClientFactory clientFactory) {

		super(place, clientFactory,(UserViewImpl)clientFactory.getHistoryManagementView());
	}
	
	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

			
		super.start(containerWidget, eventBus);
		
		historyView = clientFactory.getHistoryManagementView();
		historyView.init();

		SpicsToMeServices.Util.getInstance().getCurrentUser( new AsyncCallback<UserDTO>() {
			
			@Override
			public void onFailure(Throwable caught) {}
			@Override
			public void onSuccess(UserDTO result) {

				if(result instanceof ReferentDTO)
				{
					ReferentDTO referent = (ReferentDTO) result;
					historyView.insertStudentAlbum(referent.getStudents());
					userView.setIsLoading(false);
				}

			}			
		});	
	}

}
