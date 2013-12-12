package com.spicstome.server;


import java.util.Date;

import com.spicstome.client.hibernate.HibernateManager;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Pecs;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.User;

public class Test {
	
	public static void main(String[] args) {
		
		/* Sauvegarde étudiant */
		Folder mFolder = new Folder("nom", 0, null, new Image("Test.jpg"));
		
		Album mAlbum = new Album(mFolder);
		
		Student mStudent = new Student(new Date(), "login", "password", new Image("image.jpg"), mAlbum);
		
		HibernateManager.getInstance().save(mStudent);
		
		
	}
}
