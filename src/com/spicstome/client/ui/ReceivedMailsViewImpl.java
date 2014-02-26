package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.MailPlace;
import com.spicstome.client.place.UsersManagementPlace;
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
	
	private void updateLayout() {
		//cleaning
		for(Canvas c : containerLayout.getMembers()) {
			containerLayout.removeMember(c);
		}
		
		// adding
		for(int i=0; i<mailLayout.size(); i++) {
			containerLayout.addMember(mailLayout.get(i));
		}
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void setUser(UserDTO owner) {
		
	}

	@Override
	public void setReceivedMails(List<MailDTO> mails) {
		
		mailLayout = new ArrayList<HLayout>();
		
		for (int i=0; i<mails.size(); i++) {
			
			HLayout hLayout = new HLayout();
			hLayout.setMargin(50);
			
			VLayout vLayoutSender = new VLayout();		
			vLayoutSender.setWidth("200px");
			vLayoutSender.setHeight("350px");
			
			Label labelSender = new Label();
			labelSender.setContents("<h2>" + mails.get(i).getSender().getFirstName() + " dit : </h2>");
			
			Img imageSender = new Img(FormUtils.UPLOAD_IMAGE_PATH + mails.get(i).getSender().getImage().getFilename());
			imageSender.setWidth("200px");
			imageSender.setHeight("200px");
			
			vLayoutSender.addMember(labelSender);
			vLayoutSender.addMember(imageSender);
			
			VLayout vLayoutMessage = new VLayout();			
			vLayoutMessage.setWidth("600px");
			vLayoutMessage.setHeight("350px");
			
			vLayoutMessage.setContents(mails.get(i).getMessageHTML());
			
			hLayout.addMember(vLayoutSender);
			hLayout.addMember(vLayoutMessage);
			mailLayout.add(hLayout);
		}
		
		updateLayout();
		
		System.out.println("Nb mails : "+mails.size());
		
		System.out.println("View update done");
	}
}
