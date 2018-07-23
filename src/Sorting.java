import java.util.Arrays;
import java.util.Random;

import Plotter.Plotter;


public class Sorting {


	final static int BUBBLE_VS_QUICK_LENGTH = 12;
	final static int MERGE_VS_QUICK_LENGTH = 15;
	final static int BUBBLE_VS_QUICK_SORTED_LENGTH = 12;
	final static int ARBITRARY_VS_MEDIAN_LENGTH = 16;
	final static double T = 600;
	/**
	 * Sorts a given array using the quick sort algorithm.
	 * At each stage the pivot is chosen to be the rightmost element of the subarray.
	 *
	 * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
	 *
	 * @param arr - the array to be sorted
	 */
	public static void quickSortArbitraryPivot(double[] arr){
		quickSortArbitraryPivot(arr, 0, arr.length - 1);
	}

	public static void quickSortArbitraryPivot(double[] arr, int p, int q) {
		if (p < q) {
			int separator = partition(arr, p, q, q);
			quickSortArbitraryPivot(arr, separator + 1, q);
			quickSortArbitraryPivot(arr, p, separator - 1);
		}
	}

	/**
	 * Sorts a given array using the quick sort algorithm.
	 * At each stage the pivot is chosen in the following way:
	 * Choose 3 random elements from the array, the pivot is the median of the 3 elements.
	 *
	 * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
	 *
	 * @param arr - the array to be sorted
	 */
	public static void quickSortMedianPivot(double[] arr) {
		quickSortMedianPivot(arr, 0, arr.length - 1);
	}

	/**
	 * the recursive quickSortMedianPivot function
	 * @param arr the array we wish to sort
	 * @param p start index
	 * @param q last index
	 */
	public static void quickSortMedianPivot(double[] arr, int p, int q) {
		if (p < q) {
			int median = generateMedian(arr, p, q);
			quickSortMedianPivot(arr, p, median - 1);
			quickSortMedianPivot(arr, median + 1, q);
		}
	}

	/**
	 * retruns the position of the median between 3 random numbers between indexes p-q in the array.
	 *
	 * @param arr the array
	 * @param p start index
	 * @param q last index
	 * @return the position of the chosen median
	 */

	public static int generateMedian(double[] arr, int p, int q) {
		int n1 = p + ((int) (Math.random() * (1 + q - p)));
		int n2 = p + ((int) (Math.random() * (1 + q - p)));
		int n3 = p + ((int) (Math.random() * (1 + q - p)));
		int median = 0;
		if (arr[n1] >= arr[n2] && arr[n1] >= arr[n3]) {
			if (arr[n2] >= arr[n3]) median = n2;
			else median = n3;
		}
		if (arr[n3] >= arr[n2] && arr[n3] >= arr[n1]) {
			if (arr[n2] >= arr[n3]) median = n2;
			else median = n1;
		}
		if (arr[n2] >= arr[n1] && arr[n2] >= arr[n3]) {
			if (arr[n1] >= arr[n3]) median = n1;
			else median = n3;
		}
		return partition(arr, p, q, median);
	}

	/**
	 * sort the array in indexes p-q such that every number lower then median
	 * will be located left to it, the all the larger numbers left to it.
	 *
	 * @param arr the array
	 * @param p first index
	 * @param q last index
	 * @param median
	 * @return the position of the median
	 */

	public static int partition(double[] arr, int p, int q, int median) {
		switchIndex(arr, q, median);
		median = q;
		int mPos = p;
		for (int k = p; k < median; k++) {
			if (arr[k] < arr[median]) {
				switchIndex(arr, k, mPos);
				mPos++;
			}
		}
		switchIndex(arr, median, mPos);
		return mPos;
	}

	/**
	 * swaps 2 indexes of the array
	 * @param arr the array
	 * @param p first index
	 * @param q second index
	 */

	public static void switchIndex (double[] arr, int p, int q) {
		double temp = arr[p];
		arr[p] = arr[q];
		arr[q] = temp;
	}

	/**
	 * Sorts a given array using the merge sort algorithm.
	 *
	 * Should run in complexity O(nlog(n)) in the worst case.
	 *
	 * @param arr - the array to be sorted
	 */
	public static void mergeSort(double[] arr){
		mergeSort(arr, 0, arr.length - 1);
	}

