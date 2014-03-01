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

public class NewMailActivity extends UserActivity implements NewMailView.Presenter{
	
	NewMailView newMailview;
	UserDTO user;
	
	public NewMailActivity(NewMailPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getNewMailView());		
		
		newMailview = clientFactory.getNewMailView();

		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) 
	{
		super.start(containerWidget, eventBus);
		
		SpicsToMeServices.Util.getInstance().getCurrentUser(new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {}

			@Override
			public void onSuccess(UserDTO result) {
				
				user=result;
				
				if(result instanceof StudentDTO)
				{
					newMailview.init(userType.STUDENT);
					newMailview.setStudent((StudentDTO)result);
				}
				else
				{
					SpicsToMeServices.Util.getInstance().getAlbum(1, new AsyncCallback<AlbumDTO>() {
						@Override
						public void onSuccess(AlbumDTO result) {
							
							StudentDTO falseStudent = new StudentDTO((long)-1);
							falseStudent.setAlbum(result);
							
							newMailview.init(userType.ADMIN);
							newMailview.setStudent(falseStudent);
							
						}

						@Override
						public void onFailure(Throwable caught) {}
					});
				}
				
			}
		});
		

		SpicsToMeServices.Util.getInstance().getEverybody(new AsyncCallback<List<UserDTO>>() {
			@Override
			public void onSuccess(List<UserDTO> result) {
				
				newMailview.setRecipients(result);
				userView.setIsLoading(false);
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
				public void onSuccess(Long result) {

				}
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
			public void onSuccess(Boolean result) {
				
			}
			
		});
	}

}
