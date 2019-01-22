import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/* Hakan ARAS, 30 December 2018 */
/* ID: 041701036 */
/* Sorting with Heap */
/* The program compares running times by applying heap sort, java sort, and selection sort algorithms to various array sizes. */

public class Hakan_ARAS {

	private static Heap<Integer> tempHeap;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int size = 40000;

		int[] heapList = new int[size];
		int[] javaList = new int[size];
		int[] selectionList = new int[size];
		double tempHeapSum = 0;
		double tempJavaSum = 0;
		double tempSelectionSum = 0;

		System.out.println("Array Size: " + size);

		for (int i = 0; i < 10; i++) { // Generate 10 times array

			randomGenerator(javaList, javaList.length);

			heapList = deepClone(javaList, heapList); // Deep copy of lists
			selectionList = deepClone(javaList, selectionList);

			long start = System.nanoTime(); // Take a start time
			heapSort(heapList);
			long finish = System.nanoTime(); // Take a finish time
			double elapsedTimeMilliSeconds = (finish - start) / 1000000.0; // Calculates the difference between two
			// numbers
			tempHeapSum = tempHeapSum + elapsedTimeMilliSeconds;

			long start1 = System.nanoTime();// Take a start time
			Arrays.sort(javaList);
			long finish1 = System.nanoTime();// Take a finish time
			double elapsedTimeMilliSeconds2 = (finish1 - start1) / 1000000.0;
			tempJavaSum = tempJavaSum + elapsedTimeMilliSeconds2;

			long start2 = System.nanoTime();// Take a start time
			selectionSort(selectionList);
			long finish2 = System.nanoTime();// Take a finish time
			double elapsedTimeMilliSeconds3 = (finish2 - start2) / 1000000.0;// Calculates the difference between two
			// numbers
			tempSelectionSum = tempSelectionSum + elapsedTimeMilliSeconds3;

			System.out.println((i + 1) + "." + "Heap Sort: " + String.format("%.2f", elapsedTimeMilliSeconds) + " msec"
					+ " Array Sort: " + String.format("%.2f", elapsedTimeMilliSeconds2) + " msec" + " Selection Sort: "
					+ String.format("%.2f", elapsedTimeMilliSeconds3) + " msec");
		}
		System.out.println("Heap Avg: " + String.format("%.2f", (tempHeapSum / 10.0)) + " msec" + " Java Avg: "
				+ String.format("%.2f", (tempJavaSum / 10.0)) + " msec" + " Selection Avg."
				+ String.format("%.2f", (tempSelectionSum / 10.0)) + " msec");

	}

	public static int[] deepClone(int[] original, int[] newCopy) {
		// Deep Copy of array
		for (int i = 0; i < original.length; i++) {
			newCopy[i] = original[i];
		}
		return newCopy;
	}

	public static void randomGenerator(int input[], int size) {
		// Fill the array with random integers with values in the range [0,10000000]
		for (int i = 0; i < size; i++) {
			input[i] = (int) (Math.random() * 10000000);
		}

	}

	public static void heapSort(int[] list) {
		// Heap tempHeap = new Heap();
		tempHeap = new Heap<Integer>();

		for (int i = 0; i < list.length; i++) {
			tempHeap.add(list[i]);
		}

		for (int i = list.length - 1; i >= 0; i--) {
			list[i] = (int) tempHeap.remove();
		}

	}

	public static void selectionSort(int[] list) {

		int tempIndex = 0;
		int tempValue = 0;
		for (int i = 0; i < list.length - 1; i++) {
			tempIndex = i;
			for (int j = i + 1; j < list.length; j++) { // search min of the value and take an index
				if (list[tempIndex] > list[j]) {
					tempIndex = j;
				}
			}
			tempValue = list[i]; // Swap operations
			list[i] = list[tempIndex];
			list[tempIndex] = tempValue;
		}

	}
}
