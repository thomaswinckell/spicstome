package com.spicstome.client.ui;

import java.util.ArrayList;

import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.place.HistoryManagementPlace;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.place.UsersManagementPlace;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MainMenuViewImpl extends UserViewImpl implements MainMenuView {
	
	ImageTileGrid imageList;
	
	
	public MainMenuViewImpl() {
		super();

		imageList = new ImageTileGrid(Mode.CLICK,250,150,100){
			@Override
			public void OnSelectChanged(ImageRecord object) {
				
				redirect(object.getAttributeAsInt(ImageRecord.PICTURE_ID));
			}
		};

		imageList.setHeight(200);

		mainPanel.addMember(imageList);
	}
	
	public void redirect(int i)
	{
		switch(type)
		{
		case ADMIN:
			switch(i) {
			case 0 :
				goTo(new AlbumManagementPlace());
				break;
			case 1 :
				goTo(new UsersManagementPlace());
				break;
			case 2 :
				goTo(new MailPlace());
				break;
			}
		break;
		case REFERENT:
			switch(i) {
			case 0 :
				goTo(new AlbumManagementPlace());
				break;
			case 1 :
				goTo(new MailPlace());
				break;
			case 2 :
				goTo(new HistoryManagementPlace());
				break;
			}
		break;
		case TEACHER:
			switch(i) {
			case 0 :
				goTo(new AlbumManagementPlace());
			break;
			case 1 :
				goTo(new MailPlace());
			break;
			}
		break;
		case STUDENT:
			switch(i) {
			case 0 :
				goTo(new MailPlace());
				break;
			}
			break;
		}
	}

		
	
	
	@Override
	public void init(userType type)
	{
		
		super.init(type);
		
		ArrayList<ImageRecord> modules = new ArrayList<ImageRecord>();
		
		if(type==userType.ADMIN)
		{
			modules.add(new ImageRecord(0,"Gérer les albums","albumlogo.png"));
			modules.add(new ImageRecord(1,"Gérer les utilisateurs","userlogo.png"));
			modules.add(new ImageRecord(2,"Mail","mail.png"));
		}
		else if(type==userType.STUDENT)
		{
			modules.add(new ImageRecord(0,"Mail","mail.png"));
		}
		else if(type==userType.REFERENT)
		{
			modules.add(new ImageRecord(0,"Gérer les albums","albumlogo.png"));
			modules.add(new ImageRecord(1,"Mail","mail.png"));
			modules.add(new ImageRecord(2,"Historique","history.png"));
		}
		else if(type==userType.TEACHER)
		{
			modules.add(new ImageRecord(0,"Voir les albums","albumlogo.png"));
			modules.add(new ImageRecord(1,"Mail","mail.png"));
		}
		
		imageList.clear();
		imageList.setItems(modules);
	}

	

	
}
