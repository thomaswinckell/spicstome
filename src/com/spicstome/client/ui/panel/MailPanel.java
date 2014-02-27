package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.Date;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.ui.tts.TextToSpeech;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.MailDropZone;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public abstract class MailPanel extends VLayout{

	public AlbumBookPanel album;
	MailDropZone dropZone;
	long begin;
	String recipientMail;
	VLayout expandLayout = new VLayout();
	HLayout recipientLayout = new HLayout();
	public boolean expanded = false;
	ImageTileGrid bigMessageTileGrid;
	Label bigMessageLabel = new Label();
	Label labelTitle = new Label();
	IconButton imageRecipient = new IconButton("");
	SendingPanel sending;
	HLayout horizontalLayout = new HLayout();
	VLayout mainContent = new VLayout();
	MailMenuRightPanel menuRight;
	private TextToSpeech textToSpeech = new TextToSpeech();
	
	public MailPanel()
	{
		album = new AlbumBookPanel(new Book(100));  
	        
    	dropZone = new MailDropZone(album.book.imageSize){

			@Override
			public void onTextChange(boolean isOK) {
				
				sending.setSpeakVisible(isOK);
				
			}
    		
    	};
    	menuRight = new MailMenuRightPanel(this);
    	sending = new SendingPanel(){

			@Override
			public void onSend() {
				
				if(dropZone.message.size()>0)
				{
					long now = System.currentTimeMillis()-begin;
					int seconds = (int) (now/1000);
					
					LogDTO logDTO = new LogDTO((long)-1, null, recipientMail, new Date(),
							dropZone.message.size(),
							seconds,dropZone.getMovementCount());
					
					onSendMail(recipientMail,dropZone.message,  dropZone.getCorrectedWords(), logDTO);
				}
				else
				{
					SC.warn("Le message est vide");
				}
				
			}

			@Override
			public void onSpeak() {
				
				textToSpeech.playMessage(dropZone.getSentence());
				
			}
    		
    	};
    	
    	int bigSize = 300;
    	bigMessageTileGrid = new ImageTileGrid(Mode.CLICK,bigSize+50,bigSize+50,bigSize){};
    	bigMessageTileGrid.setHeight(bigSize+60);
    	
    	bigMessageLabel.setStyleName("bigMessage");
    	bigMessageLabel.setLeft(100);
    	bigMessageLabel.setRight(100);
    	
    	expandLayout.addMember(bigMessageTileGrid);
    	expandLayout.addMember(bigMessageLabel);
    	 
    	imageRecipient.setIconSize(100);
    	labelTitle.setHeight(40);
    	labelTitle.setWidth(300);
    	labelTitle.setContents("Que veux tu dire à cette personne ?");
    	labelTitle.setStyleName("title");
    	
    	recipientLayout.addMember(imageRecipient);
    	recipientLayout.addMember(labelTitle);
    	
    	
    	mainContent.addMember(recipientLayout);
    	mainContent.addMember(album);
    	mainContent.addMember(dropZone);
    	mainContent.addMember(sending);
    
    	
    	horizontalLayout.addMember(expandLayout);
        horizontalLayout.addMember(mainContent);
        horizontalLayout.addMember(menuRight);
        
        addMember(horizontalLayout);
		
		
		
	}
	
	public void setStudent(StudentDTO student)
	{
		album.setStudent(student);

		menuRight.init();
		sending.init();
	}
	
	public void startLog()
	{
		begin = System.currentTimeMillis();
	}
	
	public void init()
	{
		dropZone.init();
		expand(false);	
		setImageRecipient("default_user.png");
	}
	
	public void expand(boolean b)
	{
		expanded=b;

		onExpand(b);
		
		expandLayout.setVisible(expanded);
		mainContent.setVisible(!expanded);
		
		if(expanded)
		{
			ArrayList<ImageRecord> words = dropZone.UpdateMail();
			ArrayList<ImageRecord> copyWords = new ArrayList<ImageRecord>();
			for(ImageRecord record:words)
			{
				ImageRecord copyRecord = new ImageRecord(record);
				copyRecord.setAttribute(ImageRecord.PICTURE_NAME, "");
				copyWords.add(copyRecord);
			}
				
			
			bigMessageTileGrid.setItems(copyWords);
			bigMessageLabel.setContents(dropZone.getSentence());
		}
		
	}
	
	public void setRecipientMail(String mail)
	{
		recipientMail=mail;
	}
	
	public void setImageRecipient(String filename)
	{
		imageRecipient.setIcon("upload/"+filename);
	}
	
	
	public abstract void onSendMail(String recipient,ArrayList<WordDTO> words,ArrayList<String> correctedWords,LogDTO log);
	public abstract void onExpand(boolean b);
}
