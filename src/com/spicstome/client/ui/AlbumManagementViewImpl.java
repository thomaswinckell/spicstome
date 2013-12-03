package com.spicstome.client.ui;

public class AlbumManagementViewImpl extends UserView implements AlbumManagementView
{
	Presenter listener;
	
	public AlbumManagementViewImpl()
	{
		super();

	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener=listener;
		
	}

}
