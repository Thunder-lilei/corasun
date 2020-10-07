package xyz.zelamkin.MFAN.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import xyz.zelamkin.MFAN.dao.UserDao;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;
/**
 * 	每个函数请补充注释 解释功能
 * @author zhangman and lilei
 * 2019年11月9日
 */
public class UserDao {
//	private SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
	private List<User> list;
	private User user;
	/**
	 * @author ZhangMan
	 * 	查询全部用户，获取用户列表
	 * @return UserList
	 */
	public List<User> selectAllUser() {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
		list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.UserMapper.selectAllUserWithStatus");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @查询用户
	 * @param demo User对象 
	 * @return
	 */
	
	public User SelectOne(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			user=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.UserMapper.SelectOneWithStatus", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return user;
	}
	/**
	 * @查询用户ByName
	 * @param demo User对象 
	 * @return
	 */
	public User SelectOneByName(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			user=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.UserMapper.SelectByNameWithStatus", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return user;
	}
	/**
	 * @查询用户ByTel
	 * @param demo User对象 
	 * @return
	 */
	public User SelectOneByTel(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			user=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.UserMapper.SelectByTelWithStatus", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return user;
	}
	/**
	 * @查询用户ByMail
	 * @param demo User对象 
	 * @return
	 */
	public User SelectOneByMail(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			user=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.UserMapper.SelectByMailWithStatus", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return user;
	}
	/**
	 * @查询用户ByKey
	 * @return
	 */
	public User SelectOneByKey(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			user=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.UserMapper.selectByPrimaryKeyWithStatus", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return user;
	}
	/**
	 * @添加用户
	 * @param demo User对象 
	 * @return
	 */
	public int AddOne(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.insert("xyz.zelamkin.MFAN.mapper.UserMapper.insertSelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @删除用户
	 * @param demo User对象 
	 * @return
	 */
	public int DeleteOne(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			User user = new User();
			user=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.UserMapper.selectByPrimaryKey", demo);
			user.setStatus(0);
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.UserMapper.updateByPrimaryKeySelective", user);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @修改用户
	 * @return
	 */
	public int UpdataOne(User demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.UserMapper.updateByPrimaryKeySelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}

}
