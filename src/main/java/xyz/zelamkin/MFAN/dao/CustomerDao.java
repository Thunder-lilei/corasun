package xyz.zelamkin.MFAN.dao;

import java.util.ArrayList;
import java.util.List;

import xyz.zelamkin.MFAN.pojo.Customer;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author lilei
 *
 * 2019年11月12日
 */
public class CustomerDao {
	List<Customer> list;
	private Customer customer;
	/**
	 * @所有客户
	 * 查询所有客户
	 * @return
	 */
	public List<Customer> selectAllCustomer() {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
		list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.CustomerMapper.selectAllCustomer");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @我的客户
	 * 查询我的客户
	 * userid匹配
	 * @return
	 */
	public List<Customer> selectMyCustomer(Customer demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
		list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.CustomerMapper.selectMyCustomer",demo);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @查询客户ByName
	 * @return
	 */
	public Customer SelectOneByName(Customer demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			customer=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.CustomerMapper.SelectByName", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return customer;
	}
	/**
	 * @查询客户ByKey
	 * @return
	 */
	public Customer SelectOneByKey(Customer demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			customer=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.CustomerMapper.selectByPrimaryKey", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return customer;
	}
	/**
	 * @添加客户
	 * @return
	 */
	public int AddOne(Customer demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.insert("xyz.zelamkin.MFAN.mapper.CustomerMapper.insertSelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @删除客户
	 * @return
	 */
	public int DeleteOne(Customer demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.CustomerMapper.deleteByPrimaryKey", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @修改客户
	 * @return
	 */
	public int UpdataOne(Customer demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.CustomerMapper.updateByPrimaryKeySelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
}
