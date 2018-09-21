package com.cg.ars.service;

import com.cg.ars.dao.UserDao;
import com.cg.ars.dao.UserDaoImpl;
import com.cg.ars.dto.User;

public class UserServiceImpl implements UserService 
{
	UserDao udao;
	
	public UserServiceImpl() 
	{
		super();
		udao=new UserDaoImpl();
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
}
