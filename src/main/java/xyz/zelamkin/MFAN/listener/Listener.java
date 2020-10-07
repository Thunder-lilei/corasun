package xyz.zelamkin.MFAN.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;
@WebListener
public class Listener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("sqlSessionFactory启动中");
		SqlSessionFactoryUtils.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("sqlSessionFactory销毁中");
		SqlSessionFactoryUtils.close();
	}

}
