package com.spicstome.client.ui;

import java.util.ArrayList;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.HistoryPlace;
import com.spicstome.client.ui.panel.ActionPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;


public class HistoryManagementViewImpl extends UserViewImpl  implements HistoryManagementView
{

	
	ImageTileGrid imageListStudent;
	
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
				StudentDTO s = (StudentDTO)imageListStudent.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new HistoryPlace(s.getId()));
			}

		};
		
	
		
		imageListStudent = new ImageTileGrid(Mode.CLICK,200,100,60){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionPanelStudentAlbum.setHiddenActionVisible(getSelectedItem()!=null);
				
			};
		};
		
	
		
		
		labelStudent.setContents("Historiques individuels");
		labelStudent.setHeight(20);
		labelStudent.setAlign(Alignment.CENTER);
		labelStudent.setStyleName("title");
		

		imageListStudent.setHeight(170);

		
		verticalLayoutStudent.addMember(imageListStudent);
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
			imageListStudent.addItem(new ImageRecord(ImageRecord.others.HISTORY, student));
		}

	}
	
	



	@Override
	public void init() {

		imageListStudent.clearItems();
	}

	
}
