package com.spicstome.client.ui.window;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
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

public class RecipientChooseWindow extends Window {

	VLayout layout = new VLayout();
	ImageTileGrid userGrid;
	IconButton valid = new IconButton("Selectionner ce destinataire");
	Label labelNothing = new Label();
	List<UserDTO> users;
	DynamicForm form = new DynamicForm();
	TextItem item = new TextItem();
	public UserDTO selectedUser;
	
	public RecipientChooseWindow(List<UserDTO> userList) {
		super();
		
		setWidth(800);
		setHeight(600);
		
		setTitle("Selection du destinataire");

        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
        setDismissOnOutsideClick(true);
		
		
		userGrid = new ImageTileGrid(Mode.CLICK,200,150,60){

			@Override
			public void OnSelectChanged(ImageRecord object) {
				super.OnSelectChanged(object);
	
				updateValid();
			};
		};
		
		users=userList;
		
		updateWordsList("");
		updateValid();
		
		item.setTitle("Rechercher");
		form.setItems(item);
		
		item.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				
				if(event.getValue()!=null)
					updateWordsList(event.getValue().toString());
				else
					updateWordsList("");

				updateValid();
			}
		});
		
		valid.setIconSize(50);
		valid.setIcon("check.png");
		
		valid.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) 
			{
				selectedUser = (UserDTO)userGrid.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				destroy();
			}
		});
		
		labelNothing.setContents("Aucun mot ne correspond à votre recherche");
		labelNothing.setHeight(40);
		
		layout.addMember(form);
		layout.addMember(labelNothing);
		layout.addMember(userGrid);
		layout.addMember(valid);
		addItem(layout);
	}
	
	private void updateValid()
	{
		valid.setVisible(userGrid.getSelectedItem()!=null);
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
	
}
