package com.spicstome.client.syntax;

/**
 * 
 * repr�sente un verbe dont l'infinitif se termine par "cer" comme lancer, tracer, ...
 * 
 * */
public class SyntaxVerb1stGroupCer extends SyntaxVerb1stGroup
{

	public SyntaxVerb1stGroupCer(String infinitif)
	{
		super(infinitif,"cer","ce n'est pas un verbe en \"cer\"");
	}

	@Override
	public String terminaison1erePersonneSingulier()
	{
		return "c"+super.terminaison1erePersonneSingulier();
	}
	
	@Override
	public String terminaison2emePersonneSingulier()
	{
		return "c"+super.terminaison2emePersonneSingulier();
	}
	
	@Override
	public String terminaison3emePersonneSingulier()
	{
		return "c"+super.terminaison3emePersonneSingulier();
	}
	
	@Override
	public String terminaison1erePersonnePluriel()
	{
		return "�"+super.terminaison1erePersonnePluriel();
	}

	public String terminaison2emePersonnePluriel()
	{
		return "c"+super.terminaison2emePersonnePluriel();
	}
	
	@Override
	public String terminaison3emePersonnePluriel()
	{
		return "c"+super.terminaison3emePersonnePluriel();
	}
	


}// VerbeEnCer
