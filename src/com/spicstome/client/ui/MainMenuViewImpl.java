package com.spicstome.client.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;


public class MainMenuViewImpl extends SpicsToMeComposite implements MainMenuView
{
	String name;
	Label label = new Label("");
	ArrayList<ModuleComposite> modules;
	HorizontalPanel modulesPanel = new HorizontalPanel();

	public MainMenuViewImpl()
	{
		super();
		modules = new ArrayList<ModuleComposite>();
		modules.add(new ModuleComposite("images/albumlogo.png","Gestion d'album"));
		modules.add(new ModuleComposite("images/userlogo.png","Gestion des utilisateurs"));
		
		mainPanel.add(label);
		
		for(int i=0;i<modules.size();i++)
		{
			modulesPanel.add(modules.get(i));
		}
		
		mainPanel.add(modulesPanel);
		
		
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
		label.setText("Bienvenue "+name+",");
	}
	
	
}
