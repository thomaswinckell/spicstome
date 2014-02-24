package com.spicstome.client.ui;

import com.google.gwt.place.shared.Place;



public interface UserView {
	
	
	void setPresenter(Presenter listener);
	public void init(boolean a);
	
	
	public interface Presenter
	{
		void goTo(Place place);
	}
}
