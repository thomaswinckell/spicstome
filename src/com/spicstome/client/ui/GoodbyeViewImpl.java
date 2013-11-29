package com.spicstome.client.ui;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class GoodbyeViewImpl extends Composite implements GoodbyeView
{

	SimplePanel viewPanel = new SimplePanel(); 
	String name;
	Button button = new Button("");

	public GoodbyeViewImpl()
	{
		viewPanel.add(button);
		initWidget(viewPanel);
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
		button.setText("coucou "+name);
	}
	
	
}
