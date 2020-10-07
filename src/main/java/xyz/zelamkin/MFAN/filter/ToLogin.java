package xyz.zelamkin.MFAN.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.pojo.User;

/**
 * Servlet Filter implementation class ToLogin
 */
/**
 * @author lilei
 *
 * 2019年11月10日
 */
@WebFilter("/*")
public class ToLogin implements Filter {

    /**
     * Default constructor. 
     */
    public ToLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myrequest = (HttpServletRequest)request;
		HttpServletResponse myresponse = (HttpServletResponse)response;
		HttpSession session = myrequest.getSession();
		
		String url = myrequest.getRequestURI();
		int index = url.lastIndexOf("/");
		url = url.substring(index+1);
		if(url.equals("index.jsp") || url.equals("ToIndex") || url.equals("login.jsp") || url.equals("Login") || url.equals("ToLogin")) 
		{
			chain.doFilter(request, response);
		}else 
		{
			User user = (User) session.getAttribute("User");
			if(user!=null) 
			{
				chain.doFilter(request, response);
			}else 
			{
				myrequest.getRequestDispatcher("/user/ToLogin").forward(myrequest, myresponse);
			}
			
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
