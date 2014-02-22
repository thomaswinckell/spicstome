package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HistoryPlace extends Place
{
	public long idStudent;
	
	public HistoryPlace(long idStudent) {
		this.idStudent=idStudent;
	}
	

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<HistoryPlace> {

		@Override
		public String getToken(HistoryPlace place) {
			return "Student:"+place.idStudent;
		}

		@Override
		public HistoryPlace getPlace(String token) {
			String[] split = token.split(":");
			long id = Long.valueOf(split[1]);
		
			return new HistoryPlace(id);
		
		}

	}
	
}