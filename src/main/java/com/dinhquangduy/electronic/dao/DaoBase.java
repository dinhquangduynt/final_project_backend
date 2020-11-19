package com.dinhquangduy.electronic.dao;

import java.util.List;
import java.util.function.Predicate;

public interface DaoBase<T> {

 // Marks an entity as new
    T Add(T entity);

    // Marks an entity as modified
    void Update(T entity);

    // Marks an entity to be removed
    T Delete(T entity);

    T Delete(int id);

    //Delete multi records
    void DeleteMulti(Predicate<T> where);

    // Get an entity by int id
    T GetSingleById(int id);

    T GetSingleByCondition(Predicate<T> expression, StringBuilder includes);

    List<T> GetAll(StringBuilder sql);

    List<T> GetMulti(Predicate<T> predicate, StringBuilder includes);

    int Count(Predicate<T> where);

    boolean CheckContains(Predicate<T> predicate);
    
}
