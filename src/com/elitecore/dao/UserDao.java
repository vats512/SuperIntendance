package com.elitecore.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.elitecore.model.User;

@Repository("UserDao")
public class UserDao {

	private EntityManager em;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

	// To Save the article detail
	public void saveUser(User user) throws Exception {
		System.out.println("Hakuna Matata"+user.getUserName()+" "+user.getPassword());
		em.persist(user);
	}
	
	/*@SuppressWarnings("unchecked")*/
	public String checkLogin(String username, String pass) throws Exception
	{
		
		System.out.println(pass);
		System.out.println(username);
		
			Query query2=em.createQuery("SELECT id FROM User WHERE username= '"+username+"' AND password = '"+pass+"'");
			List<User> list1=(List<User>)query2.getResultList();
			if(list1.size()==1)
			{
				return "success";
			}
			else
			{
				return "failure";
			}
			
	}
	
}
