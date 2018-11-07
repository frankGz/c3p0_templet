package com.gz.mvcapp.dao;

import java.util.List;

import com.gz.mvcapp.model.Log;

public interface LogDAO {
	
	public void save(int id, String message);
	
	public List<Log> getForListWithId(int id);

}
