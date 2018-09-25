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
		Assert.assertEquals(true, user.validateMobileNo("+91-562-8582964407"));
	}
	
	@Test
	public void testValidateMobileNo2() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("+91 85829-64407"));
	}
	
	@Test
	public void testValidateMobileNo3() throws UserException
	{
		Assert.assertEquals(true, user.validateMobileNo("+91 85829 64407"));
	}
}
