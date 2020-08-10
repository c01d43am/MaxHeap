package main;

import java.util.ArrayList;

/**
 * This file contains an implementation of a max-heap. A max-heap is a complete binary
 * tree (that is, totally filled other than the rightmost elements on the last 
 * level) where the value in each node is greater than or equal to the values in the children of that node. The root, therefore,
 * has the maximum value in the tree.
 * @author Jalal Choker, jalal.choker@gmail.com
 */
public class MaxHeap<T extends Comparable<T>> 
{
	private final ArrayList<T> heap;
	private int index; // points to the last entry in the heap
	
	public MaxHeap() {
		this.heap = new ArrayList<T>();
		this.index=-1;
	}
	
    public boolean isEmpty() {
    	return index < 0;
    }
    
    public int size() {
    	return  index + 1;
    }
    
    // inserts a value at the end of the list and bubbles up if necessary; T = O(logN)
    public void insert(T value) {
    	if(value == null) throw new IllegalArgumentException("value cannot be null");
    	
    	heap.add(value);
    	index++;
    	heapify();
    }
    
    // bubbles up a newly inserted item into the heap
    private void heapify() {
        if (this.size() > 1) // has > 1 element
        {
            var cIdx = index;
            var crnt = heap.get(cIdx); // the newly inserted value

            var pIdx = parentIndex(cIdx);
            var prnt = heap.get(pIdx); // its parent

            while (prnt.compareTo(crnt) < 0) // need to swap current & parent i.e bubble up
            {
                swap(cIdx, pIdx);

                if (pIdx == 0) break; // reached root

                cIdx = pIdx;
                pIdx = parentIndex(cIdx);
                prnt = heap.get(pIdx); 
            }
        }
    }
    
    // returns the max value in the heap which is stored in the root by removing it; T = O(logN)
    public T extractMax() {
        if (this.isEmpty()) throw new IllegalStateException("Heap is empty");

        var max = heap.get(0); // save max

        if (index == 0) heap.remove(index--);
        else
        {            
            heap.set(0, heap.get(index)); // overwrite root with bottom-most, right-most node
            heap.remove(index--); // remove the last entry in the heap then decrement index

            // more than 2 elements left in heap then bubble down the new value stored in the root to maintain max heap property
            if (this.size() > 1)
            {
                var pIdx = 0;
                var lIdx = leftChildIndex(pIdx);
                var rIdx = rightChildIndex(pIdx);

                var prnt = heap.get(pIdx);
                var left = getValueOrNull(lIdx);
                var right = getValueOrNull(rIdx);

                while ((lIdx <= this.index && prnt.compareTo(left) < 0) || (rIdx <= this.index && prnt.compareTo(right) < 0)) // need to bubble down parent
                {
                    if (lIdx <= this.index && prnt.compareTo(left) < 0) // swap left
                    {
                        swap(pIdx, lIdx);
                        pIdx = lIdx;
                    }
                    else // swap right
                    {
                        swap(pIdx, rIdx);
                        pIdx = rIdx;
                    }

                    lIdx = leftChildIndex(pIdx);
                    rIdx = rightChildIndex(pIdx);

                    left = getValueOrNull(lIdx);
                    right = getValueOrNull(rIdx);
                }
            }
        }

        return max;
    }

	private T getValueOrNull(int idx) {
		return idx <= this.index ? heap.get(idx) : null;
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
}