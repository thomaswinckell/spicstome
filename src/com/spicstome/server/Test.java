package com.spicstome.server;


import com.spicstome.client.hibernate.HibernateManager;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Folder;
import com.spicstome.client.shared.Pecs;
import com.spicstome.client.shared.Student;
import com.spicstome.client.shared.User;

public class Test {

	public static void PrintAlbum(Pecs pecs)
	{
		if (pecs instanceof Article) {
			Article article = (Article) pecs;
			System.out.println(article.getName());
		}
		else if (pecs instanceof Folder) {
			Folder folder = (Folder) pecs;
			
			System.out.println(folder.getName());
			
			for(int i=0;i<folder.getContent().size();i++)
			{
				PrintAlbum(folder.getContent().get(i));
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		
		
		Album album = new Album("Album de Albert","path");
		
		Folder folder1 = new Folder("Qui", "path");
		Folder folder2 = new Folder("Quoi", "path");
		Folder folder3 = new Folder("Comment", "path");
		
		Folder folder11 = new Folder("Autre","path");
		
		Article article1 = new Article("Je","path");
		Article article2 = new Article("Médecin","path");
		
		folder11.addContent(article2);
		
		folder1.addContent(article1);
		folder1.addContent(folder11);
		
		album.addContent(folder1);
		album.addContent(folder2);
		album.addContent(folder3);
		
		Student student = new Student("Albert", "path");
		student.setAlbum(album);
		
		PrintAlbum(album);
		
	
		
		User user = new User("HEAA","k");
		user.setId_user(1);
		HibernateManager.getInstance().save(user);
		HibernateManager.getInstance().save(article1);
		
		
		/*User user = HibernateManager.getInstance().user(5);
		
		System.out.println(user.getLogin());
		
		*/
	}
	
	

}
