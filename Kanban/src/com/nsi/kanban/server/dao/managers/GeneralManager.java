package com.nsi.kanban.server.dao.managers;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.GenericDAO;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.Transaction;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.shared.domain.KanbanCard;

public class GeneralManager<T> {
	public static DAOFactory factory = null;
	
	static{
		factory = DAOFactory.getFactory(DAOFactory.HIBERNATE);
	}
	
	public Object getDAO() throws Exception
	{
		Annotation anno = this.getClass().getAnnotation(DAOManager.class);
		
		if( anno != null )
		{
			DAOManager daoanno = (DAOManager) anno;
			Class daoclass = daoanno.klasa();
			
			String methodName = "get" + daoclass.getSimpleName();
			
			try {
				
				Method getDAOMethod = factory.getClass().getMethod(methodName);
				
				try {
					Object result = getDAOMethod.invoke(factory);
					
					return result;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		throw new Exception("getDAO() failure");
	}
	
	public T save(T object)
	{
		Transaction tx = factory.getTransaction();
		try{
			tx.begin();
			
			GenericDAO<T, ?> dao = (GenericDAO<T, ?>) getDAO();
			dao.save(object);
			
			tx.commit();
		}catch(DAOTransactionExeption e){
			try {
				tx.rollback();
			} catch (DAOTransactionExeption ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	public Long count()
	{
		Transaction tx = factory.getTransaction();
		Long ret = new Long(0);
		try {
			tx.begin();
			GenericDAO<T, ?> dao = (GenericDAO<T, ?>) getDAO();
			ret = (Long) dao.count();
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public boolean delete(T card)
	{
		Transaction tx = factory.getTransaction();
		try {
			tx.begin();

			GenericDAO<T, ?> dao = (GenericDAO<T, ?>) getDAO();
			dao.delete(card);
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
				return false;
			}
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
