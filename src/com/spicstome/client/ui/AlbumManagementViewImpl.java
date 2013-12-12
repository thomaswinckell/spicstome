package com.spicstome.client.ui;


import java.util.ArrayList;
import java.util.List;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.spicstome.client.place.AlbumPlace;
import com.spicstome.client.shared.Album;
import com.spicstome.client.ui.composite.CRUDListItemComposite;
import com.spicstome.client.ui.composite.ChoiceListItemComposite;
import com.spicstome.client.ui.composite.ItemComposite;
import com.spicstome.client.ui.composite.UserViewComposite;



public class AlbumManagementViewImpl extends UserViewComposite  implements AlbumManagementView
{
	
	CRUDListItemComposite albumUser;
	ChoiceListItemComposite choice;
	
	public AlbumManagementViewImpl()
	{
		super();
		
		ArrayList<ItemComposite> modules = new ArrayList<ItemComposite>();
		modules.add(new ItemComposite("images/albumlogo.png","Album de Albert",42,true));
		modules.add(new ItemComposite("images/albumlogo.png","Album de Maxime",42,true));
		modules.add(new ItemComposite("images/albumlogo.png","Album de Robert",42,true));
		modules.add(new ItemComposite("images/albumlogo.png","Album de Jean-Claude",42,true));

		albumUser = new CRUDListItemComposite();
		albumUser.setList(modules);
		//albumUser.setMultiSelection(true);
		//choice = new ChoiceListItem(modules);
		
		
		
		mainPanel.add(albumUser);
		//mainPanel.add(choice);
		
		
		albumUser.buttonView.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				ItemComposite selection;
				
				if(albumUser.HasSelection())
				{
					selection = albumUser.GetOneSelection();
					System.out.println(selection.data);
					
					listener.goTo(new AlbumPlace());
				}
			}
		});
		
		
	}

	

	@Override
	public void setAlbum(List<Album> list) {
		
		
		
	}



	

}
