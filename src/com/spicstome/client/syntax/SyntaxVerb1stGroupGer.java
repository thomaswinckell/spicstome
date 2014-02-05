package com.spicstome.client.syntax;
/**
 * repr�sente un verbe comme : manger, ranger,...
 * 
 * */
public class SyntaxVerb1stGroupGer extends SyntaxVerb1stGroup
{

	public SyntaxVerb1stGroupGer(String infinitif) throws SyntaxVerbException
	{
		super(infinitif);
		char dernier;
		dernier = this.radical.charAt(this.radical.length()-1);
		//dernier = Character.toLowerCase(dernier);
		if (dernier != 'g') throw new SyntaxVerbException("ce n'est pas un verbe en \"ger\"");
	}

	public String terminaison1erePersonnePluriel()
	{
		// TODO Auto-generated method stub
		return "e"+super.terminaison1erePersonnePluriel();
	}

}
