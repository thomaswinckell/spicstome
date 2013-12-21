package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class Crumb extends HLayout{


	Label button = new Label();
	
	public Crumb(String s)
	{
		this.button=new Label(s);
		
		this.button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				onClickCrumb();
			}
		});
		
		
		setWidth(150);
		button.setWidth100();
		
		button.setStyleName("crumb");
		
		addMember(button);
	}
	
	public abstract void onClickCrumb();
}
