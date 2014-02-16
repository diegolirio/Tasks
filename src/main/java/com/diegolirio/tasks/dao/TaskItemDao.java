package com.diegolirio.tasks.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diegolirio.tasks.jdbc.Command;
import com.diegolirio.tasks.jdbc.FactoryConnection;
import com.diegolirio.tasks.model.TaskItem;

public class TaskItemDao {
	
	public void insert(TaskItem item) {
		String sql = "Insert into task_taskitem (task_taskitem_descricao, task_taskitem_concluido, task_task_id)" +
						" values ('" + item.getDescription() + "', " + item.isCompleted() + ", " + item.getTask().getId()+")";
		System.out.println(sql);
		Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch (SQLException e) {
			new RuntimeException(e);
		}
	}
	
	public TaskItem getTaskItem(int id) {
		TaskItem item = new TaskItem();
		String sql = "Select * from task_taskitem i where i.task_taskitem_id = " + id;
		Connection conn = new FactoryConnection().getConnection();
		ResultSet rs;
		try {
			rs = Command.executeQuery(conn, sql);
			if(rs.next()) {
				item.setId(rs.getInt("task_taskitem_id"));
				item.setDescription(rs.getString("task_taskitem_descricao"));
				item.setTask(new TaskDao(conn).getTask(item.getId()));
				item.setCompleted(rs.getBoolean("task_taskitem_concluido"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}			
		return item;
	}
	
	public void update(TaskItem item) {
		String sql = "Update task_taskitem i set i.task_taskitem_descricao = '" + item.getDescription() + "', i.task_taskitem_concluido = " + item.isCompleted() + " where i.task_taskitem_id = " + item.getId();
		System.out.println(sql);
		Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e); 
		}
	}	
	
	public void delete(TaskItem item) {
		String sql = "delete from task_taskitem where task_taskitem_id = " + item.getId();
		System.out.println(sql);
		Connection conn = new FactoryConnection().getConnection();
		try {
			Command.executeUpdate(conn, sql);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}		

}
