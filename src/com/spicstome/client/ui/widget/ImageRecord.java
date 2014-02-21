package com.spicstome.client.ui.widget;

import com.smartgwt.client.widgets.tile.TileRecord;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.StudentDTO;

public class ImageRecord extends TileRecord {

	public final static String PICTURE_ID = "id";
	public final static String PICTURE_NAME = "name";
	public final static String PICTURE_PATH = "path";
	public final static String DATA = "data";

	public static enum others{HISTORY};
	
	public ImageRecord(int id,String name,String image){
		super();
		setAttribute(PICTURE_ID, id);
		setAttribute(PICTURE_NAME, name);
		setAttribute(PICTURE_PATH, image);

	}
	
	public ImageRecord(AlbumDTO albumDTO,String name)
	{
		super();
		setAttribute(PICTURE_ID, albumDTO.getId());
		setAttribute(PICTURE_NAME, name);
		setAttribute(PICTURE_PATH, "albumlogo.png");
		setAttribute(DATA, albumDTO);
	}
	
	public ImageRecord(others type,StudentDTO student)
	{
		super();
		
		if(type==others.HISTORY)
		{
			setAttribute(PICTURE_ID, student.getId());
			setAttribute(PICTURE_NAME, "Historique de "+student.getFirstName());
			setAttribute(PICTURE_PATH, "history.png");
			setAttribute(DATA, student);
		}
		
	}
	
	public ImageRecord(AlbumDTO albumDTO,StudentDTO student)
	{
		super();
		setAttribute(PICTURE_ID, albumDTO.getId());
		setAttribute(PICTURE_NAME, "Album de "+student.getFirstName());
		setAttribute(PICTURE_PATH, "albumlogo.png");
		setAttribute(DATA, albumDTO);
	}
	
	public ImageRecord(UserDTO userDTO)
	{
		super();
		
		setAttribute(PICTURE_ID, userDTO.getId());
		setAttribute(PICTURE_NAME, userDTO.getFirstName()+" "+userDTO.getName()+"<br/>"+userDTO.getEmail());
		setAttribute(PICTURE_PATH, "upload/"+userDTO.getImage().getFilename());
		setAttribute(DATA, userDTO);
	}
	
	public ImageRecord(WordDTO wordDTO)
	{
		super();
		
		setAttribute(PICTURE_ID, wordDTO.getId());
		setAttribute(PICTURE_NAME, wordDTO.getName());
		setAttribute(PICTURE_PATH, "upload/"+wordDTO.getImage().getFilename());
		setAttribute(DATA, wordDTO);
	}
	
	public ImageRecord(int id,String name,String image,Object o){
		super();
		setAttribute(PICTURE_ID, id);
		setAttribute(PICTURE_NAME, name);
		setAttribute(PICTURE_PATH, image);
		setAttribute(DATA, o);
	}
	

}
