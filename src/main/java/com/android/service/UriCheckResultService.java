package com.android.service;

import com.android.domain.request.UriCheckResultReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriCheckResultVo;

import java.util.List;

/**
 * @ClassName UriCheckResultService
 * @Description 检测结果服务
 * @Author: lijiao73
 * @Date: 2019/11/13 17:12
 */
public interface UriCheckResultService {
    /**
     * 功能描述:<增加检查结果>
     *
     * @Param: [req]
     * @Return: boolean
     * @Author: lijiao73
     * @Date: 2019/11/14 10:31
     */
    Boolean addCheckResult(UriCheckResultReq req);

    /**
     * 功能描述: <根据用户Id获取检测结果>
     *
     * @Param: [req]
     * @Return: java.util.List<com.android.domain.response.UriCheckResultVo>
     * @Author: lijiao73
     * @Date: 2019/11/14 10:33
     */
    List<UriCheckResultVo> queryCheckResultByUserId(UriUserInfoReq req);

    /**
     * 功能描述: <获取标准结果>
     * @Param: []
     * @Return: com.android.domain.response.UriCheckResultVo
     * @Author: lijiao73
     * @Date: 2019/11/14 10:38
     */
    UriCheckResultVo queryStandResult();
}
