package xyz.zelamkin.MFAN.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.dao.DepartmentDao;
import xyz.zelamkin.MFAN.dao.ProductDao;
import xyz.zelamkin.MFAN.dao.StorageDao;
import xyz.zelamkin.MFAN.pojo.Product;
import xyz.zelamkin.MFAN.pojo.ProductNameWithCount;
import xyz.zelamkin.MFAN.pojo.Storage;
import xyz.zelamkin.MFAN.utils.IsAdmin;
import xyz.zelamkin.MFAN.utils.PageMessageUtil;
import xyz.zelamkin.MFAN.utils.SetUserUtil;

/**
 * @author lilei
 *
 * 2019年11月12日
 */
public class StorageManager {
	private StorageDao storagedao = new StorageDao();
	private ProductDao productdao = new ProductDao();
	private DepartmentDao departmentdao = new DepartmentDao();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	
	public StorageManager(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	/**
	 * @库存查询
	 * 判断商品状态
	 * @return
	 */
	public String AllStorages(){
		List<ProductNameWithCount> alllist = new ArrayList<ProductNameWithCount>();
		List<Product> allproducts = productdao.selectAllProductWithStatus();
		for(Product product:allproducts) 
		{
			ProductNameWithCount productNameWithCount = new ProductNameWithCount();
			productNameWithCount.setId(product.getId());
			productNameWithCount.setTitle(product.getTitle());
			if(productdao.SelectOneByKey(product)!=null) 
			{
				Storage storage = new Storage();
				Storage storage_two = new Storage();
				storage.setProduct_id(product.getId());
				storage_two = storagedao.SelectOneByProduct_id(storage);
				if(storage_two!=null) 
				{
					productNameWithCount.setProduct_count(storage_two.getProduct_count());
					alllist.add(productNameWithCount);
				}
			}
		}
		if(!alllist.isEmpty()) 
		{
			SetUserUtil.SetUser(request);
			request.setAttribute("AllList", alllist);
			return "/WEB-INF/Storage/AllStorages.jsp";
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.EmptyListOrOther(request);
		}
		return "/index.jsp";
	}
	/**
	 * @库存修改
	 * 查询用户权限
	 * @return
	 */
	public String UpdataStorage() 
	{
		if(IsAdmin.isadmin(request)) {
			Storage updatastorage = new Storage();
			String countString = request.getParameter("count");
			Integer count = Integer.parseInt(countString);
			String idString = request.getParameter("id");
			Integer id = Integer.parseInt(idString);
			
			updatastorage.setProduct_id(id);
			updatastorage = storagedao.SelectOneByProduct_id(updatastorage);
			updatastorage.setProduct_count(count);
			
			if(updatastorage!=null)
			{
				SetUserUtil.SetUser(request);
				storagedao.UpdataOne(updatastorage);
			}
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllStorages";
		
	}
	/**
	 * @商品库存信息
	 * 查询商品
	 * 查询库存
	 * @return
	 */
	public String StorageInformationSearch(){
		List<Product> listproduct = new ArrayList<Product>();
		List<ProductNameWithCount> listproductnamewithcount = new ArrayList<ProductNameWithCount>();
		listproduct = productdao.selectAllProductWithStatus();
		String name = request.getParameter("name");
		
		for(Product p:listproduct) 
		{
			if(p.getTitle().contains(name)) 
			{
				ProductNameWithCount productnamewithcount = new ProductNameWithCount();
				Storage storage = new Storage();
				storage.setProduct_id(p.getId());
				storage = storagedao.SelectOneByProduct_id(storage);
				productnamewithcount.setTitle(p.getTitle());
				productnamewithcount.setId(p.getId());
				productnamewithcount.setProduct_count(storage.getProduct_count());
				listproductnamewithcount.add(productnamewithcount);
			}
		}
		
			if(!listproductnamewithcount.isEmpty()) 
			{
				SetUserUtil.SetUser(request);
				request.setAttribute("AllList", listproductnamewithcount);
			}else 
			{
				SetUserUtil.SetUser(request);
				PageMessageUtil.EmptyListOrOther(request);
			}
		return "/WEB-INF/Storage/AllStorages.jsp";
	}
}
