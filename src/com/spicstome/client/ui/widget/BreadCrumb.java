package com.spicstome.client.ui.widget;

import java.util.ArrayList;
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
		crumbs.add(crumb);
		addMember(crumb);
	}
}



