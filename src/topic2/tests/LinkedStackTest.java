package topic2.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import topic2.classes.LinkedStack;
import topic2.exceptions.EmptyStackException;
import topic2.interfaces.Stack;

public class LinkedStackTest {

	@Test
	public void test1() {
		Stack<Integer> stack = new LinkedStack<>();

		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());

		// 1
		stack.push(1);
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals(1, (int) stack.top());

		// 1 2
		stack.push(2);
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals(2, (int) stack.top());

		// 1 2 3
		stack.push(3);
		assertEquals(3, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals(3, (int) stack.top());

		// 1 2
		assertEquals(3, (int) stack.pop());
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals(2, (int) stack.top());

		// 1
		assertEquals(2, (int) stack.pop());
		assertEquals(1, stack.size());
		assertFalse(stack.isEmpty());
		assertEquals(1, (int) stack.top());

		//
		assertEquals(1, (int) stack.pop());
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}

	@Test
	public void test2() {
		Stack<String> stack = new LinkedStack<>();
		for (int k = 1; k <= 2; k++) {
			for (int i = 1; i <= 100; i++) {
				stack.push("" + i);
				assertEquals("" + i, stack.top());
			}
			assertEquals(100, stack.size());
			for (int i = 100; i >= 1; i--) {
				assertEquals("" + i, stack.top());
				assertEquals("" + i, stack.pop());
			}
			assertEquals(0, stack.size());
		}
	}

	@Test
	public void test3() {
		assertThrows(EmptyStackException.class, () -> {
			Stack<String> stack = new LinkedStack<>();
			stack.pop();
		});
	}

	@Test
	public void test4() {
		assertThrows(EmptyStackException.class, () -> {
			Stack<String> stack = new LinkedStack<>();
			stack.top();
		});
	}
}
