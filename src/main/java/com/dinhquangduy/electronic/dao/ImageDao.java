package com.dinhquangduy.electronic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.ImageEntity;
@Repository
public interface ImageDao extends JpaRepository<ImageEntity, Integer>{

    @Query(value = "SELECT i FROM images i WHERE i.type = :type AND i.parent_id = :parentId",  nativeQuery = true)
    List<ImageEntity> getListImageByTypeAndParentId(@Param("type") String type,@Param("parentId") Integer parentId);
    
    @Query(value = "DELETE * FROM images i WHERE i.type = :type AND i.parent_id = :parentId",  nativeQuery = true)
    List<ImageEntity> deleteImageByTypeAndParentId(@Param("type") String type,@Param("parentId") Integer parentId);
}
