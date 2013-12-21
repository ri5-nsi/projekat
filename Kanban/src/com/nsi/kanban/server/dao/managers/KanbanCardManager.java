package com.nsi.kanban.server.dao.managers;

import java.util.List;

import org.hibernate.Session;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.Transaction;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.shared.domain.KanbanCard;

@DAOManager(klasa = KanbanCardDAO.class)
public class KanbanCardManager extends GeneralManager {
	
}
