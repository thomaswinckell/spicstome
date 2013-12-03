package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListItem extends Composite{

	VerticalPanel verticalPanel = new VerticalPanel();
	HorizontalPanel horizontalPanel = new HorizontalPanel();
	ArrayList<Item> list;
	ArrayList<Item> selectionList;
	boolean multiSelection=false;
	
	public ListItem(ArrayList<Item> list) {

		this.list=list;
		
		this.selectionList = new ArrayList<Item>();
		
		for (Item iconComposite : list) {
			
			horizontalPanel.add(iconComposite);
			iconComposite.setParent(this);

		}
		verticalPanel.add(horizontalPanel);
		
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		initWidget(verticalPanel);
	}
	
	public void setMultiSelection(boolean b)
	{
		multiSelection=b;
	}
	
	public void changeSelection(Item icon)
	{

			boolean change = !icon.check;
			
			if(multiSelection==false)
			{
				for (Item item : list){
					item.Check(false);
				}
				selectionList.clear();
			}
			
			icon.Check(change);
			
			if(icon.check)
				selectionList.add(icon);
			else
				selectionList.remove(icon);
	
	}
	
	
}
