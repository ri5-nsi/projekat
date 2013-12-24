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
	
	public List<KanbanCard> getKanbanCards(KanbanWorkflow workflow){
		
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.HIBERNATE);
		
		KanbanWorkflowDAO dao = null;
		try {
			dao = (KanbanWorkflowDAO) createDAO();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Transaction tx = factory.getTransaction();
		
//		dao.setSession(session);
		
		List<KanbanCard> cards = null;
		try {
			tx.begin();
			
			KanbanWorkflow wf2 = dao.findByPK(workflow.getId());
			if( wf2 != null )
				cards = wf2.getCards();
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
		}
		
		return cards;
		
//		Transaction tx = dao.getTransaction();
//		
//		List<KanbanCard> cards = null;
//		try {
//			tx.begin();
//			cards = workflow.getCards();
//			
//			tx.commit();
//		} catch (DAOTransactionExeption e) {
//			e.printStackTrace();
//		}
//		
//		return cards;
	}
	
	@Override
	public List find(Object example) {
		throw new NotImplementedException();
	}
	
	@Override
	public KanbanWorkflow findByPK(Serializable id) {
		return (KanbanWorkflow) super.findByPK(id);
	}
}
