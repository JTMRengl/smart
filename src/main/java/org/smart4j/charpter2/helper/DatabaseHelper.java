package org.smart4j.charpter2.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.charpter2.util.CollectionUtil;
import org.smart4j.charpter2.util.PropertiesUtil;

/**
 * ���ݿ����ֹ��ܣ�
 * 1.�ṩ��ȡ���ݿ����������ļ�
 * 2.��ȡ���ݿ�����,�ر�
 * 3.���ݲ�ѯ����,����(Ӧ��Apache��DbUtils)
 * @author JTMRengl
 * 2016-01-21
 */
public class DatabaseHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner QUERY_RUNNER;
	/**
	 * �����̵߳�����
	 */
	private static final ThreadLocal<Connection> TL_LOCAL;
	/**
	 * Apache�����ӳ�����Դ
	 */
	private static final BasicDataSource DATA_SOURCE;
	
	//�������ݿ���س���
/*	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;*/
	
	//��̬���ʼ������
	static{
		TL_LOCAL = new ThreadLocal<Connection>();
		QUERY_RUNNER = new QueryRunner();
		
		
		//ͨ�������ļ����߻�ȡ���ݿ������ļ�
		Properties config = PropertiesUtil.loadProperties("config.properties");
		String driver = config.getProperty("jdbc.driver");
		String url = config.getProperty("jdbc.url");
		String username = config.getProperty("jdbc.username");
		String password = config.getProperty("jdbc.password");
		
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}

	/**
	 * ��ȡ���ݿ�����
	 */
	public static Connection getConnection(){
		Connection conn = TL_LOCAL.get();
		if(conn == null){
			try {
				conn = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				LOGGER.error("get connection failure",e);
				throw new RuntimeException(e);
			}finally {
				TL_LOCAL.set(conn);
			}
		}
		return conn;
	}
/*	*//**
	 * �ر����ݿ�����
	 *//*
	public static void closeConnection(){
		Connection conn = TL_LOCAL.get();
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure",e);
			}finally {
				TL_LOCAL.remove();
			}		
		}
	}*/
	
	/**
	 * ��ѯʵ���б�
	 */
	public static<T> List<T> entityList(Class<T> entityClass, String sql, Object...params){
		Connection conn = getConnection();
		List<T> entityList = null;
		try {
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
		} catch (SQLException e) {
			LOGGER.error("query entity list failure",e);
			throw new RuntimeException(e);
		}
		
		return entityList;
	}
	/**
	 * ��ѯʵ��
	 */
	public static<T> T Entity(Class<T> entityClass,String sql,Object...objects ){
		T entity;
		
		Connection conn = getConnection();
		try {
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass),objects);
		} catch (SQLException e) {
			LOGGER.error("query entity failure",e);
			throw new RuntimeException(e);
		}
		return entity;
	}
	
	/**
	 * ����ѯ
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object...objects){
		List<Map<String, Object>> result = null;
		
		Connection conn = getConnection();
		try {
			result = QUERY_RUNNER.query(conn, sql, new MapListHandler(),objects);
		} catch (SQLException e) {
			LOGGER.error("execute query failure",e);
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 
	 * @param sql
	 * @param objects
	 * @return ��Ӱ�������
	 */
	public static int executeUpdate(String sql,Object...objects){
		int rows = 0;
		Connection conn = getConnection();
		try {
			rows = QUERY_RUNNER.update(conn,sql,objects);
		} catch (SQLException e) {
			LOGGER.error("execute update failure",e);
			throw new RuntimeException(e);
		}
		
		return rows;
	}
	
	
	/**
	 * ����ʵ��
	 * ���뼯�ϱ�׼���
	 * INSERT INTO tab_comp VALUES
	 * (item1, price1, qty1),
	 * (item2, price2, qty2),
	 * (item3, price3, qty3);
	 * �õ�getTableName����,������ת���ɱ���
	 *�ж���Ӱ��������ǲ���1
	 */
	public static<T> boolean insertEntity(Class<T> entityClass,Map<String, Object> map){
		
		//�ж�Ҫ����ļ����ǲ��ǿ�
		if(CollectionUtil.isEmpty(map)){
			LOGGER.error("can not insert entity:map is empty");
			return false;
		}
		String sql = "INSERT INTO " + getTableName(entityClass);
		StringBuffer tab_comp = new StringBuffer("(");
		StringBuffer VALUES = new StringBuffer("(");
		for(String fileName:map.keySet()){
			tab_comp.append(fileName).append(",");
			VALUES.append("?, ");
		}
		tab_comp.replace(tab_comp.lastIndexOf(", "), tab_comp.length(), ")");
		VALUES.replace(VALUES.lastIndexOf(", "), VALUES.length(), ")");
		sql += tab_comp + "VALUES" + VALUES;
		
		Object[] params = map.values().toArray();
		
		return executeUpdate(sql, params) == 1;
	}
	/**
	 * ����ʵ��
	 * UPDATE Person SET Address = 'Zhongshan 23', City = 'Nanjing'
	 * WHERE LastName = 'Wilson'
	 *�ж���Ӱ��������ǲ���1
	 */
	public static<T> boolean updateEntity(Class<T> entityClass,long id,Map<String, Object> map){
		//�ж�Ҫ����ļ����ǲ��ǿ�
		if(CollectionUtil.isEmpty(map)){
			LOGGER.error("can not insert entity:map is empty");
			return false;
		}
		
		String sql = "update " + getTableName(entityClass) + " SET ";
		StringBuffer tab_comp = new StringBuffer();
		for(String fieldName:map.keySet()){
			tab_comp.append(fieldName).append("=?,");
		}
		
		sql += tab_comp.substring(0,tab_comp.lastIndexOf(", ")) + " WHERE id=?";
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(map.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		
		return executeUpdate(sql, params) == 1;
	}
	/**
	 *ɾ��ʵ��
	 *�ж���Ӱ���������1
	 */
	 public static<T> boolean deleteEntity(Class<T> entityClass, long id){
		 
		 String sql = "DELETE FROM " + getTableName(entityClass) + " where id = ?";
		 return executeUpdate(sql,id) == 1;
	 }
	 public static String getTableName(Class<?> entityClass){
		 
		 return entityClass.getName();
	 }
	
}
