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
import com.google.gwt.user.client.ui.TextBox;

public class CreateKanbanWorkflowUI extends Composite {

	private static CreateKanbanWorkflowUIUiBinder uiBinder = GWT
			.create(CreateKanbanWorkflowUIUiBinder.class);

	interface CreateKanbanWorkflowUIUiBinder extends
			UiBinder<Widget, CreateKanbanWorkflowUI> {
	}

	public CreateKanbanWorkflowUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField TextBox workflowName;
	@UiField Button createWorkflowBtn;

	public CreateKanbanWorkflowUI(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
