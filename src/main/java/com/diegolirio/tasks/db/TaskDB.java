package com.diegolirio.tasks.db;

import java.util.List;

import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;
import com.diegolirio.tasks.model.User;

public interface TaskDB {

	public List<Task> getList(User user);
	public List<TaskItem> getItems(Task task);
	public Task getTask(int id);
	public void insert(Task task);
	public void update(Task task);
	public void delete(Task task);
	
}
