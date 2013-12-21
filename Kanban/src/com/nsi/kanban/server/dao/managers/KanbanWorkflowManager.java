package com.nsi.kanban.server.dao.managers;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.cache.spi.GeneralDataRegion;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.GenericDAO;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.KanbanWorkflowDAO;
import com.nsi.kanban.server.dao.Transaction;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.shared.domain.KanbanCard;
import com.nsi.kanban.shared.domain.KanbanWorkflow;

@DAOManager(klasa = KanbanWorkflowDAO.class)
public class KanbanWorkflowManager extends GeneralManager {
	
//	public List<KanbanCard> getKanbanCards(KanbanWorkflow workflow){
//		
//		Transaction tx = factory.getTransaction();
//		
//		List<KanbanCard> cards = null;
//		try {
//			tx.begin();
//			
////			GenericDAO<KanbanCard, ?> dao = (GenericDAO<KanbanCard, ?>) createDAO();
////			cards = workflow.getCards();
//			Hibernate.initialize(workflow.getCards());
//			cards = workflow.getCards();
//			
//			tx.commit();
//		} catch (DAOTransactionExeption e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return cards;
//	}
	
	@Override
	public List find(Object example) {
		throw new NotImplementedException();
	}
	
	@Override
	public KanbanWorkflow findByPK(Serializable id) {
		return (KanbanWorkflow) super.findByPK(id);
	}
}
