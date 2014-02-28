package com.spicstome.client.ui;

import com.google.gwt.place.shared.Place;
import com.spicstome.client.ui.UserViewImpl.userType;



public interface UserView {
	
	
	void setPresenter(Presenter listener);
	public void init(userType type);
	public void setMenuTopVisible(boolean b);
	public void setIsLoading(boolean b);

	
	public interface Presenter
	{
		void goTo(Place place);
	}
}
