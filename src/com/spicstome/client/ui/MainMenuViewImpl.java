package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.spicstome.client.place.AlbumManagementPlace;

public class MainMenuViewImpl extends UserView implements MainMenuView
{
	Presenter listener;
	ArrayList<IconComposite> modules;
	HorizontalPanel modulesPanel = new HorizontalPanel();

	public MainMenuViewImpl()
	{
		super();

		modules = new ArrayList<IconComposite>();
		modules.add(new IconComposite("images/albumlogo.png","Gestion album"));
		modules.add(new IconComposite("images/userlogo.png","Gestion des utilisateurs"));
		

		modules.get(0).image.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
            	listener.goTo(new AlbumManagementPlace());
            }
        });
		
		for(int i=0;i<modules.size();i++)
		{
			
			
			modulesPanel.add(modules.get(i));
		}
		
		mainPanel.add(modulesPanel);
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener=listener;
		
	}

	
}
