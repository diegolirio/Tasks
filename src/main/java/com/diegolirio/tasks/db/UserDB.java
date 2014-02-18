package com.diegolirio.tasks.db;

import com.diegolirio.tasks.model.User;

public interface UserDB {
	
	public Boolean login(User user);
	public void insert(User user);
	public Boolean ExistUserEmail(String email);


}
