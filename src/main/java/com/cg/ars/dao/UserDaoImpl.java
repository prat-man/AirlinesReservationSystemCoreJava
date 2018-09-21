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
	
	public void addUser(User user)
	{
		entityManager.getTransaction().begin();
		
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
	}

	public void changePassword(User user)
	{
		entityManager.getTransaction().begin();
		
		entityManager.merge(user);
		
		entityManager.getTransaction().commit();
	}

	public User verifyUser(User user)
	{
		User retUser = entityManager.find(User.class, user);
		
		return retUser;
	}
}
