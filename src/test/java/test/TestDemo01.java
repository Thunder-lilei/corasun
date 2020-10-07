package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import xyz.zelamkin.MFAN.dao.UserDao;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;

public class TestDemo01 {
	UserDao UserDao=new UserDao();
	@Test
	public void test01() throws IOException {
		String resouce ="mybatis-config.xml";
		InputStream config=Resources.getResourceAsStream(resouce);
		SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(config);
		//打开和数据库之间的会话
		SqlSession session=factory.openSession();
//		delete("xyz.zelamkin.MFAN.mapper.UserMapper.deleteByPrimaryKey", demo.getId());
		session.delete("deleteUserByPrimaryKey", 15);
		session.commit();
//		List<User> list=session.selectList("selectAllUser");
//		for(User user:list) {
//			System.out.println(user.getUsername());
//		}
		session.close();
	}
	@Test
	public void testselectAllUser() {
		
		List<User> list=UserDao.selectAllUser();
		for(User user:list) {
			System.out.println(user);
		}
	}
	@Test
	public void test_SelectOne() {
		User user=new User();
		user.setUsername("zhangman");
		user.setPassword("zhangman");
		System.out.println(UserDao.SelectOne(user));
	}
	
	@Test
	public void test_deleteOne() {
		User user =new User();
		user.setId(3);
		System.out.println(UserDao.DeleteOne(user));
	}
	
	@Test
	public void test_updateOne() {
		User user =new User();
		user.setId(15);
		user.setUsername("gaoxieen");;
		UserDao.UpdataOne(user);
	}
}
