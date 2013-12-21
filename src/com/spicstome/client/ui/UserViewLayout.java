package com.spicstome.client.ui;

import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import com.google.gwt.place.shared.Place;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.MenuPanel;



public class UserViewLayout extends VLayout implements UserView{

	
	protected VLayout mainPanel = new VLayout();
	protected Presenter listener;
	protected MenuPanel connectPanel;
	
	public UserViewLayout() {
		
		connectPanel=new MenuPanel(this);
		
	
		
		
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
