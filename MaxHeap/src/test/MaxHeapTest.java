package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MaxHeap;

public class MaxHeapTest {

	private MaxHeap<Integer> heap;
	
	@BeforeEach
	public void setUp() throws Exception {
		heap = new MaxHeap<Integer>();
	}

	@Test
	public void testEmptyHeap() {		
		assertTrue(heap.isEmpty());
		assertEquals(0, heap.size());
	}
	
	@Test
	public void testInsert() {
        heap.insert(13);
		assertEquals(1, heap.size());
        heap.insert(15);
		assertEquals(2, heap.size());
	}	

	@Test
	public void testGetMaxOnEmpty() {		
		assertThrows(IllegalStateException.class, () -> heap.getMax());
	}
	
	@Test
	public void testGetMax() {
        heap.insert(13);
		assertEquals(1, heap.size());
        heap.insert(15); // max
		assertEquals(2, heap.size());
        heap.insert(14);
		assertEquals(3, heap.size());
		
		assertEquals(15, heap.getMax());
		assertEquals(3, heap.size());
	}
	
	@Test
	public void testExtractMaxOnEmpty() {		
		assertThrows(IllegalStateException.class, () -> heap.extractMax());
	}
	
	@Test
	public void testExtractMax() {
        heap.insert(14);
        heap.insert(15);
        heap.insert(13);
		assertEquals(3, heap.size());

		assertEquals(15, heap.extractMax());
		assertEquals(2, heap.size());
		
		assertEquals(14, heap.extractMax());
		assertEquals(1, heap.size());        
	}
}
