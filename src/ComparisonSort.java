import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

/**
 * This class implements six different comparison sorts (and an optional
 * seventh sort for extra credit):
 * <ul>
 * <li>selection sort</li>
 * <li>insertion sort</li>
 * <li>merge sort</li>
 * <li>quick sort</li>
 * <li>heap sort</li>
 * <li>selection2 sort</li>
 * <li>(extra credit) insertion2 sort</li>
 * </ul>
 * It also has a method that runs all the sorts on the same input array and
 * prints out statistics.
 */

public class ComparisonSort {

    /**
     * Sorts the given array using the selection sort algorithm. You may use
     * either the algorithm discussed in the on-line reading or the algorithm
     * discussed in lecture (which does fewer data moves than the one from the
     * on-line reading). Note: after this method finishes the array is in sorted
     * order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
	 public static int moves = 0; //keep track of moves;
	
    public static <E extends Comparable<E>> void selectionSort(E[] A) {

   	 SortObject.resetCompares(); //reset count of compares
   	 long startTime = System.currentTimeMillis(); //current time
   	 moves = 0; //reset count of moves;
   	 
   	 int minIndex;
   	 E min; 
   	 E temp;
        for (int i = 0; i < A.length - 1; i++) {
      	  minIndex = i;
      	  min = A[i];
      	  moves++;
      	  for (int k = i + 1; k < A.length; k++) {
      		  if (A[k].compareTo(A[minIndex]) < 0) {      			  
      			  minIndex = k;
      		  }
      	  }
      	  
      	  A[i] = A[minIndex];
      	  A[minIndex] = min;
      	  moves++;
      	  moves++;
        }
        
        long endTime = System.currentTimeMillis(); //ending time
        long timelapsed = endTime - startTime; //duration of sorting
        printStatistics("selection", SortObject.getCompares(), moves, timelapsed);
    }

    /**
     * Sorts the given array using the insertion sort algorithm. Note: after
     * this method finishes the array is in sorted order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void insertionSort(E[] A) {
   	 
   	 SortObject.resetCompares();//reset count of compares
   	 long startTime = System.currentTimeMillis();//beginning time
   	 moves = 0;//reset count of moves
   	 
        for (int i = 1; i < A.length; i++) {
      	  int k = i - 1; 
      	  E temp = A[i];
      	  moves++;
      	  while((k >= 0) && (A[k].compareTo(temp) > 0)) {
      		  A[k + 1] = A[k];
      		  k--;
      		  moves++;
      	  }
      	  A[k + 1] = temp;
      	  moves++;
        }
        
        long endTime = System.currentTimeMillis();
        long timelapsed = endTime - startTime;
        if(A.length > 1000) {
      	  printStatistics("insertion", SortObject.getCompares(), moves, timelapsed);
        }
    }

    /**
     * Sorts the given array using the merge sort algorithm. Note: after this
     * method finishes the array is in sorted order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void mergeSort(E[] A) {
   	 
   	  SortObject.resetCompares();//reset count of compares
   	  long startTime = System.currentTimeMillis();//beginning time
   	  moves = 0;//reset count of moves
   	 
	     mergeSortAux(A, 0, A.length - 1);   
	     
	     long endTime = System.currentTimeMillis();//ending time
	     long timelapsed = endTime - startTime;//duration of sorting 
	     printStatistics("merge", SortObject.getCompares(), moves, timelapsed);
        
    }
    
    /**
     * An auxiliary method to do merge sort. 
     * @param A: array to sort
     * @param low: beginning index
     * @param high: ending index
     */
    private static <E extends Comparable<E>>  void mergeSortAux(E[] A, int low, int high) {
   	 
   	 if(low == high) {
   		 return ;
   	 }
   	 
   	 int mid = (low + high) / 2;
   	 
   	 mergeSortAux(A, low, mid );
   	 mergeSortAux(A, mid + 1, high);
   	 
   	 E[] temp = (E[]) (new Comparable[high - low + 1]);
   	 int left = low;
   	 int right = mid + 1;
   	 int pos = 0;
   	 while((left <= mid) && (right <= high)) {
   		 if(A[left].compareTo(A[right]) <= 0) {
   			 temp[pos] = A[left];
   			 left++;
   			 pos++;
   			 moves++;
   		 } else {
   			 temp[pos] = A[right];
   			 right++;
   			 pos++;
   			 moves++;
   		 }
   	 }
   	 
   	 while(left <= mid) {
   		 temp[pos] = A[left];
   		 pos++;
   		 left++;
   		 moves++;
   	 }
   	 while(right <= high) {
   		 temp[pos] = A[right];
   		 pos++;
   		 right++;
   		 moves++;
   	 }
   	 
   	 System.arraycopy(temp, 0, A, low, temp.length);
   	 moves += temp.length;
   	 return ;
    }

