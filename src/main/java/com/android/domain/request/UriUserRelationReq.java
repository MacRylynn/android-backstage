package com.android.domain.request;

import java.io.Serializable;

/**
 * @ClassName UriUserRelationReq
 * @Description 用户关系信息公共请求类，为了在前后端传输不使用实体类，所以使用传输层
 * @Author: lijiao73
 * @Date: 2019/11/14 09:22
 */
public class UriUserRelationReq implements Serializable {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户编号(系统内部使用)
     */
    private String userCode;

    /**
     * 用户id（与user表关联）
     */
    private Long userId;

    /**
     * 关系类型RelationTypeEnum(PARENTS,父母;SELF,自己;BROTHER,兄弟;SISTER,姐妹;CHILDREN,子女;OTHERS,其他)
     */
    private String relationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType == null ? null : relationType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", userId=").append(userId);
        sb.append(", relationType=").append(relationType);
        sb.append("]");
        return sb.toString();
    }
}