package com.spicstome.client.ui;

import com.google.gwt.place.shared.Place;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.ui.panel.MenuTopPanel;
import com.spicstome.client.ui.widget.Crumb;

public class UserViewImpl extends VLayout implements UserView{

	
	protected VLayout mainPanel = new VLayout();
	protected Presenter listener;
	protected MenuTopPanel connectPanel;
	
	public UserViewImpl() {
		
		connectPanel=new MenuTopPanel(this);
		
		addMember(connectPanel);
		addMember(mainPanel);
		
		setWidth100();
		
		mainPanel.setStyleName("mainPanel");
	}
	
	public void goTo(Place place)
	{
		listener.goTo(place);
	}
	
	public void addCrumb(Crumb crumb)
	{
		this.connectPanel.breadcrumb.addCrumb(crumb);
	}

	public void setName(String name) {
		connectPanel.setNameUser(name);
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener=listener;
		
	}
}
