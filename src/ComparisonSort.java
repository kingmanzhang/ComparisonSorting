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
    public static <E extends Comparable<E>> void selectionSort(E[] A) {
//System.out.println(Arrays.toString(A));
   	 SortObject.resetCompares();
   	 long startTime = System.currentTimeMillis();
   	 int moves = 0;
        for (int i = 0; i < A.length - 1; i++) {
      	  for (int k = i + 1; k < A.length; k++) {
      		  if (A[i].compareTo(A[k]) > 0) {
      			  swap(A, i, k);
      			  moves++;
      			  moves++;
      			  moves++;
      		  }
      	  }
        }
        long endTime = System.currentTimeMillis();
        long timelapsed = endTime - startTime;
//System.out.println(Arrays.toString(A));
        
        print("selection", SortObject.getCompares(), moves, timelapsed);
    }

    /**
     * Sorts the given array using the insertion sort algorithm. Note: after
     * this method finishes the array is in sorted order.
     * 
     * @param <E>  the type of values to be sorted
     * @param A    the array to sort
     */
    public static <E extends Comparable<E>> void insertionSort(E[] A) {
//System.out.println(Arrays.toString(A));
   	 SortObject.resetCompares();
   	 long startTime = System.currentTimeMillis();
   	 int moves = 0;
   	 
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
//System.out.println(Arrays.toString(A));
        if(A.length > 1000) {
        print("insertion", SortObject.getCompares(), moves, timelapsed);
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
   	 
   	 SortObject.resetCompares();
   	 long startTime = System.currentTimeMillis();
   	 int moves = 0;
   	 
//System.out.println(Arrays.toString(A));
        moves = mergeSortAux(A, 0, A.length - 1, moves);
//System.out.println(Arrays.toString(A));
        
        long endTime = System.currentTimeMillis();
        long timelapsed = endTime - startTime;

        
        print("merge", SortObject.getCompares(), moves, timelapsed);
        
    }
    
    //why void methods has type parameter
    private static <E extends Comparable<E>>  int mergeSortAux(E[] A, int low, int high, int totalMoves) {
   	 int newMoves = 0;
   	 if(low == high) {
   		 return totalMoves;
   	 }
   	 
   	 int mid = (low + high) / 2;
   	 
   	 totalMoves = mergeSortAux(A, low, mid, totalMoves);
   	 totalMoves = mergeSortAux(A, mid + 1, high, totalMoves);
   	 
   	 E[] temp = (E[]) (new Comparable[high - low + 1]);
   	 int left = low;
   	 int right = mid + 1;
   	 int pos = 0;
// System.out.println("temp size: " + temp.length + " pos: " + pos + " left: " + left + " right: " + right );
   	 while((left <= mid) && (right <= high)) {
   		 if(A[left].compareTo(A[right]) <= 0) {
   			 temp[pos] = A[left];
   			 left++;
   			 pos++;
   			 newMoves++;
   		 } else {
   			 temp[pos] = A[right];
   			 right++;
   			 pos++;
   			 newMoves++;
   		 }
   	 }
   	 
   	 while(left <= mid) {
   		 temp[pos] = A[left];
   		 pos++;
   		 left++;
   		 newMoves++;
   	 }
   	 while(right <= high) {
//System.out.println("temp size: " + temp.length + " pos: " + pos + " left: " + left + " right: " + right );
   		 temp[pos] = A[right];
   		 pos++;
   		 right++;
   		 newMoves++;
   	 }
   	 
   	 System.arraycopy(temp, 0, A, low, temp.length);
   	 newMoves = newMoves + temp.length;
   	 return totalMoves + newMoves;
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
 //System.out.println(Arrays.toString(A));
		 SortObject.resetCompares();
		 long startTime = System.currentTimeMillis();
		 int moves = 0;
   	 moves = quickSortAux(A, 0, A.length - 1, 0);
   	 long endTime = System.currentTimeMillis();
       long timelapsed = endTime - startTime;

       
       print("quicksort", SortObject.getCompares(), moves, timelapsed);
 //System.out.println(Arrays.toString(A));
    }
    
    private static <E extends Comparable<E>> int[] partition(E[] A, int low, int high) {
   	 int moves = 0;
   	 E pivot = medianOfThree(A, low, high); //how to get moves?
   	 moves++;//rough estimate, 1 assignment
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
   			 moves = moves + 3;
   			 left++;
   			 right--;
   		 }
   	 }
   	 swap(A, right + 1, high - 1);
   	 moves = moves + 3;
   	 return new int[] {right, moves}; 
    }
    
    private static <E extends Comparable<E>> int quickSortAux(E[] A, int low, int high, int totalMoves) {
 //System.out.println("A size: " + A.length + " low: " + low + " high: " + high);
   	 if(high - low < 4) {
   		 insertionSort(A, low, high);
 //System.out.println("called, A size: " + A.length);
   		 return totalMoves;
   	 } else {
   		 int [] temp = partition(A, low, high);
   		 int right = temp[0];
   		 int newMoves = temp[1];
   		 totalMoves = quickSortAux(A, low, right, totalMoves);
   		 totalMoves = quickSortAux(A, right + 2, high, totalMoves);
   		 return totalMoves + newMoves;
   	 }
   	 
    }
    
    private static <E extends Comparable<E>> void insertionSort(E[] A, int low, int high) {
   	 for (int i = low + 1; i < high - low + 1; i++) {
     	  int k = i - 1; 
     	  E temp = A[i];
     	  while((k >= low) && (A[k].compareTo(temp) > 0)) {
     		  A[k + 1] = A[k];
     		  k--;
     		  
     	  }
     	  A[k + 1] = temp;
     	  
       }
    }
    
    private static <E extends Comparable<E>> E medianOfThree(E[]A, int low, int high) {
   	 int median = (low + high) / 2;
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
        PriorityQueue<E> temp = new PriorityQueue<>();
        for(int i = 0; i < A.length; i++) {
      	  temp.add(A[i]);
        }
        for(int k = 0; k < temp.size(); k++) {
      	  A[k] = temp.remove();
        }
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
   	 int begin = 0;
   	 int end = A.length - 1;
       while(begin <= end) {
      	 if(A[begin].compareTo(A[end]) > 0) {
      		 swap(A, begin, end);
      	 }
      	 int left = begin + 1;
      	 int right = end - 1;
      	 while(left <= right) {
	      	 if(A[left].compareTo(A[right]) >= 0) {
	      		 if(A[left].compareTo(A[end]) > 0) {
	      			 swap(A, left, end);
	      		 }
	      		 if(A[right].compareTo(A[begin]) < 0) {
	      			 swap(A, begin, right);
	      		 } 
	      	 } else {
	      		 if(A[right].compareTo(A[end]) > 0) {
	      			 swap(A, right, end);
	      		 }
	      		 if(A[left].compareTo(A[begin]) < 0) {
	      			 swap(A, begin, left);
	      		 } 
	      	 }
	      	 left++;
	      	 right--;
      	 }
      	 begin++;
      	 end--;
      }
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
        

        SortObject[][] temp = new SortObject[7][A.length];
        for (int i = 0; i < 7; i++) {
      	  System.arraycopy(A, 0, temp[i], 0, A.length);
        }
        selectionSort(temp[0]);
        insertionSort(temp[1]);
        mergeSort(temp[2]);
        quickSort(temp[3]);
    }
    
    static private void swap(Comparable A, Comparable B) {
   	 Comparable temp; 
   	 temp = A;
   	 A = B;
   	 B = temp;
    }
    static private <E extends Comparable<E>> void swap(E[] A, int left, int right) {
   	 E temp = A[left];
   	 A[left] = A[right];
   	 A[right] = temp;
    }
    
    static private void print(String str, int compares, int moves, long timelapsed ) {
   	 
   	 String comparesToString = NumberFormat.getNumberInstance(Locale.US).format(compares);
   	 String movesToString = NumberFormat.getNumberInstance(Locale.US).format(moves);
   	 String timelapsedToString = NumberFormat.getNumberInstance(Locale.US).format(timelapsed);
   	 System.out.format("%-23s%15s%15s%15s\n", str, comparesToString, 
   			 movesToString, timelapsedToString);
    }
}
