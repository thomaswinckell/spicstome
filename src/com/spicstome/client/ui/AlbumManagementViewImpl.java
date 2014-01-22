package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;
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

	ImageTileGrid imageListStudentAlbum;
	ActionPanel actionPanelStudentAlbum;

	
	public AlbumManagementViewImpl()
	{
		super();
		
		addCrumb(new Crumb("Les albums"){});

		actionPanelStudentAlbum = new ActionPanel(false,false,true,true,false,false,false) {
			
			@Override
			public void onVisualize() {
				AlbumDTO a = (AlbumDTO)imageListStudentAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumPlace(a.getId()));
				
			}
			
			
			
			@Override
			public void onEdit() {
				
				AlbumDTO a = (AlbumDTO)imageListStudentAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumEditPlace(a.getId()));
				
			}

		
		};
		
	
		
		imageListStudentAlbum = new ImageTileGrid(Mode.CLICK,200,150,100){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionPanelStudentAlbum.setHiddenActionVisible(getSelectedItem()!=null);
				
			};
		};
		
	
		
		imageListStudentAlbum.setHeight(170);

		mainPanel.addMember(imageListStudentAlbum);
		mainPanel.addMember(actionPanelStudentAlbum);

	}

	

	@Override
	public void insertStudentAlbum(ArrayList<StudentDTO> list) {

		for(StudentDTO student:list)
		{
			imageListStudentAlbum.addItem(new ImageRecord(student.getAlbum(),student));
		}

	}

	@Override
	public void insertAlbum(List<AlbumDTO> list) {
		
		actionPanelStudentAlbum.setHiddenActionVisible(false);
		
		for(AlbumDTO album:list)
		{
			if(album.getId()==1)
				imageListStudentAlbum.addItem(new ImageRecord(album,"Album général"));
			if(album.getId()==2)
				imageListStudentAlbum.addItem(new ImageRecord(album,"Album exemple"));
		}
		
	}



	@Override
	public void initview() {
		
		imageListStudentAlbum.clearItems();
		
	}

	
}
