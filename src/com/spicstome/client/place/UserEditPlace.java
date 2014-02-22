package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * A place object representing a particular state of the UI. A Place can be converted to and from a
 * URL history token by defining a {@link PlaceTokenizer} for each {@link Place}, and the 
 * {@link PlaceHistoryHandler} automatically updates the browser URL corresponding to each 
 * {@link Place} in your app.
 */
public class UserEditPlace extends Place {
	
	private String idUser;

	public UserEditPlace(String idUser) {
		this.idUser = idUser;
	}
	
	public String getIdUser() {
		return idUser;
	}

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<UserEditPlace> {

		@Override
		public String getToken(UserEditPlace place) {
			if (place.getIdUser() == null)
				return "adduser";
			else
				return "edituser:"+place.getIdUser();
		}

		@Override
		public UserEditPlace getPlace(String token) {
			
			String [] s = token.split(":");
			
			if (s[0].equals("adduser"))
				return new UserEditPlace(null);
			else
				return new UserEditPlace(s[1]);
		}

	}
}
