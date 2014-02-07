package com.spicstome.client.syntax.french;

public class SyntaxFrenchManager {
	
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
	
	public String conjugate(int subjectNature,int subjectNumber,
			String verbName,int verbNegation,int verbGroup,
			String irregular1,String irregular2,String irregular3,
			String irregular4,String irregular5,String irregular6)
	{
		String infinitif;
		if(verbNegation==1)
		{
			String[] split = verbName.split(" ");
			infinitif = split[split.length-1];
		}
		else
		{
			infinitif = verbName;
		}
		
		String conjugateVerb = "";
			
		if(verbGroup==0 || verbGroup==1)
		{
			
			conjugateVerb = conjugateVerb1stOr2ndGroup(infinitif, subjectNature,subjectNumber);
		}	
		else
		{			
			if(subjectNumber==0)
			{

				if(subjectNature==0)
					conjugateVerb = irregular1;
				else if(subjectNature==1)
					conjugateVerb = irregular2;
				else if(subjectNature==2)
					conjugateVerb = irregular3;	
			}
			else
			{
				if(subjectNature==0)
					conjugateVerb = irregular4;
				else if(subjectNature==1)
					conjugateVerb = irregular5;
				else if(subjectNature==2)
					conjugateVerb = irregular6;
			}
		}	
		
		if(verbNegation==1)
		{
			conjugateVerb = "ne "+ conjugateVerb +" pas";
		}
		
		
		
		return conjugateVerb;
			

	}
	
	public String conjugateVerb1stOr2ndGroup(String infinitif,int subjectNature,int subjectNumber)
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
	
	public String match(int subjectGender,int subjectNumber,
			String matching1,String matching2,String matching3,String matching4)
	{
		String matchingAdjective = "";

		if(subjectGender==0)
		{
			if(subjectNumber==0)
				matchingAdjective=matching1;
			else
				matchingAdjective=matching2;
		}
		else
		{
			if(subjectNumber==0)
				matchingAdjective=matching3;
			else
				matchingAdjective=matching4;
		}

		return matchingAdjective;

	}
	
}
