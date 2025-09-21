package com.misc.arrays;

import java.util.Arrays;

/**
 * ArrayRotation - Comprehensive demonstration of array rotation operations
 * 
 * This class covers:
 * 1. Left rotation by d positions
 * 2. Right rotation by d positions
 * 3. Rotation using temporary array
 * 4. Rotation using reversal algorithm
 * 5. Rotation using cyclic replacements
 * 6. Find rotation count in rotated sorted array
 * 7. Search element in rotated array
 * 8. Check if array is rotation of another
 * 
 * Time Complexities:
 * - Rotation using temp array: O(n), Space: O(d)
 * - Rotation using reversal: O(n), Space: O(1)
 * - Rotation using cyclic: O(n), Space: O(1)
 * 
 * Interview Topics:
 * - Optimal space complexity solutions
 * - Different rotation techniques
 * - Applications in circular arrays
 * 
 * @author Vishal.Kamaliya
 */
public class ArrayRotation {
    
    /**
     * LEFT ROTATION using temporary array
     * Rotate array to left by d positions
     * Time Complexity: O(n), Space Complexity: O(d)
     */
    public static void leftRotateUsingTempArray(int[] arr, int d) {
        int n = arr.length;
        d = d % n; // Handle cases where d > n
        
        if (d == 0) return;
        
        // Store first d elements in temporary array
        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = arr[i];
        }
        
        // Shift rest of the elements to left
        for (int i = d; i < n; i++) {
            arr[i - d] = arr[i];
        }
        
