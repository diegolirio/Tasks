package com.diegolirio.tasks.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskItem {
	
	private int id;
	@NotNull @Size(min=3, max=50)
	private String description;
	private boolean completed;
	private Task task;
	
	
	public TaskItem() {
		super();
		this.task = new Task();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
	@Override
	public String toString() {
		return "Item = ID: " + id + ", Description: " + description + ", Completed: " + completed + ", Task: " + task;
	}

}
