package com.spicstome.client.ui.window;

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
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.NounDTO;
import com.spicstome.client.dto.PronounDTO;
import com.spicstome.client.dto.SubjectDTO;
import com.spicstome.client.dto.VerbDTO;
import com.spicstome.client.ui.form.ImageUploadForm;

/**
 * Word form window allow to insert information about a word ( adjective, verb, pronoun ...)
 */
public class WordFormWindow extends Window{
	
	VLayout verticalLayout = new VLayout();
	ImageUploadForm imageUploadForm = new ImageUploadForm(128, 128);
	IconButton buttonValidate = new IconButton("");
	TextItem nameDetail = new TextItem("name");

	public WordDTO word;
	
	Label labelType = new Label();
	
	RadioGroupItem radioGroupType = new RadioGroupItem();
	RadioGroupItem radioGroupGroup = new RadioGroupItem();
	CheckboxItem checkBoxFavorite = new CheckboxItem("favorite","Favoris");
	CheckboxItem checkBoxUncountable = new CheckboxItem("uncountable","Indénombrable ( 'de la ...' ou 'du ...')");
	
	HLayout verbLayout = new HLayout();
	
	public DynamicForm formWord = new DynamicForm();
	public DynamicForm formNormalVerb = new DynamicForm();
	public DynamicForm formIrregularVerb = new DynamicForm();
	public DynamicForm formSubject = new DynamicForm();
	public DynamicForm formAdjective = new DynamicForm();
	
	public DynamicForm formNoun = new DynamicForm();
	public DynamicForm formPronoun = new DynamicForm();
	
	RadioGroupItem radioGroupGender = new RadioGroupItem();
	RadioGroupItem radioGroupPerson = new RadioGroupItem();
	RadioGroupItem radioGroupNumber = new RadioGroupItem();
	
	TextItem matchingText1 = new TextItem("matching1");
	TextItem matchingText2 = new TextItem("matching2");
	TextItem matchingText3 = new TextItem("matching3");
	TextItem matchingText4 = new TextItem("matching4");
	
	CheckboxItem checkBoxNegation = new CheckboxItem("negation","Negation");
	
	TextItem irregularText1 = new TextItem("irregular1");
	TextItem irregularText2 = new TextItem("irregular2");
	TextItem irregularText3 = new TextItem("irregular3");
	TextItem irregularText4 = new TextItem("irregular4");
	TextItem irregularText5 = new TextItem("irregular5");
	TextItem irregularText6 = new TextItem("irregular6");
	
	public enum Mode{NEW, EDIT}
	public enum TypeWord{VERB,NOUN,ARTICLE,PRONOUN,ADJECTIVE}
	
	
	FolderDTO parent;
	
	TypeWord type;
	
