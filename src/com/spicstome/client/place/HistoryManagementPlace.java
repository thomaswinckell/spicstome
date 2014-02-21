package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HistoryManagementPlace extends Place
{

	public HistoryManagementPlace() {
		
	}
	

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<HistoryManagementPlace> {

		@Override
		public String getToken(HistoryManagementPlace place) {
			return "historymanagement";
		}

		@Override
		public HistoryManagementPlace getPlace(String token) {
			return new HistoryManagementPlace();
		}

	}
	
}
