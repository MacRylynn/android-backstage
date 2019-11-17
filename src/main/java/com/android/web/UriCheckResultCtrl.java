package com.android.web;

import com.android.common.util.ImageProcess;
import com.android.domain.base.CommonRequest;
import com.android.domain.base.CommonResponse;
import com.android.domain.request.UriCheckResultReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriCheckResultVo;
import com.android.service.UriCheckResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UriCheckResultCtrl
 * @Description 检测结果
 * @Author: lijiao73
 * @Date: 2019/11/14 16:07
 */
@RestController
@RequestMapping("/web/check")
public class UriCheckResultCtrl {
    private static final Logger logger = LoggerFactory.getLogger(UriCheckResultCtrl.class);

    @Resource
    private UriCheckResultService uriCheckResultService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login"); // 返回login.html页面
        return model;
    }

    @PostMapping("/addcheckresult")
    public CommonResponse<Boolean> addCheckResult(MultipartFile file, @PathVariable("userId") Long userId) {
        logger.info("UriUserCtrl|addAccount，账户用户控制层|新增账户信息，入参为：{}", userId);
        CommonResponse<Boolean> res = new CommonResponse<>();
        //todo 开发
        //1. 保存文件
        String filePath = "";
        //2. 进行算法处理
        String result = ImageProcess.imageProcess(file);
        //3. 保存处理结果
        UriCheckResultReq checkResult = new UriCheckResultReq();
        checkResult.setCheckResult(result);
        checkResult.setCheckTime(new Date());
        checkResult.setResultImagePath(filePath);
        Boolean status = uriCheckResultService.addCheckResult(checkResult);
        res.setResultData(status);
        return res;
    }

    @PatchMapping("/selectrecording")
    public CommonResponse<List<UriCheckResultVo>> queryCheckResultByUserId(@RequestBody CommonRequest<UriUserInfoReq> commonRequest) {
        logger.info("UriUserCtrl|addAccount，账户用户控制层|选择检测结果记录，入参为：{}", commonRequest.toString());
        CommonResponse<List<UriCheckResultVo>> res = new CommonResponse<>();
        UriUserInfoReq req = commonRequest.getRequestData();
        List<UriCheckResultVo> result = uriCheckResultService.queryCheckResultByUserId(req);
        res.setResultData(result);
        return res;
    }


}
