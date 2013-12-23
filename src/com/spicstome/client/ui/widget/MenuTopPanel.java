package com.spicstome.client.ui.widget;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.ui.UserViewLayout;

public class MenuTopPanel extends HLayout{

	protected Label labelUser = new Label();
	protected IconButton buttonLogout = new IconButton("");
	private Img image ;
	public BreadCrumb breadcrumb=new BreadCrumb();
	protected UserViewLayout mainView;
	
	public MenuTopPanel(UserViewLayout main)
	{
		super();
		
		this.mainView=main;
		
		
		setStyleName("connectPanel");
		
		image = new Img("logo.png");
		image.setSize(100);
		image.setMargin(10);
		
		buttonLogout.setIcon("exit.png");
		buttonLogout.setIconSize(64);
		
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
		addMember(breadcrumb);
		addMember(buttonLogout);
		
	
		
	}
	
	public void setNameUser(String s)
	{
		labelUser.setContents("User: ["+s+"]");
	}
}
