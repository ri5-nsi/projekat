package com.nsi.kanban.server.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.GenericDAO;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.KanbanWorkflowDAO;
import com.nsi.kanban.server.dao.Transaction;

public class HibDAOFactory extends DAOFactory {

	private Session session = null;
	
	public Session getSession(){
		if( session == null )
			session = HibernateUtil.getSessionFactory().openSession();
		if( session != null && !session.isOpen() )
			session = HibernateUtil.getSessionFactory().openSession();
		
		return session;
	}

	@Override
	public <T, ID extends Serializable> GenericDAO<T, ID> getGenericDAO(Class<T> klasa) {
		HibGenericDAO<T, ID> dao = new HibGenericDAO<T, ID>(klasa);
		dao.setSession(getSession());
		return dao;
	}

	@Override
	public KanbanCardDAO getKanbanCardDAO() {
		KanbanCardDAO dao = new HibKanbanCardDAO();
		dao.setSession(getSession());
		return dao;
	}
	
	@Override
	public KanbanWorkflowDAO getKanbanWorkflowDAO() {
		KanbanWorkflowDAO dao = new HibKanbanWorkflowDAO();
		dao.setSession(getSession());
		return dao;
	}

	@Override
	public Transaction getTransaction() {
		return new HibTransaction(getSession());
	}
}
