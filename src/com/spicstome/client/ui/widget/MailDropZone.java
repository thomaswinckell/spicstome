package com.spicstome.client.ui.widget;

import java.util.ArrayList;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.syntax.state.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public abstract class MailDropZone extends VLayout{

	ImageTileGrid dropZone;
	private SyntaxAnalyser analyser;
	protected Img validImg = new Img("goodgame.png");
	protected Label labelGood = new Label();
	VLayout validationLayout = new VLayout();
	private int movementCount;
	HLayout horizontalLayout = new HLayout();
	Img drophere = new Img("drophere.gif");
	public ArrayList<WordDTO> message=new ArrayList<WordDTO>();

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

		dropZone.setWidth100();
		dropZone.setHeight(iconSize+50);
		dropZone.removeOnDragOver();
		
		validationLayout.setWidth(220);
		validImg.setWidth(80);
		validImg.setHeight(70);
		labelGood.setContents("BRAVO ! continue ainsi !");
		labelGood.setStyleName("title");
		
		validationLayout.addMember(validImg);
		validationLayout.addMember(labelGood);

		drophere.setSize(60);
		drophere.setLayoutAlign(Alignment.CENTER);
		drophere.setPrompt("Glissez les mots dans ce bandeau");
		
		horizontalLayout.addMember(drophere);
		horizontalLayout.addMember(dropZone);
		horizontalLayout.addMember(validationLayout);

		horizontalLayout.setStyleName("bloc");

		addMember(horizontalLayout);

		analyser = new SyntaxAnalyser();
	}
	

	public String getSentence()
	{
		return analyser.getSentence();
	}
	
	public void UpdateValidation(Boolean b)
	{
		validationLayout.setVisible(b);
		
		if(b)
		{
			dropZone.setBackgroundColor("lightgreen");
		}
		else
		{
			dropZone.setBackgroundColor("white");
		}
		
		onTextChange(b);
	}
	
	public void init()
	{
		movementCount=0;
		dropZone.clearItems();
		UpdateValidation(false);
		drophere.setVisible(true);
		message.clear();
		
	}
	
	public ArrayList<ImageRecord> UpdateMail()
	{
		movementCount++;

		RecordList list = dropZone.getDataAsRecordList();
		drophere.setVisible(list.getLength()==0);
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
		UpdateValidation(analyser.currentState.acceptance);
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
	
	public abstract void onTextChange(boolean isOK);
}
