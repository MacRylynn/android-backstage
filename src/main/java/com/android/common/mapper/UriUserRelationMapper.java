package com.android.common.mapper;

import com.android.common.entity.UriUserRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UriUserRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UriUserRelationEntity record);

    int insertSelective(UriUserRelationEntity record);

    UriUserRelationEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UriUserRelationEntity record);

    int updateByPrimaryKey(UriUserRelationEntity record);
    
    /**
     * 功能描述: <根据usercode查询关系表>
     * @Param: [userCode]
     * @Return: java.util.List<com.android.common.entity.UriUserRelationEntity>
     * @Author: lijiao73
     * @Date: 2019/11/14 14:37
     */
    List<UriUserRelationEntity> selectByUserCode(@Param("userCode") String userCode);
}