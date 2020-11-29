package com.dinhquangduy.electronic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinhquangduy.electronic.bean.entity.RoleEntity;

public interface RoleDao extends JpaRepository<RoleEntity, Integer> {
    

}
