package com.diegolirio.tasks.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diegolirio.tasks.jdbc.Command;
import com.diegolirio.tasks.jdbc.FactoryConnection;
import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;
import com.diegolirio.tasks.model.User;

public class TaskDao {
	
	public List<Task> getList(User usuario) {
		List<Task> list = new ArrayList<>();
		Connection conn = new FactoryConnection().getConnection();
		String sql = "Select * from task_task where task_usuario_id = '" + usuario.getId() + "'";
		System.out.println(sql);
		try {
			ResultSet rs = Command.executeQuery(conn, sql);		
			while(rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("task_task_id"));
				task.setCompleted(rs.getBoolean("task_task_concluida"));
				task.setTitle(rs.getString("task_task_titulo"));
				list.add(task);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	public List<TaskItem> getItems(Task task) {
		List<TaskItem> items = new ArrayList<>();
		Connection conn = new FactoryConnection().getConnection();
		String sql = "Select * from task_taskitem where task_task_id = '" + task.getId() + "'";
		ResultSet rs;
		try {
			rs = Command.executeQuery(conn, sql);
			while(rs.next()) {
				TaskItem item = new TaskItem();
				item.setId(rs.getInt("task_taskitem_id"));
				item.setDescription(rs.getString("task_taskitem_descricao"));
				item.setCompleted(rs.getBoolean("task_taskitem_concluido"));
				items.add(item);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}				
		return items;
	}

	public Task getTask(int id) {
		Task task = new Task();
		String sql = "Select * from task_task t where t.task_task_id = " + id;
		Connection conn = new FactoryConnection().getConnection();
		ResultSet rs;
		try {
			rs = Command.executeQuery(conn, sql);
			if(rs.next()) {
				task.setId(rs.getInt("task_task_id"));
				task.setTitle(rs.getString("task_task_titulo"));
				User usuario = new User();
				usuario.setId(rs.getInt("task_usuario_id"));
				task.setUsuario(usuario);
				task.setCompleted(rs.getBoolean("task_task_concluida"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}			
		return task;
	}
	
	public void insert(Task task) {
		String sql = "Insert into task_task (task_task_titulo, task_task_concluida, task_usuario_id) values ('"+
						task.getTitle()+"',"+task.isCompleted()+", '"+ task.getUsuario().getId()+"')";
		System.out.println("INSERT: " + sql);
		Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
			System.out.println("Task gravado com sucesso!!!" + task);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Task task) {
		String sql = "Update task_task t set t.task_task_titulo = '" + task.getTitle() + "', t.task_task_concluida = " + task.isCompleted() + " where t.task_task_id = " + task.getId();
		System.out.println(sql);
		Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Task task) {
		String sql = "delete from task_task where task_task_id = " + task.getId();
		System.out.println(sql);
		Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	
	
}
