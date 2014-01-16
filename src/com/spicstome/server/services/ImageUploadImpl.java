package com.spicstome.server.services;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

public class ImageUploadImpl extends UploadAction {

	private static final long serialVersionUID = 7971828116206411598L;

	@Override
	public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) {

		String response = null ;
		FileItem image = sessionFiles.get(0);
		
		if (!image.isFormField()) {
			try {

				// we can save the received file
				String fileName = image.getName().substring(image.getName().lastIndexOf(File.separator) + 1);

				File file = new File("./images/upload/"+fileName);
				
				int i = 0;
				while (file.exists()) {
					i++;
					file = new File("./images/upload/"+i+fileName);
				}
				
				image.write(file);

				response = fileName;
				
				if (i>1)
					response = i + response;

			} catch (Exception e) {
				throw new UploadActionException(e.getMessage());
			}
		}
		
		return response;
	}
}