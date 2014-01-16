package com.spicstome.client.ui.form;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.ImageDTO;
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
	    
	    VLayout vLayout = new VLayout();
	    vLayout.addMember(labelUserImage);

		imageUploadForm = new ImageUploadForm(128, 128, vLayout);
		
		userTypeForm = new UserTypeForm();
		
		IButton validateButton = new IButton("Valider");
	    validateButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		
	    		if (validate()) {
	    		
		    		userDTO = userTypeForm.getUserDTO();
		    		basicUserForm.fillUserDTO(userDTO);
		    		userDTO.setImage(new ImageDTO((long) -1, imageUploadForm.getImageFileName()));
		    		
		    		if (mode == FormUtils.Mode.NEW) {
						saveUser();
					} else {
						updateUser();
					}
		    		
		    		// if we need to update the user image
		    		/*if ((userDTO.getImage() == null) || ((userDTO.getImage() != null) && (userDTO.getImage().getFilename() != imageUploadForm.getImageFileName()))) {
		    		
		    			final ImageDTO imageUser = new ImageDTO((long) -1, imageUploadForm.getImageFileName());
		    			
		    			SpicsToMeServices.Util.getInstance().saveImage(imageUser, new AsyncCallback<Long> () {
		    				@Override
		    				public void onFailure(Throwable caught) {
		    					System.out.println(caught);
		    				}
		    	
		    				@Override
		    				public void onSuccess(Long idImage) {
		    	
		    					imageUser.setId(idImage);
		    					
		    					userDTO.setImage(imageUser);
		    					
		    					if (mode == FormUtils.Mode.NEW) {
		    						saveUser();
		    					} else {
		    						updateUser();
		    					}
		    				}
		    		}	*/
	    		}
	    	}
	    });
	    
	    HLayout hLayout = new HLayout();
	    hLayout.addMember(basicUserForm);
	    hLayout.addMember(vLayout);
	    
	    mainPanel.setMembersMargin(10);
	    mainPanel.addMember(hLayout);
	    mainPanel.addMember(userTypeForm);
	    mainPanel.addMember(validateButton);
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
		/*SpicsToMeServices.Util.getInstance().updateUser(userDTO, new AsyncCallback<Long> () {
			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught);
			}

			@Override
			public void onSuccess(Long idImage) {
				goTo(new UsersManagementPlace());
			}
		});*/
	}
	
	private void saveUser () {
		SpicsToMeServices.Util.getInstance().saveUser(userDTO, onSuccess);
	}
}
