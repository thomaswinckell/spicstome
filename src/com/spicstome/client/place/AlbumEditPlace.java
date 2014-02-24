package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AlbumEditPlace extends Place
{

	public long idAlbum;
	
	public AlbumEditPlace(long idAlbum) {
		this.idAlbum=idAlbum;
	}
	

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<AlbumEditPlace> {

		@Override
		public String getToken(AlbumEditPlace place) {
			return "Album:"+place.idAlbum;
		}

		@Override
		public AlbumEditPlace getPlace(String token) {
			
			String[] split = token.split(":");
			long id = Long.valueOf(split[1]);
		
			return new AlbumEditPlace(id);
			
		}

	}
	
}