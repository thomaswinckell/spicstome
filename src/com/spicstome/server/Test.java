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
	
	public enum Type {EXAMPLE,GENERAL};
	
	public static Student populateWithStudent(String name,String firstname,String login,String password)
	{
		Album album = generateAlbum(Type.EXAMPLE);
		
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
	
	public static void generateSubject(int order,String name,String image,Folder parent,int gender,int number,int nature)
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
		subject.setOrder(order);
		
		HibernateManager.getInstance().save(subject);
		
	}
	
	public static void generateVerb(int order,String name,String image,Folder parent,int group,
			String irregular1,String irregular2,String irregular3,String irregular4,String irregular5,String irregular6)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Verb verb = new Verb((long)-1);
		verb.setName(name);
		verb.setGroup(group);
		verb.setIrregular1(irregular1);
		verb.setIrregular2(irregular2);
		verb.setIrregular3(irregular3);
		verb.setIrregular4(irregular4);
		verb.setIrregular5(irregular5);
		verb.setIrregular6(irregular6);
		
		verb.setImage(imageSubject);
		verb.setFolder(parent);
		verb.setOrder(order);
		
		HibernateManager.getInstance().save(verb);
	}
	
	public static Album generateAlbum(Type type)
	{
		Folder racine = generateFolder(0,"Tout", "all.png", null);
		
			Folder qui = generateFolder(0,"Qui", "qui.gif", racine);	
			
				Folder commercants = generateFolder(0,"Commerçants", "commercants.gif", qui);
				
					generateSubject(0,"Boulanger", "boulanger.gif", commercants, 0, 0, 2);
					generateSubject(1,"Coiffeur", "coiffeur.gif", commercants, 0, 0, 2);
					
				generateSubject(0,"Je", "je_1.JPG", qui, 0, 0, 0);
				generateSubject(1,"Je", "je_2.JPG", qui, 1, 0, 0);
				generateSubject(2,"Tu", "tu_1.JPG", qui, 0, 0, 1);
				generateSubject(3,"Tu", "tu_2.JPG", qui, 1, 0, 1);	
				
				if(type==Type.GENERAL)
				{		
					generateSubject(4,"Vous", "vous_1.JPG", qui, 0, 1, 1);
					generateSubject(5,"Vous", "vous_2.JPG", qui, 1, 1, 1);
					generateSubject(6, "Parent", "parent.gif", qui, 1, 1, 2);
					generateSubject(7, "Père", "pere.gif", qui, 0, 0, 2);
					generateSubject(8, "Mère", "mere.gif", qui, 1, 0, 2);
				}
				
			Folder quoi = generateFolder(1,"Quoi", "quoi.gif", racine);
			
				if(type==Type.GENERAL)
				{
					generateVerb(0,"Ecouter", "ecouter.gif", quoi, 0,"","","","","","");
					generateVerb(1,"Dormir", "dormir.gif", quoi, 2,"dors","dors","dort","dormons","dormez","dorment");
					generateVerb(2,"Dire", "dire.gif", quoi, 2,"dis","dis","dit","disons","dites","disent");
					generateVerb(3,"Etre", "etre.gif", quoi, 2,"suis","es","est","sommes","êtes","sont");
				}
			
				Folder nouriture = generateFolder(0,"Nourriture", "nourriture.gif", quoi);
				
					generateSubject(0,"Cerises", "cerises.gif", nouriture, 1, 1, 2);
					
					if(type==Type.GENERAL)
					{
						generateSubject(1, "Abricot", "abricot.gif", nouriture, 1, 0, 2);
					}
					
				Folder boissons = generateFolder(1,"Boissons", "boissons.gif", quoi);
				
					generateSubject(0,"Bouteille de coca", "bouteille de coca.gif", boissons, 1, 0, 2);	
							
			Folder comment = generateFolder(2,"Comment", "comment.gif", racine);
			
					generateSubject(0,"Fatigué", "fatiguer.gif", comment, 1, 0, 2);	
			
			if(type==Type.GENERAL)
			{
				Folder ou = generateFolder(3,"Ou", "ou.gif", racine);
				
					generateSubject(0,"Boucherie", "boucherie.gif", ou, 1, 0, 2);	
					generateSubject(0,"Boulangerie", "boulangerie.gif", ou, 1, 0, 2);
			}
			
			
		
		Album album = new Album((long)-1);
		album.setFolder(racine);
		
		HibernateManager.getInstance().save(album);
		
		return album;
	}
	
	
	
	public static Folder generateFolder(int order,String name,String image,Folder parent)
	{
		Image imageRacine = new Image((long)-1);
		imageRacine.setFilename(image);
		
		HibernateManager.getInstance().save(imageRacine);
		
		Folder folder = new Folder((long)-1);
		folder.setName(name);
		folder.setImage(imageRacine);
		folder.setFolder(parent);
		folder.setOrder(order);
		
		HibernateManager.getInstance().save(folder);
		
		
		
		return folder;
	}
	
	
	public static void main(String[] args) {
		
		/* Clear All */
		HibernateManager.getInstance().ClearAll();
		
		
		/* General album  = 1 */
		
		generateAlbum(Type.GENERAL);
		
		/* Example album = 2 */
		
		generateAlbum(Type.EXAMPLE);
		
		
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
