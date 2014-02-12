package com.diegolirio.tasks.model;

import java.util.ArrayList;
import java.util.List;

public class Task {
	
	private int id;
	private String title;
	private boolean completed;
	private List<TaskItem> items;
	private Usuario usuario;
	
	public Task() {
		this.items = new ArrayList<>();
		this.usuario = new Usuario();
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
	public List<TaskItem> getItems() {
		return items;
	}
	public void setItems(List<TaskItem> items) {
		this.items = items;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String toString() {
		return "ID: " + id + ", Title: " + title + ", Completed: " + completed + ", Usuario: " + usuario;
	}
	

}
