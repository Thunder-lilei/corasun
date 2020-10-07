package xyz.zelamkin.MFAN.mapper;

import java.util.List;

import xyz.zelamkin.MFAN.pojo.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
    List<Orders> selectAllOrder();
    
    List<Orders> selectMyOrder(Orders record);
}