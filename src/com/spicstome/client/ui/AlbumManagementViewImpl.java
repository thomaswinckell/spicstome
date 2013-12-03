package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;



public class AlbumManagementViewImpl extends UserView implements AlbumManagementView
{
	Presenter listener;
	CRUDListItem albumUser;
	ChoiceListItem choice;
	
	public AlbumManagementViewImpl()
	{
		super();
		
		ArrayList<Item> modules = new ArrayList<Item>();
		modules.add(new Item("images/albumlogo.png","Album de Albert"));
		modules.add(new Item("images/albumlogo.png","Album de Maxime"));
		modules.add(new Item("images/albumlogo.png","Album de Robert"));
		modules.add(new Item("images/albumlogo.png","Album de Jean-Claude"));

		albumUser = new CRUDListItem(modules);
		//albumUser.setMultiSelection(true);
		//choice = new ChoiceListItem(modules);
		
		
		
		mainPanel.add(albumUser);
		//mainPanel.add(choice);
		
		/*
		choice.buttonSelect.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//choice.selectionList
				
			}
		});*/
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener=listener;
		
	}

}
