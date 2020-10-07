package xyz.zelamkin.MFAN.manager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.dao.DepartmentDao;
import xyz.zelamkin.MFAN.dao.UserDao;
import xyz.zelamkin.MFAN.pojo.Customer;
import xyz.zelamkin.MFAN.pojo.Department;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.FormBean;
import xyz.zelamkin.MFAN.utils.IsAdmin;
import xyz.zelamkin.MFAN.utils.PageMessageUtil;
import xyz.zelamkin.MFAN.utils.SetUserUtil;

/**
 * @author lilei
 *
 * 2019年11月14日
 */
public class DepartmentManager {
	private DepartmentDao departmentdao = new DepartmentDao();
	private UserDao userdao = new UserDao();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	
	public DepartmentManager(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	} 
	/**
	 * @所有部门
	 * @return
	 */
	public String AllDepartments(){
		SetUserUtil.SetUser(request);
		request.setAttribute("AllDepartments", departmentdao.selectAllDepartment());
			return "/WEB-INF/Department/AllDepartments.jsp";
	}
	/**
	 * @跳转部门表单
	 * 查询用户权限
	 * @return
	 */
	public String AddDepartment(){
		if(IsAdmin.isadmin(request)) 
		{
			SetUserUtil.SetUser(request);
			return "/WEB-INF/Department/DepartmentForm.jsp";
		}else
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllDepartments";
	}
	/**
	 * @部门表单提交
	 * 查询部门
	 * @return
	 */
	public String DepartmentFormSubmit() 
	{ 
		Department department = (Department) FormBean.makeBean(request, Department.class);
		Department Selectdepartment = new Department();
		Selectdepartment = departmentdao.SelectOneByName(department);
		if(Selectdepartment==null && department!=null) 
		{
			departmentdao.AddOne(department);
			PageMessageUtil.AddSuccess(request);
		}else 
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.SameNameOrOther(request);
		}
			
		return "AllDepartments";
	}
	/**
	 * @跳转修改部门表单
	 * 查询部门
	 * 查询用户权限
	 * @return
	 */
	public String UpdataDepartment(){
		if(IsAdmin.isadmin(request)) 
		{
			Department department = new Department();
			String idstring = request.getParameter("id");
			Integer id = Integer.parseInt(idstring);
			if(id!=null) 
			{
				department.setId(id);
				department = departmentdao.SelectOneByKey(department);
				if(department!=null) 
				{
					SetUserUtil.SetUser(request);
					request.setAttribute("Department", department);
					session = request.getSession();
					session.setAttribute("DepartmentId", department.getId());
					return "/WEB-INF/Department/DepartmentFormUpdata.jsp";
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
		return "AllDepartments";
	}
	/**
	 * @部门表单修改提交
	 * @return
	 */
	public String DepartmentFormUpdataSubmit() 
	{
		Department updatadepartment = new Department();
		session = request.getSession();
		Integer departmentid = (Integer) session.getAttribute("DepartmentId");
		if(departmentid!=null) 
		{
			updatadepartment = (Department)FormBean.makeBean(request, Department.class);
			updatadepartment.setId(departmentid);
			if(updatadepartment!=null) 
			{
				departmentdao.UpdataOne(updatadepartment);
				PageMessageUtil.UpdataSuccess(request);
			}
		}
		SetUserUtil.SetUser(request);
		return "AllDepartments";
	}
	/**
	 * @跳转部门选择表单
	 * 保存人员id
	 * 查询用户权限
	 * @return
	 */
	public String InsertIntoDepartment(){
		if(IsAdmin.isadmin(request)) 
		{
			String Useridstring = request.getParameter("id");
			Integer Userid = Integer.parseInt(Useridstring);
			if(Userid!=null) 
			{
				SetUserUtil.SetUser(request);
				session = request.getSession();
				session.setAttribute("InsertIntoUserId", Userid);
			}
		}else
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "AllDepartments";
	}
	/**
	 * @部门选择表单提交
	 * 查询权限
	 * 查询人员
	 * 更新人员
	 * @return
	 */
	public String InsertIntoDepartmentSubmit() 
	{
		if(IsAdmin.isadmin(request)) 
		{
			session = request.getSession();
			Integer userid = (Integer) session.getAttribute("InsertIntoUserId");
			User user = new User();
			String Departmentidstring = request.getParameter("id");
			if(userid!=null && Departmentidstring!=null) 
			{
				Integer departmentid = Integer.parseInt(Departmentidstring);
				user.setId(userid);
				user = userdao.SelectOneByKey(user);
				if(user!=null) 
				{
					user.setDept_id(departmentid);
					userdao.UpdataOne(user);
					session.setAttribute("InsertIntoUserId", null);
					SetUserUtil.SetUser(request);
					PageMessageUtil.UpdataSuccess(request);
					return "/index.jsp";
				}
			}else 
			{
				List<User> allusers = userdao.selectAllUser();
				if(!allusers.isEmpty()) 
				{
					SetUserUtil.SetUser(request);
					request.setAttribute("AllUsers", allusers);
					return "/WEB-INF/Users/AllUsers.jsp";
				}else 
				{
					SetUserUtil.SetUser(request);
					PageMessageUtil.EmptyListOrOther(request);
				}
			}
		}else
		{
			SetUserUtil.SetUser(request);
			PageMessageUtil.NotAdmin(request);
		}
		return "/index.jsp";
		
	}

}
