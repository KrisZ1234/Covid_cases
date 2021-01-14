import java.util.ArrayList;

// Java program for implementation of Heap Sort
public class HeapSort {
	
	ArrayList<City> cities = new ArrayList<City>();
    public void sort(ArrayList<City> cities)
    {
    	
        int n = cities.size();
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(cities, n, i);
 
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            City temp = cities.get(0);
            cities.set(0, cities.get(i));
            cities.set(i, temp);
 
            // call max heapify on the reduced heap
            heapify(cities, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(ArrayList<City> cities, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && cities.get(l).compareTo(cities.get(largest)) > 0)
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && cities.get(r).compareTo(cities.get(largest)) > 0)
            largest = r;
 
        // If largest is not root
        if (largest != i) {
            City swap = cities.get(i); 
            cities.set(i, cities.get(largest));
            cities.set(largest, swap);
 
            // Recursively heapify the affected sub-tree
            heapify(cities, n, largest);
        }
    }
 
    /* A utility function to print array of size n */
    static void printArray(ArrayList<City> cities)
    {
        int n = cities.size();
        for (int i = 0; i < n; ++i)
            System.out.println(cities.get(i).getName() + " ");
        //System.out.println();
    }
 
  /*  // Driver code
    public static void main(String args[])
    {
        int arr[] = { 12, 11, 13, 5, 6, 7 };
        int n = arr.length;
 
        HeapSort ob = new HeapSort();
        ob.sort(arr);
 
        System.out.println("Sorted array is");
        printArray(arr);
    }*/
}
