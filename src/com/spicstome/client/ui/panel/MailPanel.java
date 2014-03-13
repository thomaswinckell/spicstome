package com.spicstome.client.ui.panel;

import java.util.ArrayList;
import java.util.Date;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
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

	public InteractiveAlbumBookPanel album;
	MailDropZone dropZone;
	long begin;
	String recipientMail;
	Label recipientLabel = new Label();
	VLayout expandLayout = new VLayout();
	IconButton shrinkButton = new IconButton("");
	HLayout recipientLayout = new HLayout();
	HLayout receivedMailLayout = new HLayout();
	VLayout replyMailLayout = new VLayout();
	public boolean expanded = false;
	ImageTileGrid bigMessageTileGrid;
	Label bigMessageLabel = new Label();
	Label labelTitle = new Label();
	Label replyLabel = new Label();
	IconButton imageRecipient = new IconButton("");
	SendingPanel sending;
	HLayout horizontalLayout = new HLayout();
	VLayout mainContent = new VLayout();
	//MailMenuRightPanel menuRight;
	private TextToSpeech textToSpeech = new TextToSpeech();
	
	public MailPanel()
	{		
		Book book = new Book(100){
			
			@Override
			public void onDoubleClickImage(ImageRecord image)
			{
				dropZone.addImage(new ImageRecord((WordDTO)image.getAttributeAsObject(ImageRecord.DATA)));
			}
		};
		
		album = new InteractiveAlbumBookPanel(book, this);  
	        
    	dropZone = new MailDropZone(album.book.imageSize){

			@Override
			public void onTextChange(boolean isOK) {
				
				sending.setSpeakVisible(isOK);
			}
    		
    	};
    	//menuRight = new MailMenuRightPanel(this);
    	sending = new SendingPanel(this){

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
    	
    	shrinkButton.setIcon("shrink.png");
    	shrinkButton.setIconSize(100);
    	shrinkButton.setPrompt("Sortir du mode plein écran");
    	shrinkButton.setLayoutAlign(Alignment.RIGHT);
    	shrinkButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				expand(false);
			}
		});
    	
    	expandLayout.addMember(shrinkButton);
    	expandLayout.addMember(bigMessageTileGrid);
    	expandLayout.addMember(bigMessageLabel);
    	 
    	imageRecipient.setIconSize(100);
    	labelTitle.setHeight(40);
    	labelTitle.setStyleName("title");
    	recipientLabel.setStyleName("title");    	
    	
    	recipientLayout.addMember(imageRecipient);
    	recipientLayout.addMember(labelTitle);
    	recipientLayout.addMember(recipientLabel);
    	
    	receivedMailLayout.setWidth100();
    	receivedMailLayout.setHeight("220px");    
    	receivedMailLayout.setStyleName("bloc");
    	
    	replyMailLayout.addMember(receivedMailLayout);
    	
    	mainContent.addMember(recipientLayout);
    	mainContent.addMember(replyMailLayout);
    	mainContent.addMember(album);
    	mainContent.addMember(dropZone);
    	mainContent.addMember(sending);
    
    	
    	horizontalLayout.addMember(expandLayout);
        horizontalLayout.addMember(mainContent);
        //horizontalLayout.addMember(menuRight);
        
        addMember(horizontalLayout);
		
		
		
	}
	
	public void setRecipientLabel(String s)
	{
		recipientLabel.setContents(s);
	}
	
	public void setStudent(StudentDTO student)
	{
		album.setStudent(student);

		//menuRight.init();
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
	
	public void setReceivedMail(String htmlReceivedMail) {
		if (htmlReceivedMail.isEmpty()) {
			labelTitle.setContents("Ecris un message à ");
	    	labelTitle.setWidth(270);
			replyMailLayout.setVisible(false);
		} else {
			labelTitle.setContents("Réponds à ");
	    	labelTitle.setWidth(152);
			receivedMailLayout.setContents(htmlReceivedMail);
			replyMailLayout.setVisible(true);
		}
	}
	
	public abstract void onSendMail(String recipient,ArrayList<WordDTO> words,ArrayList<String> correctedWords,LogDTO log);
	public abstract void onExpand(boolean b);
}
