package com.spicstome.client.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.spicstome.client.shared.Article;
import com.spicstome.client.shared.Student;
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
	
	public void save(User object){
		synchronized (User.class) {
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
		}
	}
	
	public void save(Article article){
		synchronized (Article.class) {
			session.beginTransaction();
			session.saveOrUpdate(article);
			session.getTransaction().commit();
		}
	}
	
	public User user(int id){
		return (User)session.load(User.class, id);
	}
	
	public Article article(int id){
		return (Article)session.load(Article.class, id);
	}
	
	public User login(String login,String password)
	{
		/*
		List<User> list = session.createQuery("FROM User WHERE login = '" + login + "' AND password = '"+password+"'").list();
		
		if(list.isEmpty()) 
			return null;
		else
			return list.get(0);
		*/
		return null;
	}
}

