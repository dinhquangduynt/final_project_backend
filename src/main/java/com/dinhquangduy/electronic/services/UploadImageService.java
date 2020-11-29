package com.dinhquangduy.electronic.services;

import org.springframework.web.multipart.MultipartFile;

import com.dinhquangduy.electronic.bean.ResultBean;

public interface UploadImageService {
    
    public ResultBean uploadImage(MultipartFile file) throws Exception;

}
