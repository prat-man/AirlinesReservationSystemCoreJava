package com.cg.ars.service;

import com.cg.ars.dto.User;
import com.cg.ars.exception.UserException;

public interface UserService 
{
	public void addUser(User user) throws UserException;
	
	public User getUser(String username) throws UserException;
	
	public boolean changePassword(String username, String oldPass, String newPass) throws UserException;
	
	public boolean changeEmail(String username, String password, String email) throws UserException;
	
	public boolean verifyUser(String username, String password) throws UserException;
	
	public boolean validateUsername(String username) throws UserException;
	
	public boolean validatePassword(String password) throws UserException;
	
	public boolean validateRole(String role) throws UserException;
	
	public boolean validateMobileNo(String mobileNo) throws UserException;

	boolean validateEmail(String email) throws UserException;
}
