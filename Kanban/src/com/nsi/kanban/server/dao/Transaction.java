package com.nsi.kanban.server.dao;

import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;

public interface Transaction {
	public abstract void flushAndClear ();
	
	public void begin() throws DAOTransactionExeption;
	
	public void commit() throws DAOTransactionExeption;
	
	public void rollback() throws DAOTransactionExeption;
	
	public boolean isActive() throws DAOTransactionExeption;
}
