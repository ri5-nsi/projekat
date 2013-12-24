package test.dba;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.nsi.kanban.server.dao.managers.KanbanBoardManager;
import com.nsi.kanban.server.dao.managers.KanbanCardManager;
import com.nsi.kanban.server.dao.managers.KanbanWorkflowManager;
import com.nsi.kanban.server.dao.managers.Managers;
import com.nsi.kanban.shared.domain.KanbanBoard;
import com.nsi.kanban.shared.domain.KanbanCard;
import com.nsi.kanban.shared.domain.KanbanWorkflow;

public class KanbanBoardDAOTests {
	private static final Logger log = Logger.getLogger(KanbanBoardDAOTests.class);
	
	private KanbanBoardManager boardManager = null;
	private KanbanWorkflowManager workflowManager = null;
	private KanbanCardManager cardManager = null;
	
	private KanbanWorkflow[] workflows = null;
	private KanbanCard[] cards = null;
	
	@Before
	public void initObjects(){
		boardManager = Managers.getKanbanBoardManager();
		workflowManager = Managers.getKanbanWorkflowManager();
		cardManager = Managers.getKanbanCardManager();
		
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
		
		for(int i = 0, j = 0; i < 2; i++, j++)
			workflows[j].addCard(cards[i]);
		
		for(int i = 2, j = 0; i < 4; i++, j++)
			workflows[j].addCard(cards[i]);
	}
	
	@Test
	public void testKanbanBoardBasic(){
		
		KanbanBoard board = new KanbanBoard();

		long cardCount = cardManager.count();
		cardManager.save(cards);
		
		long workflowCount = workflowManager.count();
		workflowManager.save(workflows);
		
		long boardCount = boardManager.count();
		board.addWorkflows(workflows);
		
		boardManager.save(board);
		List<KanbanWorkflow> wfs = board.getWorkflows();
		int size = wfs.size();
		assertEquals(workflows.length, wfs.size());
		
		board.getWorkflows().remove(workflows[0]);
		boardManager.save(board);
		
		// THIS IS LAZY AND WILL FAIL
		assertEquals(size, boardManager.findByPK(board.getId()).getWorkflows().size());
		
		boardManager.delete(board);
		assertEquals(boardCount, boardManager.count());
		
		workflowManager.delete(workflows);
		assertEquals(workflowCount, workflowManager.count());
		
		cardManager.delete(cards);
		assertEquals(cardCount, cardManager.count());
		
	}
}
