package com.diegolirio.tasks.db;

import java.util.List;

import com.diegolirio.tasks.model.User;

public interface UserDB {
	
	public Boolean login(User user);
	public void insert(User user);
	public void update(User user);
	public User getUser(int id);
	public List<User> getAllUsers();
	public Boolean ExistUserEmail(String email);


}
