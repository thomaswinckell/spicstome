package com.spicstome.client.ui;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.james.mime4j.field.datetime.DateTime;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.AddUserPlace;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Image;
import com.spicstome.client.ui.widget.Crumb;

public class AddUserViewImpl extends UserViewLayout  implements AddUserView {

	public AddUserViewImpl () {
		super();
		
		addCrumb(new Crumb("Ajout d'un utilisateur"){
			@Override
			public void onClickCrumb() {			
				goTo(new AddUserPlace());
			}
		});
		
		final DataSource dataSource = new DataSource();
	    dataSource.setID("newUser");
	    
	    DataSourceTextField firstNameTextField = new DataSourceTextField("first_name");
	    firstNameTextField.setTitle("Prénom");
	    firstNameTextField.setRequired(true);
	    
	    DataSourceTextField nameTextField = new DataSourceTextField("name");
	    nameTextField.setTitle("Nom");
	    nameTextField.setRequired(true);

	    RegExpValidator emailRegExpValidator = new RegExpValidator();
	    emailRegExpValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");

	    DataSourceTextField emailTextField = new DataSourceTextField("email");
	    emailTextField.setTitle("Email");
	    emailTextField.setValidators(emailRegExpValidator);
	    emailTextField.setRequired(true);
	    
	    DataSourceTextField loginTextField = new DataSourceTextField("login");
	    loginTextField.setTitle("Login");
	    loginTextField.setRequired(true);
	    
	    DataSourceTextField passwordTextField = new DataSourceTextField("password");
	    passwordTextField.setTitle("Mot de passe");
	    passwordTextField.setRequired(true);
	    passwordTextField.setType(FieldType.PASSWORD);
	    
	    DataSourceTextField password2TextField = new DataSourceTextField("password2");
	    password2TextField.setTitle("Confirmation du mot de passe");
	    password2TextField.setRequired(true);
	    password2TextField.setType(FieldType.PASSWORD);

	    dataSource.setFields(firstNameTextField);
	    dataSource.setFields(nameTextField);
	    dataSource.setFields(emailTextField);
	    dataSource.setFields(loginTextField);
	    dataSource.setFields(passwordTextField);
	    dataSource.setFields(password2TextField);
        
	    final DynamicForm form = new DynamicForm();
	    form.setWidth(300);
	    form.setDataSource(dataSource);
	    
	    IButton validateButton = new IButton();
	    validateButton.setTitle("Valider");
	    validateButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		if (form.validate()) {
	    			
	    			// TODO Check password = password2
	    			// TODO Verify if login already exist
	    			
	    			
	    			/*final ImageDTO folderImage = new ImageDTO((long) -1, "test.jpg");
	    			
	    			SpicsToMeServices.Util.getInstance().saveImage(folderImage, new AsyncCallback<Long> () {

						@Override
						public void onFailure(Throwable caught) {
							System.out.println(caught);							
						}

						@Override
						public void onSuccess(Long idFolderImage) {
							
							folderImage.setId(idFolderImage);
							
							final FolderDTO folderAlbum = new FolderDTO((long) -1, "Tout", 0, null, 
			    					folderImage, new HashSet<PecsDTO>());
			    			
			    			SpicsToMeServices.Util.getInstance().saveFolder(folderAlbum, new AsyncCallback<Long> () {
		
								@Override
								public void onFailure(Throwable caught) {
									System.out.println(caught);
								}
		
								@Override
								public void onSuccess(Long idFolderAlbum) {
									
									folderAlbum.setId(idFolderAlbum);
									
									final AlbumDTO album = new AlbumDTO((long) -1, folderAlbum);
									
									SpicsToMeServices.Util.getInstance().saveAlbum(album, new AsyncCallback<Long> () {
		
										@Override
										public void onFailure(Throwable caught) {
											System.out.println(caught);
										}
		
										@Override
										public void onSuccess(final Long idAlbum) {
											
											album.setId(idAlbum);*/
											
											final ImageDTO imageUser = new ImageDTO((long) -1, "test");
											
											SpicsToMeServices.Util.getInstance().saveImage(imageUser, new AsyncCallback<Long> () {
												@Override
												public void onFailure(Throwable caught) {
													System.out.println(caught);
												}
		
												@Override
												public void onSuccess(Long idImage) {
													
													imageUser.setId(idImage);
													
													StudentDTO studentDTO = new StudentDTO((long) -1, new Date(), form.getValueAsString("first_name"), 
									    					form.getValueAsString("name"), form.getValueAsString("email"), form.getValueAsString("login"), 
									    					form.getValueAsString("password"), imageUser, null, 
									    					new HashSet<LogDTO>(), new HashSet<ReferentDTO>(), new HashSet<TeacherDTO>());
									    			
									    			SpicsToMeServices.Util.getInstance().saveStudent(studentDTO, new AsyncCallback<Long> () {
				
														@Override
														public void onFailure(Throwable caught) {
															System.out.println(caught);
														}
				
														@Override
														public void onSuccess(Long result) {
															goTo(new UsersManagementPlace());
														}	    				
									    			});
												}
											});
										}	    				
					    			/*});
								}	    				
			    			});
						}    			
	    			});
	    		}*/
	    	}
	    });

	    mainPanel.setMembersMargin(10);
	    mainPanel.addMember(form);
	    mainPanel.addMember(validateButton);
	}
}
