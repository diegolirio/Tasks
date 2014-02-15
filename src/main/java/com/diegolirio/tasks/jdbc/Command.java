package com.diegolirio.tasks.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Command {
	
	public static ResultSet executeQuery(Connection conn, String sql) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao executar com.diegolirio.tasks.jdbc.Command.executeQuery:" + e.getMessage());
		}
		return rs;
	}
	 
	public static int executeUpdate(Connection conn, String sql) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		int result = stmt.executeUpdate();
		return result;
	}

}
