package com.nsi.kanban.server.dao.managers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Managers {
	
	private static final KanbanCardManager kanbanCardManager = new KanbanCardManager();
	private static final KanbanWorkflowManager kanbanWorkflowManager = new KanbanWorkflowManager();
	
	public static KanbanCardManager getKanbanCardManager()
	{
		return kanbanCardManager;
	}
	
	public static KanbanWorkflowManager getKanbanWorkflowManager() {
		return kanbanWorkflowManager;
	}
	
}
