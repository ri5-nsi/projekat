package com.nsi.kanban.shared.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "kanban_board")
public class KanbanBoard {
	
	@Column
	private Long projectid;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@OneToMany
	private List<KanbanWorkflow> workflows = null;
	
	public Long getProjectid() {
		return projectid;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<KanbanWorkflow> getWorkflows() {
		return workflows;
	}

	public void addWorkflow(KanbanWorkflow workflow) {
		if( workflows == null )
			workflows = new ArrayList<>();
		workflows.add(workflow);
	}
	
	public void addWorkflows(KanbanWorkflow[] workflows){
		for(KanbanWorkflow workflow : workflows)
			addWorkflow(workflow);
	}
}
