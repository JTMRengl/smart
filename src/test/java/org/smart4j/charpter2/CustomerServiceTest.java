package org.smart4j.charpter2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.charpter2.model.Customer;
import org.smart4j.charpter2.service.CustomerService;

/**
 * CustomerService单元测试
 * 
 * @author JTMRengl
 *
 */
public class CustomerServiceTest {

	private final CustomerService customerService;

	public CustomerServiceTest() {
		customerService = new CustomerService();
	}

	@Before
	public void init() {
		// TODO 初始化数据库
	}

	@Test
	public void getCustomerListTest() throws Exception {

		List<Customer> customerList = customerService.getCustomerList();
		for (Customer customer : customerList) {
			System.out.println(customer.getName()+","+customer.getEmail());
		}
		Assert.assertEquals(2, customerList.size());
	}

	@Test
	public void getCustomerTest() throws Exception {
		long id = 1;
		Customer customer = customerService.getCustomer(id);
		System.out.println(customer.getName()+","+customer.getEmail());
		Assert.assertNotNull(customer);
	}

	@Test
	public void createCustomerTest() throws Exception {

		Map<String, Object> filedMap = new HashMap<String, Object>();
		filedMap.put("name", "customer3");
		filedMap.put("contact", "John");
		filedMap.put("telephone", "13612345678");

		boolean result = customerService.createCustomer(filedMap);
		Assert.assertTrue(result);
	}

	@Test
	public void updateCustomerTest() throws Exception {

		long id = 1;
		Map<String, Object> filedMap = new HashMap<String, Object>();
		filedMap.put("contact", "李四");
		boolean result = customerService.updateCustomer(id, filedMap);
		Assert.assertTrue(result);
	}

	@Test
	public void deleteCustomerTest() throws Exception {

		long id = 1;
		boolean result = customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	}
}
