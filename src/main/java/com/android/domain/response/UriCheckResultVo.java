package com.android.domain.response;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UriCheckResultVo
 * @Description 用户检测结果公共返回类，为了在前后端传输不使用实体类，所以使用传输层
 * @Author: lijiao73
 * @Date: 2019/11/14 09:22
 */
public class UriCheckResultVo implements Serializable {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户id（与user表关联）
     */
    private Long userId;

    /**
     * 检测结果
     */
    private String checkResult;

    /**
     * 创建时间
     */
    private Date checkTime;

    /**
     * 检测结果图片地址
     */
    private String resultImagePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getResultImagePath() {
        return resultImagePath;
    }

    public void setResultImagePath(String resultImagePath) {
        this.resultImagePath = resultImagePath == null ? null : resultImagePath.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", checkResult=").append(checkResult);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", resultImagePath=").append(resultImagePath);
        sb.append("]");
        return sb.toString();
    }
}