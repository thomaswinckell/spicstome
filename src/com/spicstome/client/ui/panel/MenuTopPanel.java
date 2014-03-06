package com.spicstome.client.ui.panel;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.ReferentDTO;
import com.spicstome.client.dto.StudentDTO;
import com.spicstome.client.dto.TeacherDTO;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.place.LogoutPlace;
import com.spicstome.client.place.MainMenuPlace;
import com.spicstome.client.ui.UserViewImpl;
import com.spicstome.client.ui.widget.BreadCrumb;
import com.spicstome.client.ui.widget.Crumb;

public class MenuTopPanel extends VLayout{

	Label userName = new Label();
	Label userFirstName = new Label();
	Label userType = new Label();
	protected Label buttonLogout = new Label();
	private Img logo =new Img("logo.png");
	public BreadCrumb breadcrumb=new BreadCrumb();
	protected UserViewImpl mainView;
	HLayout userLayout = new HLayout();
	VLayout userDetail = new VLayout();
	Label labelTitle = new Label();
	private IconButton imageUser= new IconButton("");
	HLayout topLayout = new HLayout();
	
	public MenuTopPanel(UserViewImpl main)
	{
		super();
		
		this.mainView=main;
			
		setStyleName("connectPanel");
		
		logo.setSize(100);
		logo.setMargin(10);
		logo.setLayoutAlign(Alignment.LEFT);
		
		labelTitle.setContents(".::SpicsToMe::.");
		labelTitle.setHeight(20);
		labelTitle.setWidth100();
		labelTitle.setStyleName("mainTitle");
		labelTitle.setMargin(10);
		
		breadcrumb.addCrumb(new Crumb("Accueil"){

			@Override
			public void onClickCrumb() {		
				mainView.goTo(new MainMenuPlace());
			}
		});
		

		imageUser.setIconSize(100);
		
		userFirstName.setHeight(10);
		userName.setHeight(10);
		userType.setHeight(10);
		
		buttonLogout.setHeight(10);
		buttonLogout.setContents("deconnexion");
		buttonLogout.setStyleName("logout");
		
		buttonLogout.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				mainView.goTo(new LogoutPlace());
			}
		});
		
		userDetail.addMember(userFirstName);	
		userDetail.addMember(userName);	
		userDetail.addMember(userType);	
		userDetail.addMember(buttonLogout);
		
		userLayout.setWidth(200);
		
		userLayout.addMember(imageUser);
		userLayout.addMember(userDetail);
		
		userLayout.setLayoutAlign(Alignment.RIGHT);
		
		topLayout.addMember(logo);
		topLayout.addMember(labelTitle);
		topLayout.addMember(userLayout);

		addMember(topLayout);
		addMember(breadcrumb);
	
		
	}
	
	public void setUser(UserDTO u)
	{
		if(u!=null)
		{
			userName.setContents(u.getName());
			userFirstName.setContents(u.getFirstName());
				
			imageUser.setIcon("upload/"+u.getImage().getFilename());

			if(u instanceof StudentDTO)
				userType.setContents("étudiant");
			else if(u instanceof TeacherDTO)
				userType.setContents("enseignant");
			else if(u instanceof ReferentDTO)
				userType.setContents("référent");
		}
		
	}
}
