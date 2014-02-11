package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Set;

public class AdjectiveDTO extends WordDTO implements Serializable {


	private static final long serialVersionUID = -5015048271015265572L;
	
	private String matching1;
	private String matching2;
	private String matching3;
	private String matching4;

	
	public AdjectiveDTO()
	{		
	}
	
	public AdjectiveDTO(long id)
	{
		super(id);
	}
	
	public AdjectiveDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image, Set<LogDTO> logs,int favorite,
			String matching1,String matching2,String matching3,String matching4)
	{
		super(id, name, order, folder, image,logs,favorite);
		
		this.matching1=matching1;
		this.matching2=matching2;
		this.matching3=matching3;
		this.matching4=matching4;
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
