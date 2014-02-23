package com.spicstome.client.activity;

import java.util.ArrayList;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.HistoryPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.HistoryView;
import com.spicstome.client.ui.UserViewImpl;

public class HistoryActivity extends UserActivity{

	

	HistoryView historyView;
	
	public HistoryActivity(HistoryPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getHistoryView());

		this.historyView = clientFactory.getHistoryView();

		
		SpicsToMeServices.Util.getInstance().getUser(place.idStudent, new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(UserDTO result) {
				
				if(result instanceof StudentDTO)
				{
					StudentDTO student = (StudentDTO)result;
					
					historyView.setStudent(student);
					
					SpicsToMeServices.Util.getInstance().getAverageMessageLength(student.getLogs(), new AsyncCallback<Double>() {

						@Override
						public void onFailure(Throwable caught) {}

						@Override
						public void onSuccess(Double result) {
							historyView.setAverageMessageLength(result);
						}
					});
					
					SpicsToMeServices.Util.getInstance().getAverageTimeExecution(student.getLogs(), new AsyncCallback<Double>() {

						@Override
						public void onFailure(Throwable caught) {}

						@Override
						public void onSuccess(Double result) {
							historyView.setAverageExecutionTime(result);
						}
					});
					
					SpicsToMeServices.Util.getInstance().getMailPerWeek(student.getLogs(), new AsyncCallback<ArrayList<Integer>>() {

						@Override
						public void onFailure(Throwable caught) {}

						@Override
						public void onSuccess(ArrayList<Integer> result) {
							
							
							
						}
					});
				}
				
			}
		});
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
	}

}
