package com.cg.ars.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordHasher
{
	public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException
	{
		System.out.print("Password: ");
		
		String password = BR.readLine();
		
		PasswordManager pman = new PasswordManagerImpl();
		
		String hash = pman.hashPassword(password);
		
		System.out.println("Hash: " + hash);
	}
}
