package com.dinhquangduy.electronic.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.RoleEntity;
import com.dinhquangduy.electronic.config.LogExecutionTime;
import com.dinhquangduy.electronic.dao.RoleDao;
import com.dinhquangduy.electronic.services.RoleService;
import com.dinhquangduy.electronic.utils.Constants;

@Service
@LogExecutionTime
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ResultBean getAll() throws Exception {
        List<RoleEntity> roles = roleDao.findAll();
        return new ResultBean(roles, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getRoleById(Integer id) throws Exception {
        if (!roleDao.existsById(id)) {
            throw new Exception("Role by id " + id + " does not exist");
        }
        RoleEntity role = roleDao.findById(id).get();
        return new ResultBean(role, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean getRoleByUserId(Integer userId) throws Exception {
        return null;
    }

    @Override
    public ResultBean addRole(RoleEntity entity) throws Exception {
        RoleEntity role = roleDao.save(entity);
        return new ResultBean(role, Constants.STATUS_201, Constants.MSG_OK);
    }

    @Override
    public ResultBean updateRole(RoleEntity entity) throws Exception {
        if (!roleDao.existsById(entity.getIdRole())) {
            throw new Exception("Role by id " + entity.getIdRole() + " does not exist");
        }
        RoleEntity role = roleDao.save(entity);
        return new ResultBean(role, Constants.STATUS_OK, Constants.MSG_OK);
    }

    @Override
    public ResultBean deleteRoleById(Integer id) throws Exception {
        if (!roleDao.existsById(id)) {
            throw new Exception("Role by id " + id + " does not exist");
        }
        roleDao.deleteById(id);
        return new ResultBean(Constants.STATUS_OK, Constants.MSG_DELETE_OK);
    }

}
