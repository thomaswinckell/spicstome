package com.spicstome.client.syntax.state;

import java.util.ArrayList;

import com.spicstome.client.syntax.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageRecord;

public class TrashState extends SyntaxState{

	

	public TrashState(SyntaxAnalyser analyser) {
		super(false, analyser);
	}

	@Override
	public void check(ImageRecord record,int range,ArrayList<ImageRecord> arrayRecord) 
	{
		
	}

}
