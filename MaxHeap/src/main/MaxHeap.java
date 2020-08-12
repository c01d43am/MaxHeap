package main;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This file contains an implementation of a max-heap. A max-heap is a complete binary
 * tree (that is, totally filled other than the rightmost elements on the last 
 * level) where the value in each node is greater than or equal to the values in the children of that node.
 * The root, therefore, has the maximum value in the tree.
 * @author Jalal Choker, jalal.choker@gmail.com
 */
public class MaxHeap<T extends Comparable<T>> implements Iterable<T>
{
	private final ArrayList<T> heap;
	private int index; // points to the last entry in the heap
	private int capacity;
	
	public MaxHeap(int capacity) {
		if(capacity < 2) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		this.capacity = capacity;
		this.heap = new ArrayList<T>(capacity);
		this.index = -1;
	}
	
    public boolean isEmpty() {
    	return index < 0;
    }
    
    public int size() {
    	return  index + 1;
    }
    
    // inserts a value at the end of the list and bubbles up if necessary; T = O(logN)
    public void insert(T value) {
    	
    	if(value == null) throw new IllegalArgumentException("Value cannot be null");
    	if(this.size() == this.capacity) throw new IllegalStateException("Heap is full");

    	heap.add(value);
    	index++;
    	bubbleUp(index);
    }
    
    // bubbles up a value at a specified index
    private void bubbleUp(int pos) {
    	
    	var currentIdx = pos;
    	var parentIdx = parentIndex(currentIdx);
    			
    	while(currentIdx > 0 && heap.get(parentIdx).compareTo(heap.get(currentIdx)) < 0)
    	{
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIndex(currentIdx);            
    	}
    }
    
    // returns the max value in the heap which is stored at the root and removes it; T = O(logN)
    public T extractMax() {
    	
        if (this.isEmpty()) throw new IllegalStateException("Heap is empty");

        var max = heap.get(0);
        
        heap.set(0, heap.get(index));
        heap.remove(index--);
        sinkDown(0);
        
        return max;
    }
    
    private void sinkDown(int pos) {
    	
        var greatestIdx = pos;

        var lIdx = leftChildIndex(greatestIdx);
        var rIdx = rightChildIndex(greatestIdx);
        
		if(lIdx < this.size() && heap.get(greatestIdx).compareTo(heap.get(lIdx)) < 0)		
			greatestIdx = lIdx;
		
		if(rIdx < this.size() && heap.get(greatestIdx).compareTo(heap.get(rIdx)) < 0)		
			greatestIdx = rIdx;
		
		if(greatestIdx != pos)
		{
            swap(pos, greatestIdx);
            sinkDown(greatestIdx);
		}
    }
    
    // returns the max value in the heap which is stored in the root without removing it; T = O(1)
    public T getMax() {
        if (this.isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap.get(0);
    }
    
    private void swap(int pos1, int pos2)
    {
        var tmp = heap.get(pos1);
        heap.set( pos1,heap.get(pos2));
        heap.set( pos2,tmp);
    }
    
    private static int parentIndex(int child) {
    	return (child - 1) / 2;
    }
    
    private static int leftChildIndex(int parent) {
    	return (2 * parent) + 1;
    }
    
    private static int rightChildIndex(int parent) {
    	return (2 * parent) + 2;
    }

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private int ptr = 0;	
			
			@Override
			public boolean hasNext() {
				return size() > 0 && this.ptr <= index ;
			}
		
			@Override
			public T next() {
				return heap.get(ptr++);
			}
			
			@Override 
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};		
	}
}