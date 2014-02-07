package com.spicstome.client.ui.widget;

import java.util.ArrayList;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.dto.ArticleDTO;
import com.spicstome.client.syntax.SyntaxAnalyser;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class MailDropZone extends HLayout{

	ImageTileGrid dropZone;
	private SyntaxAnalyser analyser;
	protected IconButton valid = new IconButton("");
	
	public MailDropZone(int iconSize) {
		
		dropZone = new ImageTileGrid(Mode.DRAG_AND_DROP, iconSize+50, iconSize+50, iconSize){
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
		};
	
		
		dropZone.setWidth100();
		dropZone.setHeight(iconSize+100);
		dropZone.setStyleName("bloc");
		dropZone.removeOnDragOver();
		
		valid.setIconSize(50);
		valid.setIcon("check.png");
		
		addMember(dropZone);
		addMember(valid);
		
		analyser = new SyntaxAnalyser();
	}
	
	public void UpdateValidation(Boolean b)
	{
		valid.setVisible(b);
	}
	
	public void UpdateMail()
	{
		RecordList list = dropZone.getDataAsRecordList();
		ArrayList<ImageRecord> articles = new ArrayList<ImageRecord>();
		
		for(int i=0;i<list.getLength();i++)
		{
			ArticleDTO article = (ArticleDTO)((ImageRecord)(list.get(i))).getAttributeAsObject(ImageRecord.DATA);
			ImageRecord record = new ImageRecord(article);
			record.setAttribute(ImageRecord.PICTURE_NAME, article.getName());
			articles.add(record);
		}
		
		analyser.init();
		
		for(int i=0;i<articles.size();i++)
		{
			analyser.currentState.check(articles.get(i),i,articles);
		}
		
		UpdateValidation(analyser.currentState.acceptance);
	
		dropZone.clearItems();
		
		dropZone.setItems(articles);
		
		

	}
	
	

}
