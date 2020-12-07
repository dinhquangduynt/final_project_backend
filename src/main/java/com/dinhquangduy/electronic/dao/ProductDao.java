package com.dinhquangduy.electronic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.ProductEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;

@Repository
@LogExecutionTime
public interface ProductDao extends JpaRepository<ProductEntity, Integer>{
    
    List<ProductEntity> findByCategoryId(Integer categoryId);
    
    @Query(value = "SELECT MAX(p.id) FROM products p",  nativeQuery = true)
    Integer getMaxId();
}
