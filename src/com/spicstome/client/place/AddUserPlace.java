package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * A place object representing a particular state of the UI. A Place can be converted to and from a
 * URL history token by defining a {@link PlaceTokenizer} for each {@link Place}, and the 
 * {@link PlaceHistoryHandler} automatically updates the browser URL corresponding to each 
 * {@link Place} in your app.
 */
public class AddUserPlace extends Place {
	
	private String idUser;

	public AddUserPlace(String idUser) {
		this.idUser = idUser;
	}
	
	public String getIdUser() {
		return idUser;
	}

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<AddUserPlace> {

		@Override
		public String getToken(AddUserPlace place) {
			if (place.getIdUser() == null)
				return "adduser";
			else
				return "edituser:"+place.getIdUser();
		}

		@Override
		public AddUserPlace getPlace(String token) {
			
			String [] s = token.split(":");
			
			if (s[0].equals("adduser"))
				return new AddUserPlace(null);
			else
				return new AddUserPlace(s[1]);
		}

	}
}
