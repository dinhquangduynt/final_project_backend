package com.dinhquangduy.electronic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.OrderEntity;
@Repository
public interface OrderDao extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByCustomerId(Integer customerId);
    
}
