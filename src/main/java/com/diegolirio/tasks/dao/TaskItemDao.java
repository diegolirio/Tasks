package com.diegolirio.tasks.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diegolirio.tasks.db.TaskItemDB;
import com.diegolirio.tasks.jdbc.Command;
import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;

@Repository
public class TaskItemDao implements TaskItemDB {
	
	private Connection conn;
	
	public TaskItemDao(Connection conn) {
		this.conn = conn;
	}
	
	@Autowired
	public TaskItemDao(DataSource dataSource) {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void insert(TaskItem item) {
		String sql = "Insert into task_taskitem (task_taskitem_descricao, task_taskitem_concluido, task_task_id)" +
						" values ('" + item.getDescription() + "', " + item.isCompleted() + ", " + item.getTask().getId()+")";
		System.out.println(sql);
		//Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch (SQLException e) {
			new RuntimeException(e);
		}
	} 
	
	public TaskItem getTaskItem(int id) {
		TaskItem item = new TaskItem();
		String sql = "Select * from task_taskitem i where i.task_taskitem_id = " + id;
		//Connection conn = new FactoryConnection().getConnection();
		ResultSet rs;
		try {
			rs = Command.executeQuery(conn, sql);
			if(rs.next()) {
				item.setId(rs.getInt("task_taskitem_id"));
				item.setDescription(rs.getString("task_taskitem_descricao"));
				item.setCompleted(rs.getBoolean("task_taskitem_concluido"));
				item.setTask(this.getTask(item));
				System.out.println("====================================================================");
				System.out.println("Item: " + item);
				System.out.println("====================================================================");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}			
		return item;
	}
	
	public Task getTask(TaskItem item) {
		Task task = null;
		String sql = "  select t.task_task_id, "+
							 " t.task_task_titulo, "+
							 " t.task_task_concluida,"+
							 " t.task_usuario_id "+
						  " from task_task t, "+
						  "      task_taskitem i "+
						  " where t.task_task_id = i.task_task_id "+
						  "   and i.task_taskitem_id = 9" + item.getId();
		ResultSet rs;
		try {
			rs = Command.executeQuery(conn, sql);
			if(rs.next()) {
				task = new Task();
				task.setId(rs.getInt("task_task_id"));
				task.setTitle(rs.getString("task_task_title"));
				task.setCompleted(rs.getBoolean("task_task_concluido"));
				//task.setUsuario(new UserDao(conn).getUser(rs.getInt("task_usuario_id")));
				task.getUsuario().setId(rs.getInt("task_usuario_id"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}			
		return task;
	}	
	
	public Task getTask(int id) {
		return new TaskDao(this.conn).getTask(id);
	}		
	
	public void update(TaskItem item) {
		String sql = "Update task_taskitem i set i.task_taskitem_descricao = '" + item.getDescription() + "', i.task_taskitem_concluido = " + item.isCompleted() + " where i.task_taskitem_id = " + item.getId();
		System.out.println(sql);
		//Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e); 
		}
	}	
	
	public void delete(TaskItem item) {
		String sql = "delete from task_taskitem where task_taskitem_id = " + item.getId();
		System.out.println(sql);
		//Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}		

}
