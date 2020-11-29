package com.dinhquangduy.electronic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dinhquangduy.electronic.bean.entity.ContactEntity;

@Repository
public interface ContactDao extends JpaRepository<ContactEntity, Integer>{

}
