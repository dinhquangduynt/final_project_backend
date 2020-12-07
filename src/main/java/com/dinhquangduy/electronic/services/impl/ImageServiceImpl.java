package com.dinhquangduy.electronic.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.ImageEntity;
import com.dinhquangduy.electronic.dao.ImageDao;
import com.dinhquangduy.electronic.services.ImageService;
import com.dinhquangduy.electronic.utils.Constants;
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageDao imageDao;

    @Override
    public ResultBean getListImageByTypeAndParentId(String type, Integer parentId) throws Exception {
        List<ImageEntity> images = imageDao.getListImageByTypeAndParentId(type, parentId);
        return new ResultBean(images, Constants.STATUS_OK, Constants.MSG_OK);
    }


}
