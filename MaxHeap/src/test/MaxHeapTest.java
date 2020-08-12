package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MaxHeap;

public class MaxHeapTest {

	private MaxHeap<Integer> heap;
	
	@BeforeEach
	public void setUp() throws Exception {
		heap = new MaxHeap<Integer>(10);
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
	
	@Test
	public void testIterator() {		
        // heap structure
        //               15
        //         12          11
        //      8      9     7		
		heap.insert(15);		
        heap.insert(12);
        heap.insert(11);
        heap.insert(8);
        heap.insert(9);
        heap.insert(7);

		var expected = new int[] {15,12,11,8,9,7};

        var ptr = 0;
		for (var value : heap) {			
			assertEquals(expected[ptr++], value);
		}
		
		assertEquals(6, ptr);
	}
}
