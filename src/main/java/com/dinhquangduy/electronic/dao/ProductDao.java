package com.dinhquangduy.electronic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.ProductEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;

@Repository
@LogExecutionTime
public interface ProductDao extends JpaRepository<ProductEntity, Integer>{
    
    List<ProductEntity> findByCategoryId(Integer categoryId);
    
    @Query(value = "SELECT MAX(p.id) FROM products p",  nativeQuery = true)
    Integer getMaxId();
    
    @Query(value = "SELECT * FROM products p WHERE p.id in :listId",  nativeQuery = true)
    List<ProductEntity> getProductsRecommed(@Param("listId") List<Integer> listId);
    
    @Query(value = "SELECT * FROM products p WHERE p.hot_flg = 1 limit 10",  nativeQuery = true)
    List<ProductEntity> getHotProduct();
    
    @Query(value = "SELECT * FROM products p ORDER BY p.create_date DESC limit 10",  nativeQuery = true)
    List<ProductEntity> getNewProduct();
    
    @Query(value = "SELECT * FROM products p ORDER BY p.view_count DESC limit 10",  nativeQuery = true)
    List<ProductEntity> getProductRecomendWithouUser();
    
    @Query(value = "DELETE * FROM products p WHERE p.category_id = :idCate",  nativeQuery = true)
    List<ProductEntity> deleteAllByIdCate(@Param("idCate") Integer idCate);
    
    @Query(value = "SELECT COUNT(*) FROM feedbacks p WHERE p.product_id = :id",  nativeQuery = true)
    Integer countProductRated(@Param("id") Integer id);
    
    @Query(value = "UPDATE products p set p.rating = round((select avg(f.rate) from feedbacks f where f.product_id = :id group by f.product_id),1) where p.id = :id",  nativeQuery = true)
    void executeAVG(@Param("id") Integer id);
    
}
