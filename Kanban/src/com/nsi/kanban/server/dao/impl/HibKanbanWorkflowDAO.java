package com.nsi.kanban.server.dao.impl;

import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.server.dao.KanbanWorkflowDAO;
import com.nsi.kanban.shared.domain.KanbanCard;
import com.nsi.kanban.shared.domain.KanbanWorkflow;

public class HibKanbanWorkflowDAO extends HibGenericDAO<KanbanWorkflow, Long> implements KanbanWorkflowDAO {

	public HibKanbanWorkflowDAO() {
		super(KanbanWorkflow.class);
	}
	
}