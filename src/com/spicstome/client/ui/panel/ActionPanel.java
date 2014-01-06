package com.spicstome.client.ui.panel;

import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class ActionPanel extends HLayout{

	protected IconButton buttonNew = new IconButton("");
	protected IconButton buttonImport = new IconButton("");
	
	protected IconButton buttonRead = new IconButton("");
	protected IconButton buttonEdit = new IconButton("");
	protected IconButton buttonDelete = new IconButton("");
	
	
	private boolean readAction = false;
	private boolean createAction = false;
	private boolean editAction = false;
	private boolean deleteAction = false;
	private boolean importAction = false;
	
	
	protected HLayout hiddenPanel = new HLayout();
	
	public ActionPanel(boolean createAction,boolean importAction,boolean readAction
			,boolean editAction,boolean deleteAction)
	{
		super();
		
		this.readAction=readAction;
		this.editAction=editAction;
		this.deleteAction=deleteAction;
		this.importAction=importAction;
		this.createAction=createAction;
		
		buttonImport.setVisible(true);
		buttonNew.setVisible(true);
		buttonEdit.setVisible(false);
		buttonDelete.setVisible(false);	
		buttonRead.setVisible(false);
		
		int iconsize=40;
		
		if(this.createAction)
		{
			buttonNew.setIcon("new.png");
			buttonNew.setIconSize(iconsize);
			
			buttonNew.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onNew();
					
				}
			});
		}
		
		if(this.importAction)
		{
			buttonImport.setIcon("import.png");
			buttonImport.setIconSize(iconsize);
			
			
			buttonImport.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onImport();
					
				}
			});
		}
	
		
		if(this.readAction)
		{	
			buttonRead.setIcon("visualize.png");
			buttonRead.setIconSize(iconsize);
			hiddenPanel.addMember(buttonRead);
			
			buttonRead.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onVisualize();
					
				}
			});
			
		}
		
		if(this.editAction)
		{
			buttonEdit.setIcon("edit.png");
			buttonEdit.setIconSize(iconsize);
			hiddenPanel.addMember(buttonEdit);
			
			buttonEdit.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onEdit();
					
				}
			});
		}
			
		if(this.deleteAction)
		{
			buttonDelete.setIcon("delete.png");
			buttonDelete.setIconSize(iconsize);
			hiddenPanel.addMember(buttonDelete);
			
			buttonDelete.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onDelete();
					
				}
			});
		}
		

		hiddenPanel.setWidth(3*iconsize);
		
		if(createAction)
			addMember(buttonNew);
		if(importAction)
			addMember(buttonImport);
		
		addMember(hiddenPanel);
		
		
		setHeight(iconsize);
		setWidth(5*iconsize);
	
	}
	


	public void setHiddenActionVisible(boolean b)
	{
		if(readAction)
			buttonRead.setVisible(b);
		if(createAction)
			buttonEdit.setVisible(b);	
		if(deleteAction)
			buttonDelete.setVisible(b);
	}

	public void onVisualize(){};
	public void onEdit(){};
	public void onNew(){};
	public void onDelete(){};
	public void onImport(){};

}
