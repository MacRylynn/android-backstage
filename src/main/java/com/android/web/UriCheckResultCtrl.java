package com.android.web;

import com.android.common.util.ImageProcess;
import com.android.domain.base.CommonRequest;
import com.android.domain.base.CommonResponse;
import com.android.domain.request.UriCheckResultReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriCheckResultVo;
import com.android.service.UriCheckResultService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
    public CommonResponse<Boolean> addCheckResult(MultipartFile file, @PathVariable("userId") Long userId) throws IOException {
        logger.info("UriUserCtrl|addAccount，账户用户控制层|新增账户信息，入参为：{}", userId);
        CommonResponse<Boolean> res = new CommonResponse<>();
        //todo 开发
        //1. 保存文件
        String filePath = uriCheckResultService.uploadMediaFile(file);
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

    @PatchMapping("/selectrecordings")
    public CommonResponse<List<UriCheckResultVo>> queryCheckResultByUserId(@RequestBody CommonRequest<UriUserInfoReq> commonRequest) {
        logger.info("UriUserCtrl|addAccount，账户用户控制层|选择检测结果记录，入参为：{}", commonRequest.toString());
        CommonResponse<List<UriCheckResultVo>> res = new CommonResponse<>();
        UriUserInfoReq req = commonRequest.getRequestData();
        List<UriCheckResultVo> result = uriCheckResultService.queryCheckResultByUserId(req);
        res.setResultData(result);
        return res;
    }

    @PatchMapping("/selectrecording")
    public CommonResponse<UriCheckResultVo> queryCheckResultById(@RequestBody CommonRequest<UriCheckResultReq> commonRequest) {
        logger.info("UriUserCtrl|addAccount，账户用户控制层|选择检测结果记录，入参为：{}", commonRequest.toString());
        CommonResponse<UriCheckResultVo> res = new CommonResponse<>();
        UriCheckResultReq req = commonRequest.getRequestData();
        UriCheckResultVo result = uriCheckResultService.queryCheckResultById(req.getId());
        res.setResultData(result);
        return res;
    }

    @PostMapping(value = "/downloadfile/{id}/{downType}")
    public void downloadFile(@PathVariable("id") Long id, @PathVariable("downType") String downType, HttpServletResponse resp) throws IOException {
        logger.info("EnterpriseInfoCtrl|downloadFile|合作方信息基础服务|下载文件|传入参数，查询id：{},下载类型：{}，", id, downType);
        UriCheckResultVo result = uriCheckResultService.queryCheckResultById(id);
        String filePath = result.getResultImagePath();
        String fileName = "检测结果";
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/octet-stream");
        String downloadFileName = URLEncoder.encode(fileName, "utf-8") + "." + FilenameUtils.getExtension(filePath);
        // 以文件形式下载
        resp.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
        outPutFile(resp, filePath);
    }

    //输出文件
    private void outPutFile(HttpServletResponse resp, String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);//获取文件输入流
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream out = resp.getOutputStream();
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);//将缓冲区的数据输出到客户端浏览器
        }
        in.close();
        out.close();
    }
}
