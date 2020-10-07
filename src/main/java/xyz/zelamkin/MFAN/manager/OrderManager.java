	package xyz.zelamkin.MFAN.manager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.dao.CustomerDao;
import xyz.zelamkin.MFAN.dao.DepartmentDao;
import xyz.zelamkin.MFAN.dao.OrderDao;
import xyz.zelamkin.MFAN.dao.ProductDao;
import xyz.zelamkin.MFAN.dao.StorageDao;
import xyz.zelamkin.MFAN.dao.UserDao;
import xyz.zelamkin.MFAN.pojo.Customer;
import xyz.zelamkin.MFAN.pojo.Department;
import xyz.zelamkin.MFAN.pojo.Orders;
import xyz.zelamkin.MFAN.pojo.Product;
import xyz.zelamkin.MFAN.pojo.Storage;
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
public class OrderManager {
	private OrderDao orderdao = new OrderDao();
	private UserDao userdao = new UserDao();
	private CustomerDao customerdao = new CustomerDao();
	private ProductDao productdao = new ProductDao();
	private StorageDao storagedao = new StorageDao();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	
	public OrderManager(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	/**
	 * @我的所有订单
	 * @return
	 */	
	public String MyOrders(){
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		Orders order = new Orders();
		order.setUser_id(user.getId());
		List<Orders> allorders = orderdao.selectMyOrder(order);
		if(!allorders.isEmpty()) 
		{
			SetUserUtil.SetUser(request);
			request.setAttribute("AllOrders", allorders);
			return "/WEB-INF/Order/AllOrders.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.EmptyListOrOther(request);
		}
		return "/index.jsp";
	}
	/**
	 * @所有订单
	 * 查询管理员身份
	 * @return
	 */	
	public String AllOrders(){
		if(IsAdmin.isadmin(request)) 
		{
			List<Orders> allorders = orderdao.selectAllOrder();
			if(!allorders.isEmpty()) 
			{
				SetUserUtil.SetUser(request);
				request.setAttribute("AllOrders", allorders);
				return "/WEB-INF/Order/AllOrders.jsp";
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
	 * @跳转临时订单表单
	 * 查询管理员身份
	 * @return
	 */
	public String AddOrderTemporary(){
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		if(user!=null && IsAdmin.isadmin(request)) 
		{
			List<Customer> allcustomers = customerdao.selectAllCustomer();
			List<User> allusers = userdao.selectAllUser();
			List<Product> allproducts = productdao.selectAllProductWithStatus();
			if(!allcustomers.isEmpty() && !allproducts.isEmpty()) 
			{
				SetUserUtil.SetUser(request);
				request.setAttribute("AllCustomers", allcustomers);
				request.setAttribute("AllUsers", allusers);
				request.setAttribute("AllProducts", allproducts);
				return "/WEB-INF/Order/OrderFormTemporary.jsp";
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
	 * @临时订单表单提交
	 * 查询库存
	 * 减少库存
	 * @return
	 */
	public String OrderFormTemporarySubmit() 
	{ 
		Orders order = new Orders();
		order.setCustomer_id(Integer.parseInt(request.getParameter("customer_id")));
		order.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		order.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		order.setProduct_count(Integer.parseInt(request.getParameter("product_count")));
		
		if(order!=null) 
		{
			Storage storage = new Storage();
			storage.setProduct_id(order.getProduct_id());
			storage = storagedao.SelectOneByProduct_id(storage);
			if(storage!=null && storage.getProduct_count()>=order.getProduct_count()) 
			{
				storage.setProduct_count(storage.getProduct_count()-order.getProduct_count());
				storagedao.UpdataOne(storage);
				Date date = new Date();
				order.setCreate_time(date);
				orderdao.AddOne(order);
			}else 
			{
				SetUserUtil.SetUser(request);
				PageMessageUtil.EmptyListOrOther(request);
			}
		}
		SetUserUtil.SetUser(request);
		return "MyOrders";
	}
	/**
	 * @跳转订单表单
	 * @return
	 */
	public String AddOrder(){
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		if(user!=null) 
		{
			Customer customer = new Customer();
			customer.setUser_id(user.getId());
			List<Customer> allcustomers = customerdao.selectMyCustomer(customer);
			List<Product> allproducts = productdao.selectAllProductWithStatus();
			if(!allcustomers.isEmpty() && !allproducts.isEmpty()) 
			{
				System.out.println("顾客 "+allcustomers);
				SetUserUtil.SetUser(request);
				request.setAttribute("AllCustomers", allcustomers);
				request.setAttribute("AllProducts", allproducts);
				return "/WEB-INF/Order/OrderForm.jsp";
			}else 
			{
				SetUserUtil.SetUser(request);
				PageMessageUtil.EmptyListOrOther(request);
			}
		}
		return "MyOrders";
	}
	/**
	 * @订单表单提交
	 * 查询库存
	 * 减少库存
	 * @return
	 */
	public String OrderFormSubmit() 
	{ 
		Orders order = new Orders();
		order.setCustomer_id(Integer.parseInt(request.getParameter("customer_id")));
		order.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		order.setProduct_count(Integer.parseInt(request.getParameter("product_count")));
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		if(user!=null && order!=null) 
		{
			Storage storage = new Storage();
			storage.setProduct_id(order.getProduct_id());
			storage = storagedao.SelectOneByProduct_id(storage);
			if(storage!=null && storage.getProduct_count()>=order.getProduct_count()) 
			{
				storage.setProduct_count(storage.getProduct_count()-order.getProduct_count());
				storagedao.UpdataOne(storage);
				order.setUser_id(user.getId());
				Date date = new Date();
				order.setCreate_time(date);
				orderdao.AddOne(order);
				SetUserUtil.SetUser(request);
				return "MyOrders";
			}else 
			{
				PageMessageUtil.EmptyListOrOther(request);
			}
		}
		SetUserUtil.SetUser(request);
		return "MyOrders";
	}
	/**
	 * @订单信息
	 * 查询订单
	 * 查询用户
	 * 查询负责人
	 * 查询商品
	 * @return
	 */
	public String OrderInformation(){
		Orders order = new Orders();
		String idstring = request.getParameter("id");
		Integer id = Integer.parseInt(idstring);
		if(id!=null) 
		{
			order.setId(id);
			order = orderdao.SelectOneByKey(order);
			if(order!=null) 
			{
				User user = new User();
				user.setId(order.getUser_id());
				user = userdao.SelectOneByKey(user);
				Customer customer = new Customer();
				customer.setId(order.getCustomer_id());
				customer = customerdao.SelectOneByKey(customer);
				Product product = new Product();
				product.setId(order.getProduct_id());
				product = productdao.SelectOneByKey(product);
				if(user!=null && customer!=null && product!=null) 
				{
					SetUserUtil.SetUser(request);
					request.setAttribute("Order", order);
					request.setAttribute("OrderUser", user);
					request.setAttribute("Customer", customer);
					request.setAttribute("Product", product);
					return "/WEB-INF/Order/OrderInformation.jsp";
				}else 
				{
					SetUserUtil.SetUser(request);
					PageMessageUtil.SearchFail(request);
				}
			}else 
			{
				SetUserUtil.SetUser(request);
				PageMessageUtil.SearchFail(request);
			}
		}
		
		return "MyOrders";
		
	}
	/**
	 * @查询订单信息
	 * 查询订单
	 * @return
	 */
	@SuppressWarnings("unused")
	public String OrderInformationSearch(){
		List<Orders> order_list = new ArrayList<Orders>();
		List<Orders> order_list_two = new ArrayList<Orders>();
		String name = request.getParameter("name");
		if(name!=null) 
		{
			order_list = orderdao.selectAllOrder();
			if(!order_list.isEmpty()) 
			{
				for(Orders order:order_list) 
				{
					User user = new User();
					user.setId(order.getUser_id());
					user = userdao.SelectOneByKey(user);
					Customer customer = new Customer();
					customer.setId(order.getCustomer_id());
					customer = customerdao.SelectOneByKey(customer);
					Product product = new Product();
					product.setId(order.getProduct_id());
					product = productdao.SelectOneByKey(product);
					if(user!=null && customer!=null && product!=null && user.getUsername().contains(name) || customer.getName().contains(name) || product.getTitle().contains(name)) 
					{
						order_list_two.add(order);
					}
				}
				if(!order_list_two.isEmpty()) 
				{
					SetUserUtil.SetUser(request);
					request.setAttribute("AllOrders", order_list_two);
					return "/WEB-INF/Order/AllOrders.jsp";
				}else 
				{
					SetUserUtil.SetUser(request);
					PageMessageUtil.EmptyListOrOther(request);
				}
			}
		}
		return "MyOrders";
	}
}
