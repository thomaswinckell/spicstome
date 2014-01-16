package com.spicstome.server;

import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;


public class Test {
	
	
	public static void populateWithStudent(String name,String firstname,String login,String password)
	{
		Image imageRacine = new Image((long)-1);
		imageRacine.setFilename("all.png");
		
		HibernateManager.getInstance().save(imageRacine);
		
		Folder folderRacine = new Folder((long)-1);
		folderRacine.setName("Tout");
		folderRacine.setImage(imageRacine);
		
		HibernateManager.getInstance().save(folderRacine);
		
		Album album = new Album((long)-1);
		album.setFolder(folderRacine);
		
		HibernateManager.getInstance().save(album);
		
		Image imageUser = new Image((long)-1);
		imageUser.setFilename("default_user.png");
		
		HibernateManager.getInstance().save(imageUser);
		
		Student student = new Student((long)-1);
		student.setName(name);
		student.setFirstName(firstname);
		student.setEmail(name+"."+firstname+"@gmail.com");
		student.setLogin(login);
		student.setPassword(password);
		student.setImage(imageUser);
		student.setAlbum(album);
		
		HibernateManager.getInstance().save(student);
	}
	
	public static void main(String[] args) {
		
		/* Clear All */
		HibernateManager.getInstance().ClearAll();
		
		/* Super Admin */
		Image imageAdmin = new Image((long)-1);
		imageAdmin.setFilename("default_user.png");
		
		HibernateManager.getInstance().save(imageAdmin);
		
		Referent superAdmin = new Referent((long)-1);
		superAdmin.setName("admin");
		superAdmin.setFirstName("admin");
		superAdmin.setEmail("maxime.hass@gmail.com");
		superAdmin.setLogin("admin");
		superAdmin.setPassword("admin");
		superAdmin.setImage(imageAdmin);
		
		HibernateManager.getInstance().save(superAdmin);
		
		/* Student */
		populateWithStudent("Dagobert", "Albert", "albert", "albert");
		populateWithStudent("Hass", "Maxime", "mofo", "mofo");
		populateWithStudent("Winckell", "Thomas", "tomtom", "tomtom");
		

	}
	
	
}
