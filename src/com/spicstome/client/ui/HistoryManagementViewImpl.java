package com.spicstome.client.ui;

import java.util.ArrayList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.ui.panel.ActionPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;


public class HistoryManagementViewImpl extends UserViewImpl  implements HistoryManagementView
{

	
	ImageTileGrid imageListStudentAlbum;
	
	HLayout verticalLayoutStudent = new HLayout();
	
	Label labelStudent = new Label();

	ActionPanel actionPanelStudentAlbum;
	
	public HistoryManagementViewImpl()
	{
		super();
	
		
		addCrumb(new Crumb("Les historiques"){});
		
		
		
		
		actionPanelStudentAlbum = new ActionPanel(50,false,false,true,false,false,false,false) {
			
			@Override
			public void onVisualize() {
				AlbumDTO a = (AlbumDTO)imageListStudentAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumPlace(a.getId()));
			}

		};
		
	
		
		imageListStudentAlbum = new ImageTileGrid(Mode.CLICK,200,100,60){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionPanelStudentAlbum.setHiddenActionVisible(getSelectedItem()!=null);
				
			};
		};
		
	
		
		
		labelStudent.setContents("Historiques individuels");
		labelStudent.setHeight(20);
		labelStudent.setAlign(Alignment.CENTER);
		labelStudent.setStyleName("title");
		

		imageListStudentAlbum.setHeight(170);

		
		verticalLayoutStudent.addMember(imageListStudentAlbum);
		verticalLayoutStudent.addMember(actionPanelStudentAlbum);
		
	
		verticalLayoutStudent.setStyleName("bloc");
		
	
		
	
		mainPanel.addMember(labelStudent);
		mainPanel.addMember(verticalLayoutStudent);
		
	}

	

	@Override
	public void insertStudentAlbum(ArrayList<StudentDTO> list) {

		actionPanelStudentAlbum.setHiddenActionVisible(false);
		
		for(StudentDTO student :list)
		{
			imageListStudentAlbum.addItem(new ImageRecord(ImageRecord.others.HISTORY, student));
		}

	}
	
	



	@Override
	public void init() {

		imageListStudentAlbum.clearItems();
	}

	
}
