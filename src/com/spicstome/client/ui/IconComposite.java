package com.spicstome.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class IconComposite extends Composite {

	VerticalPanel panel = new VerticalPanel();
	Label label;
	Image image;
	
	public IconComposite(String srcImage,String text) 
	{
		label = new Label(text);
		image = new Image(srcImage);
		
		panel.add(image);
		panel.add(label);
		
		label.setStyleName("titleIconComposite");
		
		initWidget(panel);
		
		setStyleName("iconComposite");
	}

	

}
