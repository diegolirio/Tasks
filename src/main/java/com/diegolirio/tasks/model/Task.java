package com.diegolirio.tasks.model;

import java.util.ArrayList;
import java.util.List;

public class Task {
	
	private int id;
	private String title;
	private boolean completed;
	private List<TaskItems> items;
	
	public Task() {
		this.items = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public List<TaskItems> getItems() {
		return items;
	}
	public void setItems(List<TaskItems> items) {
		this.items = items;
	}
	
	

}
