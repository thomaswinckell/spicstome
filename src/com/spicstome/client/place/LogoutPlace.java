package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LogoutPlace extends Place
{

	public LogoutPlace() {
		
	}
	

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<LogoutPlace> {

		@Override
		public String getToken(LogoutPlace place) {
			return "logout";
		}

		@Override
		public LogoutPlace getPlace(String token) {
			return new LogoutPlace();
		}

	}
	
}
