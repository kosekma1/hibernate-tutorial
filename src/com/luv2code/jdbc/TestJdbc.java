package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class TestJdbc {

	public static void main(String[] args) {
		
		

		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudentyyyy";
						
		try {
			System.out.println("Connectin to database " + jdbcUrl);
						
			Connection conn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection sucessful!!!");
			
			Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM student");
			
			while(results.next()) {
				System.out.println(results.getInt(1));
				System.out.println(results.getString(2));
				System.out.println(results.getString(3));
				System.out.println(results.getString(4));
			}
			
		} catch (SQLException e) {
			System.out.println("Error with connectin " + e.getMessage());					
		}
				

	}

}

