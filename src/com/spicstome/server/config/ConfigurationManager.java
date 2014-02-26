package com.spicstome.server.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.spicstome.server.business.XMLHelper;

@XmlRootElement(name="configuration")
public class ConfigurationManager {

	private static volatile ConfigurationManager instance;
	public static String FILEPATH = ""; 
	
	private String imagesPath;
	private String mailSMTP;
	private ReferentAuth referent;
	
	private ConfigurationManager(){}
	
	public static ConfigurationManager getInstance(){
		synchronized (ConfigurationManager.class) {
			if(instance == null)
				instance = new ConfigurationManager();
		}
		return instance;
	}
	
	public void init(){
		try {
			instance = (ConfigurationManager)XMLHelper.load(ConfigurationManager.class, FILEPATH);
		} 
		catch (Exception e) {}
	}

	@XmlElement(name="album")
	public String getImagesPath() {
		return imagesPath;
	}
	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}
	@XmlElement(name="mailSMTP")
	public String getMailSMTP() {
		return mailSMTP;
	}
	public void setMailSMTP(String mailSMTP) {
		this.mailSMTP = mailSMTP;
	}
	public ReferentAuth getReferent() {
		return referent;
	}
	public void setReferent(ReferentAuth referent) {
		this.referent = referent;
	}
	public void save(){
		XMLHelper.save(instance, FILEPATH);
	}
		
}

