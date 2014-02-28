
package com.spicstome.client.activity;

import java.util.ArrayList;
import java.util.List;

import com.spicstome.client.ClientFactory;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.UserViewImpl;
import com.spicstome.client.ui.UsersManagementView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class UsersManagementActivity extends UserActivity {
	
	UsersManagementView view;

	public UsersManagementActivity(UsersManagementPlace place, ClientFactory clientFactory) {
		super(place, clientFactory,(UserViewImpl)clientFactory.getUsersManagementView());
		
		view = clientFactory.getUsersManagementView();
		
		
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		super.start(containerWidget, eventBus);
		
		SpicsToMeServices.Util.getInstance().getAllStudents(new AsyncCallback<List<StudentDTO>> () {
        	
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			@Override
			public void onSuccess(List<StudentDTO> students) {
				List<UserDTO> users = new ArrayList<UserDTO>();
				for(StudentDTO student : students) {
					users.add(((UserDTO) student));
				}
				view.setStudents(users);
				
				SpicsToMeServices.Util.getInstance().getAllTeachers(new AsyncCallback<List<TeacherDTO>> () {
		        	
					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught);
					}

					@Override
					public void onSuccess(List<TeacherDTO> teachers) {
						List<UserDTO> users = new ArrayList<UserDTO>();
						for(TeacherDTO teacher : teachers) {
							users.add(((UserDTO) teacher));
						}
						view.setTeachers(users);
						
						SpicsToMeServices.Util.getInstance().getAllReferents(new AsyncCallback<List<ReferentDTO>> () {
				        	
							@Override
							public void onFailure(Throwable caught) {
								System.out.println(caught);
							}

							@Override
							public void onSuccess(List<ReferentDTO> referents) {
								List<UserDTO> users = new ArrayList<UserDTO>();
								for(ReferentDTO referent : referents) {
									users.add(((UserDTO) referent));
								}
								view.setReferents(users);
								
								userView.setIsLoading(false);
							}
						});
					}
				});
			}
		});	
	}
}
