package com.spicstome.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.MailDTO;
import com.spicstome.client.dto.UserDTO;

public class ReceivedMailsViewImpl extends UserViewImpl  implements ReceivedMailsView {

	VLayout containerLayout = new VLayout();
	ArrayList<HLayout> mailLayout = new ArrayList<HLayout>();
	
	public ReceivedMailsViewImpl() {
		super();
		
		containerLayout.setWidth100();
		containerLayout.setHeight100();
		
		mainPanel.addMember(containerLayout);
	}
	
	private void updateLayout() {
		//containerLayout = new VLayout();
		containerLayout.clear();
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
			
			hLayout.setContents(mails.get(i).getMessageHTML());
			
			mailLayout.add(hLayout);
		}
		
		updateLayout();
		
		System.out.println("Nb mails : "+mails.size());
		
		System.out.println("View update done");
	}
}
