package com.spicstome.client.ui.form;

import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.Utils;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader.OnCancelUploaderHandler;
import gwtupload.client.IUploader.OnFinishUploaderHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;
import com.spicstome.client.ui.strings.MyUploaderConstants;

public class ImageUploadForm extends DynamicForm {
	
	private DataSourceTextField userImageFileName;
	private Img userImage;
	private DataSource dataSource;
	
	private int imageWidth, imageHeight;

	public ImageUploadForm (int imageWidth, int imageHeight, VLayout vLayout) {
		
		super();
		
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		
		dataSource = new DataSource();
	    //dataSource.setID("imageUpload");
	    
	    userImageFileName = new DataSourceTextField("image_filename");
	    userImageFileName.setHidden(true);
	    
	    dataSource.setFields(userImageFileName);
		
		userImage = new Img(FormUtils.UPLOAD_IMAGE_PATH+FormUtils.DEFAULT_USER_IMAGE_FILENAME, imageWidth, imageHeight);
	    
        SingleUploader uploader = new SingleUploader(FileInputType.BROWSER_INPUT);
        uploader.setValidExtensions("jpg","jpeg","png","bmp");
        uploader.setI18Constants(new MyUploaderConstants());
        uploader.setAvoidRepeatFiles(true);
        
        String url = GWT.getModuleBaseURL()+"imageUpload";
        uploader.setServletPath(url);
        
        uploader.addOnFinishUploadHandler(new OnFinishUploaderHandler() {
            public void onFinish(IUploader uploader) {

                 if (uploader.getStatus() == Status.SUCCESS) {
                       String response = uploader.getServerResponse();
                       //System.out.println(response);
                       if (response != null) {
                       	Document doc = XMLParser.parse(response);
                       	String fileName = Utils.getXmlNodeValue(doc, "name");
                       	if (fileName != null) {
                       		userImageFileName.setAttribute("value", fileName);
                       		userImage.setSrc(FormUtils.UPLOAD_IMAGE_PATH+fileName);
                       	}
                       } else {
                       	SC.say("Unaccessible server response");
                       }

                       uploader.reset();
                 } else {
                       SC.say("Statut du chargement : \n" + uploader.getStatus());
                 }

            }
       });
       // this will be called when the cancel button will be clicked
       uploader.addOnCancelUploadHandler(new OnCancelUploaderHandler() {
            public void onCancel(IUploader uploader) {
            	userImage.setSrc(FormUtils.UPLOAD_IMAGE_PATH+userImageFileName.getAttribute("value"));
            }
       });
       
       this.setDataSource(dataSource);
       
       vLayout.addMember(userImage);
	   vLayout.addMember(uploader);
	   vLayout.addMember(this);
	}
	
	public void setImageFileName(String imageFileName) {
	    userImageFileName.setAttribute("value", imageFileName);
	    userImage = new Img(FormUtils.UPLOAD_IMAGE_PATH+imageFileName, imageWidth, imageHeight);
	}
	
	public String getImageFileName() {
		return userImageFileName.getAttribute("value");
	}
}
