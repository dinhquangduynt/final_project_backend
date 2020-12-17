package com.dinhquangduy.electronic.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.ProductCategoryEntity;
import com.dinhquangduy.electronic.dao.ProductCategoryDao;
import com.dinhquangduy.electronic.dao.ProductDao;
import com.dinhquangduy.electronic.services.ImageStorageService;
import com.dinhquangduy.electronic.services.ProductCategoryService;
import com.dinhquangduy.electronic.utils.Constants;
import com.dinhquangduy.electronic.utils.DataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    /** The mapper. */
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductCategoryDao productCateDao;
    
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ImageStorageService imageStorageService;

    @Override
    public ResultBean getAll() throws Exception {
        List<ProductCategoryEntity> productCategoryEntities = productCateDao.findAll();
        return new ResultBean(productCategoryEntities, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getById(Integer id) throws Exception {
        ProductCategoryEntity productCate = productCateDao.findById(id).get();
        return new ResultBean(productCate, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        productDao.deleteAllByIdCate(id);
        productCateDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean addProductCategory(String json, MultipartFile[] files) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Add Product cate###");
        ProductCategoryEntity productCate = updateEntity(json);
        List<String> filesName = new ArrayList<String>();
        try {
            for (MultipartFile file : files) {
                String fileName = imageStorageService.save(file);
                filesName.add(fileName);
            }
        } catch (Exception e) {
            throw new IOException("Save file fail!" );
        }
        productCate.setImages(String.join(",", filesName));
        productCateDao.save(productCate);
        log.info("### End Add Product Cate ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateProductCategory(String json, MultipartFile[] files) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Update Product Cate ###");
        JsonObject jsonObj = DataUtil.getJsonObject(json);
        Integer id = jsonObj.get("id").getAsInt();
        if (!productCateDao.existsById(id)) {
            throw new Exception("Product Category ID " + id + " does not exits");
        }
        ProductCategoryEntity productCate = updateEntity(json);
        productCateDao.save(productCate);
        log.info("### End Update Product Cate ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    private ProductCategoryEntity updateEntity(String json) throws Exception {
        return mapper.readValue(json, ProductCategoryEntity.class);
    }
}
