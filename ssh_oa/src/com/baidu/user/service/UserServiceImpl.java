package com.baidu.user.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.baidu.user.bean.User;
import com.baidu.user.dao.UserDao;
import com.baidu.util.QueryResult;

public class UserServiceImpl implements UserService {

	private UserDao userDao;     
	
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public void addUser(User user) {
		//业务逻辑代码
		
		//dao
		userDao.save(user);
	}




	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return userDao.getCount();
	}


	@Override
	public void deleteAll(List idList) {
		userDao.deleteAll("id",idList);
	}


	@Override
	public void delete(User user) {
		userDao.delete(user);
		
	}


	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getEntityById(id);
	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}




	@Override
	public QueryResult<User> getScrollData(int firstResult, int maxresult,
			String wherehql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		return userDao.getScrollData(firstResult, maxresult, wherehql, queryParams, orderby);
	}


	@Override
	public List findAll() {
		return userDao.findAll();
	}


	

}
