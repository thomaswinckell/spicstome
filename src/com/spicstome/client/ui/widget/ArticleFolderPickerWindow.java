package com.spicstome.client.ui.widget;

import java.util.ArrayList;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.spicstome.client.shared.Album;




public class ArticleFolderPickerWindow extends Window{

	public AlbumPanel albmBook;
	DynamicForm form = new DynamicForm();
	ComboBoxItem comboBox = new ComboBoxItem("owner","Album");
    IconButton validButton = new IconButton("");
    VLayout verticalLayout = new VLayout();
    HLayout bottomLayout = new HLayout();
    Book book;
    
    public enum Mode{ARTICLE, FOLDER}
    Mode mode;
	
	public ArticleFolderPickerWindow(ArrayList<Album> listAlbum,Mode mode) {
		super();
				
		
		if(mode==Mode.ARTICLE)
		{
			 setWidth(1000);
			 setHeight(450);
		}
		else if(mode==Mode.FOLDER)
		{
			 setWidth(400);
			 setHeight(500);
		}
		
         
        
        setTitle("Import depuis autre album");
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        
        setDismissOnOutsideClick(true);
        
        
        comboBox.setValueMap("Général","Albert","Robert");
        comboBox.setValue("Générale");
        
        comboBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
			
		          //String selectedItem = (String) event.getValue();  
		          albmBook.setAlbum(new Album());
				
			}
		});
        
        form.setFields(comboBox);
        
        
        book = new Book(50){
        	@Override
        	public void onSelectChangeBook(ImageRecord image)
        	{
        		super.onSelectChangeBook(image);
        		
        		UpdateValidButton();
        	}
        	
        	@Override
        	public void onChangePage()
        	{
        		super.onChangePage();
        		
        		UpdateValidButton();
        	}
        };
        
        if(mode==Mode.FOLDER)
        {
        	albmBook = new AlbumPanel() {
        		@Override
            	public boolean onFolderClick(NodeClickEvent event)
            	{
            		boolean res = super.onFolderClick(event);
            		
            		UpdateValidButton();
            			
            		return res;
            	}
			};
		
        }
        else if(mode==Mode.ARTICLE)
        {
        	albmBook =  new AlbumBookPanel(book){
            	@Override
            	public boolean onFolderClick(NodeClickEvent event)
            	{
            		boolean res = super.onFolderClick(event);
            		
            		UpdateValidButton();
            			
            		return res;
            	}
            };
            
           
        }
        
        albmBook.setAlbum(new Album());
  
        validButton.setIcon("check.png");
        int iconsize=32;
        validButton.setIconSize(iconsize);

        validButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
						
						destroy();					
			}
		});
        
        validButton.setVisible(false);
        
        setMode(mode);
      
        bottomLayout.setHeight(iconsize);
        bottomLayout.setWidth100();
        bottomLayout.addMember(validButton);

        
        verticalLayout.addMember(form);
        verticalLayout.addMember(albmBook);
        verticalLayout.addMember(bottomLayout);
        
        addItem(verticalLayout);
       
	}
	
	public void setMode(Mode mode)
	{
		this.mode=mode;
		
		if(mode==Mode.ARTICLE)
			validButton.setTitle("Importer cet article");
		else if(mode==Mode.FOLDER)
			validButton.setTitle("Importer ce dossier");
	}
	
	public void UpdateValidButton()
	{
		if(mode==Mode.ARTICLE)
			validButton.setVisible(book.selectedImage!=null);
		else if(mode==Mode.FOLDER)
			validButton.setVisible(albmBook.folderTree.selectFolderId!=-1);
	}
}
