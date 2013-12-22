package com.nsi.kanban.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("kanban")
public interface KanbanService extends RemoteService{
	String contact(String s);
}