	public WordFormWindow(Mode mode,final WordDTO wordDTO,FolderDTO parent)
	{
		setWidth(500);
		setHeight(550);
		
		this.parent=parent;

		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();

		setDismissOnOutsideClick(true);
		
		nameDetail.setHeight(20);    
		nameDetail.setTitle("Nom");
		
		
		/* Type */

		radioGroupType.setTitle("Type du mot");
		LinkedHashMap<Integer, String> mapRadioGroupType = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupType.put(0, "Nom (Arbre, Maman...)");
		mapRadioGroupType.put(1, "Pronom (Je, Tu ...)");
		mapRadioGroupType.put(2, "Article (le, la ...)");
		mapRadioGroupType.put(3,"Verbe");
		mapRadioGroupType.put(4,"Adjectif");
		radioGroupType.setValueMap(mapRadioGroupType);
		radioGroupType.setDefaultValue(0);
		
		radioGroupType.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				int choice = (Integer.valueOf(event.getValue().toString()));

				if(choice==0)
					type=TypeWord.NOUN;
				else if(choice==1)
					type=TypeWord.PRONOUN;
				else if(choice==2)
					type=TypeWord.ARTICLE;
				else if(choice==3)
					type=TypeWord.VERB;
				else
					type=TypeWord.ADJECTIVE;
				
				SetNewArticle();
				setFields();
				UpdateForm();
			}
		});
		

		formWord.setFields(nameDetail,checkBoxFavorite,radioGroupType);
		
		/* Subject */
		
		/* Gender */
		
		radioGroupGender.setTitle("Genre");
		LinkedHashMap<Integer, String> mapRadioGroupGender = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupGender.put(0, "Masculin");
		mapRadioGroupGender.put(1,"Féminin");
		radioGroupGender.setValueMap(mapRadioGroupGender);
		radioGroupGender.setDefaultValue(0);
		
		/* Number */
		
		radioGroupNumber.setTitle("Nombre");
		LinkedHashMap<Integer, String> mapRadioGroupNumber = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupNumber.put(0, "singulier");
		mapRadioGroupNumber.put(1,"pluriel");
		radioGroupNumber.setValueMap(mapRadioGroupNumber);
		radioGroupNumber.setDefaultValue(0);

		formSubject.setFields(radioGroupGender,radioGroupNumber);
		
		/* NOUN */
		
		formNoun.setFields(checkBoxUncountable);
		
		/* PRONOUN */
		
		/* Nature */
		
		radioGroupPerson.setTitle("Personne");
		LinkedHashMap<Integer, String> mapRadioGroupNature = new LinkedHashMap<Integer, String>(2);
		mapRadioGroupNature.put(0, "1ère");
		mapRadioGroupNature.put(1,"2ème");
		mapRadioGroupNature.put(2,"3ème");
		radioGroupPerson.setValueMap(mapRadioGroupNature);
		radioGroupPerson.setDefaultValue(0);
		
		formPronoun.setFields(radioGroupPerson);
		
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

		formNormalVerb.setFields(checkBoxNegation,radioGroupGroup);
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
		buttonValidate.setPrompt("Valider");
		buttonValidate.setIcon("check.png");
		buttonValidate.setLayoutAlign(Alignment.CENTER);
		buttonValidate.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				/* Common part */
				word.setName(nameDetail.getValueAsString());
				word.getImage().setFilename(imageUploadForm.getImageFileName());
				
				word.setFavorite((checkBoxFavorite.getValueAsBoolean()?1:0));
				
				if((type==TypeWord.NOUN) || (type==TypeWord.PRONOUN) || (type==TypeWord.ARTICLE))
				{
					SubjectDTO subj = (SubjectDTO)word;
					
					subj.setGender(Integer.valueOf(radioGroupGender.getValue().toString()));
					subj.setNumber(Integer.valueOf(radioGroupNumber.getValue().toString()));
					
					
					if(type==TypeWord.NOUN)
					{
						NounDTO noun = (NounDTO) word;
						noun.setUncountable((checkBoxUncountable.getValueAsBoolean()?1:0));
					}			
					else if(type==TypeWord.PRONOUN)
					{
						PronounDTO pronoun = (PronounDTO)word;
						pronoun.setPerson(Integer.valueOf(radioGroupPerson.getValue().toString()));
					}

					
				}
				else if(type==TypeWord.VERB)
				{
					VerbDTO verb = (VerbDTO)word;
					
					verb.setNegation((checkBoxNegation.getValueAsBoolean()?1:0));
					verb.setGroup(Integer.valueOf(radioGroupGroup.getValue().toString()));
					verb.setIrregular1(irregularText1.getValueAsString());
					verb.setIrregular2(irregularText2.getValueAsString());
					verb.setIrregular3(irregularText3.getValueAsString());
					verb.setIrregular4(irregularText4.getValueAsString());
					verb.setIrregular5(irregularText5.getValueAsString());
					verb.setIrregular6(irregularText6.getValueAsString());
				}
				else
				{
					AdjectiveDTO adjective=(AdjectiveDTO)word;
					adjective.setMatching1(matchingText1.getValueAsString());
					adjective.setMatching2(matchingText2.getValueAsString());
					adjective.setMatching3(matchingText3.getValueAsString());
					adjective.setMatching4(matchingText4.getValueAsString());
				}
				
				destroy();
				
			}
		});
		
		labelType.setHeight(10);
		imageUploadForm.getUploadButton().setWidth(200);
		imageUploadForm.getImage().setLayoutAlign(Alignment.CENTER);
		imageUploadForm.getUploadButton().setLayoutAlign(Alignment.CENTER);
		

		verticalLayout.setWidth(300);
		verticalLayout.setLayoutAlign(Alignment.CENTER);
		verticalLayout.setMargin(20);
		
	    verticalLayout.addMember(imageUploadForm.getImage());
	    verticalLayout.addMember(imageUploadForm.getUploadButton());
		verticalLayout.addMember(formWord);
		verticalLayout.addMember(labelType);
		verticalLayout.addMember(formSubject);
		verticalLayout.addMember(formNoun);
		verticalLayout.addMember(formPronoun);
		verticalLayout.addMember(formAdjective);
		verticalLayout.addMember(verbLayout);
		verticalLayout.addMember(buttonValidate);
		
		UpdateIrregular(0);
		
		if(mode==Mode.NEW)
		{
			type = TypeWord.NOUN;
			
			setTitle("Création d'un nouveau mot");
			
			SetNewArticle();
			
			labelType.setVisible(false);
				
		}
		else if(mode==Mode.EDIT)
		{
			if(wordDTO instanceof SubjectDTO)
			{
				if(wordDTO instanceof PronounDTO)
				{
					type=TypeWord.PRONOUN;
					labelType.setContents("Type : "+"pronom");
				}
				else if(wordDTO instanceof NounDTO)
				{
					type=TypeWord.NOUN;
					labelType.setContents("Type : "+"nom");
				}
				else if(wordDTO instanceof ArticleDTO)
				{
					type=TypeWord.ARTICLE;
					labelType.setContents("Type : "+"article");
				}
				
			}
			else if(wordDTO instanceof VerbDTO)
			{
				type=TypeWord.VERB;
				labelType.setContents("Type : "+"verbe");
			}
			else
			{
				type=TypeWord.ADJECTIVE;
				labelType.setContents("Type : "+"adjectif");
			}
	
			setTitle("Edition d'un mot");	
			
			radioGroupType.setVisible(false);
			
			labelType.setVisible(true);
			this.word=wordDTO;
			
		}
		
		setFields();
		UpdateForm();

		addItem(verticalLayout);
	}
	
	public void setFields()
	{
		
		nameDetail.setValue(word.getName());
		imageUploadForm.setImageFileName(word.getImage().getFilename());
		
		checkBoxFavorite.setValue(word.getFavorite()==1);
			
		if(word instanceof SubjectDTO)
		{
			SubjectDTO subj = (SubjectDTO)word;
			
			radioGroupGender.setValue(subj.getGender());
			radioGroupNumber.setValue(subj.getNumber());
			
			
			if(word instanceof NounDTO)
			{
				NounDTO noun = (NounDTO) word;
				checkBoxUncountable.setValue(noun.getUncountable()==1);
				
			}
			else if(word instanceof PronounDTO)
			{
				PronounDTO pronoun = (PronounDTO) word;
				radioGroupPerson.setValue(pronoun.getPerson());
			}
			
			
		}
		else if(word instanceof VerbDTO)
		{
			VerbDTO verb = (VerbDTO)word;
			checkBoxNegation.setValue(verb.getNegation()==1);
			radioGroupGroup.setValue(verb.getGroup());
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
			AdjectiveDTO adjective = (AdjectiveDTO)word;
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
		
		if(type==TypeWord.VERB)
		{
			this.word = new VerbDTO((long)-1,
					"Nouveau verbe",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					0,0,0,"","","","","","");
			
		}
		else if(type==TypeWord.NOUN)
		{
			this.word = new NounDTO((long)-1,
					"Nouveau nom",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					0,0,0,0);
		}
		else if(type==TypeWord.PRONOUN)
		{
			this.word = new PronounDTO((long)-1,
					"Nouveau pronom",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					0,0,0,0);
		}
		else if(type==TypeWord.ARTICLE)
		{
			this.word = new ArticleDTO((long)-1,
					"Nouvel article",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					0,0,0);
		}
		else
		{
			this.word = new AdjectiveDTO((long)-1,
					"Nouvel adjectif",
					order,
					parent,
					new ImageDTO((long) -1, "default_article.png"),
					0,"","","","");
		}
		
	}
	
	public void UpdateForm()
	{
		verbLayout.setVisible(type==TypeWord.VERB);
		formSubject.setVisible((type==TypeWord.NOUN) || (type==TypeWord.PRONOUN) || (type==TypeWord.ARTICLE));
		formPronoun.setVisible(type==TypeWord.PRONOUN);
		formNoun.setVisible(type==TypeWord.NOUN);
		formAdjective.setVisible(type==TypeWord.ADJECTIVE);
	}
	
}
