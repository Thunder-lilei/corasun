package xyz.zelamkin.MFAN.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import xyz.zelamkin.MFAN.pojo.Customer;
import xyz.zelamkin.MFAN.pojo.Department;
import xyz.zelamkin.MFAN.utils.SqlSessionFactoryUtils;

/**
 * @author lilei
 *
 * 2019年11月10日
 */
public class DepartmentDao {
	private Department department;
	private List<Department> list;
	/**
	 * @查询单个部门
	 * @param department Department对象 
	 * @return
	 */
	public Department SelectOne(Department demo) 
	{
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			department=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.DepartmentMapper.selectByPrimaryKey", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
		return department;
	}
	/**
	 * @查询所有部门
	 * @return
	 */
	public List<Department> selectAllDepartment() {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
		list=SqlSession.selectList("xyz.zelamkin.MFAN.mapper.DepartmentMapper.selectAllDepartment");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			SqlSession.close();
		}
		return list;
	}
	/**
	 * @查询部门ByKey
	 * @return
	 */
	public Department SelectOneByKey(Department demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			department=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.DepartmentMapper.selectByPrimaryKey", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return department;
	}
	/**
	 * @查询部门ByName
	 * @return
	 */
	public Department SelectOneByName(Department demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		try {
			department=SqlSession.selectOne("xyz.zelamkin.MFAN.mapper.DepartmentMapper.SelectByName", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return department;
	}
	/**
	 * @添加部门
	 * @return
	 */
	public int AddOne(Department demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.insert("xyz.zelamkin.MFAN.mapper.DepartmentMapper.insertSelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}
	/**
	 * @修改部门
	 * @return
	 */
	public int UpdataOne(Department demo) {
		SqlSession SqlSession= SqlSessionFactoryUtils.getSessionFactory().openSession(true);
		int result=0;
		try {
			result=SqlSession.update("xyz.zelamkin.MFAN.mapper.DepartmentMapper.updateByPrimaryKeySelective", demo);
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				SqlSession.close();
			}
			return result;
	}

}
