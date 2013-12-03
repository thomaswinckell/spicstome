package com.spicstome.client.ui;

import java.util.ArrayList;



public class AlbumManagementViewImpl extends UserView implements AlbumManagementView
{
	Presenter listener;
	AlbumListItemSelection albumUser;
	
	public AlbumManagementViewImpl()
	{
		super();
		
		ArrayList<ItemSelection> modules = new ArrayList<ItemSelection>();
		modules.add(new ItemSelection("images/albumlogo.png","Album de Albert"));
		modules.add(new ItemSelection("images/albumlogo.png","Album de Maxime"));
		modules.add(new ItemSelection("images/albumlogo.png","Album de Robert"));
		modules.add(new ItemSelection("images/albumlogo.png","Album de Jean-Claude"));

		albumUser = new AlbumListItemSelection(modules);
		
		mainPanel.add(albumUser);
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener=listener;
		
	}

}
