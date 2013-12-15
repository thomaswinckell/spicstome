package com.spicstome.client.ui;



import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;



public class UserViewLayout extends VLayout implements UserView{

	
	protected Label label = new Label("");
	protected Presenter listener;
	
	public UserViewLayout() {
		addMember(label);
		setWidth100();
		setHeight100();
		setStyleName("mainPanel");
	}

	public void setName(String name) {
		label.setContents("User: ["+name+"]");
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener=listener;
		
	}
}
