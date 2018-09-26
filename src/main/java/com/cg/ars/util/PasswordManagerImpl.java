package com.cg.ars.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class PasswordManagerImpl implements PasswordManager
{
	private Argon2 argon2;
	
	public PasswordManagerImpl()
	{
		argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
	}
	
	@Override
	public String hashPassword(String password)
	{
		return argon2.hash(4, 65536, 1, password);
	}

	@Override
	public boolean verifyPassword(String hash, String password)
	{
		return argon2.verify(hash, password);
	}
}
