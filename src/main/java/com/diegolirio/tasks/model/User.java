package com.diegolirio.tasks.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	private int id;
	@NotNull @Size(min=5, max=50, message="Nome deve conter pelo menos 5 caracters, e no maximo 50")
	private String name;
	@NotNull(message="Digite o Email") @Size(min=1, message="Por Favor Digite o Email")
	private String email;
	@NotNull @Size(min=6, max=12, message="Senha deve conter de 6 a 12 caracters")
	private String password;
	private Calendar dateRegistration;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Calendar getDateRegistration() {
		return dateRegistration;
	}
	public void setDateRegistration(Calendar dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	@Override
	public String toString() {
		return "ID: " + id + "; Name: " + name + "; Email: " + email + "; Password: " + password;// + "; DateRegistration: " + dateRegistration.getTime();
	}
	

}
