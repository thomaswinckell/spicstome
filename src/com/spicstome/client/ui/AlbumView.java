package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.dto.AlbumDTO;

public interface AlbumView extends IsWidget{

	void setAlbum(AlbumDTO album);
}
