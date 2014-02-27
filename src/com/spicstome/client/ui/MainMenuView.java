package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.spicstome.client.ui.UserViewImpl.userType;


public interface MainMenuView extends IsWidget
{
	public void init(userType type);
}