package com.chiranjev.business;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.chiranjev.data.api.TodoService;
import com.chiranjev.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		List<String> expectedTodos = Arrays.asList(
				"Learn Spring MVC",
				"Learn Spring");
		assertEquals(2,filteredTodos.size());
		assertArrayEquals(expectedTodos.toArray(),
				filteredTodos.toArray());
	}

}
