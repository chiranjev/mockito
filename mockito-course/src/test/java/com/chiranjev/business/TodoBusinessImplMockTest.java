package com.chiranjev.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.chiranjev.data.api.TodoService;


/*
 Mocking is creating objects that simulate the behavior of real objects.
 Unlike stubs, mocks can be dynamically created from code - at runtime.
 Mocks offer more functionality than stubbing.
 You can verify method calls and a lot of other things.
 
 mock() is defined in Mockito class. You can mock class or an interface.
 mock() returns default values when they are not stubbed.
 when() is used to stub a method dynamically.
 thenReturn() sets a return value when the method specified in when() is called
 
 We can't combine a matcher with hardcoded value when stubbing
 
 when  - thenReturn
 given - willReturn
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
	

	@Test
	public void testRetrieveTodosRelatedToSpring_when3ItemListBDD() {
		// Given - setup

		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring",
				"Learn to Dance");
		List<String> expectedTodos = Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock); // we are mocking a dependency here

		// When - actual method call/SUT

		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		// then - asserts
		assertThat(filteredTodos.size(), is(2));
		assertArrayEquals(expectedTodos.toArray(),
				filteredTodos.toArray());
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_when3ItemListBDD() {
		// Given - setup
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring",
				"Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock); // we are mocking a dependency here

		// When - actual method call/SUT
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// then - asserts
		verify(todoServiceMock).deleteTodo("Learn to Dance");
		then(todoServiceMock).should().deleteTodo("Learn to Dance"); // same as verify(mock()) above
		
		verify(todoServiceMock,times(1)).deleteTodo("Learn to Dance");
		verify(todoServiceMock,atLeast(1)).deleteTodo("Learn to Dance");

		verify(todoServiceMock,never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC"); // same as verify(mock,never()) above

		verify(todoServiceMock,never()).deleteTodo("Learn Spring");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring"); // same as verify(mock,never()) above
		
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_when3ItemListBDD_argumentCapture() {
		
		// Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		// Given - setup
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring",
				"Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock); // we are mocking a dependency here

		// When - actual method call/SUT
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// then - asserts

		// Define Argument Captor on specific method call
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

		// Capture the argument		
		assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_when3ItemListBDD_argumentCaptureMultipleTimes() {
		
		// Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		// Given - setup
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList(
				"Learn to Rock and Roll",
				"Learn Spring",
				"Learn to Dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock); // we are mocking a dependency here

		// When - actual method call/SUT
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		// then - asserts

		// Define Argument Captor on specific method call
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

		// Capture the argument		
		assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
	}


}
