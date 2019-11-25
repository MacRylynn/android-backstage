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

    // todo 调用算法处理
    public static String imageProcess(MultipartFile file) {
        return "[1,3,2,4,2,3,4,5,6,2,1,3]";
    }
}
