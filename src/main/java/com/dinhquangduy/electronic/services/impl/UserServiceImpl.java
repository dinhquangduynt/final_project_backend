package com.dinhquangduy.electronic.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.RoleEntity;
import com.dinhquangduy.electronic.bean.entity.UserEntity;
import com.dinhquangduy.electronic.bean.response.UserResponse;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.dao.RoleDao;
import com.dinhquangduy.electronic.dao.UserDao;
import com.dinhquangduy.electronic.services.UserService;
import com.dinhquangduy.electronic.utils.Constants;

/**
 * The Class UserServiceImpl.
 */
@Service
@LogExecutionTime
public class UserServiceImpl implements UserService{
    
    /** The user dao. */
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    /** The model mapper. */
    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    @Override
    public ResultBean getAll() throws Exception {
        List<UserEntity> usersEntity = userDao.findAll();
        List<UserResponse> responses = usersEntity.stream().map(res -> modelMapper.map(res, UserResponse.class)).collect(Collectors.toList());
        return new ResultBean(responses, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Gets the user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws Exception the exception
     */
    @Override
    public ResultBean getUserById(Integer id) throws Exception {
        Optional<UserEntity> userOp = userDao.findById(id);
        if(!userOp.isPresent()) {
            throw new Exception("User by id " + id + " does not exist!");
        }
        UserEntity user = userOp.get();
        return new ResultBean(user, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Update user.
     *
     * @param user the user
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean updateUser(UserEntity user) throws Exception {
        Optional<UserEntity> userDb = userDao.findById(user.getId());
        if(!userDb.isPresent()) {
            throw new Exception("User by id " + user.getId() + " does not exist!");
        }
        if(!userDb.get().getPassword().equals(new BCryptPasswordEncoder().encode(user.getPassword()))) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        UserEntity entity = userDao.save(user);
        return new ResultBean(entity, Constants.STATUS_201, Constants.MSG_OK);
    }

    /**
     * Adds the user.
     *
     * @param user the user
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean addUser(UserEntity user) throws Exception {
        //RoleEntity role = roleDao.findById(2).get();
        //user.setRoles(role);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        UserEntity entity = userDao.save(user);
        return new ResultBean(entity, Constants.STATUS_OK, Constants.MSG_OK);
    }

    /**
     * Delete userby id.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    @Override
    public ResultBean deleteUserbyId(Integer id) throws Exception {
        if(!userDao.existsById(id)) {
            throw new Exception("User by id " + id + " does not exist!");
        }
        userDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public boolean isExitsUserName(String userName) throws Exception {
        return userDao.findByUserName(userName).isPresent();
    }

    @Override
    public ResultBean getUserByUsername(String username) throws Exception {
        Optional<UserEntity> userOp = userDao.findByUserName(username);
        if(!userOp.isPresent()) {
            throw new Exception("User by username " + username + " does not exist!");
        }
        UserEntity user = userOp.get();
        return new ResultBean(user, Constants.STATUS_OK, Constants.MSG_OK);
    }
}
