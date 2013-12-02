package com.spicstome.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.spicstome.client.place.GoodbyePlace;



public class LoginViewImpl extends Composite implements LoginView
{
	

	private Presenter listener;
	private TextBox textLogin;
	private TextBox textPassword;
	private Button button;
	
	VerticalPanel viewPanel = new VerticalPanel(); 

	public LoginViewImpl()
	{
		
		viewPanel.setStyleName("vp");
		viewPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		
		button = new Button("Se connecter");
		
		textLogin = new TextBox();
		textPassword = new TextBox();
		
		button.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                //listener.goTo(new GoodbyePlace(textLogin.getText()));
            	listener.login(textLogin.getText(), textPassword.getText());
            	
            }
        });
		
		
		viewPanel.add(new Label("Login :"));
		viewPanel.add(textLogin);
		viewPanel.add(new Label("Mot de passe :"));
		viewPanel.add(textPassword);
		viewPanel.add(button);
		
		
		
		initWidget(viewPanel);
	}

	


	@Override
	public void setPresenter(Presenter listener)
	{
		this.listener = listener;
	}
}
