package com.spicstome.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.spicstome.client.shared.Image;

public class CleanImages {
	
	private static List<Image> getAllImages() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Image> images = new ArrayList<Image>(session.createCriteria(Image.class).list());
		session.getTransaction().commit();
		return images;
	}

	public static void main(String[] args) {
		
		List<Image> images = getAllImages();
		
		File uploadFolder = new File("war/images/upload");
        File[] fileList = uploadFolder.listFiles();
        
        boolean isImageUsed;
        int nbImagesDeleted = 0;

        if (fileList != null) {

	        for (File file : fileList) {
	            if ( !file.isDirectory() ) {	            	
	            	isImageUsed = false;
	                for (Image image : images) {
	                	if (image.getFilename().equals(file.getName())) {
	                		isImageUsed = true;
	                		break;
	                	}
	                }
	                if (!isImageUsed) {
	                	file.delete();
	                	nbImagesDeleted++;
	                }
	            }
	        }
        } else {
        	System.out.println("Dossier vide.");
        }
        
        System.out.println("Nombre d'images supprimées : "+nbImagesDeleted);
	}
}
