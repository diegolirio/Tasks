package com.diegolirio.tasks.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.diegolirio.tasks.db.UserDB;
import com.diegolirio.tasks.model.User;

public class UserJDao implements UserDB {
	
	public Boolean login(User user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tasks");
		EntityManager manager = factory.createEntityManager();				
		String sql = "Select u from User as u where u.email = :email and u.password = :password";
		Query query = manager.createQuery(sql); 
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());		
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		manager.close();
		for (User u : users) {
			user = u;
			return true;
		}
		return false;
	}	
	
	public void insert(User user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tasks");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		System.out.println("User: gravado com sucesso: " + user );
	}
	
	public void update(User user) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tasks");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(user);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		System.out.println("User: alterado com sucesso: " + user );
	}
	
	public User getUser(int id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tasks");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		User user = manager.find(User.class, id);
		manager.getTransaction().commit();
		manager.close();
		factory.close();
		System.out.println("Busca: " + user );
		return user;
	}	
	
	public List<User> getAllUsers() {
		List<User> list = null;
		return list;
	}

	@Override
	public Boolean ExistUserEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
