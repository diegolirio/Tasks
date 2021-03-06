package com.diegolirio.tasks.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diegolirio.tasks.db.TaskDB;
import com.diegolirio.tasks.jdbc.Command;
import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;
import com.diegolirio.tasks.model.User;

@Repository
public class TaskDao implements TaskDB {
	
	private Connection conn;
	
	@Autowired
	public TaskDao(DataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TaskDao(Connection conn) {
		this.conn = conn;
	}	
	
	public List<Task> getList(User user) {
		List<Task> list = new ArrayList<>();
		String sql = "Select * from task_task where task_usuario_id = '" + user.getId() + "'";
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
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Task task) {
		String sql = "delete from task_task where task_task_id = " + task.getId();
		System.out.println(sql);
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	
	
}
