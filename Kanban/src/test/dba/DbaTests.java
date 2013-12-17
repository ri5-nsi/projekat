package test.dba;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

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

import com.bookstore.BookDetails;
import com.bookstore.HibernateUtil;
import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.GenericDAO;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.exception.DAOTransactionExeption;
import com.nsi.kanban.server.dao.managers.KanbanCardManager;
import com.nsi.kanban.server.dao.managers.Managers;
import com.nsi.kanban.shared.pojo.KanbanCard;

public class DbaTests {
	
	private static Logger log = Logger.getLogger(DbaTests.class);
	private SessionFactory factory = null;
	
	@Before
	public void initHibernate()
	{
		factory = HibernateUtil.getSessionFactory();
	}
	
	@Test
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
	
	@Test
	@Ignore
	public void testDAOAnnotation()
	{
		KanbanCardManager manager = Managers.getKanbanCardManager();
		KanbanCard card = new KanbanCard();
		card.setQuantity(102);
		manager.save(card);
		log.info("manager count: " + manager.count());
	}
	
	@Test
	@Ignore
	public void testHibConnection()
	{
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		KanbanCard kc = new KanbanCard();
		kc.setQuantity(100);
		
		Long kcid = (Long) session.save(kc);
		tx.commit();
		
		log.info(kcid);
	}
	
}
