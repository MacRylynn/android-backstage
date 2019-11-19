package com.android.service;

import com.android.domain.request.UriCheckResultReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriCheckResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * @Return: long 返回此次检测记录的主键
     * @Author: lijiao73
     * @Date: 2019/11/14 10:31
     */
    Long addCheckResult(UriCheckResultReq req);

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
     * 功能描述: <根据ID查询单条检测记录>
     *
     * @Param: [id]
     * @Return: com.android.domain.response.UriCheckResultVo
     * @Author: lijiao73
     * @Date: 2019/11/18 16:50
     */
    UriCheckResultVo queryCheckResultById(Long id);

    /**
     * 功能描述: <获取标准结果>
     *
     * @Param: []
     * @Return: com.android.domain.response.UriCheckResultVo
     * @Author: lijiao73
     * @Date: 2019/11/14 10:38
     */
    UriCheckResultVo queryStandResult();

    /**
     * 功能描述: <上传文件>
     *
     * @Param: [orginfile]
     * @Return: java.lang.String
     * @Author: lijiao73
     * @Date: 2019/11/18 16:38
     */
    String uploadMediaFile(MultipartFile orginfile) throws IOException;

    /**
     * 功能描述: <获取文件上传地址>
     *
     * @Param: []
     * @Return: java.lang.String
     * @Author: lijiao73
     * @Date: 2019/11/18 16:43
     */
    String getFilePath();
}
