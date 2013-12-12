package com.spicstome.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.shared.Album;

public interface AlbumManagementView extends IsWidget{

	
	void setAlbum(List<Album> list);
	
}
