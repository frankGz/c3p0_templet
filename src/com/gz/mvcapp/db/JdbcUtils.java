package com.gz.mvcapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Frank.Gz
 * Utility tools for JDBC
 */
public class JdbcUtils {

	/**
	 * 释放 Connection
	 */
	public static void releaseConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource dataSource = null;
	
	static{
		//数据源只能被创建一次。
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	
	
	/**
	 * 返回数据源的一个Connection对象
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
