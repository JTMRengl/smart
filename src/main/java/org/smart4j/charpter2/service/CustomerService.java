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
 * 2016-01-21 提供客户数据的服务
 * 
 * @author JTMRengl
 */
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	/**
	 * 获取客户列表 关键对象 获取数据库连接对象 执行sql文对象
	 * 
	 * 缺点： 1.静态代码块也可以抽离出去 2.数据库的连接,关闭也可以抽离出一个公共的方法 3.数据封装都在一起,代码太多.其他表查询还得再写
	 * 综上所述设计抽离一个数据库助手DatabaseHelper
	 */
	/*
	 * public List<Customer> getCustomerList() {
	 * 
	 * Connection connection = null;
	 * 
	 * List<Customer> customersList = new ArrayList<Customer>(); //获取数据库连接 try {
	 * 
	 * String sql = "SELECT * from Customer"; //获得连接-相当于人通过sql客户端工具用账号密码登陆
	 * connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	 * //输入sql-相当于人写了一条sql语句 PreparedStatement preparedStatement =
	 * connection.prepareStatement(sql); //执行sql并返回结果集-相当于人点了执行sql语句按钮 ResultSet
	 * resultSet = preparedStatement.executeQuery(); //循环结果集封装客户List
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
	 * 通过第一次改动，将缺点1,2解决了,涉及的变化点是变化1,变化2
	 */
	/*
	 * public List<Customer> getCustomerList() {
	 * 
	 * Connection conn = null;
	 * 
	 * List<Customer> customersList = new ArrayList<Customer>(); // 获取数据库连接 try
	 * {
	 * 
	 * String sql = "SELECT * from Customer"; // 获得连接-相当于人通过sql客户端工具用账号密码登陆 conn
	 * = DatabaseHelper.getConnection();//变化1 // 输入sql-相当于人写了一条sql语句
	 * PreparedStatement preparedStatement = conn.prepareStatement(sql); //
	 * 执行sql并返回结果集-相当于人点了执行sql语句按钮 ResultSet resultSet =
	 * preparedStatement.executeQuery(); // 循环结果集封装客户List while
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
	 * DatabaseHelper.closeConnection(conn);//变化2 }
	 * 
	 * return customersList; }
	 */

	/**
	 * 剩余问题: 让Connction对我们透明，还得确保连接线程安全 解决:用ThreadLocal
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
	 * 获取客户
	 */
	public Customer getCustomer(long id) {
		String sql = "SELECT * from Customer where id = " + id;
		return DatabaseHelper.Entity(Customer.class, sql);
	}

	/**
	 * 创建客户
	 */
	public boolean createCustomer(Map<String, Object> filedMap) {
		return DatabaseHelper.insertEntity(Customer.class, filedMap);
	}

	/**
	 * 更新客户
	 */
	public boolean updateCustomer(Long id, Map<String, Object> fileMap) {
		return DatabaseHelper.updateEntity(Customer.class, id, fileMap);
	}

	/**
	 * 删除客户
	 */
	public boolean deleteCustomer(long id) {
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}
