package org.smart4j.charpter2.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.charpter2.helper.DatabaseHelper;
import org.smart4j.charpter2.model.Customer;
import org.smart4j.charpter2.util.PropertiesUtil;

import com.mysql.fabric.xmlrpc.base.Data;

/**
 * 2016-01-21 �ṩ�ͻ����ݵķ���
 * 
 * @author JTMRengl
 */
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	/**
	 * ��ȡ�ͻ��б� �ؼ����� ��ȡ���ݿ����Ӷ��� ִ��sql�Ķ���
	 * 
	 * ȱ�㣺 1.��̬�����Ҳ���Գ����ȥ 2.���ݿ������,�ر�Ҳ���Գ����һ�������ķ��� 3.���ݷ�װ����һ��,����̫��.�������ѯ������д
	 * ����������Ƴ���һ�����ݿ�����DatabaseHelper
	 */
	/*
	 * public List<Customer> getCustomerList() {
	 * 
	 * Connection connection = null;
	 * 
	 * List<Customer> customersList = new ArrayList<Customer>(); //��ȡ���ݿ����� try {
	 * 
	 * String sql = "SELECT * from Customer"; //�������-�൱����ͨ��sql�ͻ��˹������˺������½
	 * connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	 * //����sql-�൱����д��һ��sql��� PreparedStatement preparedStatement =
	 * connection.prepareStatement(sql); //ִ��sql�����ؽ����-�൱���˵���ִ��sql��䰴ť ResultSet
	 * resultSet = preparedStatement.executeQuery(); //ѭ���������װ�ͻ�List
	 * while(resultSet.next()){
	 * 
	 * Customer customer = new Customer();
	 * customer.setId(resultSet.getLong("id"));
	 * customer.setName(resultSet.getString("name"));
	 * customer.setEmail(resultSet.getString("email"));
	 * customer.setTelephone(resultSet.getString("telephone"));
	 * customer.setRemark(resultSet.getString("remark"));
	 * customersList.add(customer); } } catch (SQLException e) { LOGGER.error(
	 * "execute sql failure",e); }finally { if(connection != null){ try {
	 * connection.close(); } catch (SQLException e) { LOGGER.error(
	 * "close connection failure",e); } } }
	 * 
	 * return customersList; }
	 */

	/**
	 * ͨ����һ�θĶ�����ȱ��1,2�����,�漰�ı仯���Ǳ仯1,�仯2
	 */
	/*
	 * public List<Customer> getCustomerList() {
	 * 
	 * Connection conn = null;
	 * 
	 * List<Customer> customersList = new ArrayList<Customer>(); // ��ȡ���ݿ����� try
	 * {
	 * 
	 * String sql = "SELECT * from Customer"; // �������-�൱����ͨ��sql�ͻ��˹������˺������½ conn
	 * = DatabaseHelper.getConnection();//�仯1 // ����sql-�൱����д��һ��sql���
	 * PreparedStatement preparedStatement = conn.prepareStatement(sql); //
	 * ִ��sql�����ؽ����-�൱���˵���ִ��sql��䰴ť ResultSet resultSet =
	 * preparedStatement.executeQuery(); // ѭ���������װ�ͻ�List while
	 * (resultSet.next()) {
	 * 
	 * Customer customer = new Customer();
	 * customer.setId(resultSet.getLong("id"));
	 * customer.setName(resultSet.getString("name"));
	 * customer.setEmail(resultSet.getString("email"));
	 * customer.setTelephone(resultSet.getString("telephone"));
	 * customer.setRemark(resultSet.getString("remark"));
	 * customersList.add(customer); } } catch (SQLException e) { LOGGER.error(
	 * "execute sql failure", e); } finally {
	 * DatabaseHelper.closeConnection(conn);//�仯2 }
	 * 
	 * return customersList; }
	 */

	/**
	 * ʣ������: ��Connction������͸��������ȷ�������̰߳�ȫ ���:��ThreadLocal
	 */
	/*
	 * public List<Customer> getCustomerList() {
	 * 
	 * Connection conn = DatabaseHelper.getConnection();
	 * 
	 * String sql = "SELECT * from Customer"; return
	 * DatabaseHelper.entityList(Customer.class, conn, sql); }
	 */
	public List<Customer> getCustomerList() {
		String sql = "SELECT * from Customer";
		return DatabaseHelper.entityList(Customer.class, sql);
	}

	/**
	 * ��ȡ�ͻ�
	 */
	public Customer getCustomer(long id) {
		String sql = "SELECT * from Customer where id = " + id;
		return DatabaseHelper.Entity(Customer.class, sql);
	}

	/**
	 * �����ͻ�
	 */
	public boolean createCustomer(Map<String, Object> filedMap) {
		return DatabaseHelper.insertEntity(Customer.class, filedMap);
	}

	/**
	 * ���¿ͻ�
	 */
	public boolean updateCustomer(Long id, Map<String, Object> fileMap) {
		return DatabaseHelper.updateEntity(Customer.class, id, fileMap);
	}

	/**
	 * ɾ���ͻ�
	 */
	public boolean deleteCustomer(long id) {
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}
