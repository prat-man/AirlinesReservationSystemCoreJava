package com.cg.ars.dao;

import javax.persistence.EntityManager;

import com.cg.ars.dto.User;
import com.cg.ars.util.JPAUtil;

public class UserDaoImpl implements UserDao
{
	private EntityManager entityManager;
	
	public UserDaoImpl()
	{
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public void addUser(User user)
	{
		try {
			entityManager.getTransaction().begin();
			
			entityManager.persist(user);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			exc.printStackTrace();
			
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public void updateUser(User user)
	{
		try {
			entityManager.getTransaction().begin();
			
			entityManager.merge(user);
			
			entityManager.getTransaction().commit();
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}

	@Override
	public User getUser(String username)
	{
		try {
			entityManager.getTransaction().begin();
			
			User user = entityManager.find(User.class, username);
			
			entityManager.getTransaction().commit();
			
			return user;
		}
		catch (Exception exc) {
			entityManager.getTransaction().rollback();
			
			throw exc;
		}
	}
}
