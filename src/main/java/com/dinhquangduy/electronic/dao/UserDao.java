package com.dinhquangduy.electronic.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.UserEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;

@Repository
@LogExecutionTime
public interface UserDao extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserName(String userName);
}
