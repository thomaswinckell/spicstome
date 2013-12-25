package com.spicstome.client.ui.widget;

import java.util.ArrayList;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.FocusChangedEvent;
import com.smartgwt.client.widgets.events.FocusChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.ui.widget.ImageTileGrid.Mode;


public class Book extends VLayout{
	
	protected ArrayList<ImageTileGrid> listPage= new ArrayList<ImageTileGrid>();
	protected HLayout horizontalPagePanel = new HLayout();
	protected HLayout horizontalNextPrevPanel = new HLayout();
	protected IconButton buttonNext = new IconButton("");
	protected IconButton buttonPrev = new IconButton("");
	public int imageSize;
	
	int nbRowPerPage=2;
	int nbColPerPage=2;
	
	int iLeftPage=0;
	int lastPage=0;
	
	public Book(int imageSize) {
		
		super();
		
		setMargin(20);
		this.imageSize=imageSize;
		
		buttonNext.setIcon("next-icon.png");
		buttonPrev.setIcon("prev-icon.png");
		
		buttonNext.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				iLeftPage+=2;
				UpdatePage();
			}
		});
		
		buttonPrev.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				iLeftPage-=2;
				UpdatePage();
			}
		});
		

		horizontalNextPrevPanel.addMember(buttonPrev);
		horizontalNextPrevPanel.addMember(buttonNext);
		
		addMember(horizontalPagePanel);
		addMember(horizontalNextPrevPanel);
		

		UpdateNextPrev();
	}
	

	public void setList(ArrayList<ImageRecord> list)
	{
		listPage.clear();
		iLeftPage=0;
		int nbElementPerPage = nbColPerPage*nbRowPerPage;
		lastPage = (list.size()/nbElementPerPage);
		
		if(lastPage>0)
		{
			if(list.size()%(lastPage*nbElementPerPage)!=0)
				lastPage++;
		}
		if(lastPage==0)
			lastPage=2;
		if(lastPage%2==1)
			lastPage++;

		
		for(int i=0;i<lastPage;i++)
		{
			
			
			int tileWidth  = imageSize+20;
			int tileHeight = imageSize+20;
			
			final ImageTileGrid imageList = new ImageTileGrid(Mode.DRAG,tileWidth,tileHeight,imageSize){};
			
			imageList.addFocusChangedHandler(new FocusChangedHandler() {
				
				@Override
				public void onFocusChanged(FocusChangedEvent event) {

					if(!event.getHasFocus())
					{
						if(imageList.getSelectedItem()!=null)
						{
							imageList.deselectRecord(imageList.getSelectedItem());
						}			
					}
				}

			});

			imageList.setWidth((nbColPerPage*(tileWidth+20)));
			imageList.setHeight((nbRowPerPage*(tileHeight+20)));

			imageList.setStyleName("page");
			
			listPage.add(imageList);
		}
		
		
		for(int i=0;i<list.size();i++)
		{
			int nPage = i/nbElementPerPage;
				 
			//int d = i - (nPage*nbElementPerPage);
			
			//int nRow = d / nbColPerPage;
			//int nCol = d-(nRow*nbColPerPage);

			listPage.get(nPage).addItem(list.get(i));
			
		}

		UpdatePage();
	
	}
	
	public void UpdateNextPrev()
	{
		buttonPrev.setVisible(iLeftPage>0);		
		buttonNext.setVisible(iLeftPage+2<lastPage);
	}
	
	public void UpdatePage()
	{
		Canvas[] children = horizontalPagePanel.getChildren();
		horizontalPagePanel.removeMembers(children);
	
		
		UpdateNextPrev();
			
		horizontalPagePanel.addMember(listPage.get(iLeftPage));
		horizontalPagePanel.addMember(listPage.get(iLeftPage+1));
		
		//listPage.get(iLeftPage).animShow();
		//listPage.get(iLeftPage+1).animShow();
	}

}
