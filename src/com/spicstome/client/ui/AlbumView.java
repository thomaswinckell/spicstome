package com.spicstome.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.FolderDTO;

public interface AlbumView extends IsWidget{

	void setAlbum(AlbumDTO album);
	void setFolders(List<FolderDTO> folders);
}
