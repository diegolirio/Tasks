package com.diegolirio.tasks.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FactoryConnection {
	
		private String url;
		private String user;
		private String password;
		
		public Connection getConnection() {
			
			Connection conn = null;
			
			// ******************** MYSQL ***************************************//
			// Ex: "jdbc:mysql://localhost/BANCO_DE_DADOS?user=root&password=root"
			this.url = "jdbc:mysql://localhost/dbmysql";
			this.user = "root";
			this.password = "root";
			
			// ******************** Oracle ***************************************//
			//"jdbc:oracle:thin:@201.63.16.210:1521/tdp";						
			//this.url = "jdbc:oracle:thin:@201.63.16.210:1521/tdp";
			//this.user = "wservice";
			//this.password = "wservice";			
			
			try {
				
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName("com.mysql.jdbc.Driver");
				//com.mysql.jdbc.D
				conn = DriverManager.getConnection(this.url, 
												   this.user, 
												   this.password);
				System.out.println("Conectado com sucesso...! DRIVER: ");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("TdvFactoryConnection\n"+e.getMessage());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return conn; 
		}
		
		public void close(Connection conn) {
			if(conn != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					throw new RuntimeException(e); 
				}
			}
		}
		
		public void close(Connection conn, Statement stmt) {
			if (conn != null) {
				this.close(conn);
			}
			
			if(stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		
		}
		
		public void close(Connection conn, Statement stmt, ResultSet rs) {
			if (conn != null || stmt != null) {
				this.close(conn, stmt);
			}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		}
		
		public static void main(String[] args) throws SQLException {
			Connection conn = new FactoryConnection().getConnection();
			System.out.println(conn.isClosed());
			new FactoryConnection().close(conn);
			System.out.println(conn.isClosed());
		}

}
