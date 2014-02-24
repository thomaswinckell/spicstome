package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.AlbumDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.place.AlbumEditPlace;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.ui.form.UserComboBoxItem;
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
	VLayout verticalLayoutStudent = new VLayout();
	VLayout verticalLayoutMain = new VLayout();
	Label labelStudent = new Label();
	Label labelMain = new Label();
	
	HLayout actionMainAlbumLayout = new HLayout();
	HLayout actionStudentAlbumLayout = new HLayout();
	
	HLayout horizontalLayout = new HLayout();
	
	protected int iconsize=40;
	
	protected IconButton buttonMainVisualize= new IconButton("");
	protected IconButton buttonMainEdit= new IconButton("");
	
	protected IconButton buttonStudentVisualize= new IconButton("");
	protected IconButton buttonStudentEdit= new IconButton("");
	
	
	public AlbumManagementViewImpl()
	{
		super();

		addCrumb(new Crumb("Les albums"){});
	
		imageListMainAlbum = new ImageTileGrid(Mode.CLICK,200,150,100){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	               actionMainAlbumLayout.setVisible(getSelectedItem()!=null);
	               buttonMainEdit.setVisible(admin);
			};
		};
		
		buttonMainVisualize.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				AlbumDTO a = (AlbumDTO)imageListMainAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumPlace(a.getId()));
				
			}
		});
		
		buttonMainEdit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				AlbumDTO a = (AlbumDTO)imageListMainAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumEditPlace(a.getId()));
				
			}
		});
		
		buttonStudentVisualize.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				AlbumDTO a = (AlbumDTO)imageListStudentAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumPlace(a.getId()));
				
			}
		});
		
		buttonStudentEdit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				AlbumDTO a = (AlbumDTO)imageListStudentAlbum.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				listener.goTo(new AlbumEditPlace(a.getId()));
				
			}
		});
		

		imageListStudentAlbum = new ImageTileGrid(Mode.CLICK,200,100,60){
			

			@Override
			public void OnSelectChanged(ImageRecord object) {

	                actionStudentAlbumLayout.setVisible(getSelectedItem()!=null);
	                buttonStudentEdit.setVisible(admin);
			};
		};
		
		
		buttonMainEdit.setIconSize(iconsize);
		buttonMainEdit.setIcon("edit.png");
		buttonMainEdit.setPrompt("Modifier cet album");
		buttonMainVisualize.setIconSize(iconsize);
		buttonMainVisualize.setIcon("visualize.png");
		buttonMainVisualize.setPrompt("Consulter cet album");
		
		buttonStudentEdit.setIconSize(iconsize);
		buttonStudentEdit.setIcon("edit.png");
		buttonStudentEdit.setPrompt("Modifier cet album");
		buttonStudentVisualize.setIconSize(iconsize);
		buttonStudentVisualize.setIcon("visualize.png");
		buttonStudentVisualize.setPrompt("Consulter cet album");
		
		actionMainAlbumLayout.setHeight(iconsize);
		actionMainAlbumLayout.setWidth(2*iconsize);
		actionStudentAlbumLayout.setHeight(iconsize);
		actionStudentAlbumLayout.setWidth(2*iconsize);
		
		actionMainAlbumLayout.addMember(buttonMainVisualize);
		actionMainAlbumLayout.addMember(buttonMainEdit);
		
		actionStudentAlbumLayout.addMember(buttonStudentVisualize);
		actionStudentAlbumLayout.addMember(buttonStudentEdit);
		
		labelMain.setContents("Albums généraux");
		labelMain.setHeight(20);
		labelMain.setAlign(Alignment.CENTER);
		labelMain.setStyleName("title");
		labelStudent.setContents("Albums des enfants");
		labelStudent.setHeight(20);
		labelStudent.setAlign(Alignment.CENTER);
		labelStudent.setStyleName("title");

		verticalLayoutMain.addMember(labelMain);
		verticalLayoutMain.addMember(imageListMainAlbum);
		verticalLayoutMain.addMember(actionMainAlbumLayout);

		verticalLayoutStudent.addMember(labelStudent);
		verticalLayoutStudent.addMember(imageListStudentAlbum);
		verticalLayoutStudent.addMember(actionStudentAlbumLayout);
		
		verticalLayoutMain.setHeight(400);
		verticalLayoutStudent.setHeight(400);
		
		verticalLayoutMain.setStyleName("bloc");
		verticalLayoutStudent.setStyleName("bloc");
		
		horizontalLayout.addMember(verticalLayoutMain);
		horizontalLayout.addMember(verticalLayoutStudent);
		
		
		
		mainPanel.addMember(horizontalLayout);

	
		
	}

	@Override
	public void insertStudentAlbum(ArrayList<StudentDTO> list) {

		actionStudentAlbumLayout.setVisible(false);
		
		for(StudentDTO student :list)
		{
			imageListStudentAlbum.addItem(new ImageRecord(student.getAlbum(), student));
		}

	}
	
	@Override
	public void insertMainAlbum(List<AlbumDTO> list) {

		actionMainAlbumLayout.setVisible(false);
		
		for(AlbumDTO album:list)
		{
			if(album.getId()==1)
				imageListMainAlbum.addItem(new ImageRecord(album,"Album général"));
			if(album.getId()==2)
				imageListMainAlbum.addItem(new ImageRecord(album,"Album exemple"));
		}
		
	}

	@Override
	public void init(boolean b) {
		
		super.init(b);
		
		actionMainAlbumLayout.setVisible(false);
		actionStudentAlbumLayout.setVisible(false);
		
		imageListMainAlbum.clearItems();
		imageListStudentAlbum.clearItems();
		
	}

}
