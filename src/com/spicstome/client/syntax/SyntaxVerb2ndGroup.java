package com.spicstome.client.syntax;

/**
 * 
 * repr�sente un verbe du 2�me groupe comme : finir, fr�mir,...
 * 
 * */
public class SyntaxVerb2ndGroup extends SyntaxVerb
{
	public SyntaxVerb2ndGroup(String infinitif)
	{
		super(infinitif,"ir", "verbe du 2�me groupe mal form�");
	}

	public String terminaison1erePersonneSingulier()
	{
		return "is";
	}


	@Override
	public String terminaison2emePersonneSingulier() 
	{
		return "is";
	}

	@Override
	public String terminaison3emePersonneSingulier() 
	{
		return "it";
	}
	
	@Override
	public String terminaison1erePersonnePluriel()
	{
		return "issons";
	}
	
	@Override
	public String terminaison2emePersonnePluriel() 
	{
		return "issez";
	}

	@Override
	public String terminaison3emePersonnePluriel()
	{
		return "issent";
	}


}
