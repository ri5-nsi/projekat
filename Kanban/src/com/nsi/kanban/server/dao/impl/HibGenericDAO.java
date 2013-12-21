package com.nsi.kanban.server.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import com.nsi.kanban.server.dao.GenericDAO;
import com.nsi.kanban.server.dao.Transaction;

public class HibGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID>{

	private Session session = null;
	private Class<T> clas;
	
    public HibGenericDAO(Class<T> clas) {
    	this.clas = clas;
	}
    
	public Session getSession() {
        return session;
    }
	
	public Transaction getTransaction(){
		return new HibTransaction(getSession());
	}
    
    public void setSession(Session session){
    	this.session = session;
    }
 
    public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }
 
    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    public T findByID(Class clazz, ID id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        return t;
    }

	@Override
	public Number count() {
		return (Number) getSession().createCriteria(clas).setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public List<T> findAll() {
		return getSession().createCriteria(clas).list();
	}

	@Override
	public T findByPK(ID id) {
		return (T) getSession().get(clas, id);
	}

	@Override
	public List<T> findAll(int startNumber, int fetchSize) {
		Criteria crit = getSession().createCriteria(clas);
		crit.setFirstResult(startNumber);
		crit.setFetchSize(fetchSize);
		return crit.list();
	}

	@Override
	public List<T> findByExample(T example) {
		Criteria crit = getSession()
							.createCriteria(clas).add(Example.create(example).excludeNone())
							
							;
		return crit.list();
	}

}
