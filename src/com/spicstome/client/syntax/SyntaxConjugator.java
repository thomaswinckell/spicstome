package com.spicstome.client.syntax;

public class SyntaxConjugator {
	
	public SyntaxVerb analyseSpecificGroup(String infinitif)
	{
		
		if(infinitif.endsWith("er"))
		{
			if(infinitif.endsWith("ger"))
				return new SyntaxVerb1stGroupGer(infinitif);
			else if(infinitif.endsWith("cer"))
				return new SyntaxVerb1stGroupCer(infinitif);
			else
				return new SyntaxVerb1stGroup(infinitif);
		}
		else if(infinitif.endsWith("ir"))
		{
			return new SyntaxVerb2ndGroup(infinitif);
		}
		
		return null;
	}
	
	public String conjugateVerb(String infinitif,int subjectNature,int subjectNumber)
	{
		if(subjectNumber == 0)
		{
			if(subjectNature==0)
				return analyseSpecificGroup(infinitif).conjugue1erePersonneSingulier();
			else if(subjectNature==1)
				return analyseSpecificGroup(infinitif).conjugue2emePersonneSingulier();
			else if(subjectNature==2)
				return analyseSpecificGroup(infinitif).conjugue3emePersonneSingulier();
			
		}
		else
		{
			if(subjectNature==0)
				return analyseSpecificGroup(infinitif).conjugue1erePersonnePluriel();
			else if(subjectNature==1)
				return analyseSpecificGroup(infinitif).conjugue2emePersonnePluriel();
			else if(subjectNature==2)
				return analyseSpecificGroup(infinitif).conjugue3emePersonnePluriel();
		}
		
		return "-";
	}
}
