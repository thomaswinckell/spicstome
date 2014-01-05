package com.spicstome.client.ui;


import java.util.ArrayList;
import java.util.List;

import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.widget.ActionPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;



public class AlbumManagementViewImpl extends UserViewLayout  implements AlbumManagementView
{
	
	

	ImageTileGrid imageList;
	ActionPanel actionPanel;
	
	public AlbumManagementViewImpl()
	{
		super();
		
		addCrumb(new Crumb("Les albums"){});
		
		
		
		
		actionPanel = new ActionPanel(true) {
			
			@Override
			public void onVisualize() {
				
				listener.goTo(new AlbumPlace());
				
			}
			
			@Override
			public void onNew() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onEdit() {
				listener.goTo(new AlbumEditPlace());
				
			}

			@Override
			public void onDelete() {
				// TODO Auto-generated method stub
				
			}
		};
		
		imageList = new ImageTileGrid(Mode.CLICK,200,150,100){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionPanel.setActionVisible(getSelectedItem()!=null);
				
			};
		};

		imageList.setHeight(250);
		
		

		mainPanel.addMember(imageList);
		mainPanel.addMember(actionPanel);
		
		setAlbum(null);

	}

	@Override
	public void setAlbum(List<Album> list) {
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		modules.add(new ImageRecord(0,"Album general","albumlogo.png"));
		modules.add(new ImageRecord(1,"Album exemple","albumlogo.png"));
		modules.add(new ImageRecord(2,"Album de Albert","albumlogo.png"));
		
		imageList.setItems(modules);
	}



	

}
