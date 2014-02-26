package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public abstract class SelectRecipientPanel extends VLayout {


	ImageTileGrid userGrid;
	Label labelNothing = new Label();
	List<UserDTO> users;
	DynamicForm form = new DynamicForm();
	TextItem item = new TextItem();
	IconButton validation = new IconButton("");
	int iconsize=40;
	Label labelTitle = new Label();
	
	public SelectRecipientPanel() {
		super();

		setTitle("Selection du destinataire");

		userGrid = new ImageTileGrid(Mode.CLICK,200,150,60){

			@Override
			public void OnSelectChanged(ImageRecord object) {
				super.OnSelectChanged(object);
		
				UpdateValidation();
			};
		};
		
		userGrid.setHeight(300);
		

		item.setTitle("Rechercher");
		form.setItems(item);
		
		item.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				
				if(event.getValue()!=null)
					updateWordsList(event.getValue().toString());
				else
					updateWordsList("");

				UpdateValidation();
			}
		});
		
		labelTitle.setHeight(40);
		labelTitle.setStyleName("title");
		labelTitle.setContents("A qui veux tu envoyer un mail ?");
		
		labelNothing.setContents("Aucun utilisateur ne correspond à votre recherche");
		labelNothing.setHeight(40);
		
		validation.setIconSize(iconsize);
		validation.setIcon("check.png");
		
		validation.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				UserDTO user=null;
				if(userGrid.getSelectedItem()!=null)
				{
					user = (UserDTO)userGrid.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
					onSelectedRecipient(user,user.getEmail());
				}
				else
				{
					onSelectedRecipient(null,item.getValueAsString());
				}

			}
		});
		
		addMember(labelTitle);
		addMember(form);
		addMember(labelNothing);
		addMember(userGrid);
		addMember(validation);
		
	}
	
	private void UpdateValidation()
	{
		boolean textIsMail = (item.getValue()==null?
				false:
			item.getValueAsString().matches("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$"));
		
		this.validation.setVisible((userGrid.getSelectedItem()!=null)|| textIsMail);
	}
	
	public void setRecipients(List<UserDTO> userList)
	{
		users=userList;

		updateWordsList("");
		UpdateValidation();
	}

	private void updateWordsList(String s)
	{
		
		
		ArrayList<ImageRecord> wordsRecord = new ArrayList<ImageRecord>();
		
		for(UserDTO userDTO:users)
		{
			if(userDTO.getName().toLowerCase().matches("^"+s+".*")
					|| userDTO.getFirstName().toLowerCase().matches("^"+s+".*")
					|| userDTO.getLogin().toLowerCase().matches("^"+s+".*")
					|| s.equals(""))
			{
				wordsRecord.add(new ImageRecord(userDTO));
			}
		}
		
		labelNothing.setVisible(wordsRecord.size()==0);
		
		userGrid.setItems(wordsRecord);

	}
	
	public abstract void onSelectedRecipient(UserDTO user,String mail);
	
}
