package test.dba;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import java_cup.assoc;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.GenericDAO;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.server.dao.impl.HibernateUtil;
import com.nsi.kanban.server.dao.managers.KanbanCardManager;
import com.nsi.kanban.server.dao.managers.KanbanWorkflowManager;
import com.nsi.kanban.server.dao.managers.Managers;
import com.nsi.kanban.shared.domain.KanbanCard;
import com.nsi.kanban.shared.domain.KanbanWorkflow;

public class DbaTests {
	
	private static Logger log = Logger.getLogger(DbaTests.class);
	
	@Test
	@Ignore
	public void testKanbanCardSaveCountDelete()
	{
		KanbanCardManager manager = Managers.getKanbanCardManager();
		
		Long count = null;
		count = manager.count();
		
		assert(count != null);
		
		KanbanCard card = new KanbanCard();
		assert(card.getId() == null);
		
		manager.save(card);
		
		assert(manager.count() == (count + 1));
		assert(card.getId() != null);
		
		manager.delete(card);
		
		assert(card.getId() == null);
		assert(manager.count() == count);
	}
	
	@Test
	@Ignore
	public void testKanbanCardSaveDelete()
	{
		KanbanCardManager manager = Managers.getKanbanCardManager();
		
		KanbanCard card = new KanbanCard();
		card.setName("name");
		
		manager.save(card);
		
		assert(card.getId() != null);
		
		manager.delete(card);
		
		assert(card.getId() == null);
	}
	
	@Test
//	@Ignore
	public void testKanbanWorkflowSave()
	{
		KanbanWorkflowManager workflowManager = Managers.getKanbanWorkflowManager();
		KanbanCardManager manager = Managers.getKanbanCardManager();
		
//		KanbanWorkflow workflow = new KanbanWorkflow();
//		
//		workflow.setName("workflow name");
//		
//		for(int i = 0; i < 3; i++)
//		{
//			KanbanCard card = new KanbanCard();
//			
//			manager.save(card);
//			
//			workflow.addCard(card);
//		}
//		
//		workflowManager.save(workflow);
	}
	
	@Test
	@Ignore
	public void testKanbanWorkflow()
	{
		KanbanWorkflowManager workflowManager = Managers.getKanbanWorkflowManager();
		KanbanCardManager cardManager = Managers.getKanbanCardManager();
//		
		KanbanWorkflow workflow = new KanbanWorkflow();
//		Collection list = new ArrayList<>();
//		
//		for(int i = 0; i < 3; i++)
//		{
//			KanbanCard card = new KanbanCard();
//			
//			card.setQuantity(new Integer(i));
//			
//			list.add(card);
//		}
//		
//		workflow.setCards(list);
//		
		workflow.setName("Name");
		
		workflowManager.save(workflow);
		
	}
	
	@Test
	@Ignore
	public void testGenericDAO()
	{
//		com.nsi.kanban.server.dao.KanbanCardDAO dao = new HibKanbanCardDAO();
//		dao.setSession(HibernateUtil.getSession());
		KanbanCard card = new KanbanCard();
		card.setQuantity(102);
//		KanbanCardManager mng = Managers.getKanbanCardManager();
//		mng.save(card);
		
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.HIBERNATE);
		KanbanCardDAO dao = factory.getKanbanCardDAO();
//		
//		
//		
//		HibernateUtil.getSession().beginTransaction();
//		dao.save(card);
//		
//		HibernateUtil.getSession().getTransaction().commit();
		
//		com.nsi.kanban.server.dao.Transaction tx = factory.getTransaction();
//		try {
//			tx.begin();
//			
//			dao.save(card);
//			
//			tx.commit();
//		} catch (DAOTransactionExeption e) {
//			e.printStackTrace();
//		}
		
//		KanbanCardManager manager = new KanbanCardManager();
		KanbanCardManager manager = Managers.getKanbanCardManager();
		manager.save(card);
		log.info("count: " + manager.count());
		
		log.info("WIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIICKED" + card.getId());
	}
	
}
