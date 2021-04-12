package com.atguigu.guli.service.oss.service;

import java.io.InputStream;


public interface FileService {
    /**
     * 阿里云oss文件上传
     * @param inputStream 输入流
     * @param module 文件名称
     * @param originalFilename 文件名
     * @return 文件在oss的url
     */
    String upload(InputStream inputStream,String module,String originalFilename);
}
