package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.spicstome.client.ui.widget.Crumb;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.MailDropZone;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;



public class NewMailViewImpl extends UserViewImpl  implements NewMailView{
	
	
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
	ImageTileGrid message;
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
    	dropZone = new MailDropZone(album.book.imageSize);
    	menuRight = new MailMenuRightPanel(this);
    	sending = new SendingPanel(){

			@Override
			public void onSend() {
				
				getLogs();
				
				goTo(new MailPlace());
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
    	message = new ImageTileGrid(Mode.CLICK,bigSize+50,bigSize+50,bigSize){};
    	message.setHeight(bigSize+60);
    	
    	expandLayout.addMember(message);
        
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
	}



	@Override
	public void setRecipients(List<UserDTO> recipients) {	
		recipient.setRecipients(recipients);
	}

	public void getLogs()
	{
		
		
		long now = System.currentTimeMillis()-begin;
		int seconds = (int) (now/1000);
		
		System.out.println("mouvement= "+dropZone.getMovementCount()+" temps="+seconds+" seconds");
		


		LogDTO logDTO = new LogDTO((long)-1, null, recipient.mail.getValue().toString(), new Date(),
				dropZone.message.size(),
				seconds,dropZone.getMovementCount());
		
		((NewMailView.Presenter)(listener)).saveLog(logDTO);
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
		
		expandLayout.setVisible(expanded);
		mailLayout.setVisible(!expanded);
		
		if(expanded)
		{
			ArrayList<ImageRecord> words = dropZone.UpdateMail();
			ArrayList<ImageRecord> copyWords = new ArrayList<ImageRecord>();
			for(ImageRecord record:words)
				copyWords.add(new ImageRecord(record));
			
			message.setItems(copyWords);
		}
		
	}
}
