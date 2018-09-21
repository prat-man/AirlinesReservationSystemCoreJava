package com.cg.ars.dao;

import com.cg.ars.dto.User;

public interface UserDao {
	public void addUser(User user);
	public void changePassword(User user);
	public User verifyUser(User user);
	
}
