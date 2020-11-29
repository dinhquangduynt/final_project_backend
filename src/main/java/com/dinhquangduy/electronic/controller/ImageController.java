package com.dinhquangduy.electronic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.services.ImageStorageService;
import com.dinhquangduy.electronic.utils.Constants;

@Controller
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageStorageService imageStorageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> uploadImage(@RequestParam("files") MultipartFile[] files, @RequestParam("json")String filexx) throws IOException {
        List<String> imagesName = new ArrayList<>();
        ResultBean resultBean = null;
        try {
            for (MultipartFile file : files) {
                String fileName = imageStorageService.save(file);
                imagesName.add(fileName);
            }
            resultBean = new ResultBean(imagesName, Constants.STATUS_OK, Constants.MSG_OK);
            System.out.println(String.join(",", imagesName));
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.MSG_BAD_REQUEST, e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}
