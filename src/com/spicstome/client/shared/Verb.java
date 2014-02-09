package com.spicstome.client.shared;

import java.io.Serializable;
import com.spicstome.client.dto.VerbDTO;

public class Verb extends Article implements Serializable {


	private static final long serialVersionUID = 1333962488010340815L;
	
	private int negation;
	private int group;

	private String irregular1;
	private String irregular2;
	private String irregular3;
	private String irregular4;
	private String irregular5;
	private String irregular6;
	
	public Verb() {		
	}
	
	public Verb(Long id) {
		super(id);
	}
	
	public Verb(VerbDTO verbDTO,Folder parent)
	{
		super(verbDTO,parent);	
		
		this.negation=verbDTO.getNegation();
		this.group=verbDTO.getGroup();
		this.irregular1=verbDTO.getIrregular1();
		this.irregular2=verbDTO.getIrregular2();
		this.irregular3=verbDTO.getIrregular3();
		this.irregular4=verbDTO.getIrregular4();
		this.irregular5=verbDTO.getIrregular5();
		this.irregular6=verbDTO.getIrregular6();
		
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
