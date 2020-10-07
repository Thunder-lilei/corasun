package xyz.zelamkin.MFAN.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.dao.DepartmentDao;
import xyz.zelamkin.MFAN.pojo.Department;
import xyz.zelamkin.MFAN.pojo.User;

public class IsAdmin {
	private static HttpSession session;
	private static DepartmentDao departmentdao = new DepartmentDao();
	
	public static Boolean isadmin(HttpServletRequest request) 
	{
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		Department userdepartment = new Department();
		int userdepartmentid = user.getDept_id();
		userdepartment.setId(userdepartmentid);
		userdepartment = departmentdao.SelectOne(userdepartment);
		if(userdepartment!=null)
		{
//			String departmentname = departmentdao.SelectOne(userdepartment).getDept_name();
			Integer id = departmentdao.SelectOne(userdepartment).getId();
			if(id!=null && id.equals(2)) 
			{	
				return true;
			}
		}
		return false;
	}
}
