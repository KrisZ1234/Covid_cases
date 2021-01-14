public class ReversePQ {
    /**
     * Heap based implementation of PriorityQueue
     * To implement it you need to implement the following helper functions as well
     * swim, sing, swap, grow
     */

    private City[] heap; // the heap to store data in
    private int size; // current size of the queue
    private static final int capacity = 8; // default capacity

    //queue constructor
    public ReversePQ() {
        this.heap =  new City[capacity];
        this.size = 0;
    }

    //checks if queue is empty
    public boolean isEmpty() {
    	if (heap.length == 0) return true;
    	else return false;
    }
    
    //inserts new city into the queue 
    //resizes when queue is 75% filed
    public void insert(City city) {
        // Check available space
        if (size == capacity * 3/4) {
            resize();
        }
        // Place item at the next available position
        heap[++size] = city;

        // Let the newly added item swim
        swim(size);
    }

    //Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public City getMin() {
        // Ensure not empty
        if (size == 0)
            return null;

        // Reference of the root item
        City root = heap[1];

        // Replace root item with the one at rightmost leaf
        heap[1] = heap[size];
        size--;

        // Dispose the rightmost leaf
        // Sink the new root element
        sink(1);

        // Return the int removed
        return root;
    }
    
    //removes and returns the item with the chosen id
    public City remove(int id) {
        // Ensure not empty
        if (size == 0)
            return null;
        
        //reference of the one to be removed
    	City removed = null;
    	
    	//check all the items to match the id
    	//replace it with the rightmost leaf
    	//let it sink
    	for (int i = 1; i <= size; i++) {	 
    		if ( heap[i].getId() == id ) {
    			removed = heap[i];
    			heap[i] = heap[size];
    			size--;
    			sink(i);  			 				 
    		}//if
    	}//for
    	
    	//removing the one with the chosen id
    	return removed;
    }
    
    //check the root
    public City min() {
        // Ensure not empty
        if (size == 0) return null;
        
        return heap[1];
    }
    
    //Helper function to swim items to the top
    private void swim(int i) {
        // if i is root (i==1) return
        if (i == 1)
            return;

        // find parent
        int parent = i / 2;

        // compare parent with child i
        while (i != 1 && (heap[i].compareTo(heap[parent]) < 0)) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }

    //function to swim items to the bottom
    private void sink(int i) {
        // determine left, right child
        int left = 2 * i;
        int right = left + 1;

        // if 2*i > size, node i is a leaf return
        if (left > size)
            return;

        // while haven't reached the leafs
        while (left <= size) {
            // Determine the largest child of node i
            int max = left;
            if (right <= size) {
                if (heap[left].compareTo(heap[right]) > 0)
                    max = right;
            }

            // If the heap condition holds, stop. Else swap and go on.
            // child smaller than parent
            if (heap[i].compareTo(heap[max]) <= 0)
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }//while
    }

    //Helper function to swap two elements in the heap
    //i,j index of elements
    private void swap(int i, int j) {
        City tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    //function to grow by the size of the heap/2
    private void resize() {
        City[] newHeap = new City[heap.length + heap.length/2];

        // copy array
        System.arraycopy(heap, 1, newHeap, 1, size);

        heap = newHeap;
    }
    
    //get the queue size (number of elements in it)
    public int size() {
    	return this.size;
    }
    
    //print all the elements of the tree
    public void printPQ() {
    	for (int i = 1; i <= size; i++) {
		System.out.println( heap[i].getId() + " " + heap[i].getName() + " " + 
    	                    heap[i].getPopulation() + " " + heap[i].getCovidCases() + " " +
				            heap[i].getDensity()+'\n');
    	}//for    	
    }
    
    //returns heap
    public City[] getHeap() {
    	return this.heap;
    }
      
}//PQ
