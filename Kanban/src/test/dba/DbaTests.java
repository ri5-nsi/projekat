package test.dba;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.nsi.kanban.shared.pojo.KanbanCard;

public class DbaTests {
	
	private static Logger log = Logger.getLogger(DbaTests.class);
	
	@Test
	public void testHibConnection()
	{
		
		SessionFactory factory;
		factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		Transaction tx = null;
		
		tx = session.beginTransaction();
		KanbanCard kc = new KanbanCard();
		kc.setQuantity("100");
		
		Integer kcid = (Integer) session.save(kc);
		tx.commit();
		
		session.close();
		
		log.info(kcid);
	}
	
}
