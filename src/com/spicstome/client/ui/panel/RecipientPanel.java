package com.spicstome.client.ui.panel;

import java.util.List;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.ui.window.SearchRecipientWindow;


public abstract class RecipientPanel extends HLayout{

	IconButton img = new IconButton("");
	int iconsize = 60;
	DynamicForm form = new DynamicForm();
	public TextItem mail = new TextItem("mail","Destinataire");
	List<UserDTO> userList;
	
		
	public RecipientPanel()
	{
		setWidth100();
		setHeight(80);
		
		setStyleName("bloc");
		
		
		img.setIconSize(iconsize);
		img.setPrompt("Selectionner un destinataire");
		
		img.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				SearchRecipientWindow recipientWindow = new SearchRecipientWindow(userList){
					@Override
					public void onDestroy()
					{	
						img.setIcon("upload/"+selectedUser.getImage().getFilename());
						mail.setValue(selectedUser.getEmail());
						
						onChangeRecipient(selectedUser);
					}
				};
				
				recipientWindow.show();
				
			}
		});

		form.setFields(mail);
		
		init();
		
		addMember(img);
		addMember(form);
	}
	
	public void init()
	{
		img.setIcon("upload/"+"default_user.png");
		mail.setValue("");
	}
	
	public abstract void onChangeRecipient(UserDTO user);
	
	public void setRecipients(List<UserDTO> list)
	{
		this.userList=list;
	}
	
	public String getMail() {
		return mail.getValueAsString();
	}
}
