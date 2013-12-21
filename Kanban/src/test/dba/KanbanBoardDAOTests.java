package test.dba;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.nsi.kanban.server.dao.managers.KanbanBoardManager;
import com.nsi.kanban.server.dao.managers.Managers;
import com.nsi.kanban.shared.domain.KanbanBoard;
import com.nsi.kanban.shared.domain.KanbanCard;
import com.nsi.kanban.shared.domain.KanbanWorkflow;

public class KanbanBoardDAOTests {
	private static final Logger log = Logger.getLogger(KanbanBoardDAOTests.class);
	
	private KanbanBoardManager boardManager = Managers.getKanbanBoardManager();
	private KanbanWorkflow[] workflows = null;
	private KanbanCard[] cards = null;
	
	@Before
	public void initObjects(){
		
		
		cards = new KanbanCard[]{
				new KanbanCard(),
				new KanbanCard(),
				new KanbanCard(),
				new KanbanCard()
		};
		workflows = new KanbanWorkflow[]{
				new KanbanWorkflow(),
				new KanbanWorkflow(),
		};
		
		for(int i = 0; i < 2; i++)
			workflows[i].addCard(cards[i]);
		
		for(int i = 2; i < 4; i++)
			workflows[i].addCard(cards[i]);
	}
	
	@Test
	public void testKanbanBoardBasic(){
		
		KanbanBoard board = new KanbanBoard();
		
		
		
	}
}
