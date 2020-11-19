package com.dinhquangduy.electronic.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.ProductCategory;
import com.dinhquangduy.electronic.dao.ProductCategoryDao;
import com.dinhquangduy.electronic.services.ProductCategoryService;
import com.dinhquangduy.electronic.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
    
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Autowired
    private ProductCategoryDao productCateDao;

    @Override
    public ResultBean getAll() throws Exception {
        List<ProductCategory> productCategories = productCateDao.findAll();
        return new ResultBean(productCategories, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getById(Integer id) throws Exception {
        ProductCategory productCate = productCateDao.findById(id).get();
        return new ResultBean(productCate, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        productCateDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean addProductCategory(String json) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Delete Product By Id ###");
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        ProductCategory productCate = new ProductCategory();
        setProductEntity(jsonObject, productCate);
        productCateDao.save(productCate);
        log.info("### End Delete Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateProductCategory(String json) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Delete Product By Id ###");
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        ProductCategory productCate = new ProductCategory();
        setProductEntity(jsonObject, productCate);
        if(!productCateDao.existsById(productCate.getId())) {
            throw new Exception("Product Category ID " + productCate.getId() + " does not exits" );
        }
        productCateDao.save(productCate);
        log.info("### End Delete Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }
    
    private void setProductEntity(JsonObject json, ProductCategory entity) throws Exception {
        if(json.has("name")) {
            entity.setName(json.get("name").getAsString());
        }
        
        if(json.has("alias")) {
            entity.setAlias(json.get("alias").getAsString());
        }
    }

}
