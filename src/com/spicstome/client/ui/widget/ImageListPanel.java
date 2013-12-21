package com.spicstome.client.ui.widget;


import java.util.List;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public abstract class ImageListPanel extends VLayout {

	public enum Mode{READ_ONLY, CLICK, DRAG, DRAG_AND_DROP}

	protected TileGrid tileGrid = new TileGrid(); 
	protected Mode mode;	
	

	public ImageListPanel(Mode mode,int tileWidth,int tileHeight,int imageSize) {
		super();
		this.mode = mode;
		
		
		
		setHeight(200);
		
		setShowEdges(false);
		
		setAnimateTime(800);

		tileGrid.setData(new ImageRecord[]{});
		
		tileGrid.setShowAllRecords(true);
	
		tileGrid.setTileMargin(2);
		tileGrid.setTileWidth(tileWidth);
		tileGrid.setTileHeight(tileHeight);
		
		

		
		DetailViewerField pictureField = new DetailViewerField(ImageRecord.PICTURE_PATH);  
		pictureField.setType("image");  

		pictureField.setImageSize(imageSize);

		
		DetailViewerField nameField = new DetailViewerField(ImageRecord.PICTURE_NAME);

		tileGrid.setFields(pictureField, nameField);
		tileGrid.setTileDragAppearance(DragAppearance.TRACKER); 

		initComponent();
		
		addMember(tileGrid);
		
	}
	


	protected void initComponent(){
		switch (this.mode) {
		case CLICK :
			tileGrid.addRecordClickHandler(new RecordClickHandler() {
				@Override
				public void onRecordClick(RecordClickEvent event) {
					selectChanged((ImageRecord)event.getRecord());
				}
			});
			break;
		case DRAG:
			tileGrid.setCanDragTilesOut(true);
			tileGrid.setCanAcceptDroppedRecords(false);
			tileGrid.setDragDataAction(DragDataAction.COPY);
			break;
		case DRAG_AND_DROP:
			tileGrid.setCanReorderTiles(true);  
			tileGrid.setCanDragTilesOut(true);
			tileGrid.setCanAcceptDroppedRecords(true);
			tileGrid.setDragDataAction(DragDataAction.MOVE);
			removeOnDragOver();
			break;
		case READ_ONLY: tileGrid.setCanReorderTiles(false);
		break;
		}
	}

	

	public void addItem(ImageRecord record){
		tileGrid.addData(record);
	}



	public ImageRecord getSelectedItem() {
		return (ImageRecord)tileGrid.getSelectedRecord();
		
	}

	public void setItems(List<ImageRecord> objects){
		ImageRecord[] pictures = new ImageRecord[objects.size()];
		for(int i=0; i<pictures.length; i++)
			pictures[i] = objects.get(i);
		tileGrid.setData(pictures);

	}

	public void clearItems(){
		tileGrid.setData(new ImageRecord[]{});
	}
	public void animShow(){
		animateShow(AnimationEffect.SLIDE);
	}
	public void animHide(){
		animateHide(AnimationEffect.SLIDE);
	}

	protected void removeOnDragOver(){// update
		tileGrid.addDragStopHandler(new DragStopHandler() {
			public void onDragStop(DragStopEvent event) {
				if(event.getY() >= tileGrid.getAbsoluteTop() && event.getY() <= tileGrid.getAbsoluteTop() + tileGrid.getHeight())
					return;
				event.cancel();
				tileGrid.removeData(tileGrid.getSelectedRecord());
			}
		});
	}

	public abstract void selectChanged(ImageRecord imageRecord);

}
