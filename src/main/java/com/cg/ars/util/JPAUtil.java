package com.cg.ars.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil
{
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	
	static {
		factory = Persistence.createEntityManagerFactory("JPA-PU");
	}
	
	public static EntityManager getEntityManager()
	{
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = factory.createEntityManager();
			
			Connection connection = entityManager.unwrap(Connection.class);
			
			try {
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return entityManager;
	}
}
