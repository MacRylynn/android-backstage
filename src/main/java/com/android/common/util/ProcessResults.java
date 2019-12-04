package com.android.common.util;


import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class ProcessResults {

    static int[] getlevel(Mat src) {
        if (src.empty()) {
            System.out.println("没检测到图片");
        }
        // 确定好了每一个色块的坐标值 其中A[0]为横坐标，A[1]为纵坐标
        int[] A = calibration(src);
        // 对每一个色块进行变换,并进行均值迁移
        List<Mat> cuts = new ArrayList<>();
        List<Mat> pics = new ArrayList<>();
        int[] restlts = new int[12];
        for (int i = 0; i < 12; i++) {
            Mat cut = filiter(cutPicture(src, A[0], A[i + 1]));
            Mat pic = filiter(colorCorrection(cut, i + 1));
            int[] comp = colorCheck(pic);
            restlts[i] = colorDiscrimination(comp, i + 1) + 1;
            cuts.add(cut);
            pics.add(pic);
        }
        for (int i = 0; i < cuts.size(); i++) {
            Imgcodecs.imwrite("C:\\Users\\Administrator\\Desktop\\results\\cut\\cut_" + i + ".jpg", cuts.get(i));
        }
        for (int i = 0; i < pics.size(); i++) {
            Imgcodecs.imwrite("C:\\Users\\Administrator\\Desktop\\results\\filter\\pic_" + i + ".jpg", pics.get(i));
        }
        return restlts;
    }

    /**
     * 确定每一个色块的坐标
     *
     * @param src
     * @return
     */
    private static int[] calibration(Mat src) {
        // 读取样本图片
        if (src.cols() > src.rows()) {
            System.out.println("图片格式不符");
            Core.transpose(src, src);
            Core.flip(src, src, 1);
        }
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        Mat result = src.clone();
        int cx = 0, cy = 0;
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint2f approxCurve = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), approxCurve, 50, true);
            double area = Imgproc.contourArea(contours.get(i), true);
            if (approxCurve.rows() == 3 && Math.abs(area) > 1000) {
                Imgproc.drawContours(result, contours, i, new Scalar(0, 255, 0), 4);
                Moments mm = Imgproc.moments(contours.get(i));
                cx = (int) (mm.m10 / mm.m00);
                cy = (int) (mm.m01 / mm.m00);
                Imgproc.circle(result, new Point(cx, cy), 10, new Scalar(100, 100, 100), -1);
            }
        }
        int[] A = new int[13];
        // 求每一个色块中间坐标 14个色块，其中A中15个元素，第一个为横坐标，其余为从下往上纵坐标。
        // 固定好手机位置，确定好定标物，则系数可以确定不变。
        A[0] = cx - (int) (0.005 * result.rows());
        A[1] = cy + (int) (0.080 * result.rows());
        A[2] = cy + (int) (0.130 * result.rows());
        A[3] = cy + (int) (0.185 * result.rows());
        A[4] = cy + (int) (0.240 * result.rows());
        A[5] = cy + (int) (0.293 * result.rows());
        A[6] = cy + (int) (0.340 * result.rows());
        A[7] = cy + (int) (0.395 * result.rows());
        A[8] = cy + (int) (0.445 * result.rows());
        A[9] = cy + (int) (0.500 * result.rows());
        A[10] = cy + (int) (0.555 * result.rows());
        A[11] = cy + (int) (0.610 * result.rows());
        A[12] = cy + (int) (0.665 * result.rows());
        for (int i=0;i<12;i++){
            Imgproc.circle(result, new Point(A[0], A[i+1]), 10, new Scalar(255, 0, 0), -1);
        }
        Imgcodecs.imwrite("C:\\Users\\Administrator\\Desktop\\results\\gesture\\position.jpg", result);
        return A;
    }

    /**
     * 循环输入坐标值，判断每个区域的HSI和RGB值
     * @param src
     * @return
     */
    private static int[] colorCheck(Mat src) {
        int width = src.cols();
        int height = src.rows();
        int channels = src.channels();
        byte[] data = new byte[channels * width * height];
        src.get(0, 0, data);
        int sum_r = 0;
        int sum_g = 0;
        int sum_b = 0;
        int r = 0, g = 0, b = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                b = data[row * channels * width + col * channels] & 0xff;
                g = data[row * channels * width + col * channels + 1] & 0xff;
                r = data[row * channels * width + col * channels + 2] & 0xff;
                sum_r = r + sum_r;
                sum_g = g + sum_g;
                sum_b = b + sum_b;
            }
        }
        int[] comp = new int[3];
        comp[0] = (int) (sum_r / (height * width));
        comp[1] = (int) (sum_g / (height * width));
        comp[2] = (int) (sum_b / (height * width));
//		Mat dst = new Mat();
//		Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGR2HLS);
//		double sum_h = 0;
//		double sum_l = 0;
//		double sum_s = 0;
//		int num1 = dst.rows();
//		int num2 = dst.cols();
//		for (int i = 0; i < num1; i++) {
//			for (int j = 0; j < num2; j++) {
//				double[] clone = dst.get(i, j).clone();
//				sum_h = clone[0] + sum_h;
//				sum_l = clone[1] + sum_l;
//				sum_s = clone[2] + sum_s;
//			}
//		}
//		comp[3] = (int) (sum_h / (num1 * num2));
//		comp[4] = (int) (sum_l / (num1 * num2));
//		comp[5] = (int) (sum_s / (num1 * num2));
        return comp;
    }

    /**
     * 截取图像中的ROI区域,截取出来为一个矩形
     * @param src
     * @param x
     * @param y
     * @return
     */
    private static Mat cutPicture(Mat src, int x, int y) {
        Rect rect = new Rect(x, y, 40, 40);
        Mat roi = new Mat(src, rect);
        return roi;
    }

    /**
     * 均值迁移
     *
     * @param src
     * @return
     */
    private static Mat filiter(Mat src) {
        Mat mat = new Mat();
        Imgproc.pyrMeanShiftFiltering(src, mat, 15, 10);
        return mat;
    }

    /**
     * 颜色教程函数
     *
     * @param cut
     * @param ii
     * @return
     */
    private static Mat colorCorrection(Mat cut, int ii) {
        Mat src = new Mat();
        cut.copyTo(src);
        int width = src.cols();
        int height = src.rows();
        double[][] A1 = {{2.2924, -2.0181, 0.6570, 0.0038, -0.0051, 0.0049, -0.0028, 0.0045, -0.0039, 1.6564},
                {0.4621, 2.5396, -0.8730, -0.0174, 0.0004, -0.0004, -0.0008, 0.0137, 0.0020, -27.8654},
                {0.7287, -5.0087, 6.4491, -0.0117, 0.0002, -0.0041, -0.0041, 0.0427, -0.0286, 5.4865}};
        double[][] A2 = {{1.7291, -1.4445, 0.7106, 0.0084, -0.0014, 0.0021, -0.0021, -0.0020, -0.0028, -34.2988},
                {0.6069, 1.6210, 0.4027, -0.0080, 0.0042, 0.0004, -0.0031, 0.0090, -0.0065, -97.5903},
                {1.2584, -4.4486, 5.8037, -0.0006, -0.0020, -0.0056, -0.0072, 0.0230, -0.0162, -66.9801}};
        Matrix A_1 = new Matrix(A1);
        Matrix A_2 = new Matrix(A2);
        double r, g, b = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double[] clone = src.get(row, col).clone();
                r = clone[2];
                g = clone[1];
                b = clone[0];
                double[][] V = {{r}, {g}, {b}, {r * g}, {r * b}, {g * b}, {r * r}, {g * g}, {b * b},
                        {1}};
                Matrix V2 = new Matrix(V);
                Matrix X_changed = new Matrix(3, 1);
                if (ii > 5 && ii < 10) {
                    X_changed = A_2.times(V2);
                } else {
                    X_changed = A_1.times(V2);
                }
                double[] X2 = new double[3];
                X2[0] = X_changed.get(2, 0);
                X2[1] = X_changed.get(1, 0);
                X2[2] = X_changed.get(0, 0);
//				for (int k = 0; k < X2.length; k++) {
//					X2[k] = X_changed.get(k, 0);
//				}
                src.put(row, col, X2);
            }
        }
        return src;
    }

    /**
     * 颜色判别函数
     *
     * @param comp 输入的RGB值
     * @param i    第几个色块
     * @return
     */
    private static int colorDiscrimination(int[] comp, int i) {
        int index = 0;
        switch (i) {
            case 1:
                int[][] A1 = {{227, 223, 255}, {221, 80, 33}, {214, 81, 22}, {222, 45, 0}, {232, 29, 0}};
                int[] B1 = new int[5];
                for (int j = 0; j < A1.length; j++) {
                    B1[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A1[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A1[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A1[j][2]), (double) (2)));
                }
                int min1 = B1[0];
                for (int j = 0; j < 5; j++) {
                    if (B1[j] <= min1) {
                        min1 = B1[j];
                        index = j;
                    }
                }
                break;
            case 2:
                int[][] A2 = {{229, 225, 255}, {220, 152, 92}, {216, 84, 1}, {211, 66, 1}};
                int[] B2 = new int[4];
                for (int j = 0; j < A2.length; j++) {
                    B2[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A2[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A2[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A2[j][2]), (double) (2)));
                }
                int min2 = B2[0];
                for (int j = 0; j < 4; j++) {
                    if (B2[j] <= min2) {
                        min2 = B2[j];
                        index = j;
                    }
                }
                break;
            case 3:
                int[][] A3 = {{232, 145, 134}, {222, 86, 65}, {218, 30, 28}, {196, 21, 80}, {162, 4, 144}, {106, 3, 105}};
                int[] B3 = new int[6];
                for (int j = 0; j < A3.length; j++) {
                    B3[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A3[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A3[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A3[j][2]), (double) (2)));
                }
                int min3 = B3[0];
                for (int j = 0; j < 6; j++) {
                    if (B3[j] <= min3) {
                        min3 = B3[j];
                        index = j;
                    }
                }
                break;
            case 4:
                int[][] A4 = {{228, 226, 255}, {226, 207, 241}, {220, 183, 204}, {214, 177, 190}, {206, 140, 140}};
                int[] B4 = new int[5];
                for (int j = 0; j < A4.length; j++) {
                    B4[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A4[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A4[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A4[j][2]), (double) (2)));
                }
                int min4 = B4[0];
                for (int j = 0; j < 5; j++) {
                    if (B4[j] <= min4) {
                        min4 = B4[j];
                        index = j;
                    }
                }
                break;
            case 5:
                int[][] A5 = {{255, 149, 0}, {255, 148, 1}, {231, 167, 1}, {119, 156, 0}, {25, 116, 0}};
                int[] B5 = new int[5];
                for (int j = 0; j < A5.length; j++) {
                    B5[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A5[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A5[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A5[j][2]), (double) (2)));
                }
                int min5 = B5[0];
                for (int j = 0; j < 5; j++) {
                    if (B5[j] <= min5) {
                        min5 = B5[j];
                        index = j;
                    }
                }
                break;
            case 6:
                int[][] A6 = {{255, 255, 185}, {156, 255, 86}, {114, 255, 101}, {98, 254, 189}, {3, 242, 198}, {4, 140, 152}};
                int[] B6 = new int[6];
                for (int j = 0; j < A6.length; j++) {
                    B6[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A6[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A6[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A6[j][2]), (double) (2)));
                }
                int min6 = B6[0];
                for (int j = 0; j < 6; j++) {
                    if (B6[j] <= min6) {
                        min6 = B6[j];
                        index = j;
                    }
                }
                break;
            case 7:
                int[][] A7 = {{207, 249, 248}, {182, 248, 244}, {152, 245, 247}, {98, 220, 231}};
                int[] B7 = new int[4];
                for (int j = 0; j < A7.length; j++) {
                    B7[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A7[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A7[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A7[j][2]), (double) (2)));
                }
                int min7 = B7[0];
                for (int j = 0; j < 4; j++) {
                    if (B7[j] <= min7) {
                        min7 = B7[j];
                        index = j;
                    }
                }
                break;
            case 8:
                int[][] A8 = {{243, 254, 235}, {251, 228, 218}, {252, 152, 149}};
                int[] B8 = new int[3];
                for (int j = 0; j < A8.length; j++) {
                    B8[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A8[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A8[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A8[j][2]), (double) (2)));
                }
                int min8 = B8[0];
                for (int j = 0; j < 3; j++) {
                    if (B8[j] <= min8) {
                        min8 = B8[j];
                        index = j;
                    }
                }
                break;
            case 9:
                int[][] A9 = {{241, 255, 222}, {239, 244, 206}, {239, 182, 163}, {174, 120, 162}, {98, 31, 140}};
                int[] B9 = new int[5];
                for (int j = 0; j < A9.length; j++) {
                    B9[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A9[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A9[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A9[j][2]), (double) (2)));
                }
                int min9 = B9[0];
                for (int j = 0; j < 5; j++) {
                    if (B9[j] <= min9) {
                        min9 = B9[j];
                        index = j;
                    }
                }
                break;
            case 10:
                int[][] A10 = {{243, 243, 211}, {195, 249, 141}, {124, 236, 80}, {90, 230, 13}, {1, 198, 42}};
                int[] B10 = new int[5];
                for (int j = 0; j < A10.length; j++) {
                    B10[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A10[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A10[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A10[j][2]), (double) (2)));
                }
                int min10 = B10[0];
                for (int j = 0; j < 5; j++) {
                    if (B10[j] <= min10) {
                        min10 = B10[j];
                        index = j;
                    }
                }
                break;
            case 11:
                int[][] A11 = {{0, 31, 66}, {50, 123, 0}, {74, 152, 7}, {94, 117, 1}, {145, 125, 1}, {202, 143, 1}, {219, 182, 0}};
                int[] B11 = new int[7];
                for (int j = 0; j < A11.length; j++) {
                    B11[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A11[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A11[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A11[j][2]), (double) (2)));
                }
                int min11 = B11[0];
                for (int j = 0; j < 7; j++) {
                    if (B11[j] <= min11) {
                        min11 = B11[j];
                        index = j;
                    }
                }
                break;
            case 12:
                int[][] A12 = {{255, 118, 1}, {255, 134, 1}, {255, 182, 1}, {229, 217, 1}, {176, 223, 1}, {127, 224, 3}, {19, 171, 125}};
                int[] B12 = new int[7];
                for (int j = 0; j < A12.length; j++) {
                    B12[j] = (int) Math.sqrt(Math.pow((double) (comp[0] - A12[j][0]), (double) (2)) +
                            Math.pow((double) (comp[1] - A12[j][1]), (double) (2)) + Math.pow((double) (comp[2] - A12[j][2]), (double) (2)));
                }
                int min12 = B12[0];
                for (int j = 0; j < 7; j++) {
                    if (B12[j] <= min12) {
                        min12 = B12[j];
                        index = j;
                    }
                }
                break;
        }
        return index;
    }
}
