package com.cg.ars.util;

public interface PasswordManager
{
	public String hashPassword(String password);
	
	public boolean verifyPassword(String hash, String password);
}
