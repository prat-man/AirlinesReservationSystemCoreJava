package com.cg.ars.service;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

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

	/**
	 * Add a User  with username, password, role, mobile number
	 */
	@Override
	public void addUser(User user) throws UserException
	{
		this.validateUsername(user.getUsername());
		this.validatePassword(user.getPassword());
		this.validateEmail(user.getEmail());
		this.validateRole(user.getRole());
		this.validateMobileNo(user.getMobileNo());
		
		try {
			user.setPassword(pman.hashPassword(user.getPassword()));
			
			udao.addUser(user);
			
			logger.info("User Record Added [username=" + user.getUsername() + "]");
		}
		catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}
	
	/**
	 * Get Username of User
	 * @return Username of User
	 */
	@Override
	public User getUser(String username) throws UserException
	{
		/*
		 * Do not validate username here
		 * Bypass for 'admin/admin' and other such special credential pairs
		 */
		//this.validateUsername(username);
		
		try {
			User user = udao.getUser(username);
			
			if (user == null) {
				throw new NullPointerException("User not found with [username=" + username + "]");
			}
			
			logger.info("User Record Retrieved [username=" + username + "]");
			
			return user;
		}
		catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}

	/**
	 * Change Password
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean changePassword(String username, String oldPass, String newPass) throws UserException
	{
		/*
		 * Do not validate username and old password here
		 * Bypass for 'admin/admin' and other such special credential pairs
		 */
		//this.validateUsername(username);
		//this.validatePassword(oldPass);
		
		try {
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
				logger.info("Successfully Changed Password");
				return true;
			}
			else {
				logger.warn("Invalid Credentials [username=" + username + "]");
				throw new UserException("Invalid Credentials");
			}
		}
		catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}
	
	/**
	 * Change Email
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean changeEmail(String username, String password, String email) throws UserException
	{
		/*
		 * Do not validate username and old password here
		 * Bypass for 'admin/admin' and other such special credential pairs
		 */
		//this.validateUsername(username);
		//this.validatePassword(password);
		
		try {
			User user = udao.getUser(username);
			
			// Check if user exists
			// Check if old password is correct
			if (user != null && pman.verifyPassword(user.getPassword(), password)) {
				// try to validate new email
				this.validateEmail(email);
				
				// set new email in user object
				user.setEmail(email);
				
				// update database using DAO
				udao.updateUser(user);
				
				// success
				logger.info("Successfully Changed Email ID");
				return true;
			}
			else {
				logger.warn("Invalid Credentials [username=" + username + "]");
				throw new UserException("Invalid Credentials");
			}
		}
		catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}

	/**
	 * Verifies User
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean verifyUser(String username, String password) throws UserException
	{
		/*
		 * Do not validate username and password here
		 * Bypass for 'admin/admin' and other such special credential pairs
		 */
		//this.validateUsername(username);
		//this.validatePassword(password);
		
		try {
			User user = udao.getUser(username);
			
			// Check if user exists
			// Check if password is correct
			
			if (user != null && pman.verifyPassword(user.getPassword(), password)) {
				// success
				logger.info("Successful Login");
				return true;
			}
			else {
				logger.warn("Invalid Credentials [username=" + username + "]");
				throw new UserException("Invalid Credentials");
			}
		}
		catch (Exception e) {
			throw new UserException(e.getMessage());
		}
	}

	/**
	 * Validates Username which must start with alphabet, followed by digits and can contain .-_
	 * @return boolean; true if valid, otherwise false 
	 */
	@Override
	public boolean validateUsername(String username) throws UserException
	{
		String pattern = "[A-Za-z][A-Za-z0-9\\.\\-\\_]{7,39}";
		
		if (Pattern.matches(pattern, username)) {
			logger.info("Valid [username=" + username + "]");
			return true;
		}
		else {
			logger.warn("Invalid Username [username=" + username + "]");
			throw new UserException("Invalid Username\nMust be atleast 8 characters long\nMust start with an alphabet\nCan contain UPPERCASE, lowercase, digits, ., -, and _");
		}
	}

	/**
	 * Validates Password which must contain atleast 1 lower case character, 1 upper case character, 1 digit, and 1 special character
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validatePassword(String password) throws UserException
	{
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=-_*])(?=\\S+).{8,}$";
		
		if (Pattern.matches(pattern, password)) {
			logger.info("Valid Password");
			return true;
		}
		else {
			logger.warn("Invalid Password");
			throw new UserException("Invalid Password\nMust be atleast 8 characters long\nMust contain atleast 1 UPPER CASE, 1 lower case, 1 digit, and 1 special character");
		}
	}

	/**
	 * Validates Role 
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateRole(String role) throws UserException
	{
		String[] roles = User.getRoles();
		
		if (Arrays.asList(roles).contains(role)) {
			logger.info("Role Exists [role=" + role + "]");
			return true;
		}
		else {
			logger.warn("Invalid Role [role=" + role + "]");
			throw new UserException("Invalid Role");
		}
	}

	/**
	 * Validates Mobile Number
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateMobileNo(String mobileNo) throws UserException
	{
		String pattern = "(\\+[0-9]+([\\-\\s]?[0-9]+)*[\\-\\s]?)?(([0-9]{5}[\\-\\s]?[0-9]{5})|([0-9]{3}[\\-\\s]?[0-9]{3}[\\-\\s]?[0-9]{4}))";
		
		if (Pattern.matches(pattern, mobileNo)) {
			logger.info("Valid Mobile Number [mobileNo=" + mobileNo + "]");
			return true;
		}
		else {
			logger.warn("Invalid Mobile Number [mobileNo=" + mobileNo + "]");
			throw new UserException("Invalid Mobile Number");
			
		}
	}
	
	/**
	 * Validates Email Id
	 * @return boolean; true if valid, otherwise false
	 */
	@Override
	public boolean validateEmail(String email) throws UserException 
	{
		String pattern = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
		
		if (Pattern.matches(pattern, email)) {
			logger.info("Email ID valid [email=" + email + "]");
			return true;
		}
		else {
			logger.error("Invalid Email ID [email=" + email + "]");
			throw new UserException("Invalid Email ID [email=" + email + "]");
		}
	}
}
