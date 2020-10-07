package xyz.zelamkin.MFAN.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import xyz.zelamkin.MFAN.pojo.Customer;
import xyz.zelamkin.MFAN.pojo.Product;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;

/**
 * @author lilei
 *
 * 2019年11月12日
 */
public class ProductDao {
	private List<Product> list;
	private Product product;
	/**
	 * 	查询全部商品
	 * @return UserList
	 */
	public List<Product> selectAllProductWithStatus() {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.ProductMapper.selectAllProductWithStatus");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @添加商品
	 * @return
	 */
	public int AddOne(Product demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.insert("xyz.zelamkin.MFAN.mapper.ProductMapper.insertSelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @删除商品
	 * @return
	 */
	public int DeleteOne(Product demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			Product product = new Product();
			product=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.ProductMapper.selectByPrimaryKeyWithStatus", demo);
			product.setStatus(0);
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.ProductMapper.updateByPrimaryKeySelective", product);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @修改商品
	 * @return
	 */
	public int UpdataOne(Product demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.ProductMapper.updateByPrimaryKeySelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @查询商品ByName
	 * @return
	 */
	public Product SelectOneByName(Product demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			product=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.ProductMapper.SelectByNameWithStatus", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return product;
	}
	/**
	 * @查询商品ByKey
	 * @return
	 */
	public Product SelectOneByKey(Product demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			product=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.ProductMapper.selectByPrimaryKeyWithStatus", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return product;
	}

}
