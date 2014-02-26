package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smartgwt.client.widgets.IconButton;
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
import com.spicstome.client.ui.panel.SelectRecipientPanel;
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
	SelectRecipientPanel selectionRecipient;
	IconButton imageRecipient = new IconButton("");
	SendingPanel sending;
	HLayout horizontalLayout = new HLayout();
	VLayout mailLayout = new VLayout();
	VLayout mainMailLayout = new VLayout();
	public boolean expanded = false;
	public boolean selectedRecipient = false;
	VLayout expandLayout = new VLayout();
	ImageTileGrid bigMessageTileGrid;
	Label bigMessageLabel = new Label();
	StudentDTO defaultStudent;
	Label labelTitle = new Label();
	long begin;
	HLayout recipientLayout = new HLayout();
	String recipientMail;
	
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
    	selectionRecipient = new SelectRecipientPanel(){

			@Override
			public void onSelectedRecipient(UserDTO user,String mail) {
				
				if(admin)
				{
					
					if(user instanceof StudentDTO)
					{
						StudentDTO student = (StudentDTO) user;
						album.setStudent(student);
						imageRecipient.setIcon("upload/"+student.getImage().getFilename());
						dropZone.init();
					}
					else
					{
						album.setStudent(defaultStudent);
					}
				}
				
				IsSelectedRecipient(true);
				recipientMail=mail;
				
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
    	
    	mailLayout.addMember(selectionRecipient);
    	mailLayout.addMember(recipientLayout);
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
		selectionRecipient.setRecipients(recipients);
	}

	public void getLogs()
	{

		long now = System.currentTimeMillis()-begin;
		int seconds = (int) (now/1000);
		
		LogDTO logDTO = new LogDTO((long)-1, null, recipientMail, new Date(),
				dropZone.message.size(),
				seconds,dropZone.getMovementCount());
		
		((NewMailView.Presenter)(listener)).saveLog(logDTO);
	}
	
	public void sendMail()
	{
		((NewMailView.Presenter)(listener)).sendMail(recipientMail, dropZone.message, dropZone.getCorrectedWords());
	}

	@Override
	public void init(boolean admin) {
		
		super.init(admin);
		
		IsSelectedRecipient(false);
		dropZone.init();
		expand(false);	
		imageRecipient.setIcon("upload/"+"default_user.png");
		begin = System.currentTimeMillis();
	}
	
	public void IsSelectedRecipient(boolean b)
	{
		selectedRecipient=b;
		
		sending.setVisible(selectedRecipient);
		album.setVisible(selectedRecipient);
		dropZone.setVisible(selectedRecipient);
		menuRight.setVisible(selectedRecipient);
		selectionRecipient.setVisible(!selectedRecipient);
		recipientLayout.setVisible(selectedRecipient);
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
