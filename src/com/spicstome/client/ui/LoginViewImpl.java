package com.spicstome.client.ui;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;


public class LoginViewImpl extends VLayout implements LoginView
{
	
	private Label title = new Label(".:SpicsToMe:.");
	private Presenter listener;
	private TextItem textLogin;
	private PasswordItem textPassword;
	private Button button;
	private DynamicForm form = new DynamicForm();
	private HLayout wrongPanel = new HLayout();
	private Label wrongLabel;
	private Img image ;
	
	VLayout viewPanel = new VLayout(); 

	public LoginViewImpl()
	{
		
		title.setWidth100();
		title.setHeight(30);
		
		image = new Img("logo.png");
		image.setSize(100);
		image.setMargin(10);
		
		wrongLabel  = new Label("Connexion refusée");
		wrongLabel.setStyleName("errorMessage");
		wrongLabel.setVisible(false);
		
		setStyleName("connexionPanel");
		
		
		button = new Button("Connexion");
		button.setStyleName("connexionButton");
		
		
		textLogin = new TextItem();
		textLogin.setName("Login");
		textPassword = new PasswordItem();
		textPassword.setName("Password");
		
		form.setFields(textLogin,textPassword);
		form.setMargin(20);
		
		button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	listener.login(textLogin.getValueAsString(), textPassword.getValueAsString());           	
            }
        });

		
		title.setLayoutAlign(VerticalAlignment.CENTER);
		image.setLayoutAlign(VerticalAlignment.CENTER);
		button.setLayoutAlign(VerticalAlignment.CENTER);
		form.setLayoutAlign(VerticalAlignment.CENTER);
		
		wrongPanel.addMember(wrongLabel);
		
		viewPanel.addMember(title);
		viewPanel.addMember(image);
		viewPanel.addMember(form);	
		viewPanel.addMember(button);
		viewPanel.addMember(wrongPanel);
		
		
		
		addMember(viewPanel);
		
		
	}

	@Override
	public void setPresenter(Presenter listener)
	{
		this.listener = listener;
	}




	@Override
	public void setWrongLogin() 
	{
		textLogin.setValue("");
		textPassword.setValue("");
		wrongLabel.setVisible(true);
	}
}
