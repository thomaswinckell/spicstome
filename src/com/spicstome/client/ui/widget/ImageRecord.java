package com.spicstome.client.ui.widget;


import com.smartgwt.client.widgets.tile.TileRecord;
import com.spicstome.client.dto.AlbumDTO;

public class ImageRecord extends TileRecord {

	public final static String PICTURE_ID = "id";
	public final static String PICTURE_NAME = "name";
	public final static String PICTURE_PATH = "path";
	public final static String DATA = "data";

	
	public ImageRecord(int id,String name,String image){
		super();
		setAttribute(PICTURE_ID, id);
		setAttribute(PICTURE_NAME, name);
		setAttribute(PICTURE_PATH, image);

	}
	
	public ImageRecord(AlbumDTO albumDTO)
	{
		setAttribute(PICTURE_ID, albumDTO.getId());
		setAttribute(PICTURE_NAME, "Album numéro "+albumDTO.getId().toString());
		setAttribute(PICTURE_PATH, "albumlogo.png");
		setAttribute(DATA, albumDTO);
	}
	
	public ImageRecord(int id,String name,String image,Object o){
		super();
		setAttribute(PICTURE_ID, id);
		setAttribute(PICTURE_NAME, name);
		setAttribute(PICTURE_PATH, image);
		setAttribute(DATA, o);
	}
	

}
