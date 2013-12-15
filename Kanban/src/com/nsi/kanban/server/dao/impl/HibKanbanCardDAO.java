package com.nsi.kanban.server.dao.impl;

import com.nsi.kanban.server.dao.KanbanCardDAO;
import com.nsi.kanban.shared.pojo.KanbanCard;

public class HibKanbanCardDAO extends HibGenericDAO<KanbanCard, Long> implements KanbanCardDAO {

	public HibKanbanCardDAO(){
		super(KanbanCard.class);
	}
	
}
