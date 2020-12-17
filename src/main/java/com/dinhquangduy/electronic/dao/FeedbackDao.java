package com.dinhquangduy.electronic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.FeedbackEntity;

@Repository
public interface FeedbackDao extends JpaRepository<FeedbackEntity, Integer>{
    
    List<FeedbackEntity> findByProductId(Integer productId);
    

}
