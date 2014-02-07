package com.spicstome.client.syntax;

import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.syntax.state.State0;
import com.spicstome.client.syntax.state.State1;
import com.spicstome.client.syntax.state.State2;
import com.spicstome.client.syntax.state.State3;
import com.spicstome.client.syntax.state.SyntaxState;
import com.spicstome.client.syntax.state.TrashState;
import com.spicstome.client.ui.widget.ImageRecord;

public class SyntaxAnalyser {

	public SyntaxState currentState;
	SyntaxConjugator conjugator;
	
	public State0 state0;
	public State1 state1;
	public State2 state2;
	public State3 state3;
	
	public TrashState trashState;
	
	public SyntaxAnalyser()
	{
		conjugator= new SyntaxConjugator();
		
		state0 = new State0(this);
		state1 = new State1(this);
		state2 = new State2(this);
		state3 = new State3(this);
		trashState = new TrashState(this);
		
		init();
	}
	
	public void init()
	{
		currentState = state0;
	}
	
	public void match(SubjectDTO subject,AdjectiveDTO adjective,ImageRecord recordAdjective)
	{
		String matchingAdjective = "";

		if(subject.getGender()==0)
		{
			if(subject.getNumber()==0)
				matchingAdjective=adjective.getMatching1();
			else
				matchingAdjective=adjective.getMatching2();
		}
		else
		{
			if(subject.getNumber()==0)
				matchingAdjective=adjective.getMatching3();
			else
				matchingAdjective=adjective.getMatching4();
		}

		recordAdjective.setAttribute(ImageRecord.PICTURE_NAME, matchingAdjective);

	}
	
	public void conjugate(SubjectDTO subject,VerbDTO verb,ImageRecord recordVerb)
	{
		String infinitif;
		if(verb.getNegation()==1)
		{
			String[] split = verb.getName().split(" ");
			infinitif = split[split.length-1];
		}
		else
		{
			infinitif = verb.getName();
		}
		
		String conjugateVerb = "";
			
		if(verb.getGroup()==0 || verb.getGroup()==1)
		{
			
			conjugateVerb = conjugator.conjugateVerb(infinitif, subject.getNature(),subject.getNumber());
		}	
		else
		{			
			if(subject.getNumber()==0)
			{

				if(subject.getNature()==0)
					conjugateVerb = verb.getIrregular1();
				else if(subject.getNature()==1)
					conjugateVerb = verb.getIrregular2();
				else if(subject.getNature()==2)
					conjugateVerb = verb.getIrregular3();	
			}
			else
			{
				if(subject.getNature()==0)
					conjugateVerb = verb.getIrregular4();
				else if(subject.getNature()==1)
					conjugateVerb = verb.getIrregular5();
				else if(subject.getNature()==2)
					conjugateVerb = verb.getIrregular6();
			}
		}	
		
		if(verb.getNegation()==1)
		{
			conjugateVerb = "ne "+ conjugateVerb +" pas";
		}
		
		
		
		recordVerb.setAttribute(ImageRecord.PICTURE_NAME, conjugateVerb);
			

	}
	

}
