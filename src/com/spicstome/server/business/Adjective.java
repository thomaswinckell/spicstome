package com.spicstome.server.business;

import java.io.Serializable;
import com.spicstome.client.dto.AdjectiveDTO;

public class Adjective extends Word implements Serializable {


	private static final long serialVersionUID = -5088774969872700461L;
	
	private String matching1;
	private String matching2;
	private String matching3;
	private String matching4;

	
	public Adjective() {		
	}
	
	public Adjective(Long id) {
		super(id);
	}
	
	public Adjective(AdjectiveDTO adjectiveDTO,Folder parent)
	{
		super(adjectiveDTO,parent);
		
		matching1 = adjectiveDTO.getMatching1();
		matching2 = adjectiveDTO.getMatching2();
		matching3 = adjectiveDTO.getMatching3();
		matching4 = adjectiveDTO.getMatching4();
	}
	
	
	@Override
	public String toString() {
		return "Adjective []";
	}

	public String getMatching1() {
		return matching1;
	}

	public void setMatching1(String matching1) {
		this.matching1 = matching1;
	}

	public String getMatching2() {
		return matching2;
	}

	public void setMatching2(String matching2) {
		this.matching2 = matching2;
	}

	public String getMatching3() {
		return matching3;
	}

	public void setMatching3(String matching3) {
		this.matching3 = matching3;
	}

	public String getMatching4() {
		return matching4;
	}

	public void setMatching4(String matching4) {
		this.matching4 = matching4;
	}
}
