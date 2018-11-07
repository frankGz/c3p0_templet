package com.gz.mvcapp.dao.impl;

import java.util.List;

import com.gz.mvcapp.dao.DAO;
import com.gz.mvcapp.dao.LogDAO;
import com.gz.mvcapp.model.Log;

public class LogDAOJdbcImpl extends DAO<Log> implements LogDAO {

	@Override
	public void save(int id, String message) {
		// TODO Auto-generated method stub
		//String sql = "INSERT INTO log(id, message) VALUES(?, ?)";
		String sql = "INSERT INTO log(id, time,message) VALUES(?, NOW(),?)";
		update(sql, id, message);
	}

	@Override
	public List<Log> getForListWithId(int id) {
		String sql = "select id, time, message from log where id = ?";
		return getForList(sql, id);
	}

}
