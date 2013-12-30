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
	public int heightPage; 
	public int widthPage;
	public int tileWidth;
	public int tileHeight;
			
	public ImageRecord selectedImage=null;
	
	int nbRowPerPage=2;
	int nbColPerPage=2;
	
	int iLeftPage=0;
	int lastPage=0;
	
	public Book(int imageSize) {
		
		super();
		
		setMargin(20);
		this.imageSize=imageSize;
		
		tileWidth  = imageSize+20;
		tileHeight = imageSize+20;
		
		widthPage =(nbColPerPage*(tileWidth+20));
		heightPage = (nbRowPerPage*(tileHeight+20));
		
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
		
		horizontalNextPrevPanel.setHeight(28);
		
		addMember(horizontalPagePanel);
		addMember(horizontalNextPrevPanel);
		

		UpdateNextPrev();
	}
	

	public void setList(ArrayList<ImageRecord> list)
	{
		selectedImage=null;
		
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

			final Book book=this;
			
			final ImageTileGrid imageList = new ImageTileGrid(Mode.DRAG,tileWidth,tileHeight,imageSize){
				
				@Override
				public void OnSelectChanged(ImageRecord object)
				{
					book.onSelectChangeBook(object);
				}
			};
			
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
			
			imageList.setWidth(widthPage);
			imageList.setHeight(heightPage);

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
		
		
		onChangePage();
		
		//listPage.get(iLeftPage).animShow();
		//listPage.get(iLeftPage+1).animShow();
	}
	
	public void onChangePage()
	{
		selectedImage=null;
	}
	
	public void onSelectChangeBook(ImageRecord image)
	{
		if(image!=null)
			selectedImage=image;
	}

}
