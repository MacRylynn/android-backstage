package com.android.common.mapper;

import com.android.common.entity.UriUserRelationEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UriUserRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UriUserRelationEntity record);

    int insertSelective(UriUserRelationEntity record);

    UriUserRelationEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UriUserRelationEntity record);

    int updateByPrimaryKey(UriUserRelationEntity record);
}