package com.spicstome.server;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import com.spicstome.client.shared.Album;
import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Image;
import com.spicstome.client.shared.Pecs;
import com.spicstome.client.shared.User;

/**
 * 
 * @author Maxime
 * 
 * HibernateManager execute query's and return result from database
 *
 */

public class HibernateManager {

	private static volatile HibernateManager instance;
	private Session session;
	
	private HibernateManager(){
		Configuration conf = new Configuration();
		this.session = conf.configure().buildSessionFactory().openSession();
	}
	
	public static HibernateManager getInstance(){
		synchronized (HibernateManager.class) {
			if(instance == null || !instance.session.isConnected()) instance = new HibernateManager();
			return instance;
		}
	}
	
	public void save(Album album){
		synchronized (Album.class) {
			session.beginTransaction();
			session.save(album);
			session.getTransaction().commit();
		}
	}
	
	public void save(User user){
		synchronized (User.class) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
	}
	
	public void save(Image img){
		synchronized (User.class) {
			session.beginTransaction();
			session.save(img);
			session.getTransaction().commit();
		}
	}
	
	public void save(Pecs pecs){
		synchronized (Pecs.class) {
			session.beginTransaction();
			session.save(pecs);
			session.getTransaction().commit();
		}
	}
	
	public User user(int id){
		return (User)session.load(User.class, id);
	}
	
	public Article article(int id){
		return (Article)session.load(Article.class, id);
	}
	
	public void ClearAll()
	{
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).list();
		for (User user : users) {
			session.delete(user);
		}
		
		session.getTransaction().commit();
	}
	
	
}

