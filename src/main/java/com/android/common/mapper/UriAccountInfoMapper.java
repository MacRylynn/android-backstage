package com.android.common.mapper;

import com.android.common.entity.UriAccountInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UriAccountInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UriAccountInfoEntity record);

    int insertSelective(UriAccountInfoEntity record);

    UriAccountInfoEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UriAccountInfoEntity record);

    int updateByPrimaryKey(UriAccountInfoEntity record);
}