package com.diegolirio.tasks.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.diegolirio.tasks.jpa.dao.UserJDao;
import com.diegolirio.tasks.model.User;

public class GeneratesTasks {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tasks");
		EntityManager manager = factory.createEntityManager();
		
//		User user = new User();
//		user.setEmail("diego@diegolirio.com");
//		user.setName("Diego Lirio");
//		user.setPassword("123456");
//		
//		
//		manager.getTransaction().begin();;
//		manager.persist(user);
//		manager.getTransaction().commit();
//		 
//		System.out.println(user);
		
//		User user_update = getUser(2);		
//		user_update.setDateRegistration(Calendar.getInstance());
//		
//		manager.getTransaction().begin();
//		manager.merge(user_update);
//		manager.getTransaction().commit();
		User user = new User();
		user.setEmail("diego@diegolirio.com");
		user.setPassword("123456");
		Boolean logado = new UserJDao().login(user);		
		System.out.println("Logado: " + logado);		
		manager.close();
		factory.close();
	}

}
 