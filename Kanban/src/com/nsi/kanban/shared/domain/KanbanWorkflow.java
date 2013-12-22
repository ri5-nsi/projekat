package com.nsi.kanban.shared.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kanban_workflow")
public class KanbanWorkflow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column
	private String name;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private Integer kanbanLimit;
	
	@OneToMany
	private List<KanbanCard> cards = null;

	public List<KanbanCard> getCards() {
		return cards;
	}

	public void addCard(KanbanCard card) {
		if( cards == null)
			cards = new ArrayList<>();
		cards.add(card);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLimit() {
		return kanbanLimit;
	}

	public void setLimit(Integer limit) {
		this.kanbanLimit = limit;
	}
	
	public Long getId() {
		return id;
	}
}
