package com.spicstome.client.dto;

import java.io.Serializable;
import java.util.Set;

public class VerbDTO extends ArticleDTO implements Serializable {

	private static final long serialVersionUID = -1929770844036950980L;
	
	private int negation;
	private int group;

	private String irregular1;
	private String irregular2;
	private String irregular3;
	private String irregular4;
	private String irregular5;
	private String irregular6;
	
	public VerbDTO()
	{		
	}
	
	public VerbDTO(long id)
	{
		super(id);
	}
	
	public VerbDTO(Long id, String name, int order, FolderDTO folder, ImageDTO image, Set<LogDTO> logs,int favorite,
			int negation,int group,String irregular1,String irregular2,String irregular3,String irregular4,String irregular5,String irregular6)
	{
		super(id, name, order, folder, image,logs,favorite);
		
		this.negation=negation;
		this.group=group;
		this.irregular1=irregular1;
		this.irregular2=irregular2;
		this.irregular3=irregular3;
		this.irregular4=irregular4;
		this.irregular5=irregular5;
		this.irregular6=irregular6;
	}
	
	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getIrregular1() {
		return irregular1;
	}

	public void setIrregular1(String irregular1) {
		this.irregular1 = irregular1;
	}

	public String getIrregular2() {
		return irregular2;
	}

	public void setIrregular2(String irregular2) {
		this.irregular2 = irregular2;
	}

	public String getIrregular3() {
		return irregular3;
	}

	public void setIrregular3(String irregular3) {
		this.irregular3 = irregular3;
	}

	public String getIrregular4() {
		return irregular4;
	}

	public void setIrregular4(String irregular4) {
		this.irregular4 = irregular4;
	}

	public String getIrregular5() {
		return irregular5;
	}

	public void setIrregular5(String irregular5) {
		this.irregular5 = irregular5;
	}

	public String getIrregular6() {
		return irregular6;
	}

	public void setIrregular6(String irregular6) {
		this.irregular6 = irregular6;
	}
	
	@Override
	public String toString() {
		return "Verb []";
	}


	public int getNegation() {
		return negation;
	}

	public void setNegation(int negation) {
		this.negation = negation;
	}

}
