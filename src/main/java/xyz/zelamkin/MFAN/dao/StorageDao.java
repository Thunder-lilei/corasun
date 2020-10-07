package xyz.zelamkin.MFAN.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import xyz.zelamkin.MFAN.pojo.Storage;
import xyz.zelamkin.MFAN.pojo.User;
import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;

/**
 * @author lilei
 *
 * 2019年11月12日
 */
public class StorageDao {
	private List<Storage> list;
	private Storage storage;
	/**
	 * @查询库存
	 * 	查询全部用户，获取用户列表
	 * @return 
	 */
	public List<Storage> selectAllStorage() {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
		list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.StorageMapper.selectAllStorage");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @查询用户ByKey
	 * @return
	 */
	public Storage SelectOneByProduct_id(Storage demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			storage=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.StorageMapper.selectByProduct_id", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return storage;
	}
	/**
	 * @添加库存
	 * @return
	 */
	public int AddOne(Storage demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.insert("xyz.zelamkin.MFAN.mapper.StorageMapper.insertSelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @修改库存
	 * @return
	 */
	public int UpdataOne(Storage demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.StorageMapper.updateByPrimaryKeySelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
}
