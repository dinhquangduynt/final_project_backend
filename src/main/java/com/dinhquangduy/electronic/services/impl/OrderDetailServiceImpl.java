package com.dinhquangduy.electronic.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.OrderDetailEntity;
import com.dinhquangduy.electronic.bean.response.OrderDetailResponse;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.dao.OrderDetailDao;
import com.dinhquangduy.electronic.services.OrderDetailService;
import com.dinhquangduy.electronic.utils.Constants;

@Service
@LogExecutionTime
public class OrderDetailServiceImpl implements OrderDetailService{
    
 private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    private ModelMapper modelMapper = new ModelMapper();
    
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public ResultBean getAll() throws Exception {
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### Start Get List Products ###");
        List<OrderDetailEntity> orderDetails = orderDetailDao.findAll();
        log.info("##                                      ##");
        log.info("##########################################");
        log.info("### End Get List Products ###");
        return new ResultBean(orderDetails, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getByOrderId(Integer id) throws Exception {
        Optional<OrderDetailEntity> orderDetailOp = orderDetailDao.findById(id);
        if(!orderDetailOp.isPresent()) {
            throw new Exception("Order id " + id + " does not exist!");
        }
        OrderDetailEntity orderDetailResponse = orderDetailOp.get();
        return new ResultBean(orderDetailResponse, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean addOrderDetail(OrderDetailResponse entity) throws Exception {
        OrderDetailEntity orderDeatilEntity = modelMapper.map(entity, OrderDetailEntity.class);
        OrderDetailEntity orderDetail = orderDetailDao.save(orderDeatilEntity);
        return new ResultBean(orderDetail, Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateOrderDetail(OrderDetailResponse entity) throws Exception {
        if(!orderDetailDao.existsById(entity.getId())) {
            throw new Exception("Order Detail by Id " + entity.getId() + " does not exist!");
        }
        OrderDetailEntity orderDeatilEntity = modelMapper.map(entity, OrderDetailEntity.class);
        OrderDetailEntity orderDetail = orderDetailDao.save(orderDeatilEntity);
        return new ResultBean(orderDetail, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteOrderDetailById(Integer id) throws Exception {
        if(!orderDetailDao.existsById(id)) {
            throw new Exception("Order Detail by Id " + id + " does not exist!");
        }
        orderDetailDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
    }

}
