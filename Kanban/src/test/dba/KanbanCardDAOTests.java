package test.dba;

import static org.junit.Assert.*;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.nsi.kanban.server.dao.DAOFactory;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.managers.KanbanCardManager;
import com.nsi.kanban.server.dao.managers.Managers;
import com.nsi.kanban.shared.domain.KanbanCard;

public class KanbanCardDAOTests {
	
	private static Logger log = Logger.getLogger(KanbanCardDAOTests.class);
	
	KanbanCardManager kanbanCardManager = null;
	KanbanCard[] cards = null;
	
	@Before
	public void initObjects(){
		
		kanbanCardManager = Managers.getKanbanCardManager();
		
		cards = new KanbanCard[]{
				new KanbanCard(),
				new KanbanCard(),
				new KanbanCard()
		};
		
		Integer val = 200;
		for(KanbanCard card : cards)
			card.setQuantity(new Double(val++));
	}
	
	@Test
	@Ignore
	public void testKanbanCardManagerFind()
	{
		long count = kanbanCardManager.count();
		
		kanbanCardManager.save(cards);
		
		long newcount = count+cards.length;

		assertEquals(newcount, kanbanCardManager.count());
		assertEquals(newcount, kanbanCardManager.findAll().size());

		kanbanCardManager.delete(cards);
		
		assertEquals(count, kanbanCardManager.count());
	}
	
	@Test
	@Ignore
	public void testKanbanCardSaveCountDelete()
	{
		KanbanCardManager manager = kanbanCardManager;
		
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
		KanbanCardManager manager = kanbanCardManager;
		
		KanbanCard card = new KanbanCard();
		card.setName("name");
		
		manager.save(card);
		
		assert(card.getId() != null);
		
		manager.delete(card);
		
		assert(card.getId() == null);
	}
	
	@Test
	@Ignore
	public void testKanbanCardFind()
	{
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.HIBERNATE);
		KanbanCardDAO dao = factory.getKanbanCardDAO();

		long count = dao.findAll().size();
		
		for(KanbanCard card : cards)
			dao.save(card);
		
		KanbanCard test = cards[0];
		
		KanbanCard c1;
		
		c1 = dao.findByPK(test.getId());
		assert(c1.getId() == test.getId());
		
		List<KanbanCard> list = dao.findByExample(test);
		assert(list.size() == 1);
		assert(list.get(0).getId() == test.getId());
		
		for(KanbanCard card : cards)
			dao.delete(card);
		
		assert(count == dao.findAll().size());
		
	}
	
}
