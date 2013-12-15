package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AlbumPlace extends Place{
	
	public AlbumPlace() {
		
	}
	
	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<AlbumPlace> {

		@Override
		public String getToken(AlbumPlace place) {
			return "";
		}

		@Override
		public AlbumPlace getPlace(String token) {
			return new AlbumPlace();
		}

	}
}