    /**
     * Sorts the given array using the quick sort algorithm, using the median of
     * the first, last, and middle values in each segment of the array as the
     * pivot value. Note: after this method finishes the array is in sorted
     * order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A   the array to sort
     */
    public static <E extends Comparable<E>> void quickSort(E[] A) {
 
		 SortObject.resetCompares(); //reset count of compares
		 long startTime = System.currentTimeMillis();//beginning time
		 moves = 0;//reset count of moves
		 
   	 quickSortAux(A, 0, A.length - 1);
   	 
   	 long endTime = System.currentTimeMillis();//ending time
       long timelapsed = endTime - startTime;//duration of sorting
  
       printStatistics("quick", SortObject.getCompares(), moves, timelapsed);
 
    }
    
    /**
     * An auxiliary method to do quick sort. 
     * @param A: array to sort
     * @param low: beginning index
     * @param high: ending index
     * @return index where to divide array into two subarrays.
     */
    private static <E extends Comparable<E>> int partition(E[] A, int low, int high) {
   	 
   	 E pivot = medianOfThree(A, low, high); 
   	 
   	 int left = low + 1;
   	 int right = high - 2;
   	 while(left <= right) {
   		 while(A[left].compareTo(pivot) < 0) {
   			 left++;
   		 }
   		 while(A[right].compareTo(pivot) > 0) {
   			 right--;
   		 }
   		 if(left<=right) {
   			 swap(A, left, right);
   			 
   			 left++;
   			 right--;
   		 }
   	 }
   	 swap(A, right + 1, high - 1);
   	 
   	 return right; 
    }
    
    /**
     * An auxiliary method to do quick sort. 
     * @param A: array to sort
     * @param low: beginning index
     * @param high: ending index
     */
    private static <E extends Comparable<E>> void quickSortAux(E[] A, int low, int high) {
 
   	 if(high - low < 4) {
   		 insertionSort(A, low, high);
   		 return;
   	 } else {		 
   		 int right = partition(A, low, high);
   		 quickSortAux(A, low, right );
   		 quickSortAux(A, right + 2, high);
   		 
   	 }
   	 
    }
    
    /**
     * A helper method to do an insertion sort for a subarray. 
     * @param A: array that contains a subarray to sort
     * @param low: beginning index of subarray
     * @param high: ending index of subarray
     */
    private static <E extends Comparable<E>> void insertionSort(E[] A, int low, int high) {
   	 for (int i = low + 1; i < high - low + 1; i++) {
     	  int k = i - 1; 
     	  E temp = A[i];
     	  while((k >= low) && (A[k].compareTo(temp) > 0)) {
     		  A[k + 1] = A[k];
     		  k--;
     		  moves++;
     	  }
     	  A[k + 1] = temp;
     	  moves++;
       }
    }
    
    /**
     * A helper method to calculate the median value of three items
     * @param A: an array to sort
     * @param low: index of an item
     * @param high: index of an item
     * @return median of three items at index low, high, and middle (of low and high)
     */
    private static <E extends Comparable<E>> E medianOfThree(E[]A, int low, int high) {
   	 int median = (low + high) / 2; //item at the middle of low and high
   	 if(A[low].compareTo(A[high]) > 0) {
   		 swap(A, low, high);
   		 
   	 }
   	 if(A[median].compareTo(A[low]) < 0) {
   		 swap(A, low, median);
   		 
   	 }
   	 if(A[median].compareTo(A[high]) > 0) {
   		 swap(A, median, high);
   		 
   	 }
   	 
   	 return A[median];
    }


