package com.spicstome.server;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

import com.spicstome.client.shared.Adjective;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Log;
import com.spicstome.client.shared.Noun;
import com.spicstome.client.shared.Pronoun;
import com.spicstome.client.shared.Referent;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.Teacher;
import com.spicstome.client.shared.Verb;


public class Test {
	
	public enum Type {EXAMPLE,GENERAL,EMPTY};
	
	public static Student populateWithStudent(String name,String firstname,String login,String password,String filename)
	{
		Album album = generateAlbum(Type.EMPTY);
		
		Image imageUser = populateWithImageUser(filename);
		
		Student student = new Student((long)-1);
		student.setName(name);
		student.setFirstName(firstname);
		student.setEmail(name+"."+firstname+"@gmail.com");
		student.setLogin(login);
		student.setPassword(Encryption.toSHA256(password));
		student.setImage(imageUser);
		student.setAlbum(album);
		student.setLogs(new TreeSet<Log>());
		
		HibernateManager.getInstance().save(student);


		return student;
	}
	
	public static int generateRandom(int x,int y)
	{
		return (int)(Math.random() * (y-x)) + x;
	}
	
	public static void generateLogForStudent(Student student)
	{
		Calendar calendar = Calendar.getInstance();
		String falseRecipient = "fake@gmail.com";
		
		Date now = new Date();
		calendar.setTime(now);
		
		int currentWeek;
		int currentYear;
		
		currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		currentYear = calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-6);

