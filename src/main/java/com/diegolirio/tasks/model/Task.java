package com.diegolirio.tasks.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Task {
	
	private int id;
	//@NotNull @Size(min=3, message="Titulo deve conter no minimo 3 Caracteres")
	@NotNull @Size(min=3, message="{task.title.must.contain.at.least.x.characters}")
	private String title;	
	private boolean completed;
	private List<TaskItem> items;
	private User usuario;
	
	public Task() {
		this.items = new ArrayList<>();
		this.usuario = new User();
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

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	
	public String toString() {
		return "ID: " + id + ", Title: " + title + ", Completed: " + completed + ", Usuario: " + usuario;
	}
	
	public String toJSon() {
		return null;
	}
	
	public String toXml() {
		return null;
	}

}
