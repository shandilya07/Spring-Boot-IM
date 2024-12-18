package com.shandilya.todo.controller;

import com.shandilya.todo.entity.Todo;
import com.shandilya.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

	private final TodoService todoService;

	@GetMapping("/hi")
	public String sayHi() {
		return "Hey there!";
	}

	@GetMapping
	public List<Todo> getAllTodos() {
		return todoService.findAll();
	}

	@PostMapping
	public ResponseEntity<Todo> createTodo(@RequestBody String title) {
		Todo todo = todoService.addToDo(title);
		return new ResponseEntity<>(todo, HttpStatus.CREATED);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String uuid, @RequestBody Todo todo) {
		Todo updated = todoService.updateTodo(uuid, todo.getTitle(), todo.isCompleted());
		if (updated != null) {
			return new ResponseEntity<>(updated, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String uuid) {
		boolean deleted = todoService.deleteTodo(uuid);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
