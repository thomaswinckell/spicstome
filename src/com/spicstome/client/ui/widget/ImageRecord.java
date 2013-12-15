package com.spicstome.client.ui.widget;


import com.smartgwt.client.widgets.tile.TileRecord;

public class ImageRecord extends TileRecord {

	public final static String PICTURE_ID = "picID";
	public final static String PICTURE_NAME = "name";
	public final static String PICURE_DESCRIPTION = "description";
	public final static String PICTURE_PATH = "path";


	
	public ImageRecord(int id,String name,String description,String image){
		super();
		setAttribute(PICTURE_ID, id);
		setAttribute(PICTURE_NAME, name);
		setAttribute(PICURE_DESCRIPTION, description);
		setAttribute(PICTURE_PATH, image);
	
	}
	
	public String getName() {
		return getAttribute(PICTURE_NAME);
	}
	public String getDescription() {
		return getAttribute(PICURE_DESCRIPTION);
	}
	public String getPicture() {
		return getAttribute(PICTURE_PATH);
	}
	public long getID(){
		return getAttributeAsLong(PICTURE_ID);
	}

}
