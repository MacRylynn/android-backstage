package com.android.service;


import com.alibaba.fastjson.JSON;
import com.android.domain.base.CommonRequest;
import com.android.domain.request.UriAccountInfoReq;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;


public class UriUserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UriUserServiceTest.class);

    @Test
    public void testOkHttp() {
        String url = "http://127.0.0.1:8080/web/user/addaccount";
        MediaType mediaType = MediaType.parse("application/json");
        UriAccountInfoReq uriAccountInfoReq = new UriAccountInfoReq();
        uriAccountInfoReq.setAccountName("Rylynn");
        uriAccountInfoReq.setAccountNo("admin");
        uriAccountInfoReq.setAccountPassword("admin");
        uriAccountInfoReq.setRepeatAccountPassword("admin");
        CommonRequest<UriAccountInfoReq> commonRequest = new CommonRequest<>();
        commonRequest.setRequestData(uriAccountInfoReq);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType, JSON.toJSONString(commonRequest)))
                .build();
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

}