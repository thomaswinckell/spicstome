package com.spicstome.client.ui;

import java.util.Set;
import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.StudentDTO;

public interface AlbumManagementView extends IsWidget{

	
	void setAlbums(Set<StudentDTO> list);
}
