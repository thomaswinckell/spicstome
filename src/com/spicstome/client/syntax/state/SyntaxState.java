package com.spicstome.client.syntax.state;

import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;

/**
 * A state have an acceptance ( good or bad) 
 * it checks a word and infer if it is appropriate or not.
 */

public abstract class SyntaxState {

	public boolean acceptance;
	protected SyntaxAnalyser analyser;

	public SyntaxState(boolean acceptance,SyntaxAnalyser analyser)
	{
		this.acceptance=acceptance;
		this.analyser=analyser;
	}

	public String conjugueVerb(SubjectDTO subject,VerbDTO verb)
	{
		analyser.currentState=analyser.stateVerb;
		return analyser.conjugueVerb(subject, verb);
	}
	
	public  abstract String check(WordDTO word,int range);
}
