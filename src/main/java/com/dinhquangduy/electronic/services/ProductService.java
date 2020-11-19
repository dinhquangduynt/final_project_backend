package com.dinhquangduy.electronic.services;

import com.dinhquangduy.electronic.bean.ResultBean;

/**
 * The Interface ProductService.
 */
public interface ProductService {

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public ResultBean getAll() throws Exception;

    /**
     * Gets the products by cate id.
     *
     * @param CateId the cate id
     * @return the products by cate id
     * @throws Exception the exception
     */
    public ResultBean getProductsByCateId(Integer CateId) throws Exception;

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
    public ResultBean addProduct(String json) throws Exception;

    /**
     * Update product.
     *
     * @param json the json
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateProduct(String json) throws Exception;
}
