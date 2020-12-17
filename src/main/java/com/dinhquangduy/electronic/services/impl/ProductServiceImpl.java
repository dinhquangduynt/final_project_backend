package com.dinhquangduy.electronic.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.FeedbackEntity;
import com.dinhquangduy.electronic.bean.entity.ImageEntity;
import com.dinhquangduy.electronic.bean.entity.ProductEntity;
import com.dinhquangduy.electronic.bean.entity.UserEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.dao.FeedbackDao;
import com.dinhquangduy.electronic.dao.ImageDao;
import com.dinhquangduy.electronic.dao.ProductDao;
import com.dinhquangduy.electronic.dao.UserDao;
import com.dinhquangduy.electronic.services.ImageStorageService;
import com.dinhquangduy.electronic.services.ProductService;
import com.dinhquangduy.electronic.utils.Constants;
import com.dinhquangduy.electronic.utils.DataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

/**
 * The Class ProductServiceImpl.
 */
@Service
@LogExecutionTime
@Transactional
public class ProductServiceImpl implements ProductService {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    /** The model mapper. */
    private ModelMapper modelMapper = new ModelMapper();

    /** The mapper. */
    private ObjectMapper mapper = new ObjectMapper();

    /** The product dao. */
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private FeedbackDao feedbackDao;

    /** The image storage service. */
    @Autowired
    private ImageStorageService imageStorageService;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    @Override
    public ResultBean getAll() throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Products ###");
        Map<String, List<ProductEntity>> result = new HashedMap<String, List<ProductEntity>>();
        List<ProductEntity> products = productDao.findAll();
        List<ProductEntity> productRecommend = getProductsRecommend();
        List<ProductEntity> hotProducts = productDao.getHotProduct();
        List<ProductEntity> newProducts = productDao.getNewProduct();
        
        result.put("products", products);
        result.put("productsRecommend", productRecommend);
        result.put("hotProducts", hotProducts);
        result.put("newProducts", newProducts);
        log.info("### End Get List Products ###");
        return new ResultBean(result, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the products by cate id.
     *
     * @param cateId the cate id
     * @return the products by cate id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getProductsByCateId(Integer cateId) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Products By Cate Id ###");
        Map<String, List<ProductEntity>> results = new HashedMap<String, List<ProductEntity>>();
        List<ProductEntity> products = productDao.findByCategoryId(cateId);
        List<ProductEntity> productsRecomend = getProductsRecommend();

        results.put("products", products);
        results.put("productsRecommend", productsRecomend);
        log.info("### End Get List Products By Cate Id ###");
        log.info("##########################################");
        return new ResultBean(results, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getById(Integer id) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Product By Id ###");
        Authentication authe = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> results = new HashedMap<String, Object>();
        ProductEntity product = productDao.findById(id).get();
        if (!authe.getAuthorities().stream().filter(res -> "ROLE_ADMIN".equals(res)).findFirst().isPresent()) {
            product.setViewCount(product.getViewCount() + 1);
        }
        
        List<ProductEntity> productsRecomend = getProductsRecommend();
        List<FeedbackEntity> feedbacks = feedbackDao.findByProductId(id);
        
        Integer countRated = productDao.countProductRated(id);

        results.put("product", product);
        results.put("productsRecommend", productsRecomend);
        results.put("countRated", countRated);
        results.put("feedbacks", feedbacks);
        productDao.save(product);
        log.info("### End Get List Product By Id ###");
        log.info("##########################################");
        return new ResultBean(results, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the boolean
     * @throws Exception the exception
     */
    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Delete Product By Id ###");
        productDao.deleteById(id);
        log.info("### End Delete Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Adds the product.
     *
     * @param json the json
     * @param files the files
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean addProduct(String json, MultipartFile[] files) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Add Product By Id ###");
        Set<ImageEntity> images = new HashSet<ImageEntity>();
        ProductEntity productEntity = updateEntity(json);
        Integer maxId = productDao.getMaxId();
        maxId = !Objects.isNull(maxId) ? maxId : 0;
        try {
            for (MultipartFile file : files) {
                String fileName = imageStorageService.save(file);
                ImageEntity image = new ImageEntity();
                image.setFileName(fileName);
                image.setParentId(maxId + 1);
                image.setType(Constants.TYPE_PRODUCT);
                imageDao.save(image);
            }
        } catch (Exception e) {
            throw new IOException("Save file fail!");
        }

        productEntity.setImages(images);
        productDao.save(productEntity);
        log.info("### End Add Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_201, Constants.MSG_OK);
    }

    /**
     * Update product.
     *
     * @param json the json
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean updateProduct(String json, MultipartFile[] files) throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Update Product By Id ###");
        JsonObject jsonObj = DataUtil.getJsonObject(json);
        Integer id = jsonObj.get("id").getAsInt();
        Optional<ProductEntity> productOp = productDao.findById(id);
        if (!productOp.isPresent()) {
            throw new Exception("Product Id " + id + " does not exist!");
        }
        ProductEntity productDb = productOp.get();
        ProductEntity productEntity = updateEntity(json);
        if (Objects.isNull(files)) {
            productEntity.setImages(productDb.getImages());
        }
        imageDao.deleteImageByTypeAndParentId(Constants.TYPE_PRODUCT, id);
        try {
            for (MultipartFile file : files) {
                String fileName = imageStorageService.save(file);
                ImageEntity image = new ImageEntity();
                image.setFileName(fileName);
                image.setParentId(id);
                image.setType(Constants.TYPE_PRODUCT);
                imageDao.save(image);
            }
        } catch (Exception e) {
            throw new IOException("Save file fail!");
        }

        productDao.save(productEntity);
        log.info("### End Update Product By Id ###");
        log.info("##########################################");
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Update entity.
     *
     * @param json the json
     * @return the product entity
     * @throws Exception the exception
     */
    private ProductEntity updateEntity(String json) throws Exception {
        return mapper.readValue(json, ProductEntity.class);
    }

    private List<ProductEntity> getProductsRecommend(){
        Authentication authe = SecurityContextHolder.getContext().getAuthentication(); 
        if((!"anonymousUser".equals(authe.getPrincipal()))) {
            User prin = (User) authe.getPrincipal();
            UserEntity user = userDao.findByUserName(prin.getUsername()).get();
            String result =  restTemplate.getForObject(Constants.URL_FILTER + user.getId(), String.class);
            String[] listId = result.replace("{", "").replace("}", "").replaceAll("\"", "").split(",");
            List<Integer> idRe = new ArrayList<Integer>();
            
           List<String> sorted = Arrays.asList(listId).stream().sorted((i1,i2) -> 
            i1.substring(i2.indexOf(":")+ 1 , i2.indexOf(":")+2).compareTo(i1.substring(i1.indexOf(":")+1 , i1.indexOf(":")+2)))
                    .collect(Collectors.toList());
                    
            for (String id : sorted) {
                int index = id.indexOf(":");
                idRe.add(Integer.parseInt(id.substring(0 , index)));
            }
            return productDao.getProductsRecommed(idRe);
        }
        return productDao.getProductRecomendWithouUser();
    }
}
