package com.nsi.kanban.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class KanbanBoard extends Composite implements HasText {

	private static KanbanBoardUiBinder uiBinder = GWT
			.create(KanbanBoardUiBinder.class);

	interface KanbanBoardUiBinder extends UiBinder<Widget, KanbanBoard> {
	}

	public KanbanBoard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public KanbanBoard(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public String getText() {
		return null;
	}

	@Override
	public void setText(String text) {
		
	}

}
