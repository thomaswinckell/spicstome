package com.spicstome.server;

import java.util.TreeSet;

import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.Subject;
import com.spicstome.client.shared.Verb;


public class Test {
	
	
	public static Student populateWithStudent(String name,String firstname,String login,String password)
	{
		Album album = generateExampleAlbum();
		
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
	
	public static void generateSubject(String name,String image,Folder parent,int gender,int number,int nature)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Subject subject = new Subject((long)-1);
		subject.setName(name);
		subject.setGender(gender);
		subject.setNature(nature);
		subject.setNumber(number);
		subject.setImage(imageSubject);
		subject.setFolder(parent);
		
		HibernateManager.getInstance().save(subject);
		
	}
	
	public static void generateVerb(String name,String image,Folder parent,int group)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Verb subject = new Verb((long)-1);
		subject.setName(name);
		subject.setGroup(group);
		subject.setImage(imageSubject);
		subject.setFolder(parent);
		
		HibernateManager.getInstance().save(subject);
	}
	
	public static void generateGeneralAlbum()
	{
		Folder racine = generateFolder("Tout", "all.png", null);
		Folder qui = generateFolder("Qui", "qui.gif", racine);
		Folder quoi = generateFolder("Quoi", "quoi.gif", racine);
		Folder comment = generateFolder("Comment", "comment.gif", racine);
		
		Folder couverts = generateFolder("Couverts", "couverts.gif", quoi);
		Folder choses = generateFolder("Choses", "choses.gif", quoi);
		Folder nouriture = generateFolder("Nourriture", "nourriture.gif", quoi);
		Folder commercants = generateFolder("Commerçants", "commercants.gif", qui);
		
		generateSubject("Je", "je_1.JPG", qui, 0, 0, 0);
		generateSubject("Je", "je_2.JPG", qui, 1, 0, 0);
		generateSubject("Tu", "tu_1.JPG", qui, 0, 0, 1);
		generateSubject("Tu", "tu_2.JPG", qui, 1, 0, 1);
		
		generateVerb("Ecouter", "ecouter.gif", quoi, 0);
		generateVerb("Dormir", "dormir.gif", quoi, 0);
		generateVerb("Dire", "dire.gif", quoi, 0);
		
		generateSubject("Cerises", "cerises.gif", nouriture, 1, 1, 2);
		
		generateSubject("Boulanger", "boulanger.gif", commercants, 0, 0, 2);

		Album album = new Album((long)-1);
		album.setFolder(racine);
		
		HibernateManager.getInstance().save(album);
	}
	
	
	
	public static Folder generateFolder(String name,String image,Folder parent)
	{
		Image imageRacine = new Image((long)-1);
		imageRacine.setFilename(image);
		
		HibernateManager.getInstance().save(imageRacine);
		
		Folder folder = new Folder((long)-1);
		folder.setName(name);
		folder.setImage(imageRacine);
		folder.setFolder(parent);
		
		HibernateManager.getInstance().save(folder);
		
		
		
		return folder;
	}
	
	public static Album generateExampleAlbum()
	{
	
		Folder racine = generateFolder("Tout", "all.png", null);
		Folder qui = generateFolder("Dossier", "qui.gif", racine);
	
		

		Album album = new Album((long)-1);
		album.setFolder(racine);
		
		HibernateManager.getInstance().save(album);
		
		return album;
	
	}
	
	public static void main(String[] args) {
		
		/* Clear All */
		HibernateManager.getInstance().ClearAll();
		
		
		/* General album  = 1 */
		
		generateGeneralAlbum();
		
		/* Example album = 2 */
		
		generateExampleAlbum();
		
		
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
