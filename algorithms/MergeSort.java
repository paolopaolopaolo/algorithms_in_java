/* Merge Sort Implementation

	Author: Paolo Mercado
	Date: 2015 January 19

	Thanks to Tim Roughgarden from Stanford and Robert Sedgewick from Princeton
	for their excellent MOOCs.

	More specifically:
	- Tim Roughgarden's high-level pseudocode for MergeSort
	- Robert Sedgewick's Intro to Java booksite and code samples (yes, I know he has
	a code sample for MergeSort on his own site, but I purposefully barred myself from
	looking at it for the sake of the challenge)

*/

// Compiles in directory: [dest] -> algorithms -> sorting
package algorithms.sorting;

import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class MergeSort {

	// Recursive sorting function
	public static int[] sort(int[] array){

		// Base case: array is only one item long
		if (array.length == 1) { 
			int[] res = {array[0]};
			return res; 
		}
		
		// Reductive case:
		// Initialize sub arrays; copy into halves
		int[] a1 = Arrays.copyOfRange(array, 0, array.length/2);
		int[] a2 = Arrays.copyOfRange(array, array.length/2, array.length);

		// Recursively sort both halves
		a1 = sort(a1);
		a2 = sort(a2);

		// Merge halves together and return result
		return merge(a1, a2);

	}

	// Linear merge function
	public static int[] merge(int[] array1, int[] array2) {
		// Initialize result array and sub array indices
		int[] result = new int[array1.length + array2.length];
		int a1_ind = 0;
		int a2_ind = 0;

		// Iterate along result indices, using if/elseif/else branching 
		// to block ArrayIndexOutOfBoundsExceptions
		for (int res_ind = 0; res_ind < result.length; res_ind++) {
			// If at the end of array 1, just add from array 2
			if (a1_ind > array1.length - 1){
				result[res_ind] = array2[a2_ind];
				a2_ind++;
			}
			// If at end of array 2, just add from array 1
			else if (a2_ind > array2.length - 1){
				result[res_ind] = array1[a1_ind];
				a1_ind++;
			}
			// If value at head of array 1 is less than or equal to 
			// head of array 2, add from array 1
			else if (array1[a1_ind] <= array2[a2_ind]) {
				result[res_ind] = array1[a1_ind];
				a1_ind++;
			}
			// If value at head of array 2 is less than 
			// head of array 1, add from array 2
			else {
				result[res_ind] = array2[a2_ind];
				a2_ind++;
			}
		}
		// Return resultant array
		return result;
	}

	private MergeSort(){
		// Do not instantiate!
	}

	// This will test MergeSort using Junit
	// If all tests pass, MergeSort will print nothing

	@Test
	public static void main(String[] args){
		// Test 1: random list of 10 integers
		int[] a =  {3, 1, 5, 4, 9, 7, 0, 2, 8, 6};
		int[] a_sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		// Test 2: random list of 11 integers
		int[] b = {4, 2, 7, 3, 1, 0, 10, 8, 5, 9, 6};
		int[] b_sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		// Test 3: list of 21 integers in reverse
		int[] c = {20, 19, 18, 17, 16, 15, 14, 13,
							 12, 11, 10, 9, 8, 7, 6, 5, 4, 3,
							 2, 1, 0};
		int[] c_sorted = {0, 1, 2, 3, 4, 5, 6, 7, 8,
											9, 10, 11, 12, 13, 14, 15,
											16, 17, 18, 19, 20};

		// Run Junit Assertions
		assertArrayEquals(sort(a), a_sorted);
		assertArrayEquals(sort(b), b_sorted);
		assertArrayEquals(sort(c), c_sorted);
	}

}