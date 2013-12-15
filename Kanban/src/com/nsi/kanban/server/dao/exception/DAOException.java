package com.nsi.kanban.server.dao.exception;

public abstract class DAOException extends Exception {
	public DAOException(String string) {
		super(string);
	}
}
