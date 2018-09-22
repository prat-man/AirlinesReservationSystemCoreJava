package com.cg.ars.service;

import com.cg.ars.dto.User;

public interface UserService 
{
	public void addUser(User user);
	
	public void changePassword(User user);
	
	public User verifyUser(User user);
	
	public boolean validateUsername(String username);
	
	public boolean validatePassword(String password);
	
	public boolean validateRole(String role);
	
	public boolean validateMobileNo(String mobileNo);
}
