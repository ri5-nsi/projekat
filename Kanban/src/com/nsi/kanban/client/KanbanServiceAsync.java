package com.nsi.kanban.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface KanbanServiceAsync {
	void contact(String s, AsyncCallback<String> callback);
}
