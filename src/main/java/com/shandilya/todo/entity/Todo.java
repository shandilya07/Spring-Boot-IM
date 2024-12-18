package com.shandilya.todo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Todo {
	private final UUID id;
	private String title;
	private boolean completed;

	public Todo(String title, boolean completed) {
		this.id = UUID.randomUUID();
		this.title = title;
		this.completed = completed;
	}
}
