package com.spicstome.client.ui.widget;


import com.smartgwt.client.widgets.tile.TileRecord;

public class ImageRecord extends TileRecord {

	public final static String PICTURE_ID = "id";
	public final static String PICTURE_NAME = "name";
	public final static String PICTURE_PATH = "path";


	
	public ImageRecord(int id,String name,String image){
		super();
		setAttribute(PICTURE_ID, id);
		setAttribute(PICTURE_NAME, name);
		setAttribute(PICTURE_PATH, image);
	
	}
	
	public long getID(){
		return getAttributeAsLong(PICTURE_ID);
	}

}
