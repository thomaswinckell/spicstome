package com.spicstome.client.syntax;

/**
 * 
 * repr�sente un verbe du 1er groupe comme : chanter, manger,...
 * 
 * */
public class SyntaxVerb1stGroup extends SyntaxVerb
{
	public SyntaxVerb1stGroup( String infinitif) // throws VerbeException
	{
		super( infinitif,  "er", "verbe du premier groupe mal form�");
	}


	/**
	 * @param infinitif
	 * @param terminaisonInfinitif
	 * @param messageErreur
	 */
	protected SyntaxVerb1stGroup(String infinitif, String terminaisonInfinitif,
			String messageErreur)
	{
		super(infinitif, terminaisonInfinitif, messageErreur);
	}


	public String terminaison1erePersonneSingulier()
	{
		return "e";
	}

	@Override
	public String terminaison2emePersonneSingulier() 
	{
		return "es";
	}


	@Override
	public String terminaison3emePersonneSingulier() 
	{
		return "e";
	}

	@Override
	public String terminaison1erePersonnePluriel()
	{
		return "ons";
	}
	
	@Override
	public String terminaison2emePersonnePluriel() 
	{
		return "ez";
	}


	@Override
	public String terminaison3emePersonnePluriel() 
	{
		return "ent";
	}

}
