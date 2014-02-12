package com.diegolirio.tasks.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Command {
	
	public static ResultSet executeQuery(Connection conn, String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}
	 
	public static int executeUpdate(Connection conn, String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		int result = stmt.executeUpdate();
		return result;
	}

}
