package com.luv2code.hibernate.activity8;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.activity8.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;

public class EmployeeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate-activity.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();
			
			// TRUNCATE AND DELETE table - not portable - MySQL dependent and more .createSQLQuery method is depreciated
			System.out.println("Truncate table via native SQL query ....");
			session.createSQLQuery("TRUNCATE TABLE employee").executeUpdate();
			
			
			// DELETING - universal but not set autoincrement id to zero in MySQL
			System.out.println("\nDeleting database...");
			int rows = session.createQuery("DELETE Employee").executeUpdate();
			System.out.println("Affected rows: " + rows);
			
			
			// CREATING
			// create employee object
			System.out.println("\nSaving the employee ...");
			Employee empl1 = new Employee("Martin", "Green", "Rocket Science");
			Employee empl2 = new Employee("Verca", "Yellow", "Catty");
			Employee empl3 = new Employee("Johny", "Hawk", "Gun Factory");
			

			// save employee object
			session.save(empl1);
			session.save(empl2);
			session.save(empl3);

			// commit transaction
			session.getTransaction().commit();

			
			// READING
			// create session
			session = factory.getCurrentSession();
			
			// start a transaction
			session.beginTransaction();
			
			System.out.println("\nReading all employees...");
			List<Employee> employees = session.createQuery("FROM Employee").getResultList();
			printEmployees(employees);
			
			System.out.println("\nReading employee with id=3");
			Employee em1 = session.get(Employee.class, 3); //get employee with id = 3			
			System.out.println("Employee: " + em1);
			
			
			System.out.println("\nReading employees from company Rocket Science...");
			employees = session.createQuery("FROM Employee WHERE company='Rocket Science'").getResultList();
			
			printEmployees(employees);
			
			// DELETE by ID
			System.out.println("\nDeleting employee with id=2");
			session.createQuery("DELETE Employee WHERE id=2").executeUpdate();			
			
			// commit transaction
			session.getTransaction().commit();

			
			

		} finally {
			factory.close();
		}

	}

	private static void printEmployees(List<Employee> employees) {
		for (Employee emp : employees) {
			System.out.println(emp);
		}
	}

}
