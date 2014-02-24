package com.spicstome.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.spicstome.client.shared.Image;

public class CleanImages {
	
	private static String [] protectedImageFileNames = {
		"all.png", "default_article.png", "default_folder.png", "default_user.png", 
		"no-picture.jpg"
	};
	
	private static boolean isProtectedImage(String fileName) {
		for (String s: protectedImageFileNames) {
	      if (fileName.equals(s))
	    	  return true;
	    }
		return false;
	}
	
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
	                if ((!isImageUsed) && (!isProtectedImage(file.getName()))) {
	                	System.out.println("Suppression du fichier : "+file.getName());
	                	file.delete();
	                	nbImagesDeleted++;
	                }
	            }
	        }
        } else {
        	System.out.println("Dossier vide.");
        }
        
        System.out.println("Nombre de fichiers supprimées : "+nbImagesDeleted);
	}
}
