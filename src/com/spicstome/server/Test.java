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
	
	
	
	public static void generateNoun(int order,String name,String image,Folder parent,int gender,int number)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Noun noun = new Noun((long)-1);
		noun.setName(name);
		noun.setGender(gender);
		noun.setNumber(number);
		noun.setImage(imageSubject);
		noun.setFolder(parent);
		noun.setOrder(order);
		HibernateManager.getInstance().save(noun);
		
		
		
		
		
	}
	
	public static void generatePronoun(int order,String name,String image,Folder parent,int gender,int number,int person)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
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
	
	
	public static void generateVerb(int order,String name,String image,Folder parent,int negation,int group,
			String irregular1,String irregular2,String irregular3,String irregular4,String irregular5,String irregular6)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Verb verb = new Verb((long)-1);
		verb.setName(name);
		verb.setNegation(negation);
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
				
					generateNoun(0,"le boulanger", "boulanger.gif", commercants, 0, 0);
					generateNoun(1,"le coiffeur", "coiffeur.gif", commercants, 0, 0);
					
				generatePronoun(0,"je", "je_1.JPG", qui, 0, 0, 0);
				generatePronoun(1,"je", "je_2.JPG", qui, 1, 0, 0);
				generatePronoun(2,"tu", "tu_1.JPG", qui, 0, 0, 1);
				generatePronoun(3,"tu", "tu_2.JPG", qui, 1, 0, 1);	
				
				if(type==Type.GENERAL)
				{	
					
					generatePronoun(4,"vous", "vous_1.JPG", qui, 0, 1, 1);
					generatePronoun(5,"vous", "vous_2.JPG", qui, 1, 1, 1);
					generateNoun(6, "mes parent", "parent.gif", qui, 0, 1);
					generateNoun(7, "mon papa", "pere.gif", qui, 0, 0);
					generateNoun(8, "ma maman", "mere.gif", qui, 1, 0);
					generatePronoun(9,"nous", "nous_1.JPG", qui, 0, 1, 0);
					generatePronoun(10,"nous", "nous_2.JPG", qui, 1, 1, 0);
				}
				
			Folder quoi = generateFolder(1,"Quoi", "quoi.gif", racine);
			
			generateVerb(0,"être", "etre.gif", quoi, 0,2,"suis","es","est","sommes","êtes","sont");
			generateVerb(1,"ne pas être", "nepasetre.JPG", quoi, 1,2,"suis","es","est","sommes","êtes","sont");
			generateVerb(2,"aimer", "aimer.gif", quoi, 0,0,"","","","","","");
			generateVerb(3,"ne pas aimer", "nepasaimer.gif", quoi, 1,0,"","","","","","");
			generateVerb(4,"manger", "manger.gif", quoi, 0,0,"","","","","","");
			
				if(type==Type.GENERAL)
				{
				
					generateVerb(5,"dire", "dire.gif", quoi, 0,2,"dis","dis","dit","disons","dites","disent");
					generateVerb(6,"vouloir", "vouloir.JPG", quoi, 0,2,"veux","veux","veut","voulons","voulez","veulent");
					generateVerb(7,"ne pas vouloir", "nepasvouloir.JPG", quoi, 1,2,"veux","veux","veut","voulons","voulez","veulent");
					generateVerb(8,"écouter", "ecouter.gif", quoi, 0,0,"","","","","","");
					generateVerb(9,"savoir", "savoir.jpg", quoi, 0,2,"sais","sais","sait","savons","savez","savent");
					generateVerb(10,"ne pas savoir", "nepassavoir.JPG", quoi, 1,2,"sais","sais","sait","savons","savez","savent");
					generateVerb(11,"lire", "lire.gif", quoi, 0,2,"lis","lis","lit","lisons","lisez","lisent");
					generateVerb(12,"dormir", "dormir.gif", quoi, 0,2,"dors","dors","dort","dormons","dormez","dorment");
				}
			
				Folder nouriture = generateFolder(0,"Nourriture", "nourriture.gif", quoi);
				
					generateNoun(0,"les cerises", "cerises.gif", nouriture, 1, 1);
					
					if(type==Type.GENERAL)
					{
						generateNoun(1, "l'abricot", "abricot.gif", nouriture, 0, 0);
					}
					
				Folder boissons = generateFolder(1,"Boissons", "boissons.gif", quoi);
				
					generateNoun(0,"la bouteille de coca", "bouteille de coca.gif", boissons, 1, 0);	
					
				Folder choses = generateFolder(2,"Choses", "choses.gif", quoi);
				
					generateNoun(0,"un livre", "livre.gif", choses, 0, 0);	
							
			Folder comment = generateFolder(2,"Comment", "comment.gif", racine);
			
					generateAdjective(0,"fatigué", "fatiguer.gif", comment,"fatigué","fatigués","fatiguée","fatiguées");	
					generateAdjective(0,"heureux", "heureux.jpg", comment,"heureux","heureux","heureuse","heureuses");	
			
			if(type==Type.GENERAL)
			{
				Folder ou = generateFolder(3,"Ou", "ou.gif", racine);
				
					generateNoun(0,"la boucherie", "boucherie.gif", ou, 1, 0);	
					generateNoun(0,"la boulangerie", "boulangerie.gif", ou, 1, 0);
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
