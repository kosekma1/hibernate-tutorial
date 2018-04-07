package com.luv2code.hibernate.mydemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.mydemo.entity.User;

public class MyDemo {

	public static void main(String[] args) {

		// create session factory - need to run MySQL database with table users
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
