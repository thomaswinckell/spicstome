package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.syntax.state.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MailDropZone extends VLayout{

	ImageTileGrid dropZone;
	private SyntaxAnalyser analyser;
	protected Img validImg = new Img("check.png");
	protected Label label = new Label();
	HLayout validationLayout = new HLayout();

	
	public MailDropZone(int iconSize) {
		
		dropZone = new ImageTileGrid(Mode.DRAG_AND_DROP, iconSize+50, iconSize+50, iconSize){
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
		dropZone.setHeight(iconSize+100);
		dropZone.setStyleName("bloc");
		dropZone.removeOnDragOver();
		
		validImg.setSize(30);
		label.setContents("BRAVO ! continue ainsi :)");
		label.setWidth(200);
		label.setMargin(10);
		validationLayout.addMember(validImg);
		validationLayout.addMember(label);
		
		addMember(dropZone);
		addMember(validationLayout);
		
		
		
		analyser = new SyntaxAnalyser();
	}
	
	public void UpdateValidation(Boolean b)
	{
		validationLayout.setVisible(b);
	}
	
	public void init()
	{
		dropZone.clearItems();
		UpdateValidation(false);
	}
	
	public void UpdateMail()
	{
		RecordList list = dropZone.getDataAsRecordList();
		ArrayList<ImageRecord> words = new ArrayList<ImageRecord>();
		
		for(int i=0;i<list.getLength();i++)
		{
			WordDTO word = (WordDTO)((ImageRecord)(list.get(i))).getAttributeAsObject(ImageRecord.DATA);
			ImageRecord record = new ImageRecord(word);
			record.setAttribute(ImageRecord.PICTURE_NAME, word.getName());
			words.add(record);
		}
		
		analyser.init(words);
		analyser.analyse();
		
		UpdateValidation(analyser.currentState.acceptance);
	
		dropZone.clearItems();
		
		dropZone.setItems(words);
		
		

	}
	
	

}
