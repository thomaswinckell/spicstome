package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ModuleComposite extends Composite{

	VerticalPanel panel = new VerticalPanel();
	Label label;
	Image image;
	
	public ModuleComposite(String srcImage,String text) 
	{
		label = new Label(text);
		image = new Image(srcImage);
		
		panel.add(image);
		panel.add(label);
		
		label.setStyleName("titleModuleComposite");
		
		initWidget(panel);
		
		setStyleName("moduleComposite");
	}
	
	
}
