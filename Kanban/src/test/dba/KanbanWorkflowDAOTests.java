package test.dba;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.KanbanWorkflowDAO;
import com.nsi.kanban.server.dao.Transaction;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.server.dao.managers.KanbanCardManager;
import com.nsi.kanban.server.dao.managers.KanbanWorkflowManager;
import com.nsi.kanban.server.dao.managers.Managers;
import com.nsi.kanban.shared.domain.KanbanCard;
import com.nsi.kanban.shared.domain.KanbanWorkflow;

public class KanbanWorkflowDAOTests {
	
	private static Logger log = Logger.getLogger(KanbanCardDAOTests.class);
	KanbanWorkflowManager workflowManager = null;
	KanbanCardManager kanbanCardManager = null;
	
	KanbanCard[] cards = null;
	
	@Before
	public void initObjects(){
		
		cards = new KanbanCard[]{
				new KanbanCard(),
				new KanbanCard(),
				new KanbanCard()
		};
		
		workflowManager = Managers.getKanbanWorkflowManager();
		kanbanCardManager = Managers.getKanbanCardManager();
	}
	
	@Test
	@Ignore
	public void testKanbanWorkflowSave(){
		KanbanWorkflow workflow = new KanbanWorkflow();
		workflow.setName("name");
		
		long count = workflowManager.count();
		workflowManager.save(workflow);
		
		assertEquals(count+1, workflowManager.count());
		
		workflowManager.delete(workflow);
		
		assertEquals(count, workflowManager.count());
	}
	
	@Test
	@Ignore
	public void testKanbanWorkflowAddingCard(){
		
		KanbanWorkflow workflow = new KanbanWorkflow();
		workflow.setName("name");
		
		kanbanCardManager.save(cards);
		
		workflow.addCards(cards);
		workflowManager.save(workflow);
		
		KanbanWorkflow newworkflow = (KanbanWorkflow) workflowManager.findByPK(workflow.getId());
		assertEquals(workflow.getId(), newworkflow.getId());
		
		workflowManager.delete(workflow);
		kanbanCardManager.delete(cards);
		
	}
	
	@Test
	@Ignore
	public void testKanbanWorkflowRemoveCard(){

		
		KanbanWorkflow workflow = new KanbanWorkflow();
		workflow.setName("name");
		
		kanbanCardManager.save(cards);
		
		workflow.addCards(cards);
		workflowManager.save(workflow);
		
		KanbanWorkflow workflow2 = workflowManager.findByPK(workflow.getId());
		assertEquals(cards.length, workflow2.getCards().size());
		
		workflow2.getCards().remove(0);
		workflowManager.save(workflow2);
		
		KanbanWorkflow workflow3 = workflowManager.findByPK(workflow.getId());
		assertEquals(cards.length-1, workflow3.getCards().size());
		
		workflowManager.delete(workflow);
		kanbanCardManager.delete(cards);
		
	}
	
	@Test
	public void testKanbanWorkflow(){
		
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.HIBERNATE);
		KanbanCardDAO cardDAO = factory.getKanbanCardDAO();
		KanbanWorkflowDAO workflowDAO = factory.getKanbanWorkflowDAO();
		
		
		Session session = null;
		Transaction tx = null;
		
		long workflowCount = 0;
		long cardCount = 0;
		
		// init
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			tx.begin();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			
			workflowCount = workflowDAO.count().longValue();
			cardCount = cardDAO.count().longValue();
			for(KanbanCard card : cards)
				cardDAO.save(card);
			
			tx.commit();
		} catch (DAOTransactionExeption e2) {
			e2.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e) {
				e.printStackTrace();
			}
		}

		KanbanWorkflow workflow = new KanbanWorkflow();
		workflow.setName("workflow name");

		// adding to list
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			workflow.addCards(cards);
			
			workflowDAO.save(workflow);
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
		}

		// assert added card count
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			KanbanWorkflow wf2 = workflowDAO.findByPK(workflow.getId());
			assertEquals(cards.length, wf2.getCards().size());
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
		}

		// remove card from workflow
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			KanbanWorkflow wf2 = workflowDAO.findByPK(workflow.getId());
			wf2.getCards().remove(0);
			workflowDAO.save(wf2);
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
		}

		// count workflows
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			assertEquals(workflowCount + 1, workflowDAO.count());
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
		}

		// test if removed
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			KanbanWorkflow wf2 = workflowDAO.findByPK(workflow.getId());
			assertEquals(cards.length-1, wf2.getCards().size());
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
		}
		
		// cleanup
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			workflowDAO.delete(workflow);
			
			tx.commit();
		} catch (DAOTransactionExeption e2) {
			e2.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			for(KanbanCard card : cards){
				cardDAO.delete(card);
			}
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
		}
		
		// checkout
		try {
			session = factory.getSession();
			tx = factory.getTransaction();
			workflowDAO.setSession(session);
			cardDAO.setSession(session);
			tx.begin();
			
			assertEquals(cardCount, cardDAO.count());
			assertEquals(workflowCount, workflowDAO.count());
			
			tx.commit();
		} catch (DAOTransactionExeption e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (DAOTransactionExeption e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
