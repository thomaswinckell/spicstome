package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;

public interface AlbumManagementView extends IsWidget{

	
	void insertStudentAlbum(ArrayList<StudentDTO> list);
	void insertMainAlbum(List<AlbumDTO> list);
	void init(boolean b);
	
	public interface Presenter 
	{
	
	}
}
