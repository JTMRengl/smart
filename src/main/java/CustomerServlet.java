import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smart4j.charpter2.model.Customer;
import org.smart4j.charpter2.service.CustomerService;

/**
 * ��ѯ�ͻ��б�
 * @author JTMRengl
 * 2016-01-22
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	
	/**
	 * ���� �����û�����
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Customer> customerList = customerService.getCustomerList();
		req.setAttribute("customerList", customerList);
		req.getRequestDispatcher("/view/customer.jsp").forward(req, resp);
	}

	/**
	 * �������ͻ�����
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
