package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class BreadCrumb extends HLayout{

	ArrayList<Crumb> crumbs;
	
	public BreadCrumb()
	{
		super();
		crumbs = new ArrayList<Crumb>();
	}
	
	public void addCrumb(Crumb crumb)
	{
		if(crumbs.size()>0)
		{
			//Label separator = new Label(">");
			//separator.setWidth(30);
			//addMember(separator);
		}
		
		crumbs.add(crumb);
	
		addMember(crumb);
	}
}



