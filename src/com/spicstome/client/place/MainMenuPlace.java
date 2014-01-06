package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MainMenuPlace extends Place
{

	public MainMenuPlace() {
	}


	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<MainMenuPlace> {

		@Override
		public String getToken(MainMenuPlace place) {
			return "mainmenu";
		}

		@Override
		public MainMenuPlace getPlace(String token) {
			return new MainMenuPlace();
		}

	}
	
}
