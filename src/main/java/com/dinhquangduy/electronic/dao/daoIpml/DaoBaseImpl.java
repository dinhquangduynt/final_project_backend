package com.dinhquangduy.electronic.dao.daoIpml;

import java.util.List;
import java.util.function.Predicate;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dinhquangduy.electronic.dao.DaoBase;

public class DaoBaseImpl<T> implements DaoBase<T>{

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext()
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T Add(T entity) {
        this.getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public void Update(T entity) {
        
    }

    @Override
    public T Delete(T entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T Delete(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void DeleteMulti(Predicate<T> where) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public T GetSingleById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T GetSingleByCondition(Predicate<T> expression, StringBuilder includes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> GetAll(StringBuilder sql) {
        Query query = (Query) this.entityManager.createQuery(sql.toString());
        return null ;
    }

    @Override
    public List<T> GetMulti(Predicate<T> predicate, StringBuilder includes) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int Count(Predicate<T> where) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean CheckContains(Predicate<T> predicate) {
        // TODO Auto-generated method stub
        return false;
    }

}
