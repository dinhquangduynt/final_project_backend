package com.dinhquangduy.electronic.services;

import com.dinhquangduy.electronic.bean.ResultBean;
import com.dinhquangduy.electronic.bean.entity.RoleEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface RoleService.
 */
public interface RoleService {

    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    public ResultBean getAll() throws Exception;

    /**
     * Gets the role by id.
     *
     * @param id the id
     * @return the role by id
     * @throws Exception the exception
     */
    public ResultBean getRoleById(Integer id) throws Exception;

    /**
     * Gets the role by user id.
     *
     * @param userId the user id
     * @return the role by user id
     * @throws Exception the exception
     */
    public ResultBean getRoleByUserId(Integer userId) throws Exception;

    /**
     * Adds the role.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean addRole(RoleEntity entity) throws Exception;

    /**
     * Update role.
     *
     * @param entity the entity
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean updateRole(RoleEntity entity) throws Exception;
    
    /**
     * Delete role by id.
     *
     * @param id the id
     * @return the result bean
     * @throws Exception the exception
     */
    public ResultBean deleteRoleById(Integer id) throws Exception;

}
