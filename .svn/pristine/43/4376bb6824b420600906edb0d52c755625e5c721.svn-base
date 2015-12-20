package com.baidu.user.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.baidu.user.bean.User;
import com.baidu.util.QueryResult;


public interface UserService {
      
	
	public void addUser(User user);
	
	 public long getCount();
	 
	 public void deleteAll(List<Integer> idList);
	 
	 public void delete(User user);
	 
	 public User getUserById(int id);
	 
	 public void updateUser(User user);
	 public List findAll();

	public QueryResult<User> getScrollData(int firstResult, int maxresult,String hql, Object[] array, LinkedHashMap<String, String> orderby);

	public User login(String loginName, String password);
	 
	 
	
}
