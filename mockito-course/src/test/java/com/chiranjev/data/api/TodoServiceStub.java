package com.chiranjev.data.api;

import java.util.Arrays;
import java.util.List;

// Stub is a class which returns some kind of dummy data.
// Stub is only used for Unit Testing and is a hardcoded class.

/*
 Problems with Stub - 
 1. Testing different scenarios.
 2. On addition of new method to interface, stub will have to implement it as well.
 3. Whenever there is a change in TodoService, TodoServiceStub will have to change as well.
 */
public class TodoServiceStub implements TodoService{

	public List<String> retrieveTodos(String user) {
		return Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring",
				"Learn to Dance");
	}

}
