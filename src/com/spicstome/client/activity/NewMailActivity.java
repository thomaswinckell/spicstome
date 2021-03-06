package com.spicstome.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.place.NewMailPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.NewMailView;
import com.spicstome.client.ui.UserViewImpl;
import com.spicstome.client.ui.UserViewImpl.userType;

/**
 * 
 * Mail creation activity.
 * this part allow to
 * 	-choose a recipient
 * 	-create a mail
 * 	-sending it
 *
 */
public class NewMailActivity extends UserActivity implements NewMailView.Presenter{
	
	NewMailView newMailview;
	UserDTO user;
	String recipientMail;
	String htmlReceivedMail;
	
	public NewMailActivity(NewMailPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getNewMailView());		
		
		newMailview = clientFactory.getNewMailView();
		recipientMail = place.recipientMail;
		htmlReceivedMail = place.htmlReceivedMail;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
		
		SpicsToMeServices.Util.getInstance().getEverybody(new AsyncCallback<List<UserDTO>>() {
			@Override
			public void onSuccess(List<UserDTO> result) {
				
				newMailview.setRecipients(result);
				
				SpicsToMeServices.Util.getInstance().getUser(recipientMail, new AsyncCallback<UserDTO>() {
					
					@Override
					public void onSuccess(UserDTO result) {
						
						final UserDTO recipient = result;

						SpicsToMeServices.Util.getInstance().getAlbum(1, new AsyncCallback<AlbumDTO>() {
							@Override
							public void onSuccess(AlbumDTO result) {
								
								final StudentDTO falseStudent = new StudentDTO((long)-1);
								falseStudent.setAlbum(result);
								
								SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {

									@Override
									public void onFailure(Throwable caught) {}

									@Override
									public void onSuccess(UserDTO result) {
										
										user=result;

										if(result instanceof StudentDTO)
										{
											newMailview.setStudent((StudentDTO)result);
											newMailview.init(userType.STUDENT,recipientMail,recipient,htmlReceivedMail);
										}
										else
										{	
											newMailview.setStudent(falseStudent);
											newMailview.init(userType.ADMIN,recipientMail,recipient,htmlReceivedMail);
										}		
										
										userView.setIsLoading(false);
									}
								});			
							}
							@Override
							public void onFailure(Throwable caught) {}
						});			
					}		
					@Override
					public void onFailure(Throwable caught) {}
				});
			}
			@Override
			public void onFailure(Throwable caught) {}
		});	
	}


	@Override
	public void saveLog(LogDTO log) {
		
		if(user instanceof StudentDTO)
		{
			StudentDTO student = (StudentDTO)user;
			log.setStudent(student);
			
			SpicsToMeServices.Util.getInstance().saveLog(log, new AsyncCallback<Long>() {

				@Override
				public void onFailure(Throwable caught) {}

				@Override
				public void onSuccess(Long result) {}
			});
		}
	}

	@Override
	public void sendMail(String emailReceiver, ArrayList<WordDTO> words, ArrayList<String> correctedWords) {
		SpicsToMeServices.Util.getInstance().sendMail(user, emailReceiver, words, correctedWords, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				
			}

			@Override
			public void onSuccess(Boolean result) {}
			
		});
	}

}
