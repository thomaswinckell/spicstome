package com.spicstome.client.ui.form;

import java.util.Date;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.spicstome.client.dto.UserDTO;

public class BasicUserForm extends DynamicForm{
	
	private FormUtils.Mode mode;
	private UserDTO userDTO;
	
	private DataSourceTextField passwordTextField, password2TextField;
	
	public BasicUserForm () {
		
		super();
		
		mode = FormUtils.Mode.NEW;
		userDTO = null;
		
		final DataSource dataSource = new DataSource();
	    
	    DataSourceTextField firstNameTextField = new DataSourceTextField("first_name");
	    firstNameTextField.setTitle("Pr&eacute;nom");
	    firstNameTextField.setRequired(true);
	    
	    DataSourceTextField nameTextField = new DataSourceTextField("name");
	    nameTextField.setTitle("Nom");
	    nameTextField.setRequired(true);

	    RegExpValidator emailRegExpValidator = new RegExpValidator();
	    emailRegExpValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");

	    DataSourceTextField emailTextField = new DataSourceTextField("email");
	    emailTextField.setTitle("Email");
	    emailTextField.setValidators(emailRegExpValidator);
	    emailTextField.setRequired(true);
	    
	    DataSourceTextField loginTextField = new DataSourceTextField("login");
	    loginTextField.setTitle("Login");
	    loginTextField.setRequired(true);
	    
	    passwordTextField = new DataSourceTextField("password");
	    passwordTextField.setTitle("Mot de passe");
	    passwordTextField.setType(FieldType.PASSWORD);
	    
	    password2TextField = new DataSourceTextField("password2");
	    password2TextField.setTitle("Confirmation du mot de passe");
	    password2TextField.setType(FieldType.PASSWORD);

	    dataSource.setFields(firstNameTextField);
	    dataSource.setFields(nameTextField);
	    dataSource.setFields(emailTextField);
	    dataSource.setFields(loginTextField);
	    dataSource.setFields(passwordTextField);
	    dataSource.setFields(password2TextField);

	    setDataSource(dataSource);
	}
	
	public void fillForm(UserDTO userDTO, FormUtils.Mode mode) {
		this.mode = mode;
		this.userDTO = userDTO;
		
		this.clearValues();
		
		if (mode == FormUtils.Mode.EDIT) {
	    	setValue("first_name", userDTO.getFirstName());
	    	setValue("name", userDTO.getName());
	    	setValue("email", userDTO.getEmail());
	    	setValue("login", userDTO.getLogin());
	    	getField("password").setRequired(false);
	    	getField("password2").setRequired(false);
		} else {
			getField("password").setRequired(true);
			getField("password2").setRequired(true);
		}
	}
	
	public boolean isNewPassword() {
		return (mode == FormUtils.Mode.NEW) || 
				((getValueAsString("password") != null) &&  (getValueAsString("password2") != null) &&  
				!getValueAsString("password").isEmpty());
	}
	
	public boolean isNewLogin() {
		return ((mode == FormUtils.Mode.NEW) || 
				(!getValueAsString("login").equals(userDTO.getLogin())));
	}
	
	@Override
	public boolean validate() {
		
		boolean validate = super.validate(); //((DynamicForm) this).validate();
		
		if (validate) {
		
			if (isNewPassword()) {
				if (!getValueAsString("password").equals(getValueAsString("password2"))) {
					SC.warn("La confirmation du mot de passe n'est pas bonne.");
					return false;
				}
			}
		}
		
		return validate;
	}
	
	public void fillUserDTO(final UserDTO userDTO) {
		
		if (mode == FormUtils.Mode.NEW)
			userDTO.setSubscriptionDate(new Date());
		
		userDTO.setFirstName(getValueAsString("first_name"));
		userDTO.setName(getValueAsString("name"));
		userDTO.setEmail(getValueAsString("email"));
		userDTO.setLogin(getValueAsString("login"));
		
		if (isNewPassword())
			userDTO.setPassword(getValueAsString("password"));
	}
}
