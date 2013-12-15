package com.nsi.kanban.server.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;


public interface GenericDAO<T, ID extends Serializable> {
	 
    public void save(T entity);
 
    public void merge(T entity);
 
    public void delete(T entity);
 
    public List<T> findMany(Query query);
 
    public T findOne(Query query);
 
    public List findAll(Class clazz);
    
    public void setSession(Session session);
}