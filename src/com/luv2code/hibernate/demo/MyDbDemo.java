package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.User;

public class MyDbDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg2.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		User myUser = new User("hello","karel");
		System.out.println(myUser);
		
		
		try {

			// start a transaction
			session.beginTransaction();

			// get object
			List<User> users = session.createQuery("from User").getResultList();

			for (User user : users) {
				System.out.println(user);
			}

			// commit transaction
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}
