package com.spicstome.client.ui;

import com.google.gwt.place.shared.Place;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.UserDTO;
import com.spicstome.client.ui.panel.MenuTopPanel;
import com.spicstome.client.ui.widget.Crumb;

public class UserViewImpl extends VLayout implements UserView{

	protected VLayout loadingPanel = new VLayout();
	protected Img imgLoading;
	protected VLayout mainPanel = new VLayout();
	protected Presenter listener;
	protected MenuTopPanel connectPanel;
	protected userType type;

	public static enum userType{REFERENT,TEACHER,ADMIN,STUDENT};
	
	public UserViewImpl() {
		setWidth100();
		
		connectPanel=new MenuTopPanel(this);
		
		imgLoading = new Img("loading.gif");
		imgLoading.setIconWidth(100);
		imgLoading.setWidth(100);
		imgLoading.setLayoutAlign(Alignment.CENTER);
		loadingPanel.setHeight(200);
		loadingPanel.addMember(imgLoading);
		
		mainPanel.setStyleName("mainPanel");
		loadingPanel.setStyleName("mainPanel");
		
		addMember(connectPanel);
		addMember(mainPanel);
		addMember(loadingPanel);
		

	}
	
	public void goTo(Place place)
	{
		listener.goTo(place);
	}
	
	public void addCrumb(Crumb crumb)
	{
		this.connectPanel.breadcrumb.addCrumb(crumb);
	}

	public void setUser(UserDTO user) {
		connectPanel.setUser(user);
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener=listener;
		
	}
	
	
	
	@Override
	public void init(userType type)
	{
		this.type=type;
	}

	@Override
	public void setMenuTopVisible(boolean b) {
		
		connectPanel.setVisible(b);
		
	}

	protected boolean isAdmin()
	{
		return (!(type==userType.STUDENT));
	}

	@Override
	public void setIsLoading(boolean b) {
	
		mainPanel.setVisible(!b);
		loadingPanel.setVisible(b);
		
	}


	


}
