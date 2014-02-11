package com.spicstome.client.syntax.french;

public class SyntaxFrenchManager {
	

	public boolean canBeFollowedByVerb(String infinitif)
	{
		String[] list = {"adorer","descendre","laisser","rentrer","affirmer","désirer","monter",
					"retourner","aimer","détester","nier","revenir","aller","devoir","savoir",
					"apercevoir","écouter","paraître","sembler","assurer","emmener","partir",
					"sentir","entendre","penser","sortir","avouer","entrer","pouvoir","souhaiter",
					"compter","envoyer","préférer","venir","courir","espérer","prétendre",
					"voir","croire","faillir","vouloir","daigner","faire","reconnaître","déclarer",
					"falloir","regarder"};
		
		for(int i=0;i<list.length;i++)
		{
			if(list[i].equals(infinitif))
				return true;
		}
		
		return false;

	}
	
	public boolean canBeFollowedByAdjective(String infinitif)
	{
		String[] list = {"être", "devenir", "paraître", "sembler", "demeurer", "rester"};
		
		for(int i=0;i<list.length;i++)
		{
			if(list[i].equals(infinitif))
				return true;
		}
		
		return false;
	}
	
	public boolean goodArticle(int genderArticle,int numberArticle,int genderNoun,int numberNoun)
	{
		if((numberArticle==1)&& (numberNoun==1))
		{
			return true;
		}
		else
		{
			return ((numberArticle==numberNoun) && (genderArticle==genderNoun));
		}
	}
	
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
	
	public String getVerb(int verbNegation,String infinitif)
	{

		if(verbNegation==1)
		{
			String[] split = infinitif.split(" ");
			return split[split.length-1];
		}
		else
		{
			return infinitif;
		}
	
		
	
	}
	
	public String conjugate(int subjectPerson,int subjectNumber,
			String verbName,int verbNegation,int verbGroup,
			String irregular1,String irregular2,String irregular3,
			String irregular4,String irregular5,String irregular6)
	{
		
		String infinitif=getVerb(verbNegation,verbName);
	
		
		String conjugateVerb = "";
			
		if(verbGroup==0 || verbGroup==1)
		{
			
			conjugateVerb = conjugateVerb1stOr2ndGroup(infinitif, subjectPerson,subjectNumber);
		}	
		else
		{		
			
			if(subjectNumber==0 || subjectNumber==2)
			{

				if(subjectPerson==0)
					conjugateVerb = irregular1;
				else if(subjectPerson==1)
					conjugateVerb = irregular2;
				else if(subjectPerson==2)
					conjugateVerb = irregular3;	
			}
			else
			{
				if(subjectPerson==0)
					conjugateVerb = irregular4;
				else if(subjectPerson==1)
					conjugateVerb = irregular5;
				else if(subjectPerson==2)
					conjugateVerb = irregular6;
			}
		}	
		
		if(verbNegation==1)
		{
			conjugateVerb = negation(conjugateVerb)+ conjugateVerb +" pas";
		}
		
		
		
		return conjugateVerb;
			

	}
	
	public boolean isVoyel(char c)
	{
		return (c=='a') || (c=='e') || (c=='ê')|| (c=='é') || (c=='i') || (c=='o') || (c=='u');
	}
	
	public String conjugateVerb1stOr2ndGroup(String infinitif,int subjectPerson,int subjectNumber)
	{
		if(subjectNumber == 0 || subjectNumber == 2)
		{
			if(subjectPerson==0)
				return analyseSpecificGroup(infinitif).conjugue1erePersonneSingulier();
			else if(subjectPerson==1)
				return analyseSpecificGroup(infinitif).conjugue2emePersonneSingulier();
			else if(subjectPerson==2)
				return analyseSpecificGroup(infinitif).conjugue3emePersonneSingulier();
			
		}
		else
		{
			if(subjectPerson==0)
				return analyseSpecificGroup(infinitif).conjugue1erePersonnePluriel();
			else if(subjectPerson==1)
				return analyseSpecificGroup(infinitif).conjugue2emePersonnePluriel();
			else if(subjectPerson==2)
				return analyseSpecificGroup(infinitif).conjugue3emePersonnePluriel();
		}
		
		return "-";
	}
	
	public String negation(String next)
	{
		char firstChar = next.charAt(0);
		if(isVoyel(firstChar))
			return "n'";
		else
			return "ne ";
		
	}
	
	public String formatArticle(String article,String next)
	{
		char firstChar = next.charAt(0);
		
		if(article.equals("de la") && isVoyel(firstChar))
		{
			return "de l'";
		}
		else
			return article;
	}
	
	public String formatPronoun(String pronoun,String next)
	{
		if(pronoun.equals("je"))
		{
			char firstChar = next.charAt(0);
			if(isVoyel(firstChar))
				return "j'";
			else
				return "je";
		}
		else
			return pronoun;
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
