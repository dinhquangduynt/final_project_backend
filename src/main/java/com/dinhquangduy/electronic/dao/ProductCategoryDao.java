package com.dinhquangduy.electronic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.ProductCategory;
import com.dinhquangduy.electronic.bean.entity.ProductEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;

@Repository
@LogExecutionTime
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer>{

}
