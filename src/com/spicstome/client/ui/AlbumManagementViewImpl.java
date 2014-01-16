package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.Set;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.ui.panel.ActionPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class AlbumManagementViewImpl extends UserViewImpl  implements AlbumManagementView
{

	ImageTileGrid imageList;
	ActionPanel actionPanel;
	
	public AlbumManagementViewImpl()
	{
		super();
		
		addCrumb(new Crumb("Les albums"){});

		actionPanel = new ActionPanel(true,false,true,true,true) {
			
			@Override
			public void onVisualize() {
				AlbumDTO a = (AlbumDTO)imageList.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumPlace(a.getId()));
				
			}
			
			@Override
			public void onNew() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onEdit() {
				
				AlbumDTO a = (AlbumDTO)imageList.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumEditPlace(a.getId()));
				
			}

			@Override
			public void onDelete() {
				// TODO Auto-generated method stub
				
			}
		};
		
		imageList = new ImageTileGrid(Mode.CLICK,200,150,100){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionPanel.setHiddenActionVisible(getSelectedItem()!=null);
				
			};
		};

		imageList.setHeight(250);

		mainPanel.addMember(imageList);
		mainPanel.addMember(actionPanel);

	}

	@Override
	public void setAlbums(Set<StudentDTO> list) {
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();

		for(StudentDTO student:list)
		{
			modules.add(new ImageRecord(student.getAlbum(),student.getFirstName()));
		}

		imageList.setItems(modules);
	}

}
