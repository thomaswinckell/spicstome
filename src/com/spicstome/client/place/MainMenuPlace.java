package com.spicstome.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MainMenuPlace extends Place
{

	
	public MainMenuPlace()
	{
		
	}


	public static class Tokenizer implements PlaceTokenizer<MainMenuPlace>
	{
		@Override
		public String getToken(MainMenuPlace place)
		{
			return "";
		}

		@Override
		public MainMenuPlace getPlace(String token)
		{
			return new MainMenuPlace();
		}
	}
	
}