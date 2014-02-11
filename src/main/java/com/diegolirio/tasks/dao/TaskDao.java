package com.diegolirio.tasks.dao;

import java.util.ArrayList;
import java.util.List;

import com.diegolirio.tasks.model.Task;
import com.diegolirio.tasks.model.TaskItems;

public class TaskDao {

	public List<Task> getAllList() {
		List<Task> tasks = new ArrayList<>();
		
		Task task1 = new Task();
		task1.setId(1);
		task1.setTitle("Faxina");
		task1.setCompleted(false);
		
		TaskItems item1 = new TaskItems();
		item1.setId(1);
		item1.setDescription("Lavar a Louça");
		item1.setCompleted(false);
		task1.getItems().add(item1);
		
		TaskItems item2 = new TaskItems();
		item2.setId(2);
		item2.setDescription("Varrer o chão");
		item2.setCompleted(false);
		task1.getItems().add(item2);
		
		TaskItems item3 = new TaskItems();
		item3.setId(3);
		item3.setDescription("Lavar a Roupa");
		item3.setCompleted(false);
		task1.getItems().add(item3);
		
		tasks.add(task1);
		
		return tasks;
	}
	
}
