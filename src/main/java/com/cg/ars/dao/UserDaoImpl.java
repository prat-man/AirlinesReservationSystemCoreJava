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
		entityManager.getTransaction().begin();
		
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
	}

	@Override
	public void updateUser(User user)
	{
		entityManager.getTransaction().begin();
		
		entityManager.merge(user);
		
		entityManager.getTransaction().commit();
	}

	@Override
	public User getUser(String username)
	{
		return entityManager.find(User.class, username);
	}
}
