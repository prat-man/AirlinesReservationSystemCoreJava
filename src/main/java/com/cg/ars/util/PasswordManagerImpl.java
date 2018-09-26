package com.cg.ars.util;

public class PasswordManagerImpl implements PasswordManager
{
	@Override
	public String hashPassword(String password)
	{
		// TODO: Implement password hashing; Currently returns password in clear text
		return password;
	}

	@Override
	public boolean verifyPassword(String hash, String password) {
		// TODO: Implement password verification; Currently always returns false
		return false;
	}
}
