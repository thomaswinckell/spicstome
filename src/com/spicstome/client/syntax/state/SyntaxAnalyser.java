package com.spicstome.client.syntax.state;

import java.util.ArrayList;

import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.syntax.french.SyntaxFrenchManager;
import com.spicstome.client.ui.widget.ImageRecord;

public class SyntaxAnalyser {

	public SyntaxState currentState;
	public SyntaxFrenchManager syntaxFrenchManager;
	protected ArrayList<ImageRecord> arrayRecord;
	
	public State0 state0;
	public State1 state1;
	public State2 state2;
	public State3 state3;
	public State4 state4;
	public State5 state5;
	public State6 state6;
	public State7 state7;
	public State8 state8;
	
	public TrashState trashState;
	
	public SyntaxAnalyser()
	{
		syntaxFrenchManager= new SyntaxFrenchManager();
		
		state0 = new State0(this);
		state1 = new State1(this);
		state2 = new State2(this);
		state3 = new State3(this);
		state4 = new State4(this);
		state5 = new State5(this);
		state6 = new State6(this);
		state7= new State7(this);
		state8 = new State8(this);
		
		trashState = new TrashState(this);
		
		currentState = state0;
	}
	
	public void init(ArrayList<ImageRecord> arrayRecord)
	{
		currentState = state0;
		this.arrayRecord=arrayRecord;
	}

	public ArticleDTO extractArticle(int i)
	{
		return (ArticleDTO)arrayRecord.get(i).getAttributeAsObject(ImageRecord.DATA);
	}
	
	public String check(int range)
	{
		return currentState.check(extractArticle(range), range);
	}
	
	
}