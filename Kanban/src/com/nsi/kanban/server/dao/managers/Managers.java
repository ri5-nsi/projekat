package com.nsi.kanban.server.dao.managers;

public final class Managers {
	
	private static KanbanCardManager kanbanCardManager = new KanbanCardManager();
	
	public static KanbanCardManager getKanbanCardManager()
	{
		return kanbanCardManager;
	}
	
}
