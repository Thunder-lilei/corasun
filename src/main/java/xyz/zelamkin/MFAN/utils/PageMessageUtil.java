package xyz.zelamkin.MFAN.utils;

import javax.servlet.http.HttpServletRequest;

public class PageMessageUtil {
	
	public static void TransmitInformation(HttpServletRequest request,String message) 
	{
		request.setAttribute("message", message);
	}
	public static void NotAdmin(HttpServletRequest request) 
	{
		request.setAttribute("message", "非管理员权限");
	}
	public static void WrongPhone(HttpServletRequest request) 
	{
		request.setAttribute("message", "电话号码不匹配");
	}
	public static void WrongMail(HttpServletRequest request) 
	{
		request.setAttribute("message", "邮箱不匹配");
	}
	public static void WrongCodeOrPhone(HttpServletRequest request) 
	{
		request.setAttribute("message", "验证码错误或电话号码不匹配");
	}
	public static void WrongCodeOrMail(HttpServletRequest request) 
	{
		request.setAttribute("message", "验证码错误或邮箱不匹配");
	}
	public static void FailLogin(HttpServletRequest request) 
	{
		request.setAttribute("message", "登陆失败");
	}
	public static void WrongPassword(HttpServletRequest request) 
	{
		request.setAttribute("message", "密码错误");
	}
	public static void EmptyListOrOther(HttpServletRequest request) 
	{
		request.setAttribute("message", "列表为空或其他原因");
	}
	public static void AddSuccess(HttpServletRequest request) 
	{
		request.setAttribute("message", "添加成功");
	}
	public static void AddFail(HttpServletRequest request) 
	{
		request.setAttribute("message", "添加失败");
	}
	public static void DeleteSuccess(HttpServletRequest request) 
	{
		request.setAttribute("message", "删除成功");
	}
	public static void DeleteFail(HttpServletRequest request) 
	{
		request.setAttribute("message", "删除失败");
	}
	public static void ChangeSuccess(HttpServletRequest request) 
	{
		request.setAttribute("message", "修改成功");
	}
	public static void ChangeFail(HttpServletRequest request) 
	{
		request.setAttribute("message", "修改失败");
	}
	public static void SearchFail(HttpServletRequest request) 
	{
		request.setAttribute("message", "查询失败");
	}
	public static void SearchSuccess(HttpServletRequest request) 
	{
		request.setAttribute("message", "查询成功");
	}
	public static void SameNameOrOther(HttpServletRequest request) 
	{
		request.setAttribute("message", "重名或其他原因");
	}
	public static void UpdataSuccess(HttpServletRequest request) 
	{
		request.setAttribute("message", "已更新");
	}
	public static void UpdataFail(HttpServletRequest request) 
	{
		request.setAttribute("message", "更新失败");
	}

}
