package com.spicstome.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.spicstome.client.place.GoodbyePlace;



public class LoginViewImpl extends Composite implements LoginView
{
	

	private Presenter listener;
	private TextBox text;
	private Button button;
	
	VerticalPanel viewPanel = new VerticalPanel(); 

	public LoginViewImpl()
	{
		
		
		button = new Button("OK");
		text = new TextBox();
		
		button.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                listener.goTo(new GoodbyePlace(text.getText()));
            }
        });
		
		
		
		viewPanel.add(text);
		viewPanel.add(button);
		
		
		
		initWidget(viewPanel);
	}

	


	@Override
	public void setPresenter(Presenter listener)
	{
		this.listener = listener;
	}
}
