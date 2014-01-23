package com.spicstome.client.ui.widget;

import java.util.List;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;
import com.smartgwt.client.widgets.events.DropCompleteEvent;
import com.smartgwt.client.widgets.events.DropCompleteHandler;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public abstract class ImageTileGrid extends TileGrid {

	public enum Mode{CLICK, DRAG, DRAG_AND_DROP}


	protected Mode mode;
	
	//	Only used for deselection 
	private ImageRecord old=null;
	

	public ImageTileGrid(Mode mode,int tileWidth,int tileHeight,int imageSize) {
	
		super();
		this.mode = mode;
		setShowEdges(false);
		setAnimateTime(200);
		setData(new ImageRecord[]{});
		setShowAllRecords(true);
		setTileMargin(2);
		setTileWidth(tileWidth);
		setTileHeight(tileHeight);
	
		DetailViewerField pictureField = new DetailViewerField(ImageRecord.PICTURE_PATH);  
		pictureField.setType("image");  

		pictureField.setImageSize(imageSize);	
		DetailViewerField nameField = new DetailViewerField(ImageRecord.PICTURE_NAME);

		setFields(pictureField, nameField);
		
		initComponent();
		
		/*
		 * All tilegrid can know that a tile is clicked
		 */
		
		addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				OnSelectChanged((ImageRecord)event.getRecord());
			}
		});
		
		
		
	}
	


	protected void initComponent(){
		switch (this.mode) {
		case CLICK :
			setCanReorderTiles(false);
			
			
			/* 
			 * A click on a already selected tile will deselect it
			 */
			addRecordClickHandler(new RecordClickHandler() {
				@Override
				public void onRecordClick(RecordClickEvent event) {
					
					ImageRecord object = (ImageRecord)event.getRecord();
					if(object==old) { deselectRecord(object); old=null;}
					else {old = object; }
					
				}
			});
			
			break;
		case DRAG:
			setCanDragTilesOut(true);
			setCanAcceptDroppedRecords(false);
			setDragDataAction(DragDataAction.COPY);
			setTileDragAppearance(DragAppearance.TARGET);
			setDragTrackerStyle("dragStyle");


			break;
		case DRAG_AND_DROP:
			setCanReorderTiles(true);  
			setCanDragTilesOut(true);
			setCanAcceptDroppedRecords(true);
			setDragDataAction(DragDataAction.MOVE);
			setTileDragAppearance(DragAppearance.TARGET);
			setDragTrackerStyle("dragStyle");
			
			addDropCompleteHandler(new DropCompleteHandler() {
				
				@Override
				public void onDropComplete(DropCompleteEvent event) {
					OnReorder();
					
				}
			});
			
			
			break;
		}
	}

	

	public void addItem(ImageRecord record){
		addData(record);
	}

	public void removeItem(ImageRecord record){
		removeData(record);
	}
	

	public ImageRecord getSelectedItem() {
		return (ImageRecord)getSelectedRecord();
		
	}

	public void setItems(List<ImageRecord> objects){
		ImageRecord[] pictures = new ImageRecord[objects.size()];
		for(int i=0; i<pictures.length; i++)
			pictures[i] = objects.get(i);
		setData(pictures);

	}

	public void clearItems(){
		setData(new ImageRecord[]{});
	}
	public void animShow(){
		animateShow(AnimationEffect.SLIDE);
	}
	public void animHide(){
		animateHide(AnimationEffect.SLIDE);
	}

	public void removeOnDragOver(){// update
		addDragStopHandler(new DragStopHandler() {
			public void onDragStop(DragStopEvent event) {
				if(event.getY() >= getAbsoluteTop() && event.getY() <= getAbsoluteTop() + getHeight())
					return;
				event.cancel();
				
				if(getSelectedRecord()!=null)
					removeData(getSelectedRecord());
			}
		});
	}
	
	public void OnReorder()
	{
		
	}

	public void OnSelectChanged(ImageRecord object)
	{
		
	}

}
