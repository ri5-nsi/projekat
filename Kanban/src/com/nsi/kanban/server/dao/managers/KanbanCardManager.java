package com.nsi.kanban.server.dao.managers;

import org.hibernate.Session;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.Transaction;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.shared.pojo.KanbanCard;

public class KanbanCardManager {
	
	private static DAOFactory factory = DAOFactory.getFactory(DAOFactory.HIBERNATE);
	
	public void save(KanbanCard card)
	{
		KanbanCardDAO dao = factory.getKanbanCardDAO();
		
		Transaction tx = factory.getTransaction();
		try {
			tx.begin();
			
			dao.save(card);
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
		}
		
		
	}
	
}
