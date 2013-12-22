package com.nsi.kanban.server.dao.managers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DAOManager {
	Class klasa();
}
