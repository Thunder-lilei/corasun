package xyz.zelamkin.MFAN.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import xyz.zelamkin.MFAN.utils.PhoneCodeUtil;

/**
 * Servlet implementation class CodePhoneDispatcher
 */
/**
 * @author lilei
 *
 * 2019年12月3日
 */
@WebServlet("/CodePhoneDispatcher/*")
public class CodePhoneDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodePhoneDispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		int index = url.lastIndexOf("/");
		url = url.substring(index+1);
		
		String skin = null;
		
		switch(url) 
		{
			case "Login":
				skin = "3";//登录
				break;
			case "Updata":
				skin = "7";//修改
				break;
			case "Add":
				skin = "2";//注册
				break;
		}
		
		response.setCharacterEncoding("utf-8");
		String phone = request.getParameter("tel");
		System.out.println(phone);
		String code = null;
		String message = null;
		if( phone!=null) 
		{
			if(phone.length()==11) 
			{
				String sign = "1";//签名
				code = PhoneCodeUtil.main(phone,skin,sign);
			}
		}
		if(code!=null) 
		{
			JSONObject json=new JSONObject();
			message = "验证码发送成功";
			json.put("message",message);
			json.put("code", code);
			PrintWriter out=response.getWriter(); 
		    out.write(json.toString());
		    out.flush();
		    out.close();
		}else 
		{
			JSONObject json=new JSONObject();
			message = "验证码发送失败";
			json.put("message",message);
			json.put("code", code);
			PrintWriter out=response.getWriter(); 
		    out.write(json.toString());
		    out.flush();
		    out.close();
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
