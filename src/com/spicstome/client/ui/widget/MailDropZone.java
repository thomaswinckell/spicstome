package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.ImageDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.syntax.state.SyntaxAnalyser;
import com.spicstome.client.ui.tts.TextToSpeech;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MailDropZone extends VLayout{

	ImageTileGrid dropZone;
	private SyntaxAnalyser analyser;
	protected Img validImg = new Img("goodgame.png");
	protected Label labelGood = new Label();
	VLayout validationLayout = new VLayout();
	private int movementCount;
	HLayout horizontalLayout = new HLayout();
	Img drophere = new Img("drophere.gif");
	public ArrayList<WordDTO> message=new ArrayList<WordDTO>();
	private TextToSpeech textToSpeech = new TextToSpeech();
	protected IconButton speakButton = new IconButton("");

	public MailDropZone(int iconSize) {
		
		dropZone = new ImageTileGrid(Mode.DRAG_AND_DROP, iconSize+50, iconSize+30, iconSize){
			@Override
			public void OnRemove()
			{
				UpdateMail();
			}
			
			@Override
			public void OnDropOrReorder(WordDTO article)
			{
				UpdateMail();
			}
		};
		
		speakButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				textToSpeech.playMessage(analyser.getSentence());
			}			
		});
	
		
		dropZone.setWidth100();
		dropZone.setHeight(iconSize+100);
		dropZone.setStyleName("bloc");
		dropZone.removeOnDragOver();
		
		validationLayout.setWidth(220);
		validImg.setWidth(130);
		validImg.setHeight(90);
		labelGood.setContents("BRAVO ! continue ainsi !");
		labelGood.setStyleName("title");
		speakButton.setIcon("sound.png");
		speakButton.setIconSize(128);
		validationLayout.addMember(validImg);
		validationLayout.addMember(labelGood);
		validationLayout.addMember(speakButton);
		
		horizontalLayout.addMember(dropZone);
		horizontalLayout.addMember(validationLayout);

		drophere.setSize(60);
		drophere.setLayoutAlign(Alignment.CENTER);
		drophere.setPrompt("Glissez les mots dans le bandeau ci-dessous");
		
		addMember(drophere);
		addMember(horizontalLayout);
		
		
		
		analyser = new SyntaxAnalyser();
	}
	
	public void setDropZoneIconSize(int n)
	{
		dropZone.setTileSize(n);
	}
	
	public void UpdateValidation(Boolean b,int nbElement)
	{
		validationLayout.setVisible(b);
		
		if(b)
		{
			dropZone.setBackgroundColor("lightgreen");
		}
		else if(nbElement>0)
		{
			dropZone.setBackgroundColor("lightpink");
		}
		else
		{
			dropZone.setBackgroundColor("white");
		}
	}
	
	public void init()
	{
		movementCount=0;
		dropZone.clearItems();
		UpdateValidation(false,0);
		drophere.setVisible(true);
		message.clear();
		
	}
	
	public ArrayList<ImageRecord> UpdateMail()
	{
		movementCount++;
		
		drophere.setVisible(false);
	
		RecordList list = dropZone.getDataAsRecordList();
		ArrayList<ImageRecord> words = new ArrayList<ImageRecord>();
		message.clear();
		
		for(int i=0;i<list.getLength();i++)
		{
			WordDTO word = (WordDTO)((ImageRecord)(list.get(i))).getAttributeAsObject(ImageRecord.DATA);
			ImageRecord record = new ImageRecord(word);
			record.setAttribute(ImageRecord.PICTURE_NAME, word.getName());
			words.add(record);
			message.add(word);
		}
		
		analyser.init(words);
		analyser.analyse();
		
		UpdateValidation(analyser.currentState.acceptance,list.getLength());
	
		dropZone.clearItems();
		
		dropZone.setItems(words);
		
		return words;

	}
	
	public int getMovementCount() {
		return movementCount;
	}
	
	public ArrayList<String> getCorrectedWords() {
		return analyser.getCorrectedWords();
	}
}
