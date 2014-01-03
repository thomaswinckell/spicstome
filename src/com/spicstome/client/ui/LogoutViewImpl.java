package com.spicstome.client.ui;

import com.smartgwt.client.widgets.layout.VLayout;

public class LogoutViewImpl extends VLayout implements LogoutView
{
	private Presenter listener;

	public LogoutViewImpl()
	{
		
	}

	@Override
	public void setPresenter(Presenter listener)
	{
		this.listener = listener;
	}
}
