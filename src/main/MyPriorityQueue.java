package main;

//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This is an implementation of priority queue. Count of data moves is recorded.
 */

import java.util.NoSuchElementException;

public class MyPriorityQueue <E extends Comparable<E>> {
	
	private E[] itemsArray; //implement with an array
	private int numItems;//number of items
	private final int INIT_LENGTH = 100;//default size
	private int length;//defined array size
	
	/**
	 * The default constructor creates an array of default size of items. 
	 */
	public MyPriorityQueue () {
		
		itemsArray = (E[]) (new Comparable[INIT_LENGTH]);
		numItems = 0;
		
	}
	
	/**
	 * The constructor creates an array of defined length. 
	 * @param length: defined length of array
	 */
	public MyPriorityQueue (int length) {
	
		itemsArray = (E[]) (new Comparable[length]);
		numItems = 0;
		
	}
	
	/**
	 * Insert an item to the priority queue
	 * @param item: item to insert
	 */
	void insert(E item) {
		
		 numItems++;
   	 //if array if full, double the size of the array
   	 if(numItems == itemsArray.length) {
   		 itemsArray = expand(itemsArray);
   	 }
   	 //insert the new item as a leaf and then swap the item to the proper 
   	 //place
   	 itemsArray[numItems] = item;
   	 ComparisonSort.moves++;
   	 int k = numItems;
   	 while(k > 1) {
   		 if (item.compareTo(itemsArray[k / 2]) > 0) {
   			itemsArray[k] = itemsArray[k/2];
   			itemsArray[k/2] = item;
   			ComparisonSort.moves++;
   			ComparisonSort.moves++;
   		 }
   		 k = k / 2;
   	 }
	}
	
	/**
    * A helper method to expand the array
    * @param oriArray: original array
    * @return the expanded array
    */
   private E[] expand(E[] oriArray) {
  	 
  	 int largerSize = 2 * oriArray.length; //new array size
  	 itemsArray = (E[])(new Comparable[largerSize]); //new array
  	 for (int i = 1; i < oriArray.length; i++) { //copy items to new array
  		 itemsArray[i] = oriArray[i];
  		ComparisonSort.moves++;
  	 }
  	 return itemsArray;
  	 
   }
	
   /**
    * A method to determine whether the priority queue is empty. 
    * @return true if the priority queue is empty.
    */
	boolean isEmpty() {
		return numItems == 0;
	}
	
	/**
	 * A method to remove the item of max size. 
	 * @return: the item of max size. 
	 */
	E removeMax() {
		if(numItems == 0) {
  		 throw new NoSuchElementException();
  	 }
  	 
  	 E max = itemsArray[1]; //root (first item of array) is the max
  	//move the last item to the first place, then move it to the right place
  	 itemsArray[1] = itemsArray[numItems]; 
  	 int k = 1;
  	 //compare the first item with its children, swap with the larger child
  	 //if it is smaller than either of its child; continue until it is bigger
  	 //than both of its children
  	 while(2 * k < numItems) {
  		 if(itemsArray[k].compareTo(itemsArray[2*k]) < 0
  				 || itemsArray[k].compareTo(itemsArray[2*k+1]) < 0) {
  			 E largerChild;
  			 if(itemsArray[2*k].compareTo(itemsArray[2*k + 1]) < 0) {
  				 largerChild = itemsArray[2*k + 1];
  				 itemsArray[2*k + 1] = itemsArray[k];
  				 itemsArray[k] = largerChild;
  				ComparisonSort.moves++;
  				ComparisonSort.moves++;
  				ComparisonSort.moves++;
  				 k = 2 * k + 1;
  			 } else {
  				 largerChild = itemsArray[2*k];
  				 itemsArray[2*k] = itemsArray[k];
  				 itemsArray[k] = largerChild;
  				ComparisonSort.moves++;
  				ComparisonSort.moves++;
  				ComparisonSort.moves++;
  				 k = 2 * k;
  			 }
  		 } else {
  			 break;
  		 }
  		 
  	 }
  	 	numItems--;
      return max;
	}
	
	/**
	 * A method to return the size of a queue. 
	 * @return: number of items in the queue. 
	 */
	int size() {
	   return numItems;	
	}	

}


