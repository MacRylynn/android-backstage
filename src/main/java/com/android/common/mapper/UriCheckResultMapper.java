package com.android.common.mapper;

import com.android.common.entity.UriCheckResultEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UriCheckResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UriCheckResultEntity record);

    int insertSelective(UriCheckResultEntity record);

    UriCheckResultEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UriCheckResultEntity record);

    int updateByPrimaryKey(UriCheckResultEntity record);
}