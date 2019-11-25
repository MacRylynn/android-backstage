package com.android.service;


import com.alibaba.fastjson.JSON;
import com.android.domain.base.CommonRequest;
import com.android.domain.request.UriAccountInfoReq;
import okhttp3.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;


public class OkhttpTest {

    private static final Logger logger = LoggerFactory.getLogger(OkhttpTest.class);

    //first of all 不要忘记引入okhttp的包
    //      <!--okhttp3-->
    //        <dependency>
    //            <groupId>com.squareup.okhttp3</groupId>
    //            <artifactId>okhttp</artifactId>
    //            <version>3.3.0</version>
    //        </dependency>

    //1. 构建普通的json对象的请求
    @Test
    public void PostInOkHttp() {
        //post的url
        String url = "http://127.0.0.1:8080/web/user/addaccount";
        //需要post的数据类型，这里是json的数据
        MediaType mediaType = MediaType.parse("application/json");
        //构造需要post的数据
        UriAccountInfoReq uriAccountInfoReq = new UriAccountInfoReq();
        uriAccountInfoReq.setAccountName("Rylynn");
        uriAccountInfoReq.setAccountNo("admin");
        uriAccountInfoReq.setAccountPassword("admin");
        uriAccountInfoReq.setRepeatAccountPassword("admin");
        CommonRequest<UriAccountInfoReq> commonRequest = new CommonRequest<>();
        commonRequest.setRequestData(uriAccountInfoReq);
        //构建request
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, JSON.toJSONString(commonRequest)))
                .build();
        //构建http请求，并发送请求
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("onFailure: {}", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                logger.error(response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    logger.error(headers.name(i) + ":" + headers.value(i));
                }
                logger.error("onResponse: " + response.body().string());
            }
        });
    }

    //2. 上传文件
    @Test
    public void UploadInOkHttp() {
        String url = "http://127.0.0.1:8080/web/check/addcheckresult";
        OkHttpClient client = new OkHttpClient();
        String filePath = "C:/Users/lijiao73/Desktop/test.jpg";
        File file = new File(filePath);
        //构建多种参数的请求体
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", "13")
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(multipartBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("onFailure: {}", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                logger.error(response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    logger.error(headers.name(i) + ":" + headers.value(i));
                }
                logger.error("onResponse: " + response.body().string());

            }
        });


    }

    //3. 下载文件
    @Test
    public void DownloadInOkHttp() {

    }
//    //保存文件
//    private void writeFile(Response response) {
//        InputStream is = null;
//        FileOutputStream fos = null;
//        is = response.body().byteStream();
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//        File file = new File(path, fileName);
//        try {
//            fos = new FileOutputStream(file);
//            byte[] bytes = new byte[1024];
//            int len = 0;
//            //获取下载的文件的大小
//            long fileSize = response.body().contentLength();
//            long sum = 0;
//            int porSize = 0;
//            while ((len = is.read(bytes)) != -1) {
//                fos.write(bytes);
//                sum += len;
//                porSize = (int) ((sum * 1.0f / fileSize) * 100);
//                Message message = handler.obtainMessage(1);
//                message.arg1 = porSize;
//                handler.sendMessage(message);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//                if (fos != null) {
//                    fos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Log.i("myTag", "下载成功");
//    }
}