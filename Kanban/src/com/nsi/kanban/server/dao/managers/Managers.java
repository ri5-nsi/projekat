package com.nsi.kanban.server.dao.managers;

public final class Managers {
	
	private static final KanbanCardManager kanbanCardManager = new KanbanCardManager();
	private static final KanbanWorkflowManager kanbanWorkflowManager = new KanbanWorkflowManager();
	private static final KanbanBoardManager kanbanBoardManager = new KanbanBoardManager();
	
	public static KanbanCardManager getKanbanCardManager() {
		return kanbanCardManager;
	}
	
	public static KanbanWorkflowManager getKanbanWorkflowManager() {
		return kanbanWorkflowManager;
	}
	
	public static KanbanBoardManager getKanbanBoardManager() {
		return kanbanBoardManager;
	}
	
}
