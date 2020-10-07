package xyz.zelamkin.MFAN.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import xyz.zelamkin.MFAN.utils.MailUtils;
import xyz.zelamkin.MFAN.utils.PhoneCodeUtil;

/**
 * Servlet implementation class CodeMailDispatcher
 */
/**
 * @author lilei
 *
 * 2019年12月3日
 */
@WebServlet("/CodeMailDispatcher/*")
public class CodeMailDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeMailDispatcher() {
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
		
		switch(url) 
		{
			case "Login":
				break;
			case "Updata":
				break;
			case "Add":
				break;
		}
		
		int ran = (int) ((Math.random() * 9 + 1) * 100000);
		String code = String.valueOf(ran);
		
		response.setCharacterEncoding("utf-8");
		String mail = request.getParameter("mail");
		System.out.println(mail);
		String message = null;
		if( mail!=null) 
		{
			MailUtils.SendMail(mail, code, "corasun项目组");
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
