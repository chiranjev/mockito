package com.chiranjev.business;

import java.util.ArrayList;
import java.util.List;

import com.chiranjev.data.api.TodoService;

// TodoBusinessImpl - SUT
// TodoService - Dependency

public class TodoBusinessImpl {
	private TodoService todoService;
	// Constructor enables us to test TodoBusinessImpl 
	// with any implementation of TodoService
	public TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}
	
	public List<String> retrieveTodosRelatedToSpring(String user){
		
		List<String> filteredTodos = new ArrayList<String>();
		
		List<String> todos = todoService.retrieveTodos(user);
		for(String todo:todos) {
			if(todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		
		return filteredTodos;
	}	
	public void deleteTodosNotRelatedToSpring(String user){
				
		List<String> todos = todoService.retrieveTodos(user);
		for(String todo:todos) {
			if(!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}		
	}
	
}
