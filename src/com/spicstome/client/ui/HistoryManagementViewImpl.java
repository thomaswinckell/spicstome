package com.spicstome.client.ui;

import java.util.ArrayList;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.HistoryPlace;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;


public class HistoryManagementViewImpl extends UserViewImpl  implements HistoryManagementView
{

	
	ImageTileGrid imageListStudent;
	
	VLayout verticalLayoutStudent = new VLayout();
	
	Label labelStudent = new Label();

	protected int iconsize=40;
	protected IconButton buttonHistoryVisualize= new IconButton("");
	protected HLayout actionLayout = new HLayout();
	
	public HistoryManagementViewImpl()
	{
		super();
	
		
		addCrumb(new Crumb("Les historiques"){});
		
		
		buttonHistoryVisualize.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				StudentDTO s = (StudentDTO)imageListStudent.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new HistoryPlace(s.getId()));
				
			}
		});
		
		
		
	
		
		imageListStudent = new ImageTileGrid(Mode.CLICK,200,100,60){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionLayout.setVisible(getSelectedItem()!=null);
			};
		};
		
		buttonHistoryVisualize.setIcon("visualize.png");
		buttonHistoryVisualize.setIconSize(iconsize);
		buttonHistoryVisualize.setPrompt("Consulter l'historique");
		
		actionLayout.setHeight(iconsize);
		actionLayout.setWidth(iconsize);
		
		actionLayout.addMember(buttonHistoryVisualize);
		
		labelStudent.setContents("Historiques individuels");
		labelStudent.setHeight(20);
		labelStudent.setAlign(Alignment.CENTER);
		labelStudent.setStyleName("title");
		

		imageListStudent.setHeight(170);

		
		verticalLayoutStudent.addMember(imageListStudent);
		verticalLayoutStudent.addMember(actionLayout);
		
	
		verticalLayoutStudent.setStyleName("bloc");
		
	
		
	
		mainPanel.addMember(labelStudent);
		mainPanel.addMember(verticalLayoutStudent);
		
	}

	

	@Override
	public void insertStudentAlbum(ArrayList<StudentDTO> list) {

		actionLayout.setVisible(false);
		
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
