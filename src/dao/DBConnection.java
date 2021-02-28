package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
		
	private final static String URL = "jdbc:mysql://localhost:3306/pets";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "password";
	
	private static DBConnection instance = new DBConnection();
	private static Connection connection;
	
	private DBConnection() {}
	
	public static DBConnection getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		if( connection == null ) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD );
				System.out.println("Connection Successful!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			connection = null;
		}
	}
	
}
