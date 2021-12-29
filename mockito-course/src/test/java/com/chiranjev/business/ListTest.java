package com.chiranjev.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void testmockListSize() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2,listMock.size());
	}

	@Test
	public void testmockListSize_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		// first time listMock.size() will return 2, next time it will return 3
		assertEquals(2,listMock.size());
		assertEquals(3,listMock.size());
	}


	@Test
	public void testmockListGet() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("chiranjev");
		assertEquals("chiranjev",listMock.get(0));
		assertEquals(null,listMock.get(1)); // when return value isn't specified, it returns default
	}

	@Test
	public void testmockListGet_withArgumentMatcher() {
		List listMock = mock(List.class);
		// Argument Matcher
		// anyInt() -> when listMock is called with any int value, return "chiranjev"
		when(listMock.get(anyInt())).thenReturn("chiranjev");
		assertEquals("chiranjev",listMock.get(0));
		assertEquals("chiranjev",listMock.get(1));
	}

	@Test(expected=RuntimeException.class)
	public void testmockListGet_throwAnException() {
		List listMock = mock(List.class);
		// Argument Matcher
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something is wrong..."));
		listMock.get(0);
	}
	

	@Test
	public void testmockListGet_withArgumentMatcherBDD() {
		
		// Given
		List<String> listMock = mock(List.class);
		given(listMock.get(anyInt())).willReturn("chiranjev");
		
		// When
		String firstElement = listMock.get(0);
		String secondElement = listMock.get(1);
		
		// Then
		assertThat(firstElement,is("chiranjev"));
		assertThat(secondElement,is("chiranjev"));
	}


}
