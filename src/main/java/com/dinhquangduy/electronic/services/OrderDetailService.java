package com.dinhquangduy.electronic.services;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.response.OrderDetailResponse;

/**
 * The Interface OrderDetailService.
 */
public interface OrderDetailService {
    
    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public ResultBean getAll() throws Exception;

    /**
     * Gets the by order id.
     *
     * @param id the id
     * @return the by order id
     * @throws Exception the exception
     */
    public ResultBean getByOrderId(Integer id) throws Exception;

    /**
     * Adds the.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addOrderDetail(OrderDetailResponse entity) throws Exception;

    /**
     * Update.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateOrderDetail(OrderDetailResponse entity) throws Exception;
    
    /**
     * Delete.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean deleteOrderDetailById(Integer id) throws Exception;
}
