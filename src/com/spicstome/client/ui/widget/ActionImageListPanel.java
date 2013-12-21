package com.spicstome.client.ui.widget;


import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;


public abstract class ActionImageListPanel extends ImageListPanel{

	protected ImageRecord selectedRecord;
	
	protected ActionPanel actionPanel = new ActionPanel();
	
	protected HLayout ActionPanel = new HLayout();
	
	public ActionImageListPanel(Mode mode) {
		super(mode,200,150,100);
		

		actionPanel.getButtonView().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				onVisualize();
				
			}
		});
		
		
		
		addMember(actionPanel);
			
		setHeight(250);
	}

	@Override
	public void selectChanged(ImageRecord object) {
		if(object==selectedRecord)
		{
			tileGrid.deselectRecord(object);
			selectedRecord=null;
		}
		else
		{
			selectedRecord = object;
		}
		
		
		
		
		actionPanel.setActionVisible(selectedRecord!=null);
	}
	
	public abstract void onVisualize();


	
}
