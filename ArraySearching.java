package com.misc.arrays;

/**
 * ArraySearching - Comprehensive demonstration of searching algorithms in arrays
 * 
 * This class covers:
 * 1. Linear Search (Sequential Search)
 * 2. Binary Search (for sorted arrays)
 * 3. Search for element in rotated sorted array
 * 4. Find first and last occurrence of element
 * 5. Peak element finding
 * 6. Search in 2D matrix
 * 
 * Time Complexities:
 * - Linear Search: O(n)
 * - Binary Search: O(log n)
 * - Search in rotated array: O(log n)
 * 
 * Interview Preparation Topics Covered:
 * - Basic searching concepts
 * - Binary search variations
 * - 2D array searching
 * 
 * @author Vishal.Kamaliya
 */
public class ArraySearching {
    
    /**
     * Linear Search: Search for an element by checking each element sequentially
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index if found
            }
        }
        return -1; // Return -1 if not found
    }
    
    /**
     * Binary Search: Search for an element in a sorted array using divide and conquer
     * Time Complexity: O(log n), Space Complexity: O(1)
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid overflow
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    /**
     * Recursive Binary Search implementation
     * Time Complexity: O(log n), Space Complexity: O(log n) due to recursion
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }
    
    /**
     * Search in Rotated Sorted Array
     * Example: [4,5,6,7,0,1,2] rotated from [0,1,2,4,5,6,7]
     * Time Complexity: O(log n)
     */
    public static int searchInRotatedArray(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            // Check which half is sorted
            if (arr[left] <= arr[mid]) { // Left half is sorted
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // Right half is sorted
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    
    /**
     * Find First and Last Occurrence of an element in sorted array
     * Returns array [firstIndex, lastIndex] or [-1, -1] if not found
     */
    public static int[] findFirstAndLastOccurrence(int[] arr, int target) {
        int[] result = {-1, -1};
        
        // Find first occurrence
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                result[0] = mid;
                right = mid - 1; // Continue searching in left half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // Find last occurrence
        left = 0;
        right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                result[1] = mid;
                left = mid + 1; // Continue searching in right half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Find Peak Element: An element that is greater than its neighbors
     * Time Complexity: O(log n)
     */
    public static int findPeakElement(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    /**
     * Search in 2D Matrix (row-wise and column-wise sorted)
     * Time Complexity: O(m + n) where m = rows, n = columns
     */
    public static boolean searchIn2DMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        
        int row = 0;
        int col = matrix[0].length - 1;
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
    
    /**
     * Search in fully sorted 2D Matrix
     * Time Complexity: O(log(m*n))
     */
    public static boolean searchInSorted2DMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    
    /**
     * Find Square Root using Binary Search
     * Time Complexity: O(log n)
     */
    public static int findSquareRoot(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        
        int left = 1, right = x / 2;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (mid <= x / mid) { // Use division to avoid overflow
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    // Demonstration and testing methods
    public static void main(String[] args) {
        System.out.println("=== Array Searching Algorithms Demo ===\n");
        
        // Test data
        int[] unsortedArr = {64, 34, 25, 12, 22, 11, 90};
        int[] sortedArr = {11, 12, 22, 25, 34, 64, 90};
        int[] rotatedArr = {4, 5, 6, 7, 0, 1, 2};
        int[] duplicateArr = {5, 7, 7, 8, 8, 10};
        int[] peakArr = {1, 3, 20, 4, 1, 0};
        
        int target = 22;
        
        // 1. Linear Search Demo
        System.out.println("1. LINEAR SEARCH:");
        System.out.println("Array: " + java.util.Arrays.toString(unsortedArr));
        System.out.println("Searching for: " + target);
        int linearResult = linearSearch(unsortedArr, target);
        System.out.println("Result: " + (linearResult != -1 ? "Found at index " + linearResult : "Not found"));
        System.out.println();
        
        // 2. Binary Search Demo
        System.out.println("2. BINARY SEARCH:");
        System.out.println("Sorted Array: " + java.util.Arrays.toString(sortedArr));
        System.out.println("Searching for: " + target);
        int binaryResult = binarySearch(sortedArr, target);
        System.out.println("Iterative Result: " + (binaryResult != -1 ? "Found at index " + binaryResult : "Not found"));
        
        int recursiveResult = binarySearchRecursive(sortedArr, target, 0, sortedArr.length - 1);
        System.out.println("Recursive Result: " + (recursiveResult != -1 ? "Found at index " + recursiveResult : "Not found"));
        System.out.println();
        
        // 3. Search in Rotated Array Demo
        System.out.println("3. SEARCH IN ROTATED SORTED ARRAY:");
        System.out.println("Rotated Array: " + java.util.Arrays.toString(rotatedArr));
        System.out.println("Searching for: 0");
        int rotatedResult = searchInRotatedArray(rotatedArr, 0);
        System.out.println("Result: " + (rotatedResult != -1 ? "Found at index " + rotatedResult : "Not found"));
        System.out.println();
        
        // 4. First and Last Occurrence Demo
        System.out.println("4. FIRST AND LAST OCCURRENCE:");
        System.out.println("Array with duplicates: " + java.util.Arrays.toString(duplicateArr));
        System.out.println("Searching for: 8");
        int[] firstLastResult = findFirstAndLastOccurrence(duplicateArr, 8);
        System.out.println("First occurrence: " + firstLastResult[0] + ", Last occurrence: " + firstLastResult[1]);
        System.out.println();
        
        // 5. Peak Element Demo
        System.out.println("5. PEAK ELEMENT:");
        System.out.println("Array: " + java.util.Arrays.toString(peakArr));
        int peakIndex = findPeakElement(peakArr);
        System.out.println("Peak element found at index: " + peakIndex + " with value: " + peakArr[peakIndex]);
        System.out.println();
        
        // 6. 2D Matrix Search Demo
        System.out.println("6. 2D MATRIX SEARCH:");
        int[][] matrix = {
            {1,  4,  7,  11},
            {2,  5,  8,  12},
            {3,  6,  9,  16},
            {10, 13, 14, 17}
        };
        
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println(java.util.Arrays.toString(row));
        }
        System.out.println("Searching for: 5");
        boolean matrixResult = searchIn2DMatrix(matrix, 5);
        System.out.println("Result: " + (matrixResult ? "Found" : "Not found"));
        System.out.println();
        
        // 7. Square Root Demo
        System.out.println("7. SQUARE ROOT USING BINARY SEARCH:");
        int number = 25;
        System.out.println("Finding square root of: " + number);
        int sqrtResult = findSquareRoot(number);
        System.out.println("Result: " + sqrtResult);
        
        System.out.println("\n=== End of Array Searching Demo ===");
    }
}
