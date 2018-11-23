package topic2.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import topic2.classes.LinkedQueue;
import topic2.exceptions.EmptyQueueException;
import topic2.interfaces.Queue;

public class LinkedQueueTest {

	@Test
	public void test1() {
		Queue<Integer> queue = new LinkedQueue<>();

		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());

		// 1
		queue.enqueue(1);
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());
		assertEquals(1, (int) queue.front());

		// 2 1
		queue.enqueue(2);
		assertEquals(2, queue.size());
		assertFalse(queue.isEmpty());
		assertEquals(1, (int) queue.front());

		// 3 2 1
		queue.enqueue(3);
		assertEquals(3, queue.size());
		assertFalse(queue.isEmpty());
		assertEquals(1, (int) queue.front());

		// 3 2
		assertEquals(1, (int) queue.dequeue());
		assertEquals(2, queue.size());
		assertFalse(queue.isEmpty());
		assertEquals(2, (int) queue.front());

		// 3
		assertEquals(2, (int) queue.dequeue());
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());
		assertEquals(3, (int) queue.front());

		//
		assertEquals(3, (int) queue.dequeue());
		assertEquals(0, queue.size());
		assertTrue(queue.isEmpty());
	}

	@Test
	public void test2() {
		Queue<String> queue = new LinkedQueue<>();
		for (int k = 1; k <= 2; k++) {
			for (int i = 1; i <= 100; i++) {
				queue.enqueue("" + i);
				assertEquals("1", queue.front());
			}
			assertEquals(100, queue.size());
			for (int i = 1; i <= 100; i++) {
				assertEquals("" + i, queue.front());
				assertEquals("" + i, queue.dequeue());
			}
			assertEquals(0, queue.size());
		}
	}

	@Test
	public void test3() {
		assertThrows(EmptyQueueException.class, () -> {
			Queue<String> queue = new LinkedQueue<>();
			queue.dequeue();
		});
	}

	@Test
	public void test4() {
		assertThrows(EmptyQueueException.class, () -> {
			Queue<String> queue = new LinkedQueue<>();
			queue.front();
		});
	}

}
