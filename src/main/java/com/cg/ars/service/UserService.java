package com.cg.ars.service;

import com.cg.ars.dto.User;

public interface UserService 
{
	public void addUser(User user);
    public void changePassword(User user);
    public User verifyUser(User user);
}