	/**
	 * the recursive mergesort
	 * @param arr the array
	 * @param i the start index
	 * @param j the last index
	 */
	public static void mergeSort(double[] arr, int i, int j){
		int mid;
		if (i < j) {
			mid = (i + j) / 2;
			mergeSort(arr, i, mid);
			mergeSort(arr, mid + 1, j);
			merge(arr, i, mid, j);
		}
	}

	/**
	 * receives the array with the indexes of the sub-array and sorts is
	 * @param arr
	 * @param i the start index
	 * @param mid the middle index of the sub-array
	 * @param j the last index
	 */

	public static void merge(double[] arr, int i, int mid, int j) {
		double[] left = new double[mid - i + 2];
		double[] right = new double[j - mid + 1];
		for (int k = 0; k < left.length - 1; k++)
			left[k] = arr[k + i];
		for (int k = 0; k < right.length - 1; k++)
			right[k] = arr[mid + k + 1];
		left[left.length - 1] = Integer.MAX_VALUE;
		right[right.length - 1] = Integer.MAX_VALUE;
		int l = 0;
		int r = 0;
		for (int k = i; k <= j; k++) {
			if (right[r] > left[l]) {
				arr[k] = left[l];
				l++;
			}
			else {
				arr[k] = right[r];
				r++;
			}
		}
	}

	/**
	 * Sorts a given array using bubble sort.
	 * If at any time the algorithm recognizes no more inversions are needed it should stop.
	 *
	 * The algorithm should run in complexity O(n^2) in the worst case.
	 * The algorithm should run in complexity O(n) in the best case.
	 *
	 * @param arr - the array to be sorted
	 */
	public static void bubbleSort(double[] arr) {
		boolean inversion = false;
		for (int i = 0; i < arr.length; i++) {
			inversion = false;
			for (int j = 1; j < arr.length - i; j++) {
				if (arr[j] < arr[j - 1]) {
					switchIndex(arr, j, j - 1);
					inversion = true;
				}
			}
			if (!inversion) return;
		}
	}


	public static void main(String[] args) {
		bubbleVsQuick();
		mergeVsQuick();
		bubbleVsQuickOnSortedArray();
		arbitraryPivotVsMedianPivot();
	}

	/**
	 * Compares the selection sort algorithm against quick sort on random arrays
	 */
	public static void bubbleVsQuick(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < BUBBLE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumSelection = 0;
			for(int k = 0; k < T; k++){
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumSelection += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumSelection/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "bubble sort on random array", bubbleTimes);
	}

	/**
	 * Compares the merge sort algorithm against quick sort on random arrays
	 */
	public static void mergeVsQuick(){
		double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			mergeTimes[i] = sumMerge/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
	}

	/**
	 * Compares the merge sort algorithm against quick sort on pre-sorted arrays
	 */
	public static void bubbleVsQuickOnSortedArray(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		long startTime, endTime;
		for (int i = 0; i < BUBBLE_VS_QUICK_SORTED_LENGTH; i++) {
			long sumQuick = 0;
			long sumBubble = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = j;
					b[j] = j;
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumBubble += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumBubble/T;
		}
		Plotter.plot("quick sort on sorted array", quickTimes, "bubble sort on sorted array", bubbleTimes);
	}

	/**
	 * Compares the quick sort algorithm once with a choice of an arbitrary pivot and once with a choice of a median pivot
	 */
	public static void arbitraryPivotVsMedianPivot(){
		double[] arbitraryTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
		double[] medianTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < ARBITRARY_VS_MEDIAN_LENGTH; i++) {
			long sumArbitrary = 0;
			long sumMedian = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumArbitrary += endTime - startTime;
				startTime = System.currentTimeMillis();
				quickSortMedianPivot(b);
				endTime = System.currentTimeMillis();
				sumMedian += endTime - startTime;
			}
			arbitraryTimes[i] = sumArbitrary/T;
			medianTimes[i] = sumMedian/T;
		}
		Plotter.plot("quick sort with an arbitrary pivot", arbitraryTimes, "quick sort with a median pivot", medianTimes);
	}

}
