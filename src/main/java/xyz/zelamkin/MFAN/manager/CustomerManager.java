package xyz.zelamkin.MFAN.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import xyz.zelamkin.MFAN.dao.CustomerDao;
import xyz.zelamkin.MFAN.dao.UserDao;
import xyz.zelamkin.MFAN.pojo.Customer;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.FormBean;
import xyz.zelamkin.MFAN.utils.IsAdmin;
import xyz.zelamkin.MFAN.utils.PageMessageUtil;
import xyz.zelamkin.MFAN.utils.SetUserUtil;

/**
 * @author lilei
 *
 * 2019年11月12日
 */
public class CustomerManager {
	private CustomerDao customerdao = new CustomerDao();
	private UserDao userdao = new UserDao();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	
	public CustomerManager(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	} 
	/**
	 * @所有客户
	 * 查询管理员身份
	 * @return
	 */
	public String AllCustomers(){
		if(IsAdmin.isadmin(request)) 
		{
			SetUserUtil.SetUser(request);
			request.setAttribute("AllCustomers", customerdao.selectAllCustomer());
			return "/WEB-INF/Customer/AllCustomers.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
			return "/index.jsp";
	}
	/**
	 * @我的所有客户
	 * 获取用户id
	 * 获取客户id
	 * @return
	 */
	public String MyCustomers(){
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		Customer customer = new Customer();
		customer.setUser_id(user.getId());
		List<Customer> customer_list = new ArrayList<Customer>();
		customer_list = customerdao.selectMyCustomer(customer);
		
		if(!customer_list.isEmpty()) 
		{
			SetUserUtil.SetUser(request);
			request.setAttribute("AllCustomers", customer_list);
			return "/WEB-INF/Customer/AllCustomers.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.EmptyListOrOther(request);
		}
		return "/index.jsp";
	}
	/**
	 * @删除客户
	 * @return
	 */
	public String DeleteCustomer() 
	{ 
		if(IsAdmin.isadmin(request)) 
		{
			Customer demo = (Customer) FormBean.makeBean(request, Customer.class);
			customerdao.DeleteOne(demo);
			PageMessageUtil.DeleteSuccess(request);
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllCustomers";
	}
	/**
	 * @跳转客户表单
	 * 管理员权限
	 * @return
	 * @throws IOException 
	 */
	public String AddCustomer() throws IOException{
		if(IsAdmin.isadmin(request)) 
		{
			List<User> allusers = userdao.selectAllUser();
			if(!allusers.isEmpty()) 
			{
				SetUserUtil.SetUser(request);
				request.setAttribute("AllUsers", allusers);
				return "/WEB-INF/Customer/CustomerForm.jsp";
			}else
			{
				SetUserUtil.SetUser(request);
				PageMessageUtil.EmptyListOrOther(request);
			}
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "/index.jsp";
	}
	/**
	 * @客户表单提交
	 * 查询客户
	 * 拒绝重名
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String CustomerFormSubmit()
	{ 
		Customer customer = (Customer) FormBean.makeBean(request, Customer.class);
		Customer Selectcustomer = new Customer();
		Selectcustomer = customerdao.SelectOneByName(customer);
		if(Selectcustomer==null && customer!=null) 
		{
			String idString = request.getParameter("user_id");
			if(idString!=null) 
			{
				customer.setUser_id(Integer.parseInt(idString));
				customerdao.AddOne(customer);
				PageMessageUtil.AddSuccess(request);
			}
		}else 
		{
			PageMessageUtil.SameNameOrOther(request);
		}
		SetUserUtil.SetUser(request);
		return "AllCustomers";
	}
	/**
	 * @客户信息
	 * 查询客户
	 * @return
	 */
	public String CustomerInformation(){
		Customer customer = new Customer();
		String idstring = request.getParameter("id");
		Integer id = Integer.parseInt(idstring);
		if(id!=null) 
		{
			customer.setId(id);
			customer = customerdao.SelectOneByKey(customer);
			if(customer!=null) 
			{
				SetUserUtil.SetUser(request);
				request.setAttribute("Customer", customer);
				return "/WEB-INF/Customer/CustomerInformation.jsp";
			}else 
			{
				PageMessageUtil.EmptyListOrOther(request);
			}
		}
		SetUserUtil.SetUser(request);
		return "/WEB-INF/Customer/AllCustomers.jsp";
		
	}
	/**
	 * @跳转修改客户表单
	 * 查询客户
	 * @return
	 */
	public String UpdataCustomer(){
		Customer customer = new Customer();
		String idstring = request.getParameter("id");
		Integer id = Integer.parseInt(idstring);
		if(id!=null) 
		{
			customer.setId(id);
			customer = customerdao.SelectOneByKey(customer);
			if(customer!=null) 
			{
				SetUserUtil.SetUser(request);
				request.setAttribute("Customer", customer);
				session = request.getSession();
				session.setAttribute("CustomerId", customer.getId());
				return "/WEB-INF/Customer/CustomerFormUpdata.jsp";
			}else 
			{
				SetUserUtil.SetUser(request);
				PageMessageUtil.EmptyListOrOther(request);
			}
		}
		SetUserUtil.SetUser(request);
		return "/WEB-INF/Customer/CustomerInformation.jsp";
	}
	/**
	 * @客户表单修改提交
	 * 查询客户中的用户id
	 * 只能修改自己客户的信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String CustomerFormUpdataSubmit()
	{
		Customer updatacustomer = new Customer();
		session = request.getSession();
		Integer customerid = (Integer) session.getAttribute("CustomerId");
		updatacustomer = (Customer)FormBean.makeBean(request, Customer.class);
		if(updatacustomer!=null) 
		{
			updatacustomer.setId(customerid);
			customerdao.UpdataOne(updatacustomer);
			PageMessageUtil.UpdataSuccess(request);
		}
		SetUserUtil.SetUser(request);
		return "AllCustomers";
	}
}
