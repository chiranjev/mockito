package com.chiranjev.mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void testMock() {
		List arrayListMock = mock(ArrayList.class); // when we use List mock, there won't be any logic used from the real List class
		assertEquals(0,arrayListMock.size()); // mock returns default value
		when(arrayListMock.size()).thenReturn(5);
		assertEquals(5,arrayListMock.size());
		arrayListMock.add("test"); //
		assertEquals(5,arrayListMock.size());
	}

	@Test
	public void testSpy() {
		List arrayListSpy = spy(ArrayList.class); // when we use spy here, it is like creating a real arrayList and almost same as new ArrayList() but you can stub certain methods
		assertEquals(0,arrayListSpy.size()); // mock returns default value
		arrayListSpy.add("test"); //
		assertEquals(1,arrayListSpy.size());
		when(arrayListSpy.size()).thenReturn(5); // only the size() method is overriden, rest of the methods of arrayListSpy will work like an actual ArrayList
		arrayListSpy.remove("test"); //
		assertEquals(5,arrayListSpy.size());
		verify(arrayListSpy).add("test"); // spy enables you to watch real action as well as change behavior when needed
		verify(arrayListSpy, never()).clear();
	}

}
