package com.spicstome.client.ui.form;

import java.util.HashSet;
import java.util.LinkedHashMap;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.AdjectiveDTO;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;

public class ArticleFormWindow extends Window{
	
	VLayout verticalLayout = new VLayout();
	ImageUploadForm imageUploadForm = new ImageUploadForm(128, 128);
	IconButton buttonValidate = new IconButton("");
	TextItem nameDetail = new TextItem("name");

	public ArticleDTO article;
	
	Label labelType = new Label();
	
	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	RadioGroupItem radioGroupVerbType = new RadioGroupItem();
	CheckboxItem checkBoxFavorite = new CheckboxItem("favorite","Favoris");
	
	HLayout verbLayout = new HLayout();
	
	public DynamicForm formArticle = new DynamicForm();
	public DynamicForm formNormalVerb = new DynamicForm();
	public DynamicForm formIrregularVerb = new DynamicForm();
	public DynamicForm formSubject = new DynamicForm();
	public DynamicForm formAdjective = new DynamicForm();
	
	RadioGroupItem radioGroupGender = new RadioGroupItem();
	RadioGroupItem radioGroupNature = new RadioGroupItem();
	RadioGroupItem radioGroupNumber = new RadioGroupItem();
	
	TextItem matchingText1 = new TextItem("matching1");
	TextItem matchingText2 = new TextItem("matching2");
	TextItem matchingText3 = new TextItem("matching3");
	TextItem matchingText4 = new TextItem("matching4");
	
	TextItem irregularText1 = new TextItem("irregular1");
	TextItem irregularText2 = new TextItem("irregular2");
	TextItem irregularText3 = new TextItem("irregular3");
	TextItem irregularText4 = new TextItem("irregular4");
	TextItem irregularText5 = new TextItem("irregular5");
	TextItem irregularText6 = new TextItem("irregular6");
	
	public enum Mode{NEW, EDIT}
	public enum TypeArticle{VERB,SUBJECT,ADJECTIVE}
	
	FolderDTO parent;
	
	TypeArticle type;
	
