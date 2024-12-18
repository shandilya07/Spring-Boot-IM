package com.shandilya.todo.service;

import com.shandilya.todo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
	private List<Todo> todos = new ArrayList<>();

	public List<Todo> findAll() {
		return todos;
	}

	public Todo addToDo(final String title) {
		Todo todo = new Todo(title, false);
		todos.add(todo);
		return todo;
	}

	public Todo updateTodo(final String uuid, final String title, final boolean completed) {
		// Optional<Todo> todo = todos.stream().filter(t -> t.getId().toString().equals(uuid)).findFirst();
		Optional<Todo> todo = findTodoById(uuid);
		if (todo.isPresent()) {
			Todo updatedTodo = todo.get();
			updatedTodo.setTitle(title);
			updatedTodo.setCompleted(completed);
			return updatedTodo;
		}
		return null;
	}

	public boolean deleteTodo(final String uuid) {
		Optional<Todo> todo = findTodoById(uuid);
		if (todo.isPresent()) {
			todos.remove(todo.get());
			return true;
		}
		return false;
	}

	private Optional<Todo> findTodoById(final String uuid) {
		return todos
			.stream()
			.filter(t -> t.getId().toString().equals(uuid))
			.findFirst();
	}
}
