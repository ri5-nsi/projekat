package com.nsi.kanban.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;

public class CreateKanbanCardUI extends Composite {

	private static CreateKanbanCardUIUiBinder uiBinder = GWT
			.create(CreateKanbanCardUIUiBinder.class);

	interface CreateKanbanCardUIUiBinder extends
			UiBinder<Widget, CreateKanbanCardUI> {
	}

	public CreateKanbanCardUI() {
		initWidget(uiBinder.createAndBindUi(this));
		
		orderDatePicker.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd. MM. yyyy.")));
		dueDatePicker.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd. MM. yyyy.")));
	}
	@UiField DoubleBox quantityBox;
	@UiField TextBox locationBox;
	@UiField TextBox supplierBox;
	@UiField TextBox plannerBox;
	@UiField TextBox subjectBox;
	@UiField TextBox nameBox;
	@UiField DateBox orderDatePicker;
	@UiField DateBox dueDatePicker;
	@UiField TextArea descriptionBox;

	public CreateKanbanCardUI(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
