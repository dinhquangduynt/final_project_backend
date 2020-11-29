package com.dinhquangduy.electronic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.OrderDetailEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;

@Repository
@LogExecutionTime
public interface OrderDetailDao  extends JpaRepository<OrderDetailEntity, Integer>{
    List<OrderDetailEntity> findByOrderId(Integer orderId);
}
