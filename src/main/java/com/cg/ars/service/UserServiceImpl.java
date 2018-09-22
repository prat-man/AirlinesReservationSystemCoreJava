package com.cg.ars.service;

import com.cg.ars.dao.UserDao;
import com.cg.ars.dao.UserDaoImpl;
import com.cg.ars.dto.User;

public class UserServiceImpl implements UserService 
{
	private UserDao udao;
	
	public UserServiceImpl() 
	{
		udao = new UserDaoImpl();
	}

	public void addUser(User user) 
	{
		udao.addUser(user);
	}

	public void changePassword(User user) 
	{
		udao.changePassword(user);
	}

	public User verifyUser(User user) 
	{
		udao.verifyUser(user);
		return user;
	}

	public boolean validateUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validatePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateMobileNo(String mobileNo) {
		// TODO Auto-generated method stub
		return false;
	}
}
