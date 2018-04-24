package com.elitecore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elitecore.dao.UserDao;
import com.elitecore.model.User;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

public class userservices{

	
	private UserDao userDao;

	@Autowired
	public userservices(UserDao userDao) {
		this.userDao = userDao;
	}

	public userservices() {
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) throws Exception {
	
		System.out.println("Hakuna Matata in service"+user.getUserName()+" "+user.getPassword());
		userDao.saveUser(user);
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String checklogin(String username, String password) throws Exception {
		return "failure";
		//return userDao.checkLogin(username,password);
	}
	

}
