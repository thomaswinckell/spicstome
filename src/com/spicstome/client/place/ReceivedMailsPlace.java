package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ReceivedMailsPlace extends Place{


	public ReceivedMailsPlace() {

	}

	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<ReceivedMailsPlace> {

		@Override
		public String getToken(ReceivedMailsPlace place) {
			return "receivedmails";
		}

		@Override
		public ReceivedMailsPlace getPlace(String token) {
			return new ReceivedMailsPlace();
		}

	}
}
