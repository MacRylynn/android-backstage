package com.android.service;


import com.alibaba.fastjson.JSON;
import com.android.domain.base.CommonRequest;
import com.android.domain.request.UriAccountInfoReq;
import com.android.domain.request.UriDownLoadReq;
import okhttp3.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


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
        String url = "http://127.0.0.1:8080/web/check/downloadfile";
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        CommonRequest<UriDownLoadReq> commonRequest = new CommonRequest<UriDownLoadReq>();
        UriDownLoadReq uriDownLoadReq = new UriDownLoadReq();
        uriDownLoadReq.setId(15L);
        commonRequest.setRequestData(uriDownLoadReq);
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, JSON.toJSONString(commonRequest)))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                writeFile(response);//保存文件
                System.out.println(response.protocol() + " " + response.code() + " " + response.message());
            }
        });
    }

    //保存文件
    private static void writeFile(Response response) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        String fileName = "result.png";
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = "C:/Users/lijiao73/Desktop/aim";
        //储存下载文件的目录
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        try {
            is = response.body().byteStream();
            fos = new FileOutputStream(file);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
            }
            fos.flush();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {

            }

        }

    }

}