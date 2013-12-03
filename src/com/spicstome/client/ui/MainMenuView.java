package com.spicstome.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * View interface. Extends IsWidget so a view impl can easily provide
 * its container widget. 
 * 
 * @author drfibonacci
 */
public interface MainMenuView extends IsWidget
{
	void setName(String name);
}