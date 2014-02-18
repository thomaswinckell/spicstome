package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;


/**
 * 
 * 
 * @author Maxime
 *
 *Crumb = link int Breadcrumb
 *
 */

public abstract class Crumb extends HLayout{


	private Label label = new Label();
	
	public Crumb(String s)
	{
		this.label=new Label();
		
		this.label.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				onClickCrumb();
			}
		});
		
		
		setWidth(150);
		label.setWidth100();
		label.setShowHover(true);
		
		setCrumbTitle(s);
		
		label.setStyleName("crumb");
		
		addMember(label);
	}
	
	public void setCrumbTitle(String s)
	{
		this.label.setContents(s);
		this.label.setPrompt("Accéder à '"+s+"'");
	}
	
	public void onClickCrumb()
	{
		
	}
}
