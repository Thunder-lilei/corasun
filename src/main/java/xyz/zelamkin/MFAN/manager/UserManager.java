package xyz.zelamkin.MFAN.manager;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xyz.zelamkin.MFAN.dao.UserDao;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.FormBean;
import xyz.zelamkin.MFAN.utils.IsAdmin;
import xyz.zelamkin.MFAN.utils.PageMessageUtil;
import xyz.zelamkin.MFAN.utils.SetUserUtil;

/**
 * @author lilei
 *
 * 2019年11月10日
 */
public class UserManager {
	private UserDao userdao = new UserDao();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	
	public UserManager(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	/**
	 * @登录界面
	 * @return
	 */
	public String ToLogin(){
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		if(user!=null) 
		{
			return "/index.jsp";
		}
			return "/WEB-INF/Users/Login.jsp";
	}
	/**
	 * @登录
	 * 查询用户
	 * 获取验证码
	 * 电话号码匹配
	 * 邮箱匹配
	 * @return
	 */
	public String Login(){
		User demo =(User) FormBean.makeBean(request, User.class);
		User user=new User();
		User user_two = new User();
		User user_third = new User();
		user = userdao.SelectOne(demo);
		user_two = userdao.SelectOneByTel(demo);
		user_third = userdao.SelectOneByMail(demo);
		if(user!=null) 
		{
			session = request.getSession();
			session.setAttribute("User", user);
			SetUserUtil.SetUser(request);
    	    return "/index.jsp";
		}
		else if(user_two!=null) {
				session = request.getSession();
				session.setAttribute("User", user_two);
				SetUserUtil.SetUser(request);
	    	    return "/index.jsp";
		}
		else if(user_third!=null) {
				session = request.getSession();
				session.setAttribute("User", user_third);
				SetUserUtil.SetUser(request);
	    	    return "/index.jsp";
		}
			PageMessageUtil.WrongPassword(request);
			return "/WEB-INF/Users/Login.jsp";
	}
	/**
	 * @登出 
	 * @return
	 */
	public String Logout(){
			session = request.getSession();
			session.setAttribute("User", null);
    	    return "/index.jsp";
	}
	/**
	 * @查询所有用户
	 * 判断用户状态
	 * @return
	 */
	public String AllUsers(){
		List<User> allusers = userdao.selectAllUser();
		if(!allusers.isEmpty()) 
		{
			SetUserUtil.SetUser(request);
			request.setAttribute("AllUsers", allusers);
			return "/WEB-INF/Users/AllUsers.jsp";
		}
		SetUserUtil.SetUser(request);
		PageMessageUtil.EmptyListOrOther(request);
		return "/index.jsp";
	}
	/**
	 * @跳转用户头像表单
	 * @return
	 */
	public String ChangeHead(){
		return "/WEB-INF/Users/UserFormHead.jsp";
	}
	/**
	 * @用户头像表单提交
	 * 查询用户
	 * 查询电话
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String ChangeHeadSubmit() 
	{
		DiskFileItemFactory sf= new DiskFileItemFactory();//实例化磁盘被文件列表工厂
		sf.setSizeThreshold(1024*1024);//设置文件上传小于1M放在内存中
		//从工厂得到servletupload文件上传类
		ServletFileUpload sfu = new ServletFileUpload(sf);
		sfu.setHeaderEncoding("UTF-8");//设置字符
		sfu.setSizeMax(10*1024*1024);//上传文件最大10m
		
		User Selectuser = new User();
		String message = null;
		
		try {
			List<FileItem> lst = sfu.parseRequest(request);//得到request中所有的元素
			for (FileItem fileItem : lst) {
				if(fileItem.isFormField()){
				}else{
					System.out.println("size "+fileItem.getSize());
					System.out.println(fileItem.getInputStream());
					
				    byte[] byt = new byte[fileItem.getInputStream().available()];
				    fileItem.getInputStream().read(byt);
				    
				    session = request.getSession();
					Selectuser = (User)session.getAttribute("User");
					Selectuser = userdao.SelectOneByKey(Selectuser);
					if(Selectuser!=null && byt!=null) 
					{
						System.out.println(byt);
		      			Selectuser.setImage(byt);
		      			userdao.UpdataOne(Selectuser);
		      			message = "头像更新成功";
		      			session.setAttribute("User", Selectuser);
					}
					
	                System.out.println("转换成功");
				}
			}
					
					
	                			
		} catch (Exception e) {
			e.printStackTrace();
		}
		SetUserUtil.SetUser(request);
		PageMessageUtil.TransmitInformation(request, message);	
		return "UserInformation";
	}
	/**
	 * @跳转用户表单
	 * 查询权限
	 * @return
	 */
	public String AddUser(){
		if(IsAdmin.isadmin(request)) 
		{
			SetUserUtil.SetUser(request);
			return "/WEB-INF/Users/UserForm.jsp";
		}
		SetUserUtil.SetUser(request);
		PageMessageUtil.NotAdmin(request);
		return "AllUsers";
	}
	/**
	 * @用户表单提交
	 * 查询用户
	 * 查询电话
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String UserFormSubmit()
	{
		DiskFileItemFactory sf= new DiskFileItemFactory();//实例化磁盘被文件列表工厂
		sf.setSizeThreshold(1024*1024);//设置文件上传小于1M放在内存中
		String name = "";//普通field字段
		//从工厂得到servletupload文件上传类
		ServletFileUpload sfu = new ServletFileUpload(sf);
		sfu.setHeaderEncoding("UTF-8");//设置字符
		sfu.setSizeMax(10*1024*1024);//上传文件最大10m
		
		User user = new User();
		User Selectuser = new User();
		User SelectuserTel = new User();
		User SelectuserMail = new User();
		String message = null;
		
		try {
			List<FileItem> lst = sfu.parseRequest(request);//得到request中所有的元素
			for (FileItem fileItem : lst) {
				if(fileItem.isFormField()){
					if("username".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						user.setUsername(name);
					}
					else if("password".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						user.setPassword(name);;
					}
					else if("tel".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						user.setTel(name);;
					}
					else if("sex".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						user.setSex(name);;
					}
					else if("bankAccount".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						user.setBank_account(name);;
					}
					else if("birthday".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
						Date date=format.parse(name);
						user.setBirthday(date);;
					}
					else if("address".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						user.setAddress(name);
					}
					else if("mail".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
						user.setMail(name);;
					}
				}else{
					System.out.println("size "+fileItem.getSize());
					System.out.println(fileItem.getInputStream());
					
				    byte[] byt = new byte[fileItem.getInputStream().available()];
				    fileItem.getInputStream().read(byt);
				    user.setImage(byt);
					
	                 System.out.println("转换成功");
				}
			}
			Selectuser = userdao.SelectOneByName(user);
			SelectuserTel = userdao.SelectOneByTel(user);
			SelectuserMail = userdao.SelectOneByMail(user);
			
            if(SelectuserTel!=null && SelectuserMail!=null)
     		{
            		message = message + "电话与邮箱重复";
     				user.setTel(null);
     				user.setMail(null);
         			
     		}else if(SelectuserTel==null && SelectuserMail!=null) 
     		{
     				message = message + "邮箱重复";
     				user.setMail(null);
     		}else if(SelectuserTel!=null && SelectuserMail==null) 
     		{
     				message = message + "电话重复";
     				user.setTel(null);
     		}else if(Selectuser!=null) 
     		{
     				message = message + "用户名重复";
     				PageMessageUtil.TransmitInformation(request, message);
     				return "AddUser";
     		}
             		userdao.AddOne(user);
	                System.out.println(user.getImage());
      				PageMessageUtil.AddSuccess(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		SetUserUtil.SetUser(request);
		PageMessageUtil.TransmitInformation(request, message);	
		return "AllUsers";
	}
	/**
	 * @删除用户
	 * 查询用户dept_id
	 * 修改状态
	 * 查询管理员身份
	 * @return
	 */
	public String DeleteUser(){
		if(IsAdmin.isadmin(request)) 
		{
			User deleteuser = new User();
			String idstring = request.getParameter("id");
			Integer id = Integer.parseInt(idstring);
			deleteuser.setId(id);
			if(deleteuser!=null) 
			{
				SetUserUtil.SetUser(request);
				userdao.DeleteOne(deleteuser);
				PageMessageUtil.DeleteSuccess(request);
				return "AllUsers";
			}
		}
		SetUserUtil.SetUser(request);
		PageMessageUtil.DeleteFail(request);
		return "AllUsers";
	}
	/**
	 * @跳转修改用户密码表单
	 * @return
	 */
	public String UpdataUserPassword(){
		User user = new User();
		session = request.getSession();
		user = (User) session.getAttribute("User");
		request.setAttribute("User", user);
		SetUserUtil.SetUser(request);
		return "/WEB-INF/Users/UserFormPassword.jsp";
	}
	/**
	 * @用户密码表单修改提交
	 * 获取用户
	 * 获取验证码
	 * 更改密码
	 * 更新用户
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String UserFormPasswordSubmit()
	{
		User user = new User();
		session = request.getSession();
		user = (User) session.getAttribute("User");
		
		User demo =(User) FormBean.makeBean(request, User.class);
		String oldpassword = request.getParameter("oldpassword");
		User user_two = new User();
		User user_third = new User();
		user_two = userdao.SelectOneByTel(demo);
		user_third = userdao.SelectOneByMail(demo);

		if(oldpassword!=null) 
		{
			if(oldpassword.equals(user.getPassword())) 
			{
				user.setPassword(demo.getPassword());
				userdao.UpdataOne(user);
				session = request.getSession();
				session.setAttribute("User", user);
				request.setAttribute("User", user);
				PageMessageUtil.ChangeSuccess(request);
	    	    return "UserInformation";
			}
		} else if(user_two!=null) {
			if(user.getUsername().equals(user_two.getUsername())) 
			{
				user.setPassword(demo.getPassword());
				userdao.UpdataOne(user);
				session = request.getSession();
				session.setAttribute("User", user);
				request.setAttribute("User", user);
				PageMessageUtil.ChangeSuccess(request);
	    	    return "UserInformation";
			}else
			{
				PageMessageUtil.WrongPhone(request);
				return "UpdataUserPassword";
			}
		}else if(user_third!=null) {
			if(user.getUsername().equals(user_third.getUsername())) 
			{
				user.setPassword(demo.getPassword());
				userdao.UpdataOne(user);
				session = request.getSession();
				session.setAttribute("User", user);
				request.setAttribute("User", user);
				PageMessageUtil.ChangeSuccess(request);
	    	    return "UserInformation";
			}else
			{
				PageMessageUtil.WrongMail(request);
				return "UpdataUserPassword";
			}
		}
		
		return "UpdataUserPassword";
	}
	/**
	 * @跳转修改用户表单
	 * @return
	 */
	public String UpdataUser(){
		SetUserUtil.SetUser(request);
		return "/WEB-INF/Users/UserFormUpdata.jsp";
	}
	/**
	 * @用户表单修改提交
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String UserFormUpdataSubmit()
	{
		User user = new User();
		User SelectuserTel = new User();
		User SelectuserMail = new User();
		session = request.getSession();
		user = (User) session.getAttribute("User");
		User updatauser = (User) FormBean.makeBean(request, User.class);
		updatauser.setId(user.getId());
		updatauser.setImage(user.getImage());
		SelectuserTel = userdao.SelectOneByTel(updatauser);
		SelectuserMail =  userdao.SelectOneByMail(updatauser);
		if(SelectuserTel!=null && !SelectuserTel.getUsername().equals(user.getUsername())) 
		{
			user.setTel(null);
		}else {user.setTel(updatauser.getTel());}
		if(SelectuserMail!=null && !SelectuserMail.getUsername().equals(user.getUsername())) 
		{
			user.setMail(null);
		}else {user.setMail(updatauser.getMail());}
		user.setUsername(updatauser.getUsername());
		user.setAddress(updatauser.getAddress());
		if(updatauser!=null)
		{
			userdao.UpdataOne(user);
			session = request.getSession();
			session.setAttribute("User", user);
		}
		return "UserInformation";
	}
	/**
	 * @用户信息
	 * @return
	 */
	public String UserInformation(){
		SetUserUtil.SetUser(request);
		return "/WEB-INF/Users/UserInformation.jsp";
	}
	/**
	 * @查询用户信息
	 * 查询用户
	 * @return
	 */
	public String UserInformationSearch(){
		User demo = new User();
		demo = (User) FormBean.makeBean(request, User.class);
		if(demo!=null) 
		{
			User user_two = new User();
			user_two = userdao.SelectOneByName(demo);
			if(user_two!=null) 
			{
				SetUserUtil.SetUser(request);
				request.setAttribute("User_two", user_two);
				return "/WEB-INF/Users/OtherUserInformation.jsp";
			}else
			{
				PageMessageUtil.SearchFail(request);
				return "AllUsers";
			}
		}
		return "AllUsers";
	}

}
