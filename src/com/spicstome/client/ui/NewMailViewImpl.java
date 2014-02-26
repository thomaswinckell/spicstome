package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.LogDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.ui.panel.AlbumBookPanel;
import com.spicstome.client.ui.panel.Book;
import com.spicstome.client.ui.panel.MailMenuRightPanel;
import com.spicstome.client.ui.panel.RecipientPanel;
import com.spicstome.client.ui.panel.SendingPanel;
import com.spicstome.client.ui.tts.TextToSpeech;
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.MailDropZone;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;



public class NewMailViewImpl extends UserViewImpl  implements NewMailView{
	
	private TextToSpeech textToSpeech = new TextToSpeech();
	public AlbumBookPanel album;
	MailDropZone dropZone;
	MailMenuRightPanel menuRight;
	RecipientPanel recipient;
	SendingPanel sending;
	HLayout horizontalLayout = new HLayout();
	VLayout mailLayout = new VLayout();
	VLayout mainMailLayout = new VLayout();
	public boolean expanded = false;
	VLayout expandLayout = new VLayout();
	ImageTileGrid bigMessageTileGrid;
	Label bigMessageLabel = new Label();
	StudentDTO defaultStudent;
	long begin;
	
	public NewMailViewImpl()
	{
		super();

		addCrumb(new Crumb("Mail"){
			@Override
			public void onClickCrumb() {			
				goTo(new MailPlace());
			}
		});
		
		addCrumb(new Crumb("Nouveau mail"){});

		
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
				
				getLogs();
				sendMail();
				goTo(new MailPlace());
			}

			@Override
			public void onSpeak() {
				
				textToSpeech.playMessage(dropZone.getSentence());
				
			}
    		
    	};
    	recipient = new RecipientPanel(){

			@Override
			public void onChangeRecipient(UserDTO user) {
				
				if(admin)
				{
					if(user instanceof StudentDTO)
					{
						StudentDTO student = (StudentDTO) user;
						album.setStudent(student);
						dropZone.init();
					}
					else
					{
						album.setStudent(defaultStudent);
					}
				}
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
    	
        
    	mailLayout.addMember(recipient);
    	mailLayout.addMember(album);
    	mailLayout.addMember(dropZone);
    	mailLayout.addMember(sending);
    	
    	mainMailLayout.addMember(mailLayout);
    	mainMailLayout.addMember(expandLayout);
  	
        horizontalLayout.addMember(mainMailLayout);
        horizontalLayout.addMember(menuRight);
        mainPanel.addMember(horizontalLayout);
	}

	@Override
	public void setStudent(StudentDTO owner) {
		
		defaultStudent=owner;
		album.setStudent(owner);
		
		menuRight.init();
		sending.init();
	}



	@Override
	public void setRecipients(List<UserDTO> recipients) {	
		recipient.setRecipients(recipients);
	}

	public void getLogs()
	{
		
		
		long now = System.currentTimeMillis()-begin;
		int seconds = (int) (now/1000);
		
		LogDTO logDTO = new LogDTO((long)-1, null, recipient.mail.getValue().toString(), new Date(),
				dropZone.message.size(),
				seconds,dropZone.getMovementCount());
		
		((NewMailView.Presenter)(listener)).saveLog(logDTO);
	}
	
	public void sendMail()
	{
		((NewMailView.Presenter)(listener)).sendMail(recipient.getMail(), dropZone.message, dropZone.getCorrectedWords());
	}

	@Override
	public void init(boolean admin) {
		
		super.init(admin);
		
		dropZone.init();
		recipient.init();
	
		expand(false);

		begin = System.currentTimeMillis();
	}
	
	public void expand(boolean b)
	{
		expanded=b;
		
		setMenuTopVisible(!b);
		
		expandLayout.setVisible(expanded);
		mailLayout.setVisible(!expanded);
		
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
}
