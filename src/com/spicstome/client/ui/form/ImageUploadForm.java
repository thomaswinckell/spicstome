package com.spicstome.client.ui.form;

import gwtupload.client.IUploader;
import gwtupload.client.SingleUploader;
import gwtupload.client.Utils;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader.OnFinishUploaderHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.spicstome.client.ui.strings.MyUploaderConstants;

public class ImageUploadForm {

	private String imageFileName;
	private Img image;
	private IButton uploadButton;

	public ImageUploadForm (final int imageWidth, final int imageHeight) {

		super();

		imageFileName = FormUtils.DEFAULT_USER_IMAGE_FILENAME;

		image = new Img(FormUtils.UPLOAD_IMAGE_PATH+imageFileName, imageWidth, imageHeight);

		uploadButton = new IButton("Charger une nouvelle image");
		
		final IButton saveImageButton = new IButton("Sauvegarder");

		final Img uploaderImage = new Img(FormUtils.UPLOAD_IMAGE_PATH+FormUtils.DEFAULT_IMAGE_FILENAME, imageWidth, imageHeight);

		final SingleUploader uploader = new SingleUploader(FileInputType.BROWSER_INPUT);
		uploader.setValidExtensions("jpg","jpeg","png","bmp","gif");
		uploader.setI18Constants(new MyUploaderConstants());
		uploader.setAvoidRepeatFiles(true);

		String url = GWT.getModuleBaseURL()+"imageUpload";
		uploader.setServletPath(url);
		
		final Window window = new Window();
		window.setWidth(265);
		window.setHeight(290);
		window.setTitle("Chargement d'une image");
		window.centerInPage();
		
		uploaderImage.setLayoutAlign(Alignment.CENTER);
		saveImageButton.setLayoutAlign(Alignment.CENTER);
		
		saveImageButton.disable();

		saveImageButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				image.setSrc(FormUtils.UPLOAD_IMAGE_PATH+imageFileName);
				window.hide();
			}
		});

		window.addItem(uploaderImage);       
		window.addItem(uploader);
		window.addItem(saveImageButton);
		
		uploader.addOnFinishUploadHandler(new OnFinishUploaderHandler() {
			public void onFinish(IUploader uploader) {

				if (uploader.getStatus() == Status.SUCCESS) {
					String response = uploader.getServerResponse();
					//System.out.println(response);
					if (response != null) {
						Document doc = XMLParser.parse(response);
						String fileName = Utils.getXmlNodeValue(doc, "name");
						if (fileName != null) {
							imageFileName = fileName;
							uploaderImage.setSrc(FormUtils.UPLOAD_IMAGE_PATH+imageFileName);
							saveImageButton.enable();
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

		uploadButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				window.show();
			}
		});
	}

	public Img getImage() {
		return image;
	}

	public IButton getUploadButton() {
		return uploadButton;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName=imageFileName;
		image.setSrc(FormUtils.UPLOAD_IMAGE_PATH+imageFileName);
	}

	public String getImageFileName() {
		return imageFileName;
	}
}
