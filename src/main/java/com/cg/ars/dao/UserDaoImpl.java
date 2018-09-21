package com.cg.ars.dao;

import javax.persistence.EntityManager;
import com.cg.ars.dto.Booking;
import com.cg.ars.dto.User;


public class UserDaoImpl implements UserDao {

	private EntityManager entityManager;
	public UserDaoImpl() {
		//entityManager = JPAUtil.getEntityManager();
	}
	public void addUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		
	}

	public void changePassword(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
		
	}

	public User verifyUser(User user) {
		User us=entityManager.find(User.class,user);
		return us;
	}

	
	
}
