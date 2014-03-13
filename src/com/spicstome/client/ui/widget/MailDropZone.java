package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.syntax.state.SyntaxAnalyser;
import com.spicstome.client.ui.panel.ValidationPanel;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public abstract class MailDropZone extends VLayout{

	ImageTileGrid dropZone;
	private SyntaxAnalyser analyser;
	private int movementCount;
	HLayout horizontalLayout = new HLayout();
	Img drophere = new Img("drophere.gif");
	public ArrayList<WordDTO> message=new ArrayList<WordDTO>();
	ValidationPanel validationPanel = new ValidationPanel();

	public MailDropZone(int iconSize) {
		
		dropZone = new ImageTileGrid(Mode.DRAG_AND_DROP, iconSize+50, iconSize+30, iconSize){
			@Override
			public void OnRemove()
			{
				UpdateMail();
			}
			
			@Override
			public void OnDoubleClickImage(ImageRecord object)
			{
				dropZone.removeItem(object);
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
		
		

		drophere.setSize(60);
		drophere.setLayoutAlign(Alignment.CENTER);
		drophere.setPrompt("Glissez les mots dans ce bandeau");
		
		horizontalLayout.addMember(drophere);
		horizontalLayout.addMember(dropZone);

		horizontalLayout.setStyleName("bloc");

		
		
		addMember(horizontalLayout);
		addMember(validationPanel);
		
		setHeight(200);

		analyser = new SyntaxAnalyser();
	}
	

	public String getSentence()
	{
		return analyser.getSentence();
	}
	
	public void UpdateValidation(Boolean b,int nbGoodWords,int nbBadWords)
	{
		validationPanel.setVisible((nbGoodWords!=0)|| (nbBadWords!=0));	
		validationPanel.updateChecks(b,nbGoodWords,nbBadWords);
		onTextChange(b);
	}
	
	public void init()
	{
		movementCount=0;
		dropZone.clearItems();
		UpdateValidation(false,0,0);
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
			words.add(record);
			message.add(word);
		}
		
		analyser.init(words);
		analyser.analyse();
		UpdateValidation(analyser.currentState.acceptance,analyser.nbGoodWord,words.size()-analyser.nbGoodWord);
		
		dropZone.clearItems();
		dropZone.setItems(words);
		
		
		return words;

	}
	
	public void addImage(ImageRecord image)
	{
		dropZone.addItem(image);
		UpdateMail();
	}
	
	public int getMovementCount() {
		return movementCount;
	}
	
	public ArrayList<String> getCorrectedWords() {
		return analyser.getCorrectedWords();
	}
	
	public abstract void onTextChange(boolean isOK);
}
