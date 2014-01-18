package com.spicstome.client.ui.form;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.services.SpicsToMeServices;

public class UserForm {
	
	private UserDTO userDTO;

	private BasicUserForm basicUserForm;
	private ImageUploadForm imageUploadForm;
	private UserTypeForm userTypeForm;
	private FormUtils.Mode mode;
	private AsyncCallback<Long> onSuccess;
	
	public UserForm (VLayout mainPanel, AsyncCallback<Long> onSuccess) {
		
		this.onSuccess = onSuccess;
		
		basicUserForm = new BasicUserForm();
		
		Label labelUserImage = new Label("Image de l'utilisateur :");

		imageUploadForm = new ImageUploadForm(128, 128);
		
		userTypeForm = new UserTypeForm();
		
		IButton validateButton = new IButton("Valider");
	    validateButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		
	    		if (validate()) {
	    		
		    		userDTO = userTypeForm.getUserDTO();
		    		basicUserForm.fillUserDTO(userDTO);
		    		userDTO.getImage().setFilename(imageUploadForm.getImageFileName());
		    		
		    		if (mode == FormUtils.Mode.NEW) {
						saveUser();
					} else {
						updateUser();
					}
	    		}
	    	}
	    });
	    
	    VLayout vLayout = new VLayout();
	    vLayout.addMember(basicUserForm);
	    vLayout.addMember(labelUserImage);
	    vLayout.addMember(imageUploadForm.getImage());
	    vLayout.addMember(imageUploadForm.getUploadButton());
	    vLayout.addMember(userTypeForm);
	    vLayout.addMember(validateButton);
	    
	    mainPanel.addMember(vLayout);
	}
	
	public boolean validate() {
		return basicUserForm.validate();
	}
	
	public void setUserDTO(UserDTO userDTO, FormUtils.Mode mode) {
		this.userDTO = userDTO;
		this.mode = mode;
		
		basicUserForm.fillForm(userDTO, mode);
		
		if (mode == FormUtils.Mode.NEW)
			imageUploadForm.setImageFileName(FormUtils.DEFAULT_USER_IMAGE_FILENAME);
		else
			imageUploadForm.setImageFileName(userDTO.getImage().getFilename());
		
		userTypeForm.setUserDTO(userDTO, mode);
	}
	
	private void updateUser () {
		
		if (basicUserForm.isNewLogin()) {
			SpicsToMeServices.Util.getInstance().getUserByLogin(basicUserForm.getValueAsString("login"), 
					new AsyncCallback<UserDTO>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught);
					}

					@Override
					public void onSuccess(UserDTO user) {
						if (user == null)
							SpicsToMeServices.Util.getInstance().updateUser(userDTO, onSuccess);
						else {
							SC.warn("Le login existe d&eacute;j&agrave;. Veuillez en choisir un autre.");
						}
					}				
			});
		} else {
			SpicsToMeServices.Util.getInstance().updateUser(userDTO, onSuccess);
		}
	}
	
	private void saveUser () {
		SpicsToMeServices.Util.getInstance().getUserByLogin(basicUserForm.getValueAsString("login"), 
				new AsyncCallback<UserDTO>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println(caught);
				}

				@Override
				public void onSuccess(UserDTO user) {
					if (user == null)
						SpicsToMeServices.Util.getInstance().saveUser(userDTO, onSuccess);
					else {
						SC.warn("Le login existe d&eacute;j&agrave;. Veuillez en choisir un autre.");
					}
				}				
		});
	}
}
