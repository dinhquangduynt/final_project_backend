package com.dinhquangduy.electronic.services;

import org.springframework.web.multipart.MultipartFile;

import com.dinhquangduy.electronic.bean.ResultBean;

/**
 * The Interface ProductCategoryService.
 */
public interface ProductCategoryService {

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public ResultBean getAll() throws Exception;

    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     * @throws Exception the exception
     */
    public ResultBean getById(Integer id) throws Exception;

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the boolean
     * @throws Exception the exception
     */
    public ResultBean deleteById(Integer id) throws Exception;

    /**
     * Adds the product.
     *
     * @param json the json
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addProductCategory(String json, MultipartFile[] files) throws Exception;

    /**
     * Update product.
     *
     * @param json the json
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateProductCategory(String json,  MultipartFile[] files) throws Exception;
}
