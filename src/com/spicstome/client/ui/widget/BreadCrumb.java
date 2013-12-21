package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class BreadCrumb extends HLayout{

	ArrayList<Crumb> crumbs=new ArrayList<Crumb>();
	
	
	public BreadCrumb()
	{
		super();
		
	}
	
	public void addCrumb(Crumb crumb)
	{
		addMember(crumb);
		Label separator = new Label(">");
		separator.setWidth(30);
		addMember(separator);
	}
}



