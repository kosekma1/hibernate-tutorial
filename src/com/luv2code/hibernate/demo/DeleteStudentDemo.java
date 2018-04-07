package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			int studentId = 1;

			// start a transaction
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);

			Student myStudent = session.get(Student.class, studentId);
			
			// delete student
			System.out.println("Deleting student: " + myStudent);
			if(myStudent!=null) {
				session.delete(myStudent);
			} else {
				System.out.println("Student not found in database. Can not be deleted.");
			}
						
			// delete student id=2 - another way
			System.out.println("Deleting student id=2");
			int result = session.createQuery("delete Student where id=2").executeUpdate();			
			System.out.println("Rows affected " + result);
			
			
			result = session.createQuery("delete from Student").executeUpdate();
			System.out.println("Rows affected (delete all) " + result);
			
			// commit the transaction
			session.getTransaction().commit();

			
			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

}
