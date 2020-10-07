package xyz.zelamkin.MFAN.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import xyz.zelamkin.MFAN.pojo.Customer;
import xyz.zelamkin.MFAN.pojo.Orders;
import xyz.zelamkin.MFAN.pojo.Product;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;

/**
 * @author lilei
 *
 * 2019年11月12日
 */
public class OrderDao {
	private List<Orders> list;
	private Orders order;
	/**
	 * @所有订单
	 * @return
	 */
	public List<Orders> selectAllOrder() {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
		list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.OrdersMapper.selectAllOrder");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @我的订单
	 * @return
	 */
	public List<Orders> selectMyOrder(Orders demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
		list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.OrdersMapper.selectMyOrder",demo);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @添加订单
	 * @return
	 */
	public int AddOne(Orders demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.insert("xyz.zelamkin.MFAN.mapper.OrdersMapper.insertSelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @查询订单ByKey
	 * @return
	 */
	public Orders SelectOneByKey(Orders demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			order=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.OrdersMapper.selectByPrimaryKey", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return order;
	}
}
