package com.spicstome.server;

import java.util.TreeSet;

import com.spicstome.client.shared.Adjective;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Noun;
import com.spicstome.client.shared.Pronoun;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;
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
	
	public static void generateSubject(int order,String name,String image,Folder parent,int gender,int number,int person,boolean isPronoun)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		
		
		
		if(isPronoun)
		{
			Pronoun pronoun = new Pronoun((long)-1);
			pronoun.setName(name);
			pronoun.setGender(gender);
			pronoun.setPerson(person);
			pronoun.setNumber(number);
			pronoun.setImage(imageSubject);
			pronoun.setFolder(parent);
			pronoun.setOrder(order);
			HibernateManager.getInstance().save(pronoun);
		}
		else
		{
			Noun noun = new Noun((long)-1);
			noun.setName(name);
			noun.setGender(gender);
			noun.setPerson(person);
			noun.setNumber(number);
			noun.setImage(imageSubject);
			noun.setFolder(parent);
			noun.setOrder(order);
			HibernateManager.getInstance().save(noun);
		}
		
		
	}
	
	public static void generateVerb(int order,String name,String image,Folder parent,int negation,int group,int type,
			String irregular1,String irregular2,String irregular3,String irregular4,String irregular5,String irregular6)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Verb verb = new Verb((long)-1);
		verb.setName(name);
		verb.setNegation(negation);
		verb.setGroup(group);
		verb.setType(type);
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
	
	public static void generateAdjective(int order,String name,String image,Folder parent,
			String matching1,String matching2,String matching3,String matching4)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Adjective adjective = new Adjective((long)-1);
		adjective.setName(name);
		
		adjective.setMatching1(matching1);
		adjective.setMatching2(matching2);
		adjective.setMatching3(matching3);
		adjective.setMatching4(matching4);

		
		adjective.setImage(imageSubject);
		adjective.setFolder(parent);
		adjective.setOrder(order);
		
		HibernateManager.getInstance().save(adjective);
	}
	
	public static Album generateAlbum(Type type)
	{
		Folder racine = generateFolder(0,"Tout", "all.png", null);
		
			Folder qui = generateFolder(0,"Qui", "qui.gif", racine);	
			
				Folder commercants = generateFolder(0,"Commerçants", "commercants.gif", qui);
				
					generateSubject(0,"Boulanger", "boulanger.gif", commercants, 0, 0, 2,false);
					generateSubject(1,"Coiffeur", "coiffeur.gif", commercants, 0, 0, 2,false);
					
				generateSubject(0,"Je", "je_1.JPG", qui, 0, 0, 0,true);
				generateSubject(1,"Je", "je_2.JPG", qui, 1, 0, 0,true);
				generateSubject(2,"Tu", "tu_1.JPG", qui, 0, 0, 1,true);
				generateSubject(3,"Tu", "tu_2.JPG", qui, 1, 0, 1,true);	
				
				if(type==Type.GENERAL)
				{		
					generateSubject(4,"Vous", "vous_1.JPG", qui, 0, 1, 1,true);
					generateSubject(5,"Vous", "vous_2.JPG", qui, 1, 1, 1,true);
					generateSubject(6, "Parent", "parent.gif", qui, 0, 1, 2,false);
					generateSubject(7, "Père", "pere.gif", qui, 0, 0, 2,false);
					generateSubject(8, "Mère", "mere.gif", qui, 1, 0, 2,false);
				}
				
			Folder quoi = generateFolder(1,"Quoi", "quoi.gif", racine);
			
			generateVerb(0,"Etre", "etre.gif", quoi, 0,2,2,"suis","es","est","sommes","êtes","sont");
			
				if(type==Type.GENERAL)
				{
					generateVerb(1,"Dormir", "dormir.gif", quoi, 0,2,0,"dors","dors","dort","dormons","dormez","dorment");
					generateVerb(2,"Dire", "dire.gif", quoi, 0,2,0,"dis","dis","dit","disons","dites","disent");
					generateVerb(3,"Vouloir", "vouloir.JPG", quoi, 0,2,1,"veux","veux","veut","voulons","voulez","veulent");
					generateVerb(4,"Aimer", "aimer.gif", quoi, 0,0,3,"","","","","","");
					generateVerb(5,"Ne pas vouloir", "nepasvouloir.JPG", quoi, 1,2,1,"veux","veux","veut","voulons","voulez","veulent");
					generateVerb(6,"Ecouter", "ecouter.gif", quoi, 0,0,0,"","","","","","");
				}
			
				Folder nouriture = generateFolder(0,"Nourriture", "nourriture.gif", quoi);
				
					generateSubject(0,"Cerises", "cerises.gif", nouriture, 1, 1, 2,false);
					
					if(type==Type.GENERAL)
					{
						generateSubject(1, "Abricot", "abricot.gif", nouriture, 1, 0, 2,false);
					}
					
				Folder boissons = generateFolder(1,"Boissons", "boissons.gif", quoi);
				
					generateSubject(0,"Bouteille de coca", "bouteille de coca.gif", boissons, 1, 0, 2,false);	
							
			Folder comment = generateFolder(2,"Comment", "comment.gif", racine);
			
					generateAdjective(0,"Fatigué", "fatiguer.gif", comment,"fatigué","fatigués","fatiguée","fatiguées");	
					generateAdjective(0,"Heureux", "heureux.jpg", comment,"heureux","heureux","heureuse","heureuses");	
			
			if(type==Type.GENERAL)
			{
				Folder ou = generateFolder(3,"Ou", "ou.gif", racine);
				
					generateSubject(0,"Boucherie", "boucherie.gif", ou, 1, 0, 2,false);	
					generateSubject(0,"Boulangerie", "boulangerie.gif", ou, 1, 0, 2,false);
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