	public ArticleFormWindow(Mode mode,final ArticleDTO articleDTO,FolderDTO parent)
	{
		setWidth(500);
		setHeight(530);
		
		this.parent=parent;

		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();

		setDismissOnOutsideClick(true);
		
		nameDetail.setHeight(20);    
		nameDetail.setTitle("Nom");
		
		
		/* Type */

		radioGroupType.setTitle("Type de l'article");
		LinkedHashMap<Integer, String> mapRadioGroupType = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupType.put(0, "Sujet (nom,pronom)");
		mapRadioGroupType.put(1,"Verbe");
		mapRadioGroupType.put(2,"Adjectif");
		radioGroupType.setValueMap(mapRadioGroupType);
		radioGroupType.setDefaultValue(0);
		
		radioGroupType.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				int choice = (Integer.valueOf(event.getValue().toString()));

				if(choice==0)
					type=TypeArticle.SUBJECT;
				else if(choice==1)
					type=TypeArticle.VERB;
				else
					type=TypeArticle.ADJECTIVE;
				
				SetNewArticle();
				setFields();
				UpdateType();
			}
		});
	
		formArticle.setFields(nameDetail,checkBoxFavorite,radioGroupType);
		
		/* Subject */
		
		/* Gender */
		
		radioGroupGender.setTitle("Genre");
		LinkedHashMap<Integer, String> mapRadioGroupGender = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupGender.put(0, "Masculin");
		mapRadioGroupGender.put(1,"Féminin");
		radioGroupGender.setValueMap(mapRadioGroupGender);
		radioGroupGender.setDefaultValue(0);
		
		/* Nature */
		
		radioGroupNature.setTitle("Nature");
		LinkedHashMap<Integer, String> mapRadioGroupNature = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupNature.put(0, "1ère personne");
		mapRadioGroupNature.put(1,"2ème personne");
		mapRadioGroupNature.put(2,"3ème personne");
		radioGroupNature.setValueMap(mapRadioGroupNature);
		radioGroupNature.setDefaultValue(0);
		
		/* Number */
		
		radioGroupNumber.setTitle("Nombre");
		LinkedHashMap<Integer, String> mapRadioGroupNumber = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupNumber.put(0, "singulier");
		mapRadioGroupNumber.put(1,"pluriel");
		radioGroupNumber.setValueMap(mapRadioGroupNumber);
		radioGroupNumber.setDefaultValue(0);
		
		formSubject.setFields(radioGroupGender,radioGroupNature,radioGroupNumber);
		
		/* Verb */
		
		/* Group */
		radioGroupGroup.setTitle("Groupe du verbe");
		LinkedHashMap<Integer, String> mapRadioGroupGroup = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupGroup.put(0,"1er groupe");
		mapRadioGroupGroup.put(1,"2ème groupe");
		mapRadioGroupGroup.put(2,"3ème groupe");
		radioGroupGroup.setValueMap(mapRadioGroupGroup);
		radioGroupGroup.setDefaultValue(0);
		
		
		
		radioGroupGroup.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				int choice = (Integer.valueOf(event.getValue().toString()));
				
				UpdateIrregular(choice);

			}
		});
		
		/* Verb Type */
		radioGroupVerbType.setTitle("Type du verbe");
		LinkedHashMap<Integer, String> mapRadioGroupVerbType = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupVerbType.put(0,"Verbe d'action");
		mapRadioGroupVerbType.put(1,"Verbe de volonté");
		mapRadioGroupVerbType.put(2,"Verbe d'état");
		mapRadioGroupVerbType.put(3,"Verbe d'appréciation");
		radioGroupVerbType.setValueMap(mapRadioGroupVerbType);
		radioGroupVerbType.setDefaultValue(0);
		
		formNormalVerb.setFields(radioGroupVerbType,radioGroupGroup);
		formNormalVerb.setWidth(250);
		
		
		irregularText1.setTitle("1ère / singulier");
		irregularText2.setTitle("2ème / singulier");
		irregularText3.setTitle("3ème / singulier");
		irregularText4.setTitle("1ère / pluriel");
		irregularText5.setTitle("2ème / pluriel");
		irregularText6.setTitle("3ème / pluriel");
		
		formIrregularVerb.setFields(irregularText1,
				irregularText2,
				irregularText3,
				irregularText4,
				irregularText5,
				irregularText6);
		
		verbLayout.addMember(formNormalVerb);
		verbLayout.addMember(formIrregularVerb);
		
		verbLayout.setHeight(200);
		
		
		/* ADJECTIVE */
		
		matchingText1.setTitle("masculin singulier");
		matchingText2.setTitle("masculin pluriel");
		matchingText3.setTitle("féminin singulier");
		matchingText4.setTitle("féminin pluriel");
		
		formAdjective.setFields(matchingText1,
				matchingText2,
				matchingText3,
				matchingText4);
		
		buttonValidate.setIconSize(42);
		buttonValidate.setIcon("check.png");
		buttonValidate.setLayoutAlign(Alignment.CENTER);
		buttonValidate.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				/* Common part */
				article.setName(nameDetail.getValueAsString());
				article.getImage().setFilename(imageUploadForm.getImageFileName());
				
				article.setFavorite((checkBoxFavorite.getValueAsBoolean()?1:0));
				
				if(type==TypeArticle.SUBJECT)
				{
					SubjectDTO subj = (SubjectDTO)article;
					
					subj.setGender(Integer.valueOf(radioGroupGender.getValue().toString()));
					subj.setNumber(Integer.valueOf(radioGroupNumber.getValue().toString()));
					subj.setNature(Integer.valueOf(radioGroupNature.getValue().toString()));
				}
				else if(type==TypeArticle.VERB)
				{
					VerbDTO verb = (VerbDTO)article;
					
					verb.setGroup(Integer.valueOf(radioGroupGroup.getValue().toString()));
					verb.setType(Integer.valueOf(radioGroupVerbType.getValue().toString()));
					verb.setIrregular1(irregularText1.getValueAsString());
					verb.setIrregular2(irregularText2.getValueAsString());
					verb.setIrregular3(irregularText3.getValueAsString());
					verb.setIrregular4(irregularText4.getValueAsString());
					verb.setIrregular5(irregularText5.getValueAsString());
					verb.setIrregular6(irregularText6.getValueAsString());
				}
				else
				{
					AdjectiveDTO adjective=(AdjectiveDTO)article;
					adjective.setMatching1(matchingText1.getValueAsString());
					adjective.setMatching2(matchingText2.getValueAsString());
					adjective.setMatching3(matchingText3.getValueAsString());
					adjective.setMatching4(matchingText4.getValueAsString());
				}
				
				destroy();
				
			}
		});
		
		labelType.setHeight(10);
		
	    verticalLayout.addMember(imageUploadForm.getImage());
	    verticalLayout.addMember(imageUploadForm.getUploadButton());
		verticalLayout.addMember(formArticle);
		verticalLayout.addMember(labelType);
		verticalLayout.addMember(formSubject);
		verticalLayout.addMember(formAdjective);
		verticalLayout.addMember(verbLayout);
		verticalLayout.addMember(buttonValidate);
		
		UpdateIrregular(0);
		
		if(mode==Mode.NEW)
		{
			type = TypeArticle.SUBJECT;
			
			setTitle("Création d'un nouvel article");
			
			SetNewArticle();
			
			labelType.setVisible(false);
				
		}
		else if(mode==Mode.EDIT)
		{
			if(articleDTO instanceof SubjectDTO)
			{
				type=TypeArticle.SUBJECT;
				labelType.setContents("Type : "+"sujet");
			}
			else if(articleDTO instanceof VerbDTO)
			{
				type=TypeArticle.VERB;
				labelType.setContents("Type : "+"verbe");
			}
			else
			{
				type=TypeArticle.ADJECTIVE;
				labelType.setContents("Type : "+"adjectif");
			}
	
			setTitle("Edition d'un article");	
			
			radioGroupType.setVisible(false);
			
			labelType.setVisible(true);
			this.article=articleDTO;
			
		}
		
		setFields();
		UpdateType();
		
		
		
		addItem(verticalLayout);
	}
	
	public void setFields()
	{
		
		nameDetail.setValue(article.getName());
		imageUploadForm.setImageFileName(article.getImage().getFilename());
		
		checkBoxFavorite.setValue(article.getFavorite()==1);
		
			
		if(article instanceof SubjectDTO)
		{
			SubjectDTO subj = (SubjectDTO)article;
			
			radioGroupGender.setValue(subj.getGender());
			radioGroupNature.setValue(subj.getNature());
			radioGroupNumber.setValue(subj.getNumber());
			
		}
		else if(article instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO)article;
			radioGroupGroup.setValue(verb.getGroup());
			radioGroupVerbType.setValue(verb.getType());
			irregularText1.setValue(verb.getIrregular1());
			irregularText2.setValue(verb.getIrregular2());
			irregularText3.setValue(verb.getIrregular3());
			irregularText4.setValue(verb.getIrregular4());
			irregularText5.setValue(verb.getIrregular5());
			irregularText6.setValue(verb.getIrregular6());
			
			UpdateIrregular(verb.getGroup());
		}
		else
		{
			AdjectiveDTO adjective = (AdjectiveDTO)article;
			matchingText1.setValue(adjective.getMatching1());
			matchingText2.setValue(adjective.getMatching2());
			matchingText3.setValue(adjective.getMatching3());
			matchingText4.setValue(adjective.getMatching4());
		}
	}
	
	public void UpdateIrregular(int c)
	{
		formIrregularVerb.setVisible(c==2);
	}
	
	public void SetNewArticle()
	{
		
		int order = parent.getContent().size();
		
		if(type==TypeArticle.VERB)
		{
			this.article = new VerbDTO((long)-1,
					"Nouveau verbe",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					new HashSet<LogDTO>(),0,0,0,"","","","","","");
			
		}
		else if(type==TypeArticle.SUBJECT)
		{
			this.article = new SubjectDTO((long)-1,
					"Nouveau sujet",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					new HashSet<LogDTO>(),0,0,0,0);
		}
		else
		{
			this.article = new AdjectiveDTO((long)-1,
					"Nouvel adjectif",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					new HashSet<LogDTO>(),0,"","","","");
		}
	}
	
	public void UpdateType()
	{
		verbLayout.setVisible(type==TypeArticle.VERB);
		formSubject.setVisible(type==TypeArticle.SUBJECT);
		formAdjective.setVisible(type==TypeArticle.ADJECTIVE);
	}
	
}
