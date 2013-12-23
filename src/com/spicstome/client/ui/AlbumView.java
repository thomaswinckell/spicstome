package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.shared.Album;

public interface AlbumView extends IsWidget{

	void setAlbum(Album album,String mode);
}
