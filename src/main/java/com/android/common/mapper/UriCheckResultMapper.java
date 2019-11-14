package com.android.common.mapper;

import com.android.common.entity.UriCheckResultEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UriCheckResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UriCheckResultEntity record);

    int insertSelective(UriCheckResultEntity record);

    UriCheckResultEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UriCheckResultEntity record);

    int updateByPrimaryKey(UriCheckResultEntity record);

    /**
     * 功能描述: <根据userId查询检测记录>
     *
     * @Param: [userId]
     * @Return: java.util.List<com.android.common.entity.UriCheckResultEntity>
     * @Author: lijiao73
     * @Date: 2019/11/14 15:09
     */
    List<UriCheckResultEntity> selectByUserId(@Param("userId") Long userId);
}