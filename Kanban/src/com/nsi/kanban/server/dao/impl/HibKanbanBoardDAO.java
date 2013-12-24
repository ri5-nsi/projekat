package com.nsi.kanban.server.dao.impl;

import com.nsi.kanban.server.dao.KanbanBoardDAO;
import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.shared.domain.KanbanBoard;
import com.nsi.kanban.shared.domain.KanbanCard;

public class HibKanbanBoardDAO extends HibGenericDAO<KanbanBoard, Long> implements KanbanBoardDAO {

	public HibKanbanBoardDAO(){
		super(KanbanBoard.class);
	}
	
}
