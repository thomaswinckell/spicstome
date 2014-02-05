package com.spicstome.client.syntax;


public abstract class SyntaxVerb
{
	protected String radical; /* vaut fr�m pour le verbe fr�mir, vaut mang pour le verbe manger...*/

	/*
	 * attention l'argument doit �tre le radical et non l'infinitif !
	 * */
	protected SyntaxVerb(String radical) {this.radical = radical.trim().toLowerCase();}

	/**
	 * @param infinitif: infinitif du verbe
	 * @param terminaisonInfinitif : terminaison du verbe � l'infinitif
	 * @param messageErreur : message d'erreur � incorporer � l'exception si l'exception est lanc�e
	 * @exception : lance une VerbeException si infinitif et terminaisonInfinitif sont incompatibles 
	 * 
	 * */
	protected SyntaxVerb( String infinitif, String terminaisonInfinitif, String messageErreur) // throws VerbeException
	{
		String infi, term;

		infi = infinitif.toLowerCase().trim();
		term = terminaisonInfinitif.toLowerCase().trim();
		if (!infi.endsWith(term)) 
			throw new SyntaxVerbException(messageErreur);
		int l1 = infi.length();
		int l2 = term.length();
		int l = l1 - l2;
		radical = infi.substring(0, l);
	}

	
	public String conjugue1erePersonneSingulier() 
	{
		return this.radical+this.terminaison1erePersonneSingulier();
	}
	
	public String conjugue2emePersonneSingulier() 
	{
		return this.radical+this.terminaison2emePersonneSingulier();
	}
	
	public String conjugue3emePersonneSingulier() 
	{
		return this.radical+this.terminaison3emePersonneSingulier();
	}


	public String conjugue1erePersonnePluriel() 
	{
		return this.radical+this.terminaison1erePersonnePluriel();
	}
	
	public String conjugue2emePersonnePluriel() 
	{
		return this.radical+this.terminaison2emePersonnePluriel();
	}

	public String conjugue3emePersonnePluriel() 
	{
		return this.radical+this.terminaison3emePersonnePluriel();
	}


	abstract public String terminaison1erePersonneSingulier();
	abstract public String terminaison2emePersonneSingulier();
	abstract public String terminaison3emePersonneSingulier();
	abstract public String terminaison1erePersonnePluriel();
	abstract public String terminaison2emePersonnePluriel();
	abstract public String terminaison3emePersonnePluriel();


}//Verbe
