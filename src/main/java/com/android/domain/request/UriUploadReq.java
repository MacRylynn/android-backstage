package com.android.domain.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class UriUploadReq implements Serializable {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private MultipartFile file;
    private String userId;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UriUploadReq{" +
                "file=" + file +
                ", userId='" + userId + '\'' +
                '}';
    }
}
