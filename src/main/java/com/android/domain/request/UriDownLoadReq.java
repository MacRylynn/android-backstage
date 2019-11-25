package com.android.domain.request;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @ClassName UriDownLoadReq
 * @Description
 * @Author: lijiao73
 * @Date: 2019/11/25 11:34
 */
public class UriDownLoadReq implements Serializable {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UriDownLoadReq{" +
                "id=" + id +
                '}';
    }
}
