package com.spicstome.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;




public class LoginViewImpl extends Composite implements LoginView
{
	

	private Presenter listener;
	private TextBox textLogin;
	private PasswordTextBox textPassword;
	private Button button;
	
	private HorizontalPanel wrongPanel = new HorizontalPanel();
	private Label wrongLabel;

	
	VerticalPanel viewPanel = new VerticalPanel(); 

	public LoginViewImpl()
	{
		Image image = new Image("images/logo.png");
		wrongLabel  = new Label("Connexion refusée");
		wrongLabel.setStyleName("errorMessage");
		
		viewPanel.setStyleName("connexionPanel");
		viewPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		
		button = new Button("Connexion");
		button.setStyleName("connexionButton");
		
		
		textLogin = new TextBox();
		textPassword = new PasswordTextBox();
		
		
		button.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
            	listener.login(textLogin.getText(), textPassword.getText());
            	
            }
        });
		wrongLabel.setVisible(false);
		wrongPanel.add(wrongLabel);
		
		
		
		viewPanel.add(image);
		viewPanel.add(new Label("Login :"));
		viewPanel.add(textLogin);
		viewPanel.add(new Label("Mot de passe :"));
		viewPanel.add(textPassword);
		viewPanel.add(button);
		viewPanel.add(wrongPanel);
		
		
		initWidget(viewPanel);
	}

	


	@Override
	public void setPresenter(Presenter listener)
	{
		this.listener = listener;
	}




	@Override
	public void setWrongLogin() 
	{
		textLogin.setText("");
		textPassword.setText("");
		wrongLabel.setVisible(true);
	}
}
