package test.dba;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
	
}
