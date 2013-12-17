package com.nsi.kanban.server.dao.managers;

import java.util.List;

import org.hibernate.Session;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.Transaction;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.shared.pojo.KanbanCard;

public class KanbanCardManager extends GeneralManager {
	
	public KanbanCard save(KanbanCard card)
	{
		Transaction tx = factory.getTransaction();
		try {
			tx.begin();
			KanbanCardDAO dao = factory.getKanbanCardDAO();
			
			dao.save(card);
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
				return null;
			}
			e.printStackTrace();
			return null;
		}
		
		return card;
	}
	
	public Long count()
	{
		Transaction tx = factory.getTransaction();
		Long ret = new Long(0);
		try {
			tx.begin();
			KanbanCardDAO dao = factory.getKanbanCardDAO();
			
			ret = (Long) dao.count();
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public boolean delete(KanbanCard card)
	{
		Transaction tx = factory.getTransaction();
		try {
			tx.begin();
			
			KanbanCardDAO dao = factory.getKanbanCardDAO();
			dao.delete(card);
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
				return false;
			}
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
