package com.chiranjev.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.chiranjev.data.api.TodoService;


/*
 Mocking is creating objects that simulate the behavior of real objects.
 Unlike stubs, mocks can be dynamically created from code - at runtime.
 Mocks offer more functionality than stubbing.
 You can verify method calls and a lot of other things.
 
 mock() is defined in Mockito class. You can mock class or an interface.
 mock() returns default values when they are not stubbed.
 */



public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_when3ItemList() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring",
				"Learn to Dance");
		// When retrieveTodos is called on todoServiceMock, return this list of values.
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock); // we are mocking a dependency here
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		List<String> expectedTodos = Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring");
		assertEquals(2,filteredTodos.size());
		assertArrayEquals(expectedTodos.toArray(),
				filteredTodos.toArray());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_whenEmptyList() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		List<String> expectedTodos = Arrays.asList();
		assertEquals(0,filteredTodos.size());
		assertArrayEquals(expectedTodos.toArray(),
				filteredTodos.toArray());
	}

}
