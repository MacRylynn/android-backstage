package com.android.common.mapper;

import com.android.common.entity.UriUserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UriUserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UriUserInfoEntity record);

    int insertSelective(UriUserInfoEntity record);

    UriUserInfoEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UriUserInfoEntity record);

    int updateByPrimaryKey(UriUserInfoEntity record);
}