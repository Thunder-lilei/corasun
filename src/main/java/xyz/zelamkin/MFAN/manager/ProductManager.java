package xyz.zelamkin.MFAN.manager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.dao.ProductDao;
import xyz.zelamkin.MFAN.dao.StorageDao;
import xyz.zelamkin.MFAN.pojo.Product;
import xyz.zelamkin.MFAN.pojo.Storage;
import xyz.zelamkin.MFAN.utils.FormBean;
import xyz.zelamkin.MFAN.utils.IsAdmin;
import xyz.zelamkin.MFAN.utils.PageMessageUtil;
import xyz.zelamkin.MFAN.utils.SetUserUtil;

/**
 * @author lilei
 *
 * 2019年11月12日
 */
public class ProductManager {
	private ProductDao productdao=new ProductDao();
	private StorageDao storagedao=new StorageDao();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	
	public ProductManager(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	/**
	 * @查询所有商品
	 * 判断商品状态
	 * @return
	 */
	public String AllProducts(){
		List<Product> allproducts = productdao.selectAllProductWithStatus();
		if(!allproducts.isEmpty()) 
		{
			SetUserUtil.SetUser(request);
			request.setAttribute("AllProducts", allproducts);
			return "/WEB-INF/Product/AllProducts.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.EmptyListOrOther(request);
		}
		return "/index.jsp";
	}
	/**
	 * @查看商品详情
	 * @return
	 */
	public String ProductInformation(){
		Product product = new Product();
		String idString = request.getParameter("id");
		product.setId(Integer.parseInt(idString));
		product = productdao.SelectOneByKey(product);
		if(product!=null) 
		{
			request.setAttribute("Product", product);
			SetUserUtil.SetUser(request);
			return "/WEB-INF/Product/ProductInformation.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.EmptyListOrOther(request);
		}
		return "AllProducts";
	}
	/**
	 * @搜索商品详情
	 * @return
	 */
	@SuppressWarnings("unused")
	public String ProductInformationSearch(){
		List<Product> listproduct = new ArrayList<Product>();
		List<Product> listproduct_two = new ArrayList<Product>();
		listproduct = productdao.selectAllProductWithStatus();
		String name = request.getParameter("name");
		
		for(Product p:listproduct) 
		{
			if(p.getTitle().contains(name)) 
			{
				listproduct_two.add(p);
			}
		}
		if(!listproduct_two.isEmpty()) 
		{
			SetUserUtil.SetUser(request);
			request.setAttribute("AllProducts", listproduct_two);
			return "/WEB-INF/Product/AllProducts.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.EmptyListOrOther(request);
		}
		return "AllProducts";
	}
	/**
	 * @跳转商品表单
	 * 查询用户权限
	 * @return
	 */
	public String AddProduct(){
		if(IsAdmin.isadmin(request)) 
		{
			SetUserUtil.SetUser(request);
			return "/WEB-INF/Product/ProductForm.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "/index.jsp";
	}
	/**
	 * @商品表单提交
	 * 查询商品
	 * 创建库存
	 * 查询管理员身份
	 * @return
	 */
	public String ProductFormSubmit() throws UnsupportedEncodingException
	{	
		if(IsAdmin.isadmin(request)) 
		{
			Product product = (Product) FormBean.makeBean(request, Product.class);
			Product Selectproduct = new Product();
			Selectproduct = productdao.SelectOneByName(product);
			if(Selectproduct==null && product!=null) 
			{
				Storage storage = new Storage();
				productdao.AddOne(product);
				Selectproduct = productdao.SelectOneByName(product);
				if(Selectproduct!=null) 
				{
					storage.setProduct_id(Selectproduct.getId());
					storagedao.AddOne(storage);
				}
			}else 
			{
				SetUserUtil.SetUser(request);
				PageMessageUtil.SameNameOrOther(request);
			}
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllProducts";
	}
	/**
	 * @跳转修改商品表单
	 * 查询用户权限
	 * @return
	 */
	public String UpdataProduct(){
		if(IsAdmin.isadmin(request)) 
		{
			Product product = new Product();
			String idstring = request.getParameter("id");
			Integer id = Integer.parseInt(idstring);
			if(id!=null) 
			{
				product.setId(id);
				product = productdao.SelectOneByKey(product);
				if(product!=null) 
				{
					request.setAttribute("Product", product);
					session = request.getSession();
					session.setAttribute("ProductId", product.getId());
					SetUserUtil.SetUser(request);
					return "/WEB-INF/Product/ProductFormUpdata.jsp";
				}else 
				{
					SetUserUtil.SetUser(request);
					PageMessageUtil.SearchFail(request);
				}
			}
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllProducts";
	}
	/**
	 * @商品表单修改提交
	 * 用户权限查询
	 * @return
	 */
	public String ProductFormUpdataSubmit() 
	{
		if(IsAdmin.isadmin(request)) 
		{
			Product updataproduct = new Product();
			session = request.getSession();
			Integer productid = (Integer) session.getAttribute("ProductId");
			if(productid!=null) 
			{
					updataproduct = (Product)FormBean.makeBean(request, Product.class);
					updataproduct.setId(productid);
					if(updataproduct!=null) 
					{
						SetUserUtil.SetUser(request);
						productdao.UpdataOne(updataproduct);
					}
			}
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllProducts";
	}
	/**
	 * @删除商品
	 * 查询用户权限
	 * 修改状态
	 * @return
	 */
	public String DeleteProduct(){
		if(IsAdmin.isadmin(request)) 
		{
			Product deleteproduct = new Product();
			String idstring = request.getParameter("id");
			Integer id = Integer.parseInt(idstring);
			deleteproduct.setId(id);
			if(deleteproduct!=null) 
			{
				SetUserUtil.SetUser(request);
				productdao.DeleteOne(deleteproduct);
				PageMessageUtil.DeleteSuccess(request);
			}
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllProducts";
	}

}
