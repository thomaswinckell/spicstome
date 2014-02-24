package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AlbumManagementPlace extends Place
{

	public AlbumManagementPlace() {
		
	}
	

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<AlbumManagementPlace> {

		@Override
		public String getToken(AlbumManagementPlace place) {
			return "albummanagement";
		}

		@Override
		public AlbumManagementPlace getPlace(String token) {
			return new AlbumManagementPlace();
		}

	}
	
}
