package com.spicstome.client.ui.window;

import java.util.ArrayList;



import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.dto.FolderDTO;
import com.spicstome.client.dto.PecsDTO;
import com.spicstome.client.dto.WordDTO;
import com.spicstome.client.ui.widget.ImageRecord;
import com.spicstome.client.ui.widget.ImageTileGrid;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;

public class SearchWordWindow extends Window {

	VLayout layout = new VLayout();
	ImageTileGrid wordGrid;
	IconButton valid = new IconButton("Selectionner ce mot");
	Label labelNothing = new Label();
	ArrayList<WordDTO> words = new ArrayList<WordDTO>();
	DynamicForm form = new DynamicForm();
	TextItem item = new TextItem();
	public WordDTO selectedWord;
	
	public SearchWordWindow(FolderDTO racine) {
		super();
		
		setWidth(800);
		setHeight(600);
		
		setTitle("Rechercher un mot");

        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
        setDismissOnOutsideClick(true);
		
		
		wordGrid = new ImageTileGrid(Mode.CLICK,200,100,60){

			@Override
			public void OnSelectChanged(ImageRecord object) {
				super.OnSelectChanged(object);
	
				updateValid();
			};
		};
		
		fillWordsList(racine);
		
		updateWordsList("");
		updateValid();
		
		item.setTitle("Rechercher");
		form.setItems(item);
		
		item.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				
				if(event.getValue()!=null)
					updateWordsList(event.getValue().toString());
				else
					updateWordsList("");

				updateValid();
			}
		});
		
		valid.setIconSize(50);
		valid.setIcon("check.png");
		
		valid.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) 
			{
				selectedWord = (WordDTO)wordGrid.getSelectedItem().getAttributeAsObject(ImageRecord.DATA);
				destroy();
			}
		});
		
		labelNothing.setContents("Aucun mot ne correspond à votre recherche");
		labelNothing.setHeight(40);
		
		layout.addMember(form);
		layout.addMember(labelNothing);
		layout.addMember(wordGrid);
		layout.addMember(valid);
		addItem(layout);
	}
	
	private void updateValid()
	{
		valid.setVisible(wordGrid.getSelectedItem()!=null);
	}
	
	private void updateWordsList(String s)
	{
		ArrayList<ImageRecord> wordsRecord = new ArrayList<ImageRecord>();
		
		for(WordDTO wordDTO:words)
		{
			if(wordDTO.getName().matches("^"+s+".*") || s.equals(""))
			{
				wordsRecord.add(new ImageRecord(wordDTO));
			}
				
		}
		
		labelNothing.setVisible(wordsRecord.size()==0);
		
		wordGrid.setItems(wordsRecord);
		
		
		

	}
	
	private void fillWordsList(FolderDTO folder)
	{
		for(PecsDTO pecs:folder.getContent())
		{
			if(pecs instanceof WordDTO)
			{
				words.add((WordDTO)pecs);
			}
			else
			{
				fillWordsList((FolderDTO)pecs);
			}
		}
	}
}
