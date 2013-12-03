package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserView extends Composite {

	protected VerticalPanel mainPanel = new VerticalPanel(); 
	protected Label label = new Label("");
	
	public UserView() {
		mainPanel.add(label);
		mainPanel.setStyleName("mainPanel");
		initWidget(mainPanel);
	}

	public void setName(String name) {
		label.setText("User: ["+name+"]");
	}
}
