package xyz.zelamkin.MFAN.mapper;

import java.util.List;

import xyz.zelamkin.MFAN.pojo.Storage;

public interface StorageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);
    
    int insertOne(Storage record);

    Storage selectByPrimaryKey(Integer id);

    Storage selectByProduct_id(Integer id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);
    
    List<Storage> selectAllStorage();
}