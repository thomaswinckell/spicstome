package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpicsToMeComposite extends Composite{

	protected VerticalPanel mainPanel = new VerticalPanel(); 
	
	public SpicsToMeComposite() {
		mainPanel.setStyleName("mainPanel");
		initWidget(mainPanel);
	}
}
