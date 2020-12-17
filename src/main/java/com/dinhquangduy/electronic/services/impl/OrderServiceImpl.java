package com.dinhquangduy.electronic.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.OrderDetailEntity;
import com.dinhquangduy.electronic.bean.entity.OrderEntity;
import com.dinhquangduy.electronic.bean.entity.UserEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.dao.OrderDao;
import com.dinhquangduy.electronic.dao.OrderDetailDao;
import com.dinhquangduy.electronic.dao.UserDao;
import com.dinhquangduy.electronic.services.OrderService;
import com.dinhquangduy.electronic.utils.Constants;
import com.dinhquangduy.electronic.utils.DataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

/**
 * The Class OrderServiceImpl.
 */
@Service
@LogExecutionTime
public class OrderServiceImpl implements OrderService {

    /** The order dao. */
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderDetailDao orderDetailDao;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private UserDao userDao;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    @Override
    public ResultBean getAll() throws Exception {
        List<OrderEntity> orders = orderDao.findAll();
        return new ResultBean(orders, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the order by id.
     *
     * @param id the id
     * @return the order by id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getOrderById(Integer id) throws Exception {
        Optional<OrderEntity> orderOp = orderDao.findById(id);
        if (!orderOp.isPresent()) {
            throw new Exception("Order Id " + id + " does not exist!");
        }
        OrderEntity order = orderOp.get();
        return new ResultBean(order, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the order by user id.
     *
     * @param userId the user id
     * @return the order by user id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getOrderByUserId(Integer userId) throws Exception {
        List<OrderEntity> orders = orderDao.findByCustomerId(userId);
        return new ResultBean(orders, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Adds the order.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean addOrder(String json) throws Exception {
       OrderEntity order = updateOrderEntity(json);
       Authentication authe = SecurityContextHolder.getContext().getAuthentication(); 
       if((!"anonymousUser".equals(authe.getPrincipal()))) {
           User prin = (User) authe.getPrincipal();
           UserEntity user = userDao.findByUserName(prin.getUsername()).get();
           order.setCustomerId(user.getId());
       }
       order.getOrderDetails().forEach(res -> res.setOrder(order));
       OrderEntity orderSave = orderDao.save(order);
        return new ResultBean(orderSave,Constants.STATUS_201, Constants.MSG_OK);
    }

    /**
     * Update order.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean updateOrder(OrderEntity entity) throws Exception {
        if (!orderDao.existsById(entity.getId())) {
            throw new Exception("Order Id " + entity.getId() + " does not exist!");
        }
        entity.setStatus(true);
        OrderEntity order = orderDao.save(entity);
        return new ResultBean(order, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean deleteById(Integer id) throws Exception {
        if (!orderDao.existsById(id)) {
            throw new Exception("Order Id " + id + " does not exist!");
        }
        List<OrderDetailEntity> orderDetails = orderDetailDao.findByOrderId(id);
        orderDetails.forEach(order -> {
            orderDetailDao.deleteById(order.getId());
        });
        orderDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }
    
    private OrderEntity updateOrderEntity(String json) throws Exception {
        return mapper.readValue(json, OrderEntity.class);
    }
    
    private OrderDetailEntity updateOrderDetailEntity(String json) throws Exception {
        return mapper.readValue(json, OrderDetailEntity.class);
    }

}
