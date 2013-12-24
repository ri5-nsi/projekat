package com.nsi.kanban.server.dao.managers;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import com.nsi.kanban.server.dao.KanbanBoardDAO;
import com.nsi.kanban.shared.domain.KanbanBoard;

@DAOManager(klasa = KanbanBoardDAO.class)
public class KanbanBoardManager extends GeneralManager {
	
	@Override
	public List find(Object example) {
		throw new NotImplementedException();
	}
	
	@Override
	public KanbanBoard findByPK(Serializable id) {
		return (KanbanBoard) super.findByPK(id);
	}
}