        // Put temp elements at the end
        for (int i = 0; i < d; i++) {
            arr[n - d + i] = temp[i];
        }
    }
    
    /**
     * LEFT ROTATION using reversal algorithm
     * Time Complexity: O(n), Space Complexity: O(1)
     * 
     * Algorithm:
     * 1. Reverse first d elements
     * 2. Reverse remaining n-d elements
     * 3. Reverse entire array
     */
    public static void leftRotateUsingReversal(int[] arr, int d) {
        int n = arr.length;
        d = d % n;
        
        if (d == 0) return;
        
        // Reverse first d elements
        reverse(arr, 0, d - 1);
        
        // Reverse remaining elements
        reverse(arr, d, n - 1);
        
        // Reverse entire array
        reverse(arr, 0, n - 1);
    }
    
    /**
     * LEFT ROTATION using cyclic replacements
     * Time Complexity: O(n), Space Complexity: O(1)
     * Most space-efficient method
     */
    public static void leftRotateUsingCyclic(int[] arr, int d) {
        int n = arr.length;
        d = d % n;
        
        if (d == 0) return;
        
        int cycles = gcd(n, d);
        
        for (int i = 0; i < cycles; i++) {
            int current = i;
            int prev = arr[i];
            
            do {
                int next = (current + d) % n;
                int temp = arr[next];
                arr[next] = prev;
                prev = temp;
                current = next;
            } while (current != i);
        }
    }
    
    /**
     * RIGHT ROTATION using temporary array
     * Rotate array to right by d positions
     * Time Complexity: O(n), Space Complexity: O(d)
     */
    public static void rightRotateUsingTempArray(int[] arr, int d) {
        int n = arr.length;
        d = d % n;
        
        if (d == 0) return;
        
        // Store last d elements in temporary array
        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = arr[n - d + i];
        }
        
        // Shift elements to right
        for (int i = n - 1; i >= d; i--) {
            arr[i] = arr[i - d];
        }
        
        // Put temp elements at the beginning
        for (int i = 0; i < d; i++) {
            arr[i] = temp[i];
        }
    }
    
    /**
     * RIGHT ROTATION using reversal algorithm
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void rightRotateUsingReversal(int[] arr, int d) {
        int n = arr.length;
        d = d % n;
        
        if (d == 0) return;
        
        // Reverse entire array
        reverse(arr, 0, n - 1);
        
        // Reverse first d elements
        reverse(arr, 0, d - 1);
        
        // Reverse remaining elements
        reverse(arr, d, n - 1);
    }
    
    /**
     * LEFT ROTATION one by one (for educational purpose)
     * Time Complexity: O(n*d), Space Complexity: O(1)
     * Not efficient but shows basic concept
     */
    public static void leftRotateOneByOne(int[] arr, int d) {
        int n = arr.length;
        d = d % n;
        
        for (int i = 0; i < d; i++) {
            leftRotateByOne(arr);
        }
    }
    
    /**
     * Helper method to rotate array left by one position
     */
    private static void leftRotateByOne(int[] arr) {
        int temp = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = temp;
    }
    
    /**
     * Find rotation count in rotated sorted array
     * Time Complexity: O(log n)
     */
    public static int findRotationCount(int[] arr) {
        int n = arr.length;
        int left = 0, right = n - 1;
        
        // Array is not rotated
        if (arr[left] <= arr[right]) {
            return 0;
        }
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int next = (mid + 1) % n;
            int prev = (mid - 1 + n) % n;
            
            // Check if mid is the minimum element
            if (arr[mid] <= arr[next] && arr[mid] <= arr[prev]) {
                return mid;
            }
            
            // Decide which half to search
            if (arr[mid] <= arr[right]) {
                right = mid - 1;
            } else if (arr[mid] >= arr[left]) {
                left = mid + 1;
            }
        }
        
        return 0;
    }
    
    /**
     * Check if one array is rotation of another
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static boolean isRotation(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        
        // Create concatenated string of arr1
        StringBuilder sb = new StringBuilder();
        for (int num : arr1) {
            sb.append(num).append(",");
        }
        String concatenated = sb.toString() + sb.toString();
        
        // Create string from arr2
        StringBuilder sb2 = new StringBuilder();
        for (int num : arr2) {
            sb2.append(num).append(",");
        }
        String arr2Str = sb2.toString();
        
        return concatenated.contains(arr2Str);
    }
    
    /**
     * Find maximum sum of i*arr[i] with only rotations allowed
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxSumRotation(int[] arr) {
        int n = arr.length;
        int arrSum = 0; // Sum of all elements
        int currSum = 0; // Sum of i*arr[i] for current rotation
        
        // Calculate initial values
        for (int i = 0; i < n; i++) {
            arrSum += arr[i];
            currSum += i * arr[i];
        }
        
        int maxSum = currSum;
        
        // Try all rotations
        for (int i = 1; i < n; i++) {
            // Calculate sum for next rotation
            currSum = currSum + arrSum - n * arr[n - i];
            maxSum = Math.max(maxSum, currSum);
        }
        
        return maxSum;
    }
    
    /**
     * Rotate array in groups
     * Useful for rotating 2D array rows/columns
     */
    public static void rotateInGroups(int[] arr, int k, int d) {
        int n = arr.length;
        
        for (int i = 0; i < n; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1);
            int elements = right - left + 1;
            
            // Extract subarray
            int[] temp = new int[elements];
            for (int j = 0; j < elements; j++) {
                temp[j] = arr[left + j];
            }
            
            // Rotate subarray
            leftRotateUsingReversal(temp, d % elements);
            
            // Put back rotated subarray
            for (int j = 0; j < elements; j++) {
                arr[left + j] = temp[j];
            }
        }
    }
    
    /**
     * Utility method to reverse array elements from index i to j
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    /**
     * Utility method to find GCD of two numbers
     */
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
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
        System.out.println("=== Array Rotation Operations Demo ===\n");
        
        // Test array
        int[] originalArr = {1, 2, 3, 4, 5, 6, 7};
        int rotations = 2;
        
        System.out.println("Original Array: " + Arrays.toString(originalArr));
        System.out.println("Rotation positions: " + rotations);
        System.out.println();
        
        // 1. Left Rotation using different methods
        System.out.println("1. LEFT ROTATION METHODS:");
        
        int[] arr1 = originalArr.clone();
        leftRotateUsingTempArray(arr1, rotations);
        System.out.println("Using Temp Array: " + Arrays.toString(arr1));
        
        int[] arr2 = originalArr.clone();
        leftRotateUsingReversal(arr2, rotations);
        System.out.println("Using Reversal:   " + Arrays.toString(arr2));
        
        int[] arr3 = originalArr.clone();
        leftRotateUsingCyclic(arr3, rotations);
        System.out.println("Using Cyclic:     " + Arrays.toString(arr3));
        
        int[] arr4 = originalArr.clone();
        leftRotateOneByOne(arr4, rotations);
        System.out.println("One by One:       " + Arrays.toString(arr4));
        System.out.println();
        
        // 2. Right Rotation methods
        System.out.println("2. RIGHT ROTATION METHODS:");
        
        int[] arr5 = originalArr.clone();
        rightRotateUsingTempArray(arr5, rotations);
        System.out.println("Using Temp Array: " + Arrays.toString(arr5));
        
        int[] arr6 = originalArr.clone();
        rightRotateUsingReversal(arr6, rotations);
        System.out.println("Using Reversal:   " + Arrays.toString(arr6));
        System.out.println();
        
        // 3. Find rotation count in rotated sorted array
        System.out.println("3. FIND ROTATION COUNT:");
        int[] rotatedSorted = {4, 5, 6, 7, 1, 2, 3};
        System.out.println("Rotated sorted array: " + Arrays.toString(rotatedSorted));
        int rotationCount = findRotationCount(rotatedSorted);
        System.out.println("Rotation count: " + rotationCount);
        System.out.println();
        
        // 4. Check if arrays are rotations of each other
        System.out.println("4. CHECK ROTATION:");
        int[] arr7 = {1, 2, 3, 4, 5};
        int[] arr8 = {3, 4, 5, 1, 2};
        int[] arr9 = {1, 2, 4, 3, 5};
        
        System.out.println("Array 1: " + Arrays.toString(arr7));
        System.out.println("Array 2: " + Arrays.toString(arr8));
        System.out.println("Array 3: " + Arrays.toString(arr9));
        System.out.println("Is Array 2 rotation of Array 1? " + isRotation(arr7, arr8));
        System.out.println("Is Array 3 rotation of Array 1? " + isRotation(arr7, arr9));
        System.out.println();
        
        // 5. Maximum sum rotation
        System.out.println("5. MAXIMUM SUM ROTATION:");
        int[] sumArr = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Array: " + Arrays.toString(sumArr));
        int maxSum = maxSumRotation(sumArr);
        System.out.println("Maximum sum of i*arr[i]: " + maxSum);
        System.out.println();
        
        // 6. Rotate in groups
        System.out.println("6. ROTATE IN GROUPS:");
        int[] groupArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println("Original: " + Arrays.toString(groupArr));
        rotateInGroups(groupArr, 3, 2); // Rotate groups of 3 by 2 positions
        System.out.println("After rotating groups of 3 by 2 positions: " + Arrays.toString(groupArr));
        System.out.println();
        
        // 7. Performance comparison
        System.out.println("7. PERFORMANCE COMPARISON:");
        int[] largeArr = new int[100000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = i;
        }
        
        System.out.println("Testing on array of size: " + largeArr.length);
        
        // Test different rotation methods
        testRotationPerformance("Temp Array Method", largeArr.clone(), 1000, true);
        testRotationPerformance("Reversal Method", largeArr.clone(), 1000, false);
        
        System.out.println("\n=== Rotation Algorithm Characteristics ===");
        printRotationCharacteristics();
        
        System.out.println("\n=== End of Array Rotation Demo ===");
    }
    
    /**
     * Test rotation performance
     */
    private static void testRotationPerformance(String method, int[] arr, int d, boolean useTemp) {
        long startTime = System.nanoTime();
        
        if (useTemp) {
            leftRotateUsingTempArray(arr, d);
        } else {
            leftRotateUsingReversal(arr, d);
        }
        
        long endTime = System.nanoTime();
        System.out.println(method + ": " + (endTime - startTime) / 1000000.0 + " ms");
    }
    
    /**
     * Print characteristics of rotation algorithms
     */
    private static void printRotationCharacteristics() {
        System.out.println("Method              | Time Complexity | Space Complexity | Notes");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Temp Array          | O(n)            | O(d)             | Simple, needs extra space");
        System.out.println("Reversal Algorithm  | O(n)            | O(1)             | Most space efficient");
        System.out.println("Cyclic Replacement  | O(n)            | O(1)             | Complex but efficient");
        System.out.println("One by One          | O(n*d)          | O(1)             | Simple but slow");
    }
}
