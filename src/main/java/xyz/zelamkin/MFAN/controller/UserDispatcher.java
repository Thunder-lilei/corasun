package xyz.zelamkin.MFAN.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.manager.UserManager;

/**
 * Servlet implementation class UserDispatcher
 */
/**
 * @author lilei
 *
 * 2019年11月10日
 */
@WebServlet("/user/*")
public class UserDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserManager manager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		manager = new UserManager(request,response);
		
		String url = request.getRequestURI();
		int index = url.lastIndexOf("/");
		url = url.substring(index+1);
		String traget="";	
	
			Method m;
			//reflect包
			try {
				System.out.println("url "+url);
				m = UserManager.class.getMethod(url, null);//获取方法
				traget=(String)m.invoke(manager, null);//获取结果
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			if(traget.endsWith("jsp")){//获取结果以jsp结尾为跳转页面
				request.getRequestDispatcher(traget).forward(request, response);
			}else{//否则执行方法
				response.sendRedirect(traget);
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
