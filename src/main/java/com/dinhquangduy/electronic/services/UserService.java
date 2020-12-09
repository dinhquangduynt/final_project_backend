package com.dinhquangduy.electronic.services;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.UserEntity;

public interface UserService {
    
    public ResultBean getAll() throws Exception;

    public ResultBean getUserById(Integer id) throws Exception;

    public ResultBean updateUser(UserEntity user) throws Exception;

    public ResultBean addUser(UserEntity user) throws Exception;

    public ResultBean deleteUserbyId(Integer id) throws Exception;
    
    public ResultBean getUserByUsername(String username) throws Exception;
    
    public boolean isExitsUserName(String userName) throws Exception;
    
    public ResultBean resetPass(String email) throws Exception;

}
