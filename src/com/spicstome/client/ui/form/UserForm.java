package com.spicstome.client.ui.form;

import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.Utils;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader.OnCancelUploaderHandler;
import gwtupload.client.IUploader.OnFinishUploaderHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.services.SpicsToMeServices;
import com.spicstome.client.ui.strings.MyUploaderConstants;

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
		    		userDTO.setImage(new ImageDTO((long) -1, imageUploadForm.getImageFileName()));
		    		
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
