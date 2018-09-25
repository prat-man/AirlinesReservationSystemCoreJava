package com.cg.ars.service;

import org.junit.Assert;
import org.junit.Test;

import com.cg.ars.exception.UserException;

public class UserServiceTest
{
	private UserService user;
	
	public UserServiceTest()
	{
		user = new UserServiceImpl();
	}
	
	@Test
	public void testValidateMobileNo1() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("+91-8582964407"));
	}
}
