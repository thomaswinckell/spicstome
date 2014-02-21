package com.spicstome.client.ui;

import java.util.ArrayList;
import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.StudentDTO;

public interface HistoryManagementView extends IsWidget{

	
	void insertStudentAlbum(ArrayList<StudentDTO> list);
	void init();
	
	public interface Presenter 
	{
	
	}
}
