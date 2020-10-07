package xyz.zelamkin.MFAN.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.pojo.User;

public class SetUserUtil {
	private static HttpSession session;
	
	
	public static void SetUser(HttpServletRequest request) 
	{
		session = request.getSession();
		User user = (User) session.getAttribute("User");
		request.setAttribute("User", user);
	}

}
