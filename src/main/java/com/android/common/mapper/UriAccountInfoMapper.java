package com.android.common.mapper;

import com.android.common.entity.UriAccountInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UriAccountInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UriAccountInfoEntity record);

    int insertSelective(UriAccountInfoEntity record);

    UriAccountInfoEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UriAccountInfoEntity record);

    int updateByPrimaryKey(UriAccountInfoEntity record);

    /**
     * 功能描述: <根据账号查信息>
     * @Param: [accountNo]
     * @Return: com.android.common.entity.UriAccountInfoEntity
     * @Author: lijiao73
     * @Date: 2019/11/14 13:57
     */
    UriAccountInfoEntity selectByAccountNo(@Param("accountNo") String accountNo);

    /**
     * 功能描述: <查询所有账号信息>
     * @Param: []
     * @Return: java.util.List<com.android.common.entity.UriAccountInfoEntity>
     * @Author: lijiao73
     * @Date: 2019/11/14 14:20
     */
    List<UriAccountInfoEntity>selectAll();

}