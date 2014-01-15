package com.spicstome.client.ui.panel;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.place.LogoutPlace;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.ui.UserViewImpl;
import com.spicstome.client.ui.widget.BreadCrumb;
import com.spicstome.client.ui.widget.Crumb;

public class MenuTopPanel extends HLayout{

	protected Label labelUser = new Label();
	protected IconButton buttonLogout = new IconButton("");
	private Img image ;
	public BreadCrumb breadcrumb=new BreadCrumb();
	protected UserViewImpl mainView;
	
	public MenuTopPanel(UserViewImpl main)
	{
		super();
		
		this.mainView=main;
		
		
		setStyleName("connectPanel");
		
		image = new Img("logo.png");
		image.setSize(100);
		image.setMargin(10);
		
		buttonLogout.setIcon("exit.png");
		buttonLogout.setIconSize(64);
		
		buttonLogout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				mainView.goTo(new LogoutPlace());
			}
		});
		
		image.setLayoutAlign(Alignment.LEFT);
		buttonLogout.setLayoutAlign(Alignment.RIGHT);
		
		breadcrumb.addCrumb(new Crumb("Accueil"){

			@Override
			public void onClickCrumb() {
				
				mainView.goTo(new MainMenuPlace());
				
			}
			
		});
		
		breadcrumb.setMargin(10);

		
		addMember(image);
		addMember(labelUser);
		addMember(breadcrumb);
		addMember(buttonLogout);
		
	
		
	}
	
	public void setNameUser(String s)
	{
		labelUser.setContents("User: ["+s+"]");
	}
}
