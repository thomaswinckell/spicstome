package com.spicstome.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.spicstome.client.ClientFactory;
import com.spicstome.client.activity.AlbumManagementActivity;
import com.spicstome.client.activity.MainMenuActivity;
import com.spicstome.client.activity.LoginActivity;
import com.spicstome.client.activity.MainMenuLifeProjetActivity;
import com.spicstome.client.place.AlbumManagementPlace;
import com.spicstome.client.place.MainMenuLifeProjetPlace;
import com.spicstome.client.place.MainMenuPlace;
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
	public Activity getActivity(Place place) 
	{
		if (place instanceof LoginPlace)
			return new LoginActivity((LoginPlace) place, clientFactory);
		else if (place instanceof MainMenuPlace)
			return new MainMenuActivity((MainMenuPlace) place, clientFactory);
		else if (place instanceof AlbumManagementPlace)
			return new AlbumManagementActivity((AlbumManagementPlace) place, clientFactory);
		else if (place instanceof MainMenuLifeProjetPlace)
			return new MainMenuLifeProjetActivity((MainMenuLifeProjetPlace)place, clientFactory);
		return null;
	}

}
