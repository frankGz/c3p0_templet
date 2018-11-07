package com.gz.mvcapp.dao.impl;

import com.gz.mvcapp.dao.DAO;
import com.gz.mvcapp.dao.UserDAO;
import com.gz.mvcapp.model.User;

public class UserDAOJdbcImpl extends DAO<User> implements UserDAO{

	@Override
	public boolean isExist(String login, String password) {
		String sql = "select login, password from user WHERE login = ?";
		User user = get(sql, login);
		if (user.getPassword() == null || !user.getPassword().equals(password)) {
			return false;
		}
		return true;
	}



}
