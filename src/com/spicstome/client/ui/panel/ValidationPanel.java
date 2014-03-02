package com.spicstome.client.ui.panel;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ValidationPanel extends HLayout{

	HLayout checksLayout = new HLayout();
	protected Img validImg = new Img("goodgame.png");
	protected Label labelGood = new Label();
	
	public ValidationPanel()
	{
	
		validImg.setWidth(50);
		validImg.setHeight(50);
	
		
		

		
		setStyleName("bloc");
		
		
		addMember(checksLayout);
		addMember(validImg);
		
		setHeight(60);
	}
	
	public void updateChecks(boolean b,int nbOK,int nbKO)
	{
		validImg.setVisible(b);
		
		checksLayout.removeMembers(checksLayout.getMembers());
		for(int i=0;i<nbOK;i++)
		{
			VLayout checkLayout = new VLayout();
			checkLayout.setHeight(50);
			checkLayout.setWidth(160);
			Img imgCheck = new Img("check.png");
			imgCheck.setHeight(50);
			imgCheck.setWidth(50);
			imgCheck.setLayoutAlign(Alignment.CENTER);
			checkLayout.addMember(imgCheck);
			checksLayout.addMember(checkLayout);
		}
		for(int i=0;i<nbKO;i++)
		{
			VLayout checkLayout = new VLayout();
			checkLayout.setHeight(50);
			checkLayout.setWidth(160);
			Img imgCheck = new Img("delete.png");
			imgCheck.setHeight(50);
			imgCheck.setWidth(50);
			imgCheck.setLayoutAlign(Alignment.CENTER);
			checkLayout.addMember(imgCheck);
			checksLayout.addMember(checkLayout);
		}
	}
}
