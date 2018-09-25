package com.cg.ars.service;

import java.util.regex.Pattern;

import com.cg.ars.dao.UserDao;
import com.cg.ars.dao.UserDaoImpl;
import com.cg.ars.dto.User;
import com.cg.ars.exception.BookingException;
import com.cg.ars.exception.FlightException;
import com.cg.ars.exception.UserException;

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

	public boolean validateUsername(String username) throws UserException 
	{
		String pattern = "([A-Z][a-z]+ )*[A-Z][a-z]+";
		
		if (Pattern.matches(pattern, username)) {
			return true;
		}
		else {
			throw new UserException("Invalid UserName");
		}
	}

	public boolean validatePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validateMobileNo(String mobileNo) throws UserException
	{
		String pattern = "[\\+[0-9]{1,}[(-\\s){1}[0-9]{1,}]*(-\\s){1}]*(([0-9]{5}(-\\s){0,1}[0-9]{5}))";
		
		if (Pattern.matches(pattern, mobileNo)) {
			return true;
		}
		else {
			throw new UserException("Invalid Mobile Number");
		}
	}
}
