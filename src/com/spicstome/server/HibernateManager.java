package com.spicstome.server;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.spicstome.server.business.Album;
import com.spicstome.server.business.Image;
import com.spicstome.server.business.Log;
import com.spicstome.server.business.Pecs;
import com.spicstome.server.business.User;
import com.spicstome.server.business.Word;

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
	
	public void save(Log log){
		synchronized (Log.class) {
			session.beginTransaction();
			session.save(log);
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
	
	public Word article(int id){
		return (Word)session.load(Word.class, id);
	}
	
	public void ClearAll()
	{
		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Log> logss = session.createCriteria(Log.class).list();
		for (Log log : logss) {
			session.delete(log);
		}
		
		@SuppressWarnings("unchecked")
		List<User> users = session.createCriteria(User.class).list();
		for (User user : users) {
			session.delete(user);
		}
		
		@SuppressWarnings("unchecked")
		List<Album> albums = session.createCriteria(Album.class).list();
		for (Album album : albums) {
			session.delete(album);
		}
		
		@SuppressWarnings("unchecked")
		List<Pecs> pecss = session.createCriteria(Pecs.class).list();
		for (Pecs pecs : pecss) {
			session.delete(pecs);
		}
		
		session.getTransaction().commit();
	}
	
	
}

