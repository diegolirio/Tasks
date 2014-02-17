package com.diegolirio.tasks.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diegolirio.tasks.jdbc.Command;
import com.diegolirio.tasks.model.User;

@Repository
public class UserDao {
	
	private Connection conn;
	
	@Autowired
	public UserDao(DataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Boolean login(User user) {
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
	
	public void insert(User user) {
		String sql = "Insert Into task_usuario (task_usuario_nome, task_usuario_email, task_usuario_senha, task_usuario_datacadastro) values ('" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', current_date)";
		try {
			Command.executeUpdate(conn, sql);
			System.out.println(user + " ==> gravado com sucesso!!!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}