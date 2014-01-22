package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;

public interface AlbumManagementView extends IsWidget{

	
	void setAlbums(ArrayList<StudentDTO> list);

	
	public interface Presenter 
	{
		void copy(AlbumDTO album);
	}
}