    /**
     * Sorts the given array using the heap sort algorithm outlined below. Note:
     * after this method finishes the array is in sorted order.
     * <p>
     * The heap sort algorithm is:
     * </p>
     * 
     * <pre>
     * for each i from 1 to the end of the array
     *     insert A[i] into the heap (contained in A[0]...A[i-1])
     *     
     * for each i from the end of the array up to 1
     *     remove the max element from the heap and put it in A[i]
     * </pre>
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void heapSort(E[] A) {
   	 
   	  SortObject.resetCompares();//reset count of compares
		  long startTime = System.currentTimeMillis();//beginning time
		  moves = 0;//reset moves
		 
		  //instantiate a queue; give an explicit number to minimize resizing
        MyPriorityQueue<E> temp = new MyPriorityQueue<>(5000);
        for(int i = 0; i < A.length; i++) {
      	  temp.insert(A[i]);
      	  
        }
        for(int k = A.length - 1; k >= 0; k--) {
      	  A[k] = temp.removeMax();
      	  
        }
        long endTime = System.currentTimeMillis();
        long timelapsed = endTime - startTime;

        
        printStatistics("heap", SortObject.getCompares(), moves, timelapsed);
        
    }

    /**
     * Sorts the given array using the selection2 sort algorithm outlined
     * below. Note: after this method finishes the array is in sorted order.
     * <p>
     * The selection2 sort is a bi-directional selection sort that sorts
     * the array from the two ends towards the center. The selection2 sort
     * algorithm is:
     * </p>
     * 
     * <pre>
     * begin = 0, end = A.length-1
     * 
     * // At the beginning of every iteration of this loop, we know that the 
     * // elements in A are in their final sorted positions from A[0] to A[begin-1]
     * // and from A[end+1] to the end of A.  That means that A[begin] to A[end] are
     * // still to be sorted.
     * do
     *     use the MinMax algorithm (described below) to find the minimum and maximum 
     *     values between A[begin] and A[end]
     *     
     *     swap the maximum value and A[end]
     *     swap the minimum value and A[begin]
     *     
     *     ++begin, --end
     * until the middle of the array is reached
     * </pre>
     * <p>
     * The MinMax algorithm allows you to find the minimum and maximum of N
     * elements in 3N/2 comparisons (instead of 2N comparisons). The way to do
     * this is to keep the current min and max; then
     * </p>
     * <ul>
     * <li>take two more elements and compare them against each other</li>
     * <li>compare the current max and the larger of the two elements</li>
     * <li>compare the current min and the smaller of the two elements</li>
     * </ul>
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void selection2Sort(E[] A) {
   	 
   	 SortObject.resetCompares();
		 long startTime = System.currentTimeMillis();
		 moves = 0;
		 
   	 int begin = 0;
   	 int end = A.length - 1;
   	 int minIndex;
   	 int maxIndex;
   	 int left;
   	 int right;
   	 E smallerItem;
   	 E largerItem;
   	 while(begin < end) {
   		 //ranker begin and end items in order
   		 //A[begin] is always smaller than A[end]
   		 if(A[begin].compareTo(A[end]) > 0) {
   			 swap(A, begin, end);
   		 } 
   		 smallerItem = A[begin];
   		 largerItem = A[end];
   		 minIndex = begin;
			 maxIndex = end;
   		 left = begin + 1;
   		 right = end - 1;
  		 
   		 while(left <= right) {
   	 
   			 if(A[left].compareTo(A[right]) <= 0) { //left is smaller
   				
   	   		 if(A[left].compareTo(A[minIndex]) < 0) {
   	   			 minIndex = left;
   	   		 }
   	   		 if(A[right].compareTo(A[maxIndex]) > 0) {
   	   			 maxIndex = right;
 
   	   		 }
   				 
   			 } else {//right is smaller
   				 if(A[right].compareTo(A[minIndex]) < 0) {
   					 minIndex = right;
   				 } 
   				 if(A[left].compareTo(A[maxIndex]) > 0) {
   					 maxIndex = left;
   				 } 
   			 } 			 
   			 left++;
				 right--;
   		 }	
			 A[begin] = A[minIndex];
   		 A[end] = A[maxIndex];
   		 A[minIndex] = smallerItem;
   		 A[maxIndex] = largerItem;
   		 begin++;
   		 end--;
   		 moves += 4;
   	 }
       long endTime = System.currentTimeMillis();
       long timelapsed = endTime - startTime;

       
       printStatistics("selection2", SortObject.getCompares(), moves, timelapsed);
    }
    


    
    /**
     * <b>Extra Credit:</b> Sorts the given array using the insertion2 sort 
     * algorithm outlined below.  Note: after this method finishes the array 
     * is in sorted order.
     * <p>
     * The insertion2 sort is a bi-directional insertion sort that sorts the 
     * array from the center out towards the ends.  The insertion2 sort 
     * algorithm is:
     * </p>
     * <pre>
     * precondition: A has an even length
     * left = element immediately to the left of the center of A
     * right = element immediately to the right of the center of A
     * if A[left] > A[right]
     *     swap A[left] and A[right]
     * left--, right++ 
     *  
     * // At the beginning of every iteration of this loop, we know that the elements
     * // in A from A[left+1] to A[right-1] are in relative sorted order.
     * do
     *     if (A[left] > A[right])
     *         swap A[left] and A[right]
     *  
     *     starting with with A[right] and moving to the left, use insertion sort 
     *     algorithm to insert the element at A[right] into the correct location 
     *     between A[left+1] and A[right-1]
     *     
     *     starting with A[left] and moving to the right, use the insertion sort 
     *     algorithm to insert the element at A[left] into the correct location 
     *     between A[left+1] and A[right-1]
     *  
     *     left--, right++
     * until left has gone off the left edge of A and right has gone off the right 
     *       edge of A
     * </pre>
     * <p>
     * This sorting algorithm described above only works on arrays of even 
     * length.  If the array passed in as a parameter is not even, the method 
     * throws an IllegalArgumentException
     * </p>
     *
     * @param  A the array to sort
     * @throws IllegalArgumentException if the length or A is not even
     */    
    public static <E extends Comparable<E>> void insertion2Sort(E[] A) { 
        // TODO: implement this sorting algorithm 
    }

