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
	protected IconButton buttonCopy = new IconButton("");
	protected IconButton buttonMove = new IconButton("");
	
	
	private boolean readAction = false;
	private boolean createAction = false;
	private boolean editAction = false;
	private boolean deleteAction = false;
	private boolean importAction = false;
	private boolean copyAction = false;
	private boolean moveAction = false;
	
	protected HLayout hiddenPanel = new HLayout();
	
	public ActionPanel(int iconsize,boolean createAction,boolean importAction,boolean readAction
			,boolean editAction,boolean copyAction,boolean moveAction,boolean deleteAction)
	{
		super();
		
		this.readAction=readAction;
		this.editAction=editAction;
		this.deleteAction=deleteAction;
		this.importAction=importAction;
		this.createAction=createAction;
		this.copyAction=copyAction;
		this.moveAction=moveAction;
		
		buttonImport.setVisible(true);
		buttonNew.setVisible(createAction);
		buttonEdit.setVisible(false);
		buttonDelete.setVisible(false);	
		buttonRead.setVisible(false);
		buttonCopy.setVisible(false);
		buttonMove.setVisible(false);
		
		int nbAction=0;
		
		if(this.createAction)
		{
			buttonNew.setIcon("new.png");
			buttonNew.setIconSize(iconsize);
			nbAction++;
			
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
			nbAction++;
			
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
			nbAction++;
			
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
			nbAction++;
			
			buttonEdit.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onEdit();
					
				}
			});
		}
		
		if(this.copyAction)
		{
			buttonCopy.setIcon("copy.png");
			buttonCopy.setIconSize(iconsize);
			hiddenPanel.addMember(buttonCopy);
			nbAction++;
			
			buttonCopy.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onCopy();
					
				}
			});
		}
		
		if(this.moveAction)
		{
			buttonMove.setIcon("move.png");
			buttonMove.setIconSize(iconsize);
			hiddenPanel.addMember(buttonMove);
			nbAction++;
			
			buttonMove.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onMove();
					
				}
			});
		}
			
			
		if(this.deleteAction)
		{
			buttonDelete.setIcon("delete.png");
			buttonDelete.setIconSize(iconsize);
			hiddenPanel.addMember(buttonDelete);
			nbAction++;
			
			buttonDelete.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					
					onDelete();
					
				}
			});
		}
		

		hiddenPanel.setWidth100();
		
		if(createAction)
			addMember(buttonNew);
		if(importAction)
			addMember(buttonImport);
		
		addMember(hiddenPanel);
		
		
		setHeight(iconsize);
		setWidth(nbAction*iconsize);
	
	}
	


	public void setHiddenActionVisible(boolean b)
	{
		if(readAction)
			buttonRead.setVisible(b);	
		if(deleteAction)
			buttonDelete.setVisible(b);
		if(copyAction)
			buttonCopy.setVisible(b);
		if(moveAction)
			buttonMove.setVisible(b);
		if(editAction)
			buttonEdit.setVisible(b);
		
	}

	public void onVisualize(){};
	public void onEdit(){};
	public void onNew(){};
	public void onDelete(){};
	public void onImport(){};
	public void onCopy(){};
	public void onMove(){};

}
