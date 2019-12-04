package com.android.common.util;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @ClassName ImageProcess
 * @Description 算法处理工具
 * @Author: lijiao73
 * @Date: 2019/11/13 17:11
 */
public class ImageProcess implements Serializable {

//    public static void main(String[] args) {
//        //加载 opencv
//        System.load("H:\\软件压缩包\\OpenCV for Java\\OpenCV_3_4_7\\build\\java\\x64\\opencv_java347.dll");
//        Mat src = Imgcodecs.imread("C:\\Users\\Administrator\\Desktop\\results\\4.jpg");
//        int[] result = ProcessResults.getlevel(src);
//        for (int a :result){
//            System.out.print(a+" ");
//        }
//    }

    // todo 调用算法处理
    public static String imageProcess(MultipartFile file) {
        return "[1,3,2,4,2,3,4,5,6,2,1,3]";
    }
}
