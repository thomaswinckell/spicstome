package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.MailListDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.place.ReceivedMailsPlace;
import com.spicstome.client.ui.form.FormUtils;
import com.spicstome.client.ui.widget.Crumb;

public class ReceivedMailsViewImpl extends UserViewImpl  implements ReceivedMailsView {

	VLayout containerLayout = new VLayout();
	ArrayList<HLayout> mailLayout = new ArrayList<HLayout>();
	
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
		
		containerLayout.setWidth100();
		containerLayout.setHeight100();
		
		mainPanel.addMember(containerLayout);
	}
	
	private void updateLayout(boolean hasPrevious, boolean hasNext, final int startPosition, 
			final int nextStartPosition) {
		
		//cleaning
		
		for(Canvas c : containerLayout.getMembers()) {
			containerLayout.removeMember(c);
		}
		
		// adding navigation buttons
		
		if (hasPrevious) {
			Button previousButton = new Button("Précédent");
			previousButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(startPosition, false));
				}				
			});
			
			containerLayout.addMember(previousButton);
		}
		
		if (hasNext) {
			Button nextButton = new Button("Suivant");
			nextButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(nextStartPosition, true));
				}				
			});
			
			containerLayout.addMember(nextButton);
		}
		
		// adding mails
		
		for(int i=0; i<mailLayout.size(); i++) {
			containerLayout.addMember(mailLayout.get(i));
		}
		
		// adding navigation buttons
		
		if (hasPrevious) {
			Button previousButtonBottom = new Button("Précédent");
			previousButtonBottom.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(startPosition, false));
				}				
			});
			
			containerLayout.addMember(previousButtonBottom);
		}
		
		if (hasNext) {
			Button nextButtonBottom = new Button("Suivant");
			nextButtonBottom.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					goTo(new ReceivedMailsPlace(nextStartPosition, true));
				}				
			});
			
			containerLayout.addMember(nextButtonBottom);
		}
	}

	@Override
	public void setReceivedMails(final MailListDTO mails) {
		
		mailLayout = new ArrayList<HLayout>();
		
		for (int i=0; i<mails.getMails().size(); i++) {
			
			HLayout hLayout = new HLayout();
			hLayout.setMargin(50);
			
			VLayout vLayoutSender = new VLayout();		
			vLayoutSender.setWidth("200px");
			vLayoutSender.setHeight("350px");
			
			Label labelSender = new Label();
			labelSender.setContents("<h2>" + mails.getMails().get(i).getSender().getFirstName() + " dit : </h2>");
			
			Img imageSender = new Img(FormUtils.UPLOAD_IMAGE_PATH + mails.getMails().get(i).getSender().getImage().getFilename());
			imageSender.setWidth("200px");
			imageSender.setHeight("200px");
			
			vLayoutSender.addMember(labelSender);
			vLayoutSender.addMember(imageSender);
			
			VLayout vLayoutMessage = new VLayout();
			vLayoutMessage.setWidth100();
			//vLayoutMessage.setWidth("600px");
			vLayoutMessage.setHeight("350px");
			
			vLayoutMessage.setContents(mails.getMails().get(i).getMessageHTML());
			
			hLayout.addMember(vLayoutSender);
			hLayout.addMember(vLayoutMessage);
			mailLayout.add(hLayout);
		}
			
		
		updateLayout(mails.hasPrevious(), mails.hasNext(), mails.getStartPosition(), mails.getNextStartPosition());
		
		System.out.println("Nb mails : "+mails.getMails().size());
		
		System.out.println("View update done");
	}
}
