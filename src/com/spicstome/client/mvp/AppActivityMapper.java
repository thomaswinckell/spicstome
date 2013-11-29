package com.spicstome.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.activity.GoodbyeActivity;
import com.spicstome.client.activity.LoginActivity;
import com.spicstome.client.place.GoodbyePlace;
import com.spicstome.client.place.LoginPlace;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public AppActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		// This is begging for GIN
		System.out.println("Une place est demandé");
		if (place instanceof LoginPlace)
			return new LoginActivity((LoginPlace) place, clientFactory);
		else if (place instanceof GoodbyePlace)
			return new GoodbyeActivity((GoodbyePlace) place, clientFactory);

		return null;
	}

}
