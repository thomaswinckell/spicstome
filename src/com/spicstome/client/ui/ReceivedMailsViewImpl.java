package com.spicstome.client.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.MailListDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.place.NewMailPlace;
import com.spicstome.client.place.ReceivedMailsPlace;
import com.spicstome.client.ui.form.FormUtils;
import com.spicstome.client.ui.widget.Crumb;

public class ReceivedMailsViewImpl extends UserViewImpl  implements ReceivedMailsView {

	VLayout containerLayout = new VLayout();
	ArrayList<HLayout> mailLayout = new ArrayList<HLayout>();
	Label receivedMailsLabel = new Label();
	
	public ReceivedMailsViewImpl() {
		super();
		
		addCrumb(new Crumb("Mail"){
			@Override
			public void onClickCrumb() {			
				goTo(new MailPlace());
			}
		});
		
		addCrumb(new Crumb("Mails reçus"){});
		
		mainPanel.setWidth100();
		mainPanel.setHeight100();
		
		receivedMailsLabel.setContents("Messages reçus :");
		receivedMailsLabel.setStyleName("bigMessage");
		receivedMailsLabel.setLeft(100);
		
		containerLayout.setWidth100();
		containerLayout.setHeight100();
		
		mainPanel.addMember(receivedMailsLabel);
		mainPanel.addMember(containerLayout);
	}
	
	private void updateLayout(boolean hasPrevious, boolean hasNext, final int startPosition, 
			final int nextStartPosition) {
		
		//cleaning
		
		for(Canvas c : containerLayout.getMembers()) {
			containerLayout.removeMember(c);
		}
		
		// adding navigation buttons
		HLayout navLayout = new HLayout();
		navLayout.setWidth100();
		navLayout.setHeight("100px");
		
		if (hasPrevious) {
			
			IconButton previousButton = new IconButton("");
			previousButton.setIcon("arrow-left.png");
			previousButton.setIconSize(96);
			previousButton.setPrompt("Page précédente");			
			previousButton.setWidth(96);
			previousButton.setLayoutAlign(Alignment.LEFT);
			
			previousButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(startPosition, false));
				}				
			});
			
			navLayout.addMember(previousButton);
		}
		
		if (hasNext) {
			
			IconButton nextButton = new IconButton("");
			nextButton.setIcon("arrow-right.png");
			nextButton.setIconSize(96);
			nextButton.setPrompt("Page suivante");			
			nextButton.setWidth(96);
			nextButton.setLayoutAlign(Alignment.RIGHT);
			
			nextButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(nextStartPosition, true));
				}				
			});
			
			navLayout.addMember(nextButton);
		}
		
		containerLayout.addMember(navLayout);
		
		// adding mails
		
		for(int i=0; i<mailLayout.size(); i++) {
			containerLayout.addMember(mailLayout.get(i));
		}
		
		// adding navigation buttons
		
		if (hasPrevious) {
			
			IconButton previousButtonBottom = new IconButton("");
			previousButtonBottom.setIcon("arrow-left.png");
			previousButtonBottom.setIconSize(96);
			previousButtonBottom.setPrompt("Page précédente");			
			previousButtonBottom.setWidth(96);
			previousButtonBottom.setLayoutAlign(Alignment.LEFT);
			
			previousButtonBottom.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(startPosition, false));
				}				
			});
			
			containerLayout.addMember(previousButtonBottom);
		}
		
		if (hasNext) {
			
			IconButton nextButtonBottom = new IconButton("");
			nextButtonBottom.setIcon("arrow-right.png");
			nextButtonBottom.setIconSize(96);
			nextButtonBottom.setPrompt("Page suivante");			
			nextButtonBottom.setWidth(96);
			nextButtonBottom.setLayoutAlign(Alignment.RIGHT);
			
			nextButtonBottom.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(nextStartPosition, true));
				}				
			});
			
			containerLayout.addMember(nextButtonBottom);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setReceivedMails(final MailListDTO mails) {
		
		mailLayout = new ArrayList<HLayout>();
		
		for (int i=0; i<mails.getMails().size(); i++) {
			
			if (i>0) {
				HLayout hr = new HLayout();
				hr.setWidth100();
				hr.setHeight("10px");
				hr.setContents("<hr/>");
				mailLayout.add(hr);
			}
			
			final MailDTO currentMail = mails.getMails().get(i);
			
			HLayout hLayout = new HLayout();
			hLayout.setMargin(10);
			
			VLayout vLayoutSender = new VLayout();		
			vLayoutSender.setWidth("200px");
			vLayoutSender.setHeight("350px");
			vLayoutSender.setLayoutAlign(VerticalAlignment.CENTER);
			
			Label labelSender = new Label();
			labelSender.setContents("<h2>" + currentMail.getSender().getFirstName() + " dit : </h2>");
			
			Img imageSender = new Img(FormUtils.UPLOAD_IMAGE_PATH + currentMail.getSender().getImage().getFilename());
			imageSender.setWidth("200px");
			imageSender.setHeight("200px");
			
			Label labelDateReception = new Label();
			
			DateFormat fullDateFormatFR = DateFormat.getDateInstance(DateFormat.FULL, new Locale("FR","fr"));
			labelDateReception.setContents("<h2>Le " + fullDateFormatFR.format(currentMail.getReceivedDate()) + "</h2>");
			
			vLayoutSender.addMember(labelSender);
			vLayoutSender.addMember(imageSender);
			vLayoutSender.addMember(labelDateReception);
			
			VLayout vLayoutMessage = new VLayout();
			vLayoutMessage.setWidth100();
			vLayoutMessage.setHeight("290px");
			vLayoutMessage.setLayoutAlign(VerticalAlignment.CENTER);
			
			vLayoutMessage.setContents(currentMail.getMessageHTML());
			
			VLayout vLayoutReply = new VLayout();
			vLayoutReply.setWidth("200px");
			vLayoutReply.setHeight("250px");
			
			Label labelReply = new Label();
			labelReply.setContents("<h2>Répondre à "+currentMail.getSender().getFirstName() + " :</h2>");
			
			IconButton replyButton = new IconButton("");
			replyButton.setIcon("mail.png");
			replyButton.setIconSize(180);
			replyButton.setPrompt("Répondre à "+currentMail.getSender().getFirstName());			
			replyButton.setWidth(180);
			replyButton.setLayoutAlign(VerticalAlignment.CENTER);
			
			replyButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new NewMailPlace(currentMail.getSender().getEmail(), currentMail.getMessageHTML()));
				}				
			});
			
			vLayoutReply.addMember(labelReply);
			vLayoutReply.addMember(replyButton);
			
			hLayout.addMember(vLayoutSender);
			hLayout.addMember(vLayoutMessage);
			hLayout.addMember(vLayoutReply);
			
			hLayout.setMembersMargin(40);
			
			mailLayout.add(hLayout);
		}
		
		
		updateLayout(mails.hasPrevious(), mails.hasNext(), mails.getStartPosition(), mails.getNextStartPosition());
		
		System.out.println("Nb mails : "+mails.getMails().size());
		
		System.out.println("View update done");
	}
}
