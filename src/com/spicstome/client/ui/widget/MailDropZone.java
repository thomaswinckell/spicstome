package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.data.RecordList;
import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.syntax.SyntaxConjugator;

public class MailDropZone extends ImageTileGrid{

	private SyntaxConjugator syntaxConjugator;
	
	public MailDropZone(int iconSize) {
		super(Mode.DRAG_AND_DROP, iconSize+20, iconSize+50, iconSize);
		
		setWidth100();
		setHeight(iconSize+100);
		setStyleName("bloc");
		removeOnDragOver();
		
		syntaxConjugator = new SyntaxConjugator();
	}
	
	public void UpdateMail()
	{
		RecordList list = getDataAsRecordList();
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		ArticleDTO first=null;
		
		if(list.getLength()>0)
			first = (ArticleDTO)((ImageRecord)(list.get(0))).getAttributeAsObject(ImageRecord.DATA);
		
		
		
		for(int i=0;i<list.getLength();i++)
		{
			ArticleDTO article = (ArticleDTO)((ImageRecord)(list.get(i))).getAttributeAsObject(ImageRecord.DATA);
			if(article instanceof VerbDTO)
			{
				VerbDTO verb = (VerbDTO)article;
				
				String conjugateVerb = "";
				
				if(!(first==null) && first instanceof SubjectDTO)
				{
					SubjectDTO subjectDTO = (SubjectDTO)first;
								
					if(verb.getGroup()==0 || verb.getGroup()==1)
						conjugateVerb = syntaxConjugator.conjugateVerb(verb.getName(), subjectDTO.getNature(),subjectDTO.getNumber());
					else
					{			
						if(subjectDTO.getNumber()==0)
						{

							if(subjectDTO.getNature()==0)
								conjugateVerb = verb.getIrregular1();
							else if(subjectDTO.getNature()==1)
								conjugateVerb = verb.getIrregular2();
							else if(subjectDTO.getNature()==2)
								conjugateVerb = verb.getIrregular3();	
						}
						else
						{
							if(subjectDTO.getNature()==0)
								conjugateVerb = verb.getIrregular4();
							else if(subjectDTO.getNature()==1)
								conjugateVerb = verb.getIrregular5();
							else if(subjectDTO.getNature()==2)
								conjugateVerb = verb.getIrregular6();
						}
					}	
				}
				else
				{
					conjugateVerb = verb.getName();
				}
				
				ImageRecord verbRecord = new ImageRecord(verb);
				verbRecord.setAttribute(ImageRecord.PICTURE_NAME, conjugateVerb);
				articles.add(verbRecord);
			
				
			}
			else if(article instanceof AdjectiveDTO)
			{
				AdjectiveDTO adjective = (AdjectiveDTO)article;
				
				String matchingAdjective = "";
				
				if(!(first==null) && first instanceof SubjectDTO)
				{
					SubjectDTO subjectDTO = (SubjectDTO)first;
					
					if(subjectDTO.getGender()==0)
					{
						if(subjectDTO.getNumber()==0)
							matchingAdjective=adjective.getMatching1();
						else
							matchingAdjective=adjective.getMatching2();
					}
					else
					{
						if(subjectDTO.getNumber()==0)
							matchingAdjective=adjective.getMatching3();
						else
							matchingAdjective=adjective.getMatching4();
					}
					
				
				}
				else
				{
					matchingAdjective = adjective.getName();
				}
				
				ImageRecord verbRecord = new ImageRecord(adjective);
				verbRecord.setAttribute(ImageRecord.PICTURE_NAME, matchingAdjective);
				articles.add(verbRecord);
			}
			else
			{
				articles.add(new ImageRecord(article));
			}
		}
		
		clearItems();
		
		setItems(articles);
		
		

	}
	
	@Override
	public void OnRemove()
	{
		UpdateMail();
	}
	
	@Override
	public void OnDropOrReorder(ArticleDTO article)
	{
		UpdateMail();
	}

}
