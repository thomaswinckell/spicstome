package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AlbumPlace extends Place {
	  
	/**
	 * Sample property (stores token). 
	 */
	private String name;

	public AlbumPlace(String token) {
		this.name = token;
	}

	public String getName() {
		return name;
	}

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<AlbumPlace> {

		@Override
		public String getToken(AlbumPlace place) {
			return place.getName();
		}

		@Override
		public AlbumPlace getPlace(String token) {
			return new AlbumPlace(token);
		}

	}
}
