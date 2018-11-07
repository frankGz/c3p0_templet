package com.gz.mvcapp.dao;


public interface UserDAO {
	
	public boolean isExist(String login, String password);
	
}