    /**
     * Internal helper for printing rows of the output table.
     * 
     * @param sort          name of the sorting algorithm
     * @param compares      number of comparisons performed during sort
     * @param moves         number of data moves performed during sort
     * @param milliseconds  time taken to sort, in milliseconds
     */
    private static void printStatistics(String sort, int compares, int moves,
                                        long milliseconds) {
        System.out.format("%-23s%,15d%,15d%,15d\n", sort, compares, moves, 
                          milliseconds);
    }

    /**
     * Sorts the given array using the six (seven with the extra credit)
     * different sorting algorithms and prints out statistics. The sorts 
     * performed are:
     * <ul>
     * <li>selection sort</li>
     * <li>insertion sort</li>
     * <li>merge sort</li>
     * <li>quick sort</li>
     * <li>heap sort</li>
     * <li>selection2 sort</li>
     * <li>(extra credit) insertion2 sort</li>
     * </ul>
     * <p>
     * The statistics displayed for each sort are: number of comparisons, 
     * number of data moves, and time (in milliseconds).
     * </p>
     * <p>
     * Note: each sort is given the same array (i.e., in the original order) 
     * and the input array A is not changed by this method.
     * </p>
     * 
     * @param A  the array to sort
     */
    static public void runAllSorts(SortObject[] A) {
        System.out.format("%-23s%15s%15s%15s\n", "algorithm", "data compares", 
                          "data moves", "milliseconds");
        System.out.format("%-23s%15s%15s%15s\n", "---------", "-------------", 
                          "----------", "------------");
        

        //copy the unsorted array so that identifical array can be sorted with
        //different algorithms. 
        SortObject[][] temp = new SortObject[7][A.length];
        for (int i = 0; i < 7; i++) {
      	  System.arraycopy(A, 0, temp[i], 0, A.length);
        }
        //test various sorting algorithms
        selectionSort(temp[0]);
        insertionSort(temp[1]);
        mergeSort(temp[2]);
        quickSort(temp[3]);
        heapSort(temp[4]);
        selection2Sort(temp[5]);
    }
    

    /**
     * A helper method to swap items at two indexes
     * @param A: array of items
     * @param left: an index of two items to swap
     * @param right: the other index of two items to swap
     */
    static private <E extends Comparable<E>> void swap(E[] A, int left, int right) {
   	 E temp = A[left];
   	 A[left] = A[right];
   	 A[right] = temp;
   	 moves+=3;
    }

}
