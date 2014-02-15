package com.diegolirio.tasks.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.diegolirio.tasks.jdbc.Command;
import com.diegolirio.tasks.jdbc.FactoryConnection;
import com.diegolirio.tasks.model.User;

public class UserDao {
	
	public Boolean login(User user) {
		Connection conn = new FactoryConnection().getConnection();
		String sql = "Select * from task_usuario u where u.task_usuario_email = '" + 
						user.getEmail() + "' and task_usuario_senha = '" + user.getPassword() +"'";
		ResultSet rs = Command.executeQuery(conn, sql);
		try {
			if(rs.next()) {
				user.setId(rs.getInt("task_usuario_id"));
				user.setName(rs.getString("task_usuario_nome"));
				user.setPassword("");
				user.setDateRegistration(Calendar.getInstance());
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao efetuar Login: " + e.getMessage());
		}
		return false;
	}

}
