package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.ui.form.UserComboBoxItem;
import com.spicstome.client.ui.panel.ActionPanel;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;


public class AlbumManagementViewImpl extends UserViewImpl  implements AlbumManagementView
{

	UserComboBoxItem combo;
	DynamicForm form = new DynamicForm();
	ImageTileGrid imageListStudentAlbum;
	ImageTileGrid imageListMainAlbum;
	HLayout verticalLayoutStudent = new HLayout();
	HLayout verticalLayoutMain = new HLayout();
	Label labelStudent = new Label();
	Label labelMain = new Label();
	ActionPanel actionPanelMainAlbum;
	ActionPanel actionPanelStudentAlbum;
	
	public AlbumManagementViewImpl()
	{
		super();
	
		
		addCrumb(new Crumb("Les albums"){});
		
		
		imageListMainAlbum = new ImageTileGrid(Mode.CLICK,200,150,100){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionPanelMainAlbum.setHiddenActionVisible(getSelectedItem()!=null);
				
			};
		};
		
		actionPanelMainAlbum = new ActionPanel(50,false,false,true,true,false,false,false) {
			
			@Override
			public void onVisualize() {
				AlbumDTO a = (AlbumDTO)imageListMainAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumPlace(a.getId()));
				
			}

			@Override
			public void onEdit() {
				
				AlbumDTO a = (AlbumDTO)imageListMainAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumEditPlace(a.getId()));
				
			}

		};
		
		actionPanelStudentAlbum = new ActionPanel(50,false,false,true,true,false,false,false) {
			
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
		
	
		
		imageListStudentAlbum = new ImageTileGrid(Mode.CLICK,200,100,60){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionPanelStudentAlbum.setHiddenActionVisible(getSelectedItem()!=null);
				
			};
		};
		
	
		
		labelMain.setContents("Albums généraux");
		labelMain.setHeight(20);
		labelMain.setAlign(Alignment.CENTER);
		labelMain.setStyleName("title");
		labelStudent.setContents("Albums des enfants");
		labelStudent.setHeight(20);
		labelStudent.setAlign(Alignment.CENTER);
		labelStudent.setStyleName("title");
		
		imageListMainAlbum.setWidth(700);
		imageListMainAlbum.setHeight(170);


		verticalLayoutMain.addMember(imageListMainAlbum);
		verticalLayoutMain.addMember(actionPanelMainAlbum);
		
		imageListStudentAlbum.setHeight(170);

		
		verticalLayoutStudent.addMember(imageListStudentAlbum);
		verticalLayoutStudent.addMember(actionPanelStudentAlbum);
		
		verticalLayoutMain.setStyleName("bloc");
		verticalLayoutStudent.setStyleName("bloc");
		
		verticalLayoutMain.setWidth(800);
		verticalLayoutMain.setLayoutAlign(Alignment.CENTER);
		
		mainPanel.addMember(labelMain);
		mainPanel.addMember(verticalLayoutMain);
		mainPanel.addMember(labelStudent);
		mainPanel.addMember(verticalLayoutStudent);
		
	}

	

	@Override
	public void insertStudentAlbum(ArrayList<StudentDTO> list) {

		imageListStudentAlbum.clearItems();
		
		actionPanelStudentAlbum.setHiddenActionVisible(false);
		
		for(StudentDTO student :list)
		{
			imageListStudentAlbum.addItem(new ImageRecord(student.getAlbum(), student));
		}

	}
	
	@Override
	public void insertMainAlbum(List<AlbumDTO> list) {
		
		imageListMainAlbum.clearItems();
		
		actionPanelMainAlbum.setHiddenActionVisible(false);
		
		for(AlbumDTO album:list)
		{
			if(album.getId()==1)
				imageListMainAlbum.addItem(new ImageRecord(album,"Album général"));
			if(album.getId()==2)
				imageListMainAlbum.addItem(new ImageRecord(album,"Album exemple"));
		}
		
	}

	
}
