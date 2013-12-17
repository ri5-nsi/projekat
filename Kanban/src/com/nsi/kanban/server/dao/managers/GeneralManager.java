package com.nsi.kanban.server.dao.managers;

import java.io.Serializable;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.GenericDAO;

public class GeneralManager {
	public static DAOFactory factory = null;
	
	static{
		factory = DAOFactory.getFactory(DAOFactory.HIBERNATE);
	}
}
