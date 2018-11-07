package com.gz.mvcapp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gz.mvcapp.db.JdbcUtils;
/**
 *��װ��CRUD �ķ������Թ�����ʹ��
 *��ǰ DAO ֱ���ڷ����л�ȡ���ݿ����� 
 * @param <T>����ǰ DAO ����ʵ�����������ʲô
 */
public class DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();

	private Class<T> clazz;
	
	public DAO() {
		Type superClass = getClass().getGenericSuperclass();//���ظ���̳еĴ��з��͵ĸ���
		//System.out.println( superClass);
		//����Type ֻ��һ���ӿڣ��޷���ȡ�䷺�����ͣ�����Type�ӿڼ̳�ParameterizedType ��ʹ�ô����еķ�����ȡ
		if(superClass instanceof ParameterizedType) {//�������Ƿ��Ǵ���������
			ParameterizedType parameterizedType = (ParameterizedType)superClass;//����ǽ���ǿת�ɸ�����
			Type[] typeArgs = parameterizedType.getActualTypeArguments();//��ô�����������ͣ�����һ��Type����
			
			if(typeArgs != null && typeArgs.length > 0) {//�������ֵ���ǿգ����ҳ��ȴ���0 ֤����ȡ�˲�������
				
				if(typeArgs[0] instanceof Class) {//���������Ƿ���һ��Class
					clazz = (Class<T>) typeArgs[0];//����ǽ���ǿת 
				}
			}
		} 
	}
	
	/**
	 * ����ĳһ���ֶε�ֵ�����緵��ĳһ����¼customer NAME,�򷵻����ݱ������ж�������¼
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object ... args) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return (E)queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * ���� T ����Ӧ��List
	 * @param <T>
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql,Object ... args) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * ���ض�ӦT��һ��ʵ����Ķ���
	 * @param <T>
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object ... args) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * �÷�����װ��INSERT\DELETE\UPDATE ����
	 * @param sql��SQL ���
	 * @param args�� ���SQL����ռλ��
	 */
	public void update(String sql,Object ... args) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql, args);//SQLSERVER 2008 ʹ�ô˷���ִ��insert into�ᱨ����
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		
	}
	
}
