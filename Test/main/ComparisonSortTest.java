package main;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComparisonSortTest {
	

	@Test
	public void testSelectionSort() {
		
		Integer [] testArray = 
				  new Integer[] {5, 8, 2, 4, 7, 1, 9, 10, 60, 0, -2, -100, 500};
	  	ComparisonSort.selectionSort(testArray);
	  	Integer[] result = new Integer[] {-100, -2, 0, 1, 2, 4, 5, 7, 8, 9, 10, 60, 500};
		assertArrayEquals(result, testArray);
  	  
	}

	@Test
	public void testInsertionSort() {
		Integer [] testArray = 
				  new Integer[] {5, 8, 2, 4, 7, 1, 9, 10, 60, 0, -2, -100, 500};
	  	ComparisonSort.insertionSort(testArray);
	  	Integer[] result = new Integer[] {-100, -2, 0, 1, 2, 4, 5, 7, 8, 9, 10, 60, 500};
		assertArrayEquals(result, testArray);
	}

	@Test
	public void testMergeSort() {
		Integer [] testArray = 
				  new Integer[] {5, 8, 2, 4, 7, 1, 9, 10, 60, 0, -2, -100, 500};
	  	ComparisonSort.mergeSort(testArray);
	  	Integer[] result = new Integer[] {-100, -2, 0, 1, 2, 4, 5, 7, 8, 9, 10, 60, 500};
		assertArrayEquals(result, testArray);
	}

	@Test
	public void testQuickSort() {
		Integer [] testArray = 
				new Integer[] {5, 8, 2, 4, 7, 1, 9, 10, 60, 0, -2, -100, 500};
		ComparisonSort.quickSort(testArray);
	  	Integer[] result = 
	  			new Integer[] {-100, -2, 0, 1, 2, 4, 5, 7, 8, 9, 10, 60, 500};
		assertArrayEquals(result, testArray);
	}
	
	@Test
	public void testMedianOfThree() {
		
		Integer [] testArray = new Integer[] { 3, 8, 2, 1, 5};
		int median = ComparisonSort.medianOfThree(testArray, 0, 4);
		assertEquals(3, median);
		//  new Integer[] {5, 8, 2, 4, 7, 1, 9, 10, 60, 0, -2, -100, 500};
	
	   Integer[] result = new Integer[] {2, 8, 1, 3, 5};
			//new Integer[] {-100, -2, 0, 1, 2, 4, 5, 7, 8, 9, 10, 60, 500};
	   assertArrayEquals(result, testArray);
	
	}
	
   @Test
   public void testHelperInsertionSort() {
   	Integer [] testArray = new Integer[] { 3, 8, 2, 0};
   	ComparisonSort.insertionSort(testArray, 0, 3);
   	Integer [] expected = new Integer[] {0, 2, 3, 8};
   	assertArrayEquals(expected, testArray);
   }

	@Test
	public void testHeapSort() {
		
		Integer [] testArray = 
				new Integer[] {5, 8, 2, 4, 7, 1, -50, -200};
		ComparisonSort.heapSort(testArray);
		Integer[] result = new Integer[] {-200, -50, 1, 2, 4, 5, 7, 8};
		assertArrayEquals(result, testArray);
		
	}

	@Test
	public void testSelection2Sort() {
		Integer [] testArray = 
				  new Integer[] {5, 8, 2, 4, 7, 1, 9, 10, 60, 0, -2, -100, 500};
	  	ComparisonSort.selection2Sort(testArray);
	  	Integer[] result = new Integer[] {-100, -2, 0, 1, 2, 4, 5, 7, 8, 9, 10, 60, 500};
		assertArrayEquals(result, testArray);
	}
	
	@Test
	public void testBubbleSort() {
		Integer [] testArray = 
				  new Integer[] {5, 8, 2, 4, 7, 1, 9, 10, 60, 0, -2, -100, 500};
	  	ComparisonSort.bubbleSort(testArray);
	  	Integer[] result = new Integer[] {-100, -2, 0, 1, 2, 4, 5, 7, 8, 9, 10, 60, 500};
		assertArrayEquals(result, testArray);
	}


}
