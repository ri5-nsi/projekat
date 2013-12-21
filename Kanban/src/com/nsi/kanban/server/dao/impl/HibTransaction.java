package com.nsi.kanban.server.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nsi.kanban.server.dao.Transaction;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;

public class HibTransaction implements Transaction {

	private Session session = null;
	org.hibernate.Transaction transaction = null;
	
	public HibTransaction(Session session) {
		 this.session = session;
	}
	
	@Override
	public void begin() throws DAOTransactionExeption {
		if (transaction == null) {
			try {
				transaction = session.beginTransaction();
			} catch (HibernateException ex) {
				throw new DAOTransactionExeption("Cannot begin transaction " + ex.getMessage());
			}
		}
	}

	@Override
	public void commit() throws DAOTransactionExeption {
		if (transaction == null) {
			throw new DAOTransactionExeption("Transaction not initialized.");
		}
		try {
			transaction.commit();
		} catch (HibernateException ex) {
			throw new DAOTransactionExeption("Cannot commit transaction " + ex.getMessage());
		}
		transaction = null;
		session.close();
	}

	@Override
	public void rollback() throws DAOTransactionExeption {
		if (transaction == null) {
			throw new DAOTransactionExeption("Transakcija nije zapoceta");
		}
		try {
			transaction.rollback();
		} catch (HibernateException ex) {
			throw new DAOTransactionExeption("Rollback error " + ex.getMessage());
		}
		transaction = null;
		session.close();
	}

	@Override
	public boolean isActive() throws DAOTransactionExeption {
		if (transaction == null) {
			return false;
		}
		boolean active = false;
		try {
			active = transaction.isActive();
		} catch (HibernateException ex) {
			throw new DAOTransactionExeption("Transaction active error " + ex.getMessage());
		}
		return active;
	}

	@Override
	public void flushAndClear() {
		session.flush();
		session.clear();
	}

}
