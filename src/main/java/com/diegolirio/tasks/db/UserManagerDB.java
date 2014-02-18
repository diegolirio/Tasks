package com.diegolirio.tasks.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class UserManagerDB implements UserDB {

	@SuppressWarnings("unused")
	private Connection conn;
	 
	public UserManagerDB(DataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
