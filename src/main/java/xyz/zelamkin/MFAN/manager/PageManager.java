package xyz.zelamkin.MFAN.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.zelamkin.MFAN.utils.SetUserUtil;

/**
 * @author lilei
 *
 * 2019年11月10日
 */
public class PageManager {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	
	public PageManager(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	/**
	 * @index界面
	 * @return
	 */
	public String ToIndex(){
		SetUserUtil.SetUser(request);
			return "/index.jsp";
	}
}
