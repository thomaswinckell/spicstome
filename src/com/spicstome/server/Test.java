package com.spicstome.server;

import java.util.TreeSet;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;


public class Test {
	
	
	public static Student populateWithStudent(String name,String firstname,String login,String password)
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
		
		Image imageUser = populateWithImageUser();
		
		Student student = new Student((long)-1);
		student.setName(name);
		student.setFirstName(firstname);
		student.setEmail(name+"."+firstname+"@gmail.com");
		student.setLogin(login);
		student.setPassword(Encryption.toSHA256(password));
		student.setImage(imageUser);
		student.setAlbum(album);
		
		HibernateManager.getInstance().save(student);
		
		return student;
	}
	
	public static Image populateWithImageUser()
	{
		Image imageUser = new Image((long)-1);
		imageUser.setFilename("default_user.png");
		
		HibernateManager.getInstance().save(imageUser);
		
		return imageUser;
	}
	
	public static void main(String[] args) {
		
		/* Clear All */
		HibernateManager.getInstance().ClearAll();
		
		/* Super Admin */
		Image imageAdmin = populateWithImageUser();
		
		Referent superAdmin = new Referent((long)-1);
		superAdmin.setName("admin");
		superAdmin.setFirstName("admin");
		superAdmin.setEmail("maxime.hass@gmail.com");
		superAdmin.setLogin("admin");
		superAdmin.setPassword(Encryption.toSHA256("admin"));
		superAdmin.setImage(imageAdmin);
		
		HibernateManager.getInstance().save(superAdmin);
		
		/* General album  = 1 */
		
		/* Example album = 2 */
		
		/* Student */
		Student dagobert = populateWithStudent("Dagobert", "Albert", "albert", "albert");
		Student maxime = populateWithStudent("Hass", "Maxime", "mofo", "mofo");
		Student thomas = populateWithStudent("Winckell", "Thomas", "tomtom", "tomtom");
		
		/* Referant */
		Image imageReferant = populateWithImageUser();
		
		Referent referent = new Referent((long)-1);
		referent.setName("La Fripouile");
		referent.setFirstName("Jaquouille");
		referent.setEmail("visiteur@gmail.com");
		referent.setLogin("referent");
		referent.setPassword(Encryption.toSHA256("referent"));
		referent.setImage(imageReferant);
		
		referent.setStudents(new TreeSet<Student>());
		referent.addStudent(dagobert);
		referent.addStudent(maxime);
		referent.addStudent(thomas);
		
		HibernateManager.getInstance().save(referent);
		

	}
	
	
}
