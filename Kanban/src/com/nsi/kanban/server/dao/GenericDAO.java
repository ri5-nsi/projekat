package com.nsi.kanban.server.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public interface GenericDAO<T, ID extends Serializable> {
	 
    public void save(T entity);
 
    public void merge(T entity);
 
    public void delete(T entity);
 
    @Deprecated
    public List<T> findMany(Query query);
 
    @Deprecated
    public T findOne(Query query);
 
    public List<T> findAll();
    
    public T findByPK(ID id);
    
    public List<T> findAll(int startNumber, int fetchSize);
    
    @Deprecated
    public List<T> findByExample(T example);
    
    public Number count();
    
    public void setSession(Session session);
    
    public Transaction getTransaction();
}