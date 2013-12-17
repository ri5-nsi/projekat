package com.nsi.kanban.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nsi.kanban.client.KanbanService;

public class KanbanServiceImpl extends RemoteServiceServlet
	implements KanbanService {

	public String contact(String s) {
		return s;
	}


}
