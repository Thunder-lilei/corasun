package xyz.zelamkin.MFAN.mapper;

import java.util.*;

import xyz.zelamkin.MFAN.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);
    
    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectAllUserWithStatus();
    
    User SelectOneWithStatus(User record);
    
    User SelectByNameWithStatus(User record);
    
    User SelectByTelWithStatus(User record);
    
    User SelectByMailWithStatus(User record);
}