package com.dinhquangduy.electronic.services;

import com.dinhquangduy.electronic.bean.ResultBean;

public interface ContactService {
    public ResultBean getAll() throws Exception;

    /**
     * Gets the by order id.
     *
     * @param id the id
     * @return the by order id
     * @throws Exception the exception
     */
    public ResultBean getById(Integer id) throws Exception;

    /**
     * Adds the.
     *
     * @param json the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addContact(String json) throws Exception;

    /**
     * Update.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
//    public ResultBean updateContact(ContactEntity entity) throws Exception;

    /**
     * Delete.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean deleteById(Integer id) throws Exception;
}
