package com.nsi.kanban.server.dao;

import java.io.Serializable;

import org.hibernate.Session;

import com.nsi.kanban.server.dao.impl.HibDAOFactory;

public abstract class DAOFactory {
	public static final Class<HibDAOFactory> HIBERNATE = HibDAOFactory.class;
	
	public static DAOFactory getFactory(Class factory){
		try {
			return (DAOFactory) factory.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public abstract Session getSession();
	
	public abstract Transaction getTransaction();
	
	public abstract KanbanCardDAO getKanbanCardDAO();
	public abstract <T, ID extends Serializable> GenericDAO<T, ID> getGenericDAO(Class<T> clas);
	
}