		while(!(((currentWeek+1)==calendar.get(Calendar.WEEK_OF_YEAR)) && 
				(currentYear==calendar.get(Calendar.YEAR))))
		{
			int random = generateRandom(2,10);
			
			for(int i=0;i<random;i++)
			{
				int nbAction = generateRandom(1, 4);
				int timeExecution = generateRandom(0, 120);
				int messageLength = generateRandom(1, 6);
				
				generateLog(student, calendar.getTime(), falseRecipient, timeExecution, nbAction, messageLength);
			}
			
			calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR)+1);
			
		};
	}
	
	public static void generateLog(Student student,Date date,String recipientMail,int executionTime,int nbAction,int messageLength)
	{
		Log log = new Log((long)-1);
		log.setActions(nbAction);
		log.setDate(date);
		log.setEmailRecipient(recipientMail);
		log.setExecutionTime(executionTime);
		log.setMessageLength(messageLength);
		log.setStudent(student);
		
		HibernateManager.getInstance().save(log);
	}
	
	public static Image populateWithImageUser(String filename)
	{
		
		
		Image imageUser = new Image((long)-1);
		if(!filename.equals(""))
			imageUser.setFilename(filename);
		else
			imageUser.setFilename("default_user.png");
		
		
		HibernateManager.getInstance().save(imageUser);
		
		return imageUser;
	}
	

	public static void generateNoun(int order,String name,String image,Folder parent,int gender,int number,int uncountable)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Noun noun = new Noun((long)-1);
		noun.setName(name);
		noun.setUncountable(uncountable);
		noun.setGender(gender);
		noun.setNumber(number);
		noun.setImage(imageSubject);
		noun.setFolder(parent);
		noun.setOrder(order);
		HibernateManager.getInstance().save(noun);

	}
	
	public static void generateArticle(int order,String name,String image,Folder parent,int gender,int number)
	{
		Image imageSubject = new Image((long)-1);
		imageSubject.setFilename(image);
		
		HibernateManager.getInstance().save(imageSubject);
		
		Article article = new Article((long)-1);
		article.setName(name);
		article.setGender(gender);
		article.setNumber(number);
		article.setImage(imageSubject);
		article.setFolder(parent);
		article.setOrder(order);
		HibernateManager.getInstance().save(article);

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
		
		if(type==Type.GENERAL)
		{
			
			Folder articles = generateFolder(0,"Articles", "articles.gif", racine);	
			
				generateArticle(0, "le", "le.gif", articles, 0, 0);
				generateArticle(1, "la", "la.gif", articles, 1, 0);
				generateArticle(2, "un", "un.gif", articles, 0, 0);
				generateArticle(3, "une", "une.gif", articles, 1, 0);
				generateArticle(4, "des", "des.gif", articles, 0, 1);
				generateArticle(5, "les", "les.gif", articles, 0, 1);
				generateArticle(6, "de la", "dela.gif", articles, 1, 0);
				generateArticle(7, "du", "du.gif", articles, 0, 0);
				generateArticle(8, "mon", "mon.gif", articles, 0, 0);
				generateArticle(9, "ma", "ma.gif", articles, 1, 0);
				generateArticle(10, "mes", "mes.gif", articles, 1, 1);
			
				
			Folder qui = generateFolder(1,"Qui", "qui.gif", racine);	
			
				Folder commercants = generateFolder(0,"Commerçants", "commercants.gif", qui);
				
					generateNoun(0,"boulanger", "boulanger.gif", commercants, 0, 0,0);
					generateNoun(1,"coiffeur", "coiffeur.gif", commercants, 0, 0,0);
					
				Folder animaux = generateFolder(1,"Animaux", "animaux.gif", qui);
					
					generateNoun(0,"chien", "chien.gif", animaux, 0, 0,0);	
					generateNoun(0,"pingouin", "pingouin.gif", animaux, 0, 0,0);	
					
				generatePronoun(0,"je", "je_1.JPG", qui, 0, 0, 0);
				generatePronoun(1,"je", "je_2.JPG", qui, 1, 0, 0);
				generatePronoun(2,"tu", "tu_1.JPG", qui, 0, 0, 1);
				generatePronoun(3,"tu", "tu_2.JPG", qui, 1, 0, 1);	
				generatePronoun(4,"il", "il_1.JPG", qui, 0, 0, 2);
				generatePronoun(5,"elle", "elle_1.JPG", qui, 1, 0, 2);
				generateNoun(6, "parent", "parent.gif", qui, 0, 1,0);
				generateNoun(7, "papa", "pere.gif", qui, 0, 0,0);
				generateNoun(8, "maman", "mere.gif", qui, 1, 0,0);
				generatePronoun(9,"nous", "nous_1.JPG", qui, 0, 1, 0);
				generatePronoun(10,"nous", "nous_2.JPG", qui, 1, 1, 0);
				generatePronoun(11,"vous", "vous_1.JPG", qui, 0, 1, 1);
				generatePronoun(12,"vous", "vous_2.JPG", qui, 1, 1, 1);
					
			Folder quoi = generateFolder(2,"Quoi", "quoi.gif", racine);
			
				generateVerb(0,"être", "etre.gif", quoi, 0,2,"suis","es","est","sommes","êtes","sont");
				generateVerb(1,"ne pas être", "nepasetre.JPG", quoi, 1,2,"suis","es","est","sommes","êtes","sont");
				generateVerb(2,"aimer", "aimer.gif", quoi, 0,0,"","","","","","");
				generateVerb(3,"ne pas aimer", "nepasaimer.gif", quoi, 1,0,"","","","","","");
				generateVerb(4,"manger", "manger.gif", quoi, 0,0,"","","","","","");
				generateVerb(5,"boire", "boire.gif", quoi, 0,2,"bois","bois","boit","buvons","buvez","boivent");
				generateVerb(6,"vouloir", "vouloir.JPG", quoi, 0,2,"veux","veux","veut","voulons","voulez","veulent");
				generateVerb(7,"ne pas vouloir", "nepasvouloir.JPG", quoi, 1,2,"veux","veux","veut","voulons","voulez","veulent");
				generateVerb(8,"écouter", "ecouter.gif", quoi, 0,0,"","","","","","");
				generateVerb(9,"savoir", "savoir.jpg", quoi, 0,2,"sais","sais","sait","savons","savez","savent");
				generateVerb(10,"ne pas savoir", "nepassavoir.JPG", quoi, 1,2,"sais","sais","sait","savons","savez","savent");
				generateVerb(11,"lire", "lire.gif", quoi, 0,2,"lis","lis","lit","lisons","lisez","lisent");
				generateVerb(12,"dormir", "dormir.gif", quoi, 0,2,"dors","dors","dort","dormons","dormez","dorment");
				generateVerb(13,"trouver", "trouver.gif", quoi, 0,0,"","","","","","");
				generateVerb(14,"courir", "courir.gif", quoi, 0,2,"cours","cours","court","courons","courez","courent");
				generateVerb(15,"dire", "dire.gif", quoi, 0,2,"dis","dis","dit","disons","dites","disent");
				generateVerb(16,"danser", "danser.gif", quoi, 0,0,"","","","","","");
				generateVerb(17,"demander", "demander.gif", quoi, 0,0,"","","","","","");
				generateVerb(18,"rêver", "rêver.gif", quoi, 0,0,"","","","","","");
				generateVerb(19,"rire", "rire.gif", quoi, 0,2,"ris","ris","rit","rions","riez","rient");
				
				Folder nouriture = generateFolder(0,"Nourriture", "nourriture.gif", quoi);
				
					generateNoun(0, "yaourt", "yaourt.gif", nouriture, 0, 0,1);
					generateNoun(1, "biscuit", "biscuit.gif", nouriture, 0, 0,1);
					generateNoun(2, "pain au chocolat", "pain au chocolat.gif", nouriture, 0, 0,0);
					generateNoun(3, "pain", "pain.gif", nouriture, 0, 0,1);
					generateNoun(4, "oeuf sur le plat", "oeuf sur le plat.gif", nouriture, 0, 0,0);
					generateNoun(5, "pizza", "pizza.gif", nouriture, 1, 0,1);
					generateNoun(6, "poulet", "poulet.gif", nouriture, 0, 0,1);
				
					Folder fruits = generateFolder(0,"Fruits", "fruits.gif", nouriture);
					
						generateNoun(0,"cerises", "cerises.gif", fruits, 1, 1,0);
						generateNoun(1, "abricot", "abricot.gif", fruits, 0, 0,1);
						generateNoun(2, "fraise", "fraise.gif", fruits, 1, 0,1);
						generateNoun(3, "banane", "banane.gif", fruits, 1, 0,1);
						generateNoun(4, "pasteque", "pasteque.gif", fruits, 1, 0,1);
					
				Folder boissons = generateFolder(1,"Boissons", "boissons.gif", quoi);
				
					generateNoun(0,"coca", "bouteille de coca.gif", boissons, 0, 0,1);
					generateNoun(1,"eau", "eau.gif", boissons, 1, 0,1);	
					generateNoun(2,"bière", "biere.gif", boissons, 1, 0,1);
					generateNoun(3, "jus de fruit", "jusdefruit.gif", fruits, 0, 0,1);
					
				Folder choses = generateFolder(2,"Choses", "choses.gif", quoi);
				
					generateNoun(0,"livre", "livre.gif", choses, 0, 0,0);	
					generateNoun(1,"voiture", "voiture.gif", choses, 0, 1,0);
							
			Folder comment = generateFolder(3,"Comment", "comment.gif", racine);
			
				generateAdjective(0,"fatigué", "fatiguer.gif", comment,"fatigué","fatigués","fatiguée","fatiguées");	
				generateAdjective(1,"heureux", "heureux.jpg", comment,"heureux","heureux","heureuse","heureuses");	
				generateAdjective(2,"enervé", "enerve.JPG", comment,"enervé","enervés","enervée","enervées");	

			Folder ou = generateFolder(4,"Ou", "ou.gif", racine);
				
				generateNoun(0,"boucherie", "boucherie.gif", ou, 1, 0,0);	
				generateNoun(1,"boulangerie", "boulangerie.gif", ou, 1, 0,0);
				generateNoun(2,"restaurant", "restaurant.gif", ou, 0, 0,0);
				
			Folder sports = generateFolder(5,"Sports", "sports.gif", racine);
				
				generateNoun(0,"escalade", "escalade.gif", sports, 1, 0,1);	
				generateNoun(1,"escrime", "escrime.gif", sports, 1, 0,1);
				generateNoun(2,"gymnastique", "gymnastique.gif", sports, 1, 0,1);
				generateNoun(3,"course", "course.gif", sports, 1, 0,1);
				generateNoun(4,"boxe", "boxe.gif", sports, 1, 0,1);
				generateNoun(5,"bowling", "bowling.gif", sports, 0, 0,1);
				generateNoun(6,"judo", "judo.gif", sports, 0, 0,1);
				generateNoun(7,"planche à voile", "plancheavoile.gif", sports, 1, 0,1);
			
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
		Image imageAdmin = populateWithImageUser("");
		
		Referent superAdmin = new Referent((long)-1);
		superAdmin.setName("admin");
		superAdmin.setFirstName("admin");
		superAdmin.setEmail("maxime.hass@gmail.com");
		superAdmin.setLogin("admin");
		superAdmin.setPassword(Encryption.toSHA256("admin"));
		superAdmin.setImage(imageAdmin);
		
		HibernateManager.getInstance().save(superAdmin);

		/* Student */
		
		Student albert = populateWithStudent("Dupuis", "Albert", "albert", "albert","albert.png");
		Student stephane = populateWithStudent("Mars", "Stephane", "stephane", "stephane","stephane.png");
		Student sophie = populateWithStudent("Dupont", "Sophie", "sophie", "sophie","sophie.png");
		Student marie = populateWithStudent("Schmitt", "Marie", "marie", "marie","marie.png");
		Student leo = populateWithStudent("Dubois", "Leo", "leo", "leo","leo.png");
		Student laura = populateWithStudent("Doe", "Laura", "laura", "laura","laura.png");
		
		generateLogForStudent(albert);
	
		
		/* Referant */
		Image imageJacques = populateWithImageUser("jacques.png");
		
		Referent jacques = new Referent((long)-1);
		jacques.setName("Martin");
		jacques.setFirstName("Jacques");
		jacques.setEmail("mj@gmail.com");
		jacques.setLogin("jacques");
		jacques.setPassword(Encryption.toSHA256("jacques"));
		jacques.setImage(imageJacques);
		
		jacques.setStudents(new TreeSet<Student>());
		jacques.addStudent(albert);
		jacques.addStudent(stephane);
		jacques.addStudent(sophie);
		jacques.addStudent(marie);
		jacques.addStudent(leo);
		jacques.addStudent(laura);
		
		HibernateManager.getInstance().save(jacques);
		
		Image imageDelphine = populateWithImageUser("delphine.png");
		
		Referent delphine = new Referent((long)-1);
		delphine.setName("Marchand");
		delphine.setFirstName("Delphine");
		delphine.setEmail("dm@gmail.com");
		delphine.setLogin("delphine");
		delphine.setPassword(Encryption.toSHA256("delphine"));
		delphine.setImage(imageDelphine);
		
		delphine.setStudents(new TreeSet<Student>());
		delphine.addStudent(stephane);
		
		HibernateManager.getInstance().save(delphine);
		
		/* Teacher */
		Image imageRobert = populateWithImageUser("robert.png");
		
		Teacher robert = new Teacher((long)-1);
		robert.setName("Duchemin");
		robert.setFirstName("Robert");
		robert.setEmail("rd@gmail.com");
		robert.setLogin("robert");
		robert.setPassword(Encryption.toSHA256("robert"));
		robert.setImage(imageRobert);
		
		robert.setStudents(new TreeSet<Student>());
		robert.addStudent(albert);
		robert.addStudent(stephane);
		robert.addStudent(leo);
		robert.addStudent(marie);
		
		HibernateManager.getInstance().save(robert);

	}

}
