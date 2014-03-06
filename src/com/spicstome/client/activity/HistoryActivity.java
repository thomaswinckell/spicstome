package com.spicstome.client.activity;

import java.util.ArrayList;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.Point2D;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.HistoryPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.HistoryView;
import com.spicstome.client.ui.UserViewImpl;

public class HistoryActivity extends UserActivity{

	
	long idStudent;
	HistoryView historyView;
	
	public HistoryActivity(HistoryPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getHistoryView());

		this.historyView = clientFactory.getHistoryView();
		this.idStudent=place.idStudent;
		
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
		
		SpicsToMeServices.Util.getInstance().getUser(idStudent, new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(UserDTO result) {
				
				if(result instanceof StudentDTO)
				{
					final StudentDTO student = (StudentDTO)result;
					
					historyView.setStudent(student);
					
					SpicsToMeServices.Util.getInstance().getAverageMessageLength(-1,-1,student.getLogs(), new AsyncCallback<Double>() {

						@Override
						public void onFailure(Throwable caught) {}

						@Override
						public void onSuccess(Double result) {
							
							historyView.setAverageMessageLength(result);
							
							SpicsToMeServices.Util.getInstance().getAverageTimeExecution(-1,-1,student.getLogs(), new AsyncCallback<Double>() {

								@Override
								public void onFailure(Throwable caught) {}

								@Override
								public void onSuccess(Double result) {
									historyView.setAverageExecutionTime(result);
									
									SpicsToMeServices.Util.getInstance().getHistoryPerWeek(student.getLogs(), 0,new AsyncCallback<ArrayList<Point2D>>() {

										@Override
										public void onFailure(Throwable caught) {}

										@Override
										public void onSuccess(ArrayList<Point2D> result) {
											
											historyView.setNbMailPerWeek(result);
											
											SpicsToMeServices.Util.getInstance().getHistoryPerWeek(student.getLogs(), 2,new AsyncCallback<ArrayList<Point2D>>() {

												@Override
												public void onFailure(Throwable caught) {}

												@Override
												public void onSuccess(ArrayList<Point2D> result) {
													
													historyView.setMessageLengthPerWeek(result);
													
													SpicsToMeServices.Util.getInstance().getHistoryPerWeek(student.getLogs(), 1,new AsyncCallback<ArrayList<Point2D>>() {

														@Override
														public void onFailure(Throwable caught) {}

														@Override
														public void onSuccess(ArrayList<Point2D> result) {
															
															historyView.setExecutionTimePerWeek(result);
															
															SpicsToMeServices.Util.getInstance().getPartitionMessageLength(student.getLogs(),new AsyncCallback<ArrayList<Double>>() {

																@Override
																public void onFailure(Throwable caught) {}

																@Override
																public void onSuccess(ArrayList<Double> result) {
																	
																	historyView.setPartitionMessageLength(result);
																	userView.setIsLoading(false);
																	historyView.drawCharts();
																}
															});
														}
													});
												}
											});
										}
									});
								}
							});
						}
					});
				}	
			}
		});
	}
}
