package com.spicstome.client.ui.widget;



public class DropZone extends ImageTileGrid{

	public DropZone(int iconSize) {
		super(Mode.DRAG_AND_DROP, iconSize+20, iconSize+20, iconSize);
		
		setWidth100();
		setHeight(iconSize+100);
		setStyleName("bloc");
		removeOnDragOver();
	}

}
