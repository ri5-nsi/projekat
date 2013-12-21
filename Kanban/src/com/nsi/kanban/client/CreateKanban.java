package com.nsi.kanban.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class CreateKanban implements EntryPoint {
	
	private final KanbanServiceAsync kanbanService = GWT.create(KanbanService.class);
	
	AsyncCallback callback = new AsyncCallback<String>() {

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(String result) {
			GWT.log("result: " + result);
		}
		
	};
	
	@Override
	public void onModuleLoad() {
		System.out.println("wicked sick 2");
		
		RootPanel createFormPanel = RootPanel.get("createForm");
		
		Button getDateBtn = new Button("my button");
		final Label dateLabel = new Label("date");
		
		final DatePicker picker = new DatePicker();
		getDateBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String date = DateTimeFormat.getFormat("dd.MM.yyyy").format(picker.getValue());
				dateLabel.setText(date);
				
				kanbanService.contact("wicked", callback);
			}
		});

		createFormPanel.add(getDateBtn);
		createFormPanel.add(dateLabel);
		createFormPanel.add(picker);
	}

}
