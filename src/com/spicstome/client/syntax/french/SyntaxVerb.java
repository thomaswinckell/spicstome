package com.spicstome.client.syntax.french;


public abstract class SyntaxVerb
{
	protected String radical; 

	protected SyntaxVerb(String radical) {this.radical = radical.trim().toLowerCase();}

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


}
