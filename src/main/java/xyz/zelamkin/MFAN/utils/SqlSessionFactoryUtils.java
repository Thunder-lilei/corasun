 package xyz.zelamkin.MFAN.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
public class SqlSessionFactoryUtils {
	/**
	 * resouce mybatis配置文件的加载 
	 */
	private static String resouce="mybatis-config.xml";//配置文件
	private static SqlSessionFactory SqlsessionFactory;
	private static ThreadLocal<SqlSession> threadLocal=new ThreadLocal<SqlSession>();
	
	
//	static {
//			try {
//				InputStream is=Resources.getResourceAsStream(resouce);
//				SqlsessionFactory = new SqlSessionFactoryBuilder().build(is);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	}
	public static void init(){
		try {
			InputStream is=Resources.getResourceAsStream(resouce);
			SqlsessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取工厂对象的方法 
	 * @return
	 */
	public static SqlSessionFactory getSessionFactory() {
		return SqlsessionFactory;
	}
	/**
	 * 关闭Sqlsession
	 */
	public static void close() {
		SqlSession session=threadLocal.get();
		if(session!=null) {
			session.close();
			threadLocal.set(null);
		}
	}
}
