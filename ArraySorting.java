package com.misc.arrays;

import java.util.Arrays;

/**
 * ArraySorting - Comprehensive demonstration of sorting algorithms
 * 
 * This class covers all major sorting algorithms with detailed explanations:
 * 1. Bubble Sort - O(n²)
 * 2. Selection Sort - O(n²)
 * 3. Insertion Sort - O(n²)
 * 4. Merge Sort - O(n log n)
 * 5. Quick Sort - O(n log n) average, O(n²) worst
 * 6. Heap Sort - O(n log n)
 * 7. Counting Sort - O(n + k)
 * 8. Radix Sort - O(d * (n + k))
 * 
 * Interview Preparation Topics:
 * - Time and Space complexities
 * - Stable vs Unstable sorting
 * - In-place vs Out-of-place sorting
 * - Best case, average case, worst case analysis
 * 
 * @author Vishal.Kamaliya
 */
public class ArraySorting {
    
    /**
     * BUBBLE SORT
     * Time Complexity: O(n²), Space Complexity: O(1)
     * Stable: Yes, In-place: Yes
     * 
     * Repeatedly steps through the list, compares adjacent elements 
     * and swaps them if they are in wrong order.
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            // If no swapping occurred, array is sorted
            if (!swapped) {
                break;
            }
        }
    }
    
    /**
     * SELECTION SORT
     * Time Complexity: O(n²), Space Complexity: O(1)
     * Stable: No, In-place: Yes
     * 
     * Finds the minimum element and places it at the beginning.
     * Repeat for remaining unsorted portion.
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            // Find minimum element in remaining array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap minimum element with first element
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }
    
    /**
     * INSERTION SORT
     * Time Complexity: O(n²), Space Complexity: O(1)
     * Stable: Yes, In-place: Yes
     * 
     * Builds the final sorted array one item at a time.
     * Very efficient for small datasets.
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // Move elements greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    /**
     * MERGE SORT
     * Time Complexity: O(n log n), Space Complexity: O(n)
     * Stable: Yes, In-place: No
     * 
     * Divide and conquer algorithm that divides array into halves,
     * sorts them and then merges them.
     */
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Sort first and second halves
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temporary arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        
        // Merge the temporary arrays back
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    /**
     * QUICK SORT
     * Time Complexity: O(n log n) average, O(n²) worst, Space Complexity: O(log n)
     * Stable: No, In-place: Yes
     * 
     * Picks a pivot element and partitions array around it.
     */
    public static void quickSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            
            // Recursively sort elements before and after partition
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose last element as pivot
        int i = low - 1; // Index of smaller element
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /**
     * HEAP SORT
     * Time Complexity: O(n log n), Space Complexity: O(1)
     * Stable: No, In-place: Yes
     * 
     * Uses binary heap data structure to sort elements.
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swap(arr, 0, i);
            
            // Call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);
            
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    
    /**
     * COUNTING SORT
     * Time Complexity: O(n + k), Space Complexity: O(k)
     * Stable: Yes, In-place: No
     * Where k is the range of input
     */
    public static void countingSort(int[] arr) {
        if (arr.length == 0) return;
        
        // Find the maximum element to determine range
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        
        // Create count array
        int[] count = new int[range];
        int[] output = new int[arr.length];
        
        // Count occurrences of each element
        for (int num : arr) {
            count[num - min]++;
        }
        
        // Change count[i] to actual position of element in output array
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        
        // Build the output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        
        // Copy the output array to original array
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    
    /**
     * RADIX SORT
     * Time Complexity: O(d * (n + k)), Space Complexity: O(n + k)
     * Stable: Yes, In-place: No
     * Where d is number of digits, k is range of digits (0-9)
     */
    public static void radixSort(int[] arr) {
        if (arr.length == 0) return;
        
        // Find the maximum number to know number of digits
        int max = Arrays.stream(arr).max().getAsInt();
        
        // Do counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }
    
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        
        // Count occurrences of each digit
        for (int num : arr) {
            count[(num / exp) % 10]++;
        }
        
        // Change count[i] to actual position
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        
        // Copy the output array to original array
        System.arraycopy(output, 0, arr, 0, n);
    }
    
    /**
     * Utility method to swap two elements in an array
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Utility method to check if array is sorted
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Utility method to print array
     */
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * Demo and testing method
     */
    public static void main(String[] args) {
        System.out.println("=== Array Sorting Algorithms Demo ===\n");
        
        // Test data - same for all algorithms
        int[] originalArr = {64, 34, 25, 12, 22, 11, 90, 5, 77, 30};
        
        System.out.println("Original Array: " + Arrays.toString(originalArr));
        System.out.println("Array Length: " + originalArr.length);
        System.out.println();
        
        // Test all sorting algorithms
        testSortingAlgorithm("Bubble Sort", originalArr.clone(), ArraySorting::bubbleSort);
        testSortingAlgorithm("Selection Sort", originalArr.clone(), ArraySorting::selectionSort);
        testSortingAlgorithm("Insertion Sort", originalArr.clone(), ArraySorting::insertionSort);
        testSortingAlgorithm("Merge Sort", originalArr.clone(), ArraySorting::mergeSort);
        testSortingAlgorithm("Quick Sort", originalArr.clone(), ArraySorting::quickSort);
        testSortingAlgorithm("Heap Sort", originalArr.clone(), ArraySorting::heapSort);
        testSortingAlgorithm("Counting Sort", originalArr.clone(), ArraySorting::countingSort);
        testSortingAlgorithm("Radix Sort", originalArr.clone(), ArraySorting::radixSort);
        
        // Performance comparison on larger array
        System.out.println("\n=== Performance Analysis ===");
        int[] largeArr = generateRandomArray(10000);
        
        System.out.println("Testing on array of size: " + largeArr.length);
        System.out.println("Note: Only efficient algorithms tested for large arrays\n");
        
        measureSortingTime("Merge Sort", largeArr.clone(), ArraySorting::mergeSort);
        measureSortingTime("Quick Sort", largeArr.clone(), ArraySorting::quickSort);
        measureSortingTime("Heap Sort", largeArr.clone(), ArraySorting::heapSort);
        
        System.out.println("\n=== Sorting Algorithm Characteristics ===");
        printAlgorithmCharacteristics();
        
        System.out.println("\n=== End of Array Sorting Demo ===");
    }
    
    /**
     * Test a sorting algorithm and verify correctness
     */
    private static void testSortingAlgorithm(String name, int[] arr, SortingFunction sortFunc) {
        System.out.println(name + ":");
        System.out.println("Before: " + Arrays.toString(arr));
        
        long startTime = System.nanoTime();
        sortFunc.sort(arr);
        long endTime = System.nanoTime();
        
        System.out.println("After:  " + Arrays.toString(arr));
        System.out.println("Sorted: " + isSorted(arr));
        System.out.println("Time:   " + (endTime - startTime) / 1000000.0 + " ms");
        System.out.println();
    }
    
    /**
     * Measure sorting time for performance analysis
     */
    private static void measureSortingTime(String name, int[] arr, SortingFunction sortFunc) {
        long startTime = System.nanoTime();
        sortFunc.sort(arr);
        long endTime = System.nanoTime();
        
        System.out.println(name + ": " + (endTime - startTime) / 1000000.0 + " ms, Sorted: " + isSorted(arr));
    }
    
    /**
     * Generate random array for testing
     */
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }
    
    /**
     * Print characteristics of all sorting algorithms
     */
    private static void printAlgorithmCharacteristics() {
        System.out.println("Algorithm     | Time Complexity        | Space Complexity | Stable | In-Place");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Bubble Sort   | O(n²)                  | O(1)             | Yes    | Yes");
        System.out.println("Selection Sort| O(n²)                  | O(1)             | No     | Yes");
        System.out.println("Insertion Sort| O(n²)                  | O(1)             | Yes    | Yes");
        System.out.println("Merge Sort    | O(n log n)             | O(n)             | Yes    | No");
        System.out.println("Quick Sort    | O(n log n) avg, O(n²)  | O(log n)         | No     | Yes");
        System.out.println("Heap Sort     | O(n log n)             | O(1)             | No     | Yes");
        System.out.println("Counting Sort | O(n + k)               | O(k)             | Yes    | No");
        System.out.println("Radix Sort    | O(d × (n + k))         | O(n + k)         | Yes    | No");
    }
    
    /**
     * Functional interface for sorting algorithms
     */
    @FunctionalInterface
    private interface SortingFunction {
        void sort(int[] arr);
    }
}
