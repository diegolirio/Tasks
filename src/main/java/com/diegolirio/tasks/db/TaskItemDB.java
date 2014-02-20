package com.diegolirio.tasks.db;

import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItem;

public interface TaskItemDB {
	
	public void insert(TaskItem item);	
	public TaskItem getTaskItem(int id);	
	public Task getTask(TaskItem item);
	public Task getTask(int id);
	public void update(TaskItem item);
	public void delete(TaskItem item);
	
}
