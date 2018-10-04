package com.cg.ars.service;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ars.dao.UserDao;
import com.cg.ars.dao.UserDaoImpl;
import com.cg.ars.dto.User;
import com.cg.ars.exception.UserException;
import com.cg.ars.util.PasswordManager;
import com.cg.ars.util.PasswordManagerImpl;

public class UserServiceImpl implements UserService
{
	private UserDao udao;
	private PasswordManager pman;
	
	private Logger logger;
	
	public UserServiceImpl()
	{
		udao = new UserDaoImpl();
		pman = new PasswordManagerImpl();
		
		logger = Logger.getLogger(this.getClass());
	}

	@Override
	public void addUser(User user) throws UserException
	{
		this.validateUsername(user.getUsername());
		this.validatePassword(user.getPassword());
		this.validateRole(user.getRole());
		this.validateMobileNo(user.getMobileNo());
		
		user.setPassword(pman.hashPassword(user.getPassword()));
		
		udao.addUser(user);
	}
	
	@Override
	public User getUser(String username) throws UserException
	{
		return udao.getUser(username);
	}

	@Override
	public boolean changePassword(String username, String oldPass, String newPass) throws UserException
	{
		User user = udao.getUser(username);
		
		// Check if user exists
		// Check if old password is correct
		if (user != null && pman.verifyPassword(user.getPassword(), oldPass)) {
			// try to validate new password
			this.validatePassword(newPass);
			
			// hash new password and set it in user object
			user.setPassword(pman.hashPassword(newPass));
			
			// update database using DAO
			udao.updateUser(user);
			
			// success
			return true;
		}
		else {
			throw new UserException("Invalid Credentials");
		}
	}

	@Override
	public boolean verifyUser(String username, String password) throws UserException
	{
		User user = udao.getUser(username);
		
		// Check if user exists
		// Check if password is correct
		if (user != null && pman.verifyPassword(user.getPassword(), password)) {
			// success
			return true;
		}
		else {
			throw new UserException("Invalid Credentials");
		}
	}

	@Override
	public boolean validateUsername(String username) throws UserException
	{
		String pattern = "[A-Za-z][A-Za-z0-9\\.\\-\\_]{7,39}";
		
		if (Pattern.matches(pattern, username)) {
			return true;
		}
		else {
			throw new UserException("Invalid UserName");
		}
	}

	@Override
	public boolean validatePassword(String password) throws UserException
	{
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		
		if (Pattern.matches(pattern, password)) {
			return true;
		}
		else {
			throw new UserException("Invalid Password. Must contain atleast 1 lower case character, 1 upper case character, 1 digit, and 1 special character.");
		}
	}

	@Override
	public boolean validateRole(String role) throws UserException
	{
		String[] roles = User.getRoles();
		
		if (Arrays.asList(roles).contains(role)) {
			return true;
		}
		else {
			throw new UserException("Invalid Role");
		}
	}

	@Override
	public boolean validateMobileNo(String mobileNo) throws UserException
	{
		String pattern = "(\\+[0-9]+([\\-\\s]?[0-9]+)*[\\-\\s]?)?(([0-9]{5}[\\-\\s]?[0-9]{5})|([0-9]{3}[\\-\\s]?[0-9]{3}[\\-\\s]?[0-9]{4}))";
		
		if (Pattern.matches(pattern, mobileNo)) {
			return true;
		}
		else {
			throw new UserException("Invalid Mobile Number");
		}
	}
}
