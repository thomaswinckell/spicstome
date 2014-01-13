package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AlbumPlace extends Place
{
	public long idAlbum;
	
	public AlbumPlace(long idAlbum) {
		this.idAlbum=idAlbum;
	}
	

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<AlbumPlace> {

		@Override
		public String getToken(AlbumPlace place) {
			return "Album:"+place.idAlbum;
		}

		@Override
		public AlbumPlace getPlace(String token) {
			String[] split = token.split(":");
			long id = Long.valueOf(split[1]);
		
			return new AlbumPlace(id);
		
		}

	}
	
}