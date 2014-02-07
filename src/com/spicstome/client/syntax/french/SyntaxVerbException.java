package com.spicstome.client.syntax.french;

/**
 * erreur sur la classification d'un verbe lors de l'instantiation
 * 
 * */
public class SyntaxVerbException extends IllegalArgumentException
{

	private static final long serialVersionUID = 1L;

	public SyntaxVerbException()
	{
		super("erreur sur la classification d'un verbe lors de l'instantiation");
	}

	public SyntaxVerbException(String message)
	{
		super(message);
	}

}
