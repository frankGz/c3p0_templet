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
	 * �ͷ� Connection
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
		//����Դֻ�ܱ�����һ�Ρ�
		dataSource = new ComboPooledDataSource("mvcapp");
	}
	
	
	/**
	 * ��������Դ��һ��Connection����
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
