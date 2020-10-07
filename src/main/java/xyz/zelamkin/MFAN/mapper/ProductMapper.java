package xyz.zelamkin.MFAN.mapper;

import java.util.List;

import xyz.zelamkin.MFAN.pojo.Product;
import xyz.zelamkin.MFAN.pojo.User;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKeyWithStatus(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    
    List<Product> selectAllProduct();
    
    List<Product> selectAllProductWithStatus();
    
    Product SelectByNameWithStatus(Product record);
}