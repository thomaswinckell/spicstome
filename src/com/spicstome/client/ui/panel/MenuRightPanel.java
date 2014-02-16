package com.spicstome.client.ui.panel;

import java.util.ArrayList;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.MouseMoveEvent;
import com.smartgwt.client.widgets.events.MouseMoveHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class MenuRightPanel extends VLayout{

	Img img = new Img("details.png");
	ArrayList<IconButton> icons = new ArrayList<IconButton>();
	
	public MenuRightPanel()
	{
		setHeight100();
		img.setSize(60);
		img.setLayoutAlign(Alignment.CENTER);
		
		addMember(img);		
		setCanHover(true);
				
		addMouseMoveHandler(new MouseMoveHandler() {
			
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				
				setIconsVisible(true);
				
			}
		});
		
		addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				
				setIconsVisible(false);
				
			}
		});
	
	}
	public void addIcon(IconButton icon)
	{
		icons.add(icon);
		icon.setWidth100();
		addMember(icon);
	}
	
	
	public void setIconsVisible(boolean b)
	{
		if(b)
			setWidth(300);
		else
			setWidth(80);
		
		for (IconButton icon : icons) {
			icon.setVisible(b);
		}
	}
}
