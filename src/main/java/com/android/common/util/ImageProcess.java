package com.android.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @ClassName ImageProcess
 * @Description 算法处理工具
 * @Author: lijiao73
 * @Date: 2019/11/13 17:11
 */
public class ImageProcess implements Serializable {

    // 调用算法处理
    public static String imageProcess(MultipartFile file) {
        return "test result";
    }
}
