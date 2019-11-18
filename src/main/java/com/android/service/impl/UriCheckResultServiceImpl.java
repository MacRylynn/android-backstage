package com.android.service.impl;

import com.android.common.entity.UriCheckResultEntity;
import com.android.common.mapper.UriCheckResultMapper;
import com.android.domain.request.UriCheckResultReq;
import com.android.domain.request.UriUserInfoReq;
import com.android.domain.response.UriCheckResultVo;
import com.android.service.UriCheckResultService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName UriCheckResultServiceImpl
 * @Description
 * @Author: lijiao73
 * @Date: 2019/11/13 17:13
 */
@Service
public class UriCheckResultServiceImpl implements UriCheckResultService {

    private static final Logger logger = LoggerFactory.getLogger(UriCheckResultServiceImpl.class);
    @Value("${file.path}")
    private String filePath;

    @Resource
    private UriCheckResultMapper uriCheckResultMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCheckResult(UriCheckResultReq req) {
        logger.info("UriCheckResultServiceImpl|addCheckResult,检测结果管理服务|增加检测结果，参数为：{}", req.toString());
        UriCheckResultEntity uriCheckResultEntity = new UriCheckResultEntity();
        BeanUtils.copyProperties(req, uriCheckResultEntity);
        int res = uriCheckResultMapper.insertSelective(uriCheckResultEntity);
        return res == 1;
    }

    @Override
    public List<UriCheckResultVo> queryCheckResultByUserId(UriUserInfoReq req) {
        logger.info("UriCheckResultServiceImpl|queryCheckResultByUserId,检测结果管理服务|根据userId查询检测结果，参数为：{}", req.toString());
        List<UriCheckResultVo> res = new ArrayList<>();
        List<UriCheckResultEntity> resList = uriCheckResultMapper.selectByUserId(req.getId());
        resList.forEach(entity -> {
            UriCheckResultVo uriCheckResultVo = new UriCheckResultVo();
            BeanUtils.copyProperties(entity, uriCheckResultVo);
            res.add(uriCheckResultVo);
        });
        return res;
    }

    @Override
    public UriCheckResultVo queryCheckResultById(Long id) {
        logger.info("UriCheckResultServiceImpl|queryCheckResultById,检测结果管理服务|根据id获取单条检测记录，id为：{}", id);
        UriCheckResultVo result = new UriCheckResultVo();
        UriCheckResultEntity uriCheckResultEntity = uriCheckResultMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(uriCheckResultEntity, result);
        return result;
    }

    @Override
    public UriCheckResultVo queryStandResult() {
        logger.info("UriCheckResultServiceImpl|queryStandResult,检测结果管理服务|查询标准检测结果");
        UriCheckResultEntity uriCheckResultEntity = uriCheckResultMapper.selectByPrimaryKey(1L);
        UriCheckResultVo uriCheckResultVo = new UriCheckResultVo();
        BeanUtils.copyProperties(uriCheckResultEntity, uriCheckResultVo);
        return uriCheckResultVo;
    }

    @Override
    public String uploadMediaFile(MultipartFile orginfile) throws IOException {
        logger.info("UriCheckResultServiceImpl|uploadMediaFile,检测结果管理服务|文件上传开始");
        if (orginfile == null) {
            return null;
        }
        //获取上传时的文件名称
        String originalFilename = orginfile.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString();
        String suffix = FilenameUtils.getExtension(originalFilename);
        String filename = newFileName + "." + suffix;
        File file = new File(this.getFilePath(), filename);
        //将上传的文件存储到指定位置
        orginfile.transferTo(file);
        return file.getAbsolutePath();
    }

    @Override
    public String getFilePath() {
        logger.info("UriCheckResultServiceImpl|getFilePath,检测结果管理服务|获取文件存储路径");
        return this.filePath;
    }
}
