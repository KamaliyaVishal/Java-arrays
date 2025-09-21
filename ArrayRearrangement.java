package com.misc.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * ArrayRearrangement - Comprehensive demonstration of array rearrangement operations
 * 
 * This class covers:
 * 1. Reverse array
 * 2. Rearrange positive and negative numbers
 * 3. Move zeros to end
 * 4. Segregate even and odd numbers
 * 5. Rearrange array in alternating fashion
 * 6. Rearrange such that arr[i] = i
 * 7. Rearrange to maximize sum of arr[i]*i
 * 8. Move all negative elements to one side
 * 
 * Interview Topics:
 * - Two-pointer technique
 * - In-place rearrangement
 * - Stable vs unstable rearrangement
 * - Partition algorithms
 * 
 * @author Vishal.Kamaliya
 */
public class ArrayRearrangement {
    
    /**
     * REVERSE ARRAY
     * Time Complexity: O(n), Space Complexity: O(1)
     * Reverse elements of array in-place
     */
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
    
    /**
     * REVERSE ARRAY using recursion
     * Time Complexity: O(n), Space Complexity: O(n) due to recursion
     */
    public static void reverseArrayRecursive(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        
        swap(arr, start, end);
        reverseArrayRecursive(arr, start + 1, end - 1);
    }
    
    /**
     * REARRANGE POSITIVE AND NEGATIVE NUMBERS
     * Alternating positive and negative (maintain relative order)
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static int[] rearrangeAlternatePositiveNegative(int[] arr) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        
        // Separate positive and negative numbers
        for (int num : arr) {
            if (num >= 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }
        
        int[] result = new int[arr.length];
        int posIndex = 0, negIndex = 0, resIndex = 0;
        
        // Alternate between positive and negative
        while (posIndex < positive.size() && negIndex < negative.size()) {
            result[resIndex++] = positive.get(posIndex++);
            result[resIndex++] = negative.get(negIndex++);
        }
        
        // Add remaining elements
        while (posIndex < positive.size()) {
            result[resIndex++] = positive.get(posIndex++);
        }
        while (negIndex < negative.size()) {
            result[resIndex++] = negative.get(negIndex++);
        }
        
        return result;
    }
    
    /**
     * REARRANGE POSITIVE AND NEGATIVE (In-place, order may change)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void rearrangePositiveNegativeInPlace(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            // Move left pointer to find negative number
            while (left < right && arr[left] >= 0) {
                left++;
            }
            
            // Move right pointer to find positive number
            while (left < right && arr[right] < 0) {
                right--;
            }
            
            // Swap if both found
            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
    }
    
    /**
     * MOVE ALL ZEROS TO END
     * Maintain relative order of non-zero elements
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void moveZerosToEnd(int[] arr) {
        int writeIndex = 0;
        
        // Move all non-zero elements to front
        for (int readIndex = 0; readIndex < arr.length; readIndex++) {
            if (arr[readIndex] != 0) {
                arr[writeIndex++] = arr[readIndex];
            }
        }
        
        // Fill remaining positions with zeros
        while (writeIndex < arr.length) {
            arr[writeIndex++] = 0;
        }
    }
    
    /**
     * MOVE ZEROS TO END (Alternative approach with swapping)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void moveZerosToEndSwap(int[] arr) {
        int left = 0;
        
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] != 0) {
                swap(arr, left, right);
                left++;
            }
        }
    }
    
    /**
     * SEGREGATE EVEN AND ODD NUMBERS
     * All even numbers before odd numbers
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void segregateEvenOdd(int[] arr) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            // Move left to find odd number
            while (left < right && arr[left] % 2 == 0) {
                left++;
            }
            
            // Move right to find even number
            while (left < right && arr[right] % 2 == 1) {
                right--;
            }
            
            // Swap odd and even
            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
    }
    
    /**
     * REARRANGE ARRAY IN ALTERNATING PATTERN
     * Arrange in max, min, second max, second min pattern
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static void rearrangeMaxMin(int[] arr) {
        // First sort the array
        Arrays.sort(arr);
        
        int[] temp = new int[arr.length];
        int left = 0, right = arr.length - 1;
        boolean flag = true; // true for max, false for min
        
        for (int i = 0; i < arr.length; i++) {
            if (flag) {
                temp[i] = arr[right--];
            } else {
                temp[i] = arr[left++];
            }
            flag = !flag;
        }
        
        // Copy back to original array
        System.arraycopy(temp, 0, arr, 0, arr.length);
    }
    
    /**
     * REARRANGE ARRAY SUCH THAT arr[i] = i
     * If i is not present, set arr[i] = -1
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void rearrangeArrayIndex(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // If arr[i] is in valid range and not at correct position
            while (arr[i] != -1 && arr[i] != i && arr[i] >= 0 && arr[i] < arr.length) {
                // Swap arr[i] with arr[arr[i]]
                int temp = arr[arr[i]];
                arr[arr[i]] = arr[i];
                arr[i] = temp;
            }
        }
        
        // Set remaining positions to -1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i) {
                arr[i] = -1;
            }
        }
    }
    
    /**
     * REARRANGE TO MAXIMIZE SUM OF arr[i] * i
     * Simply sort the array in ascending order
     * Time Complexity: O(n log n), Space Complexity: O(1)
     */
    public static void rearrangeToMaximizeSum(int[] arr) {
        Arrays.sort(arr);
    }
    
    /**
     * WAVE ARRAY ARRANGEMENT
     * Arrange elements such that arr[0] >= arr[1] <= arr[2] >= arr[3]...
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void arrangeInWaveForm(int[] arr) {
        // Traverse even positions and swap with next if needed
        for (int i = 0; i < arr.length - 1; i += 2) {
            // If even position element is smaller than next
            if (arr[i] < arr[i + 1]) {
                swap(arr, i, i + 1);
            }
            
            // If even position element is smaller than previous
            if (i > 0 && arr[i] < arr[i - 1]) {
                swap(arr, i, i - 1);
            }
        }
    }
    
    /**
     * DUTCH NATIONAL FLAG ALGORITHM
     * Segregate 0s, 1s, and 2s
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void dutchNationalFlag(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;
        
        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, high);
                    high--;
                    // Don't increment mid here as we need to check swapped element
                    break;
            }
        }
    }
    
    /**
     * REARRANGE ARRAY IN SPECIFIC ORDER
     * Smallest, largest, second smallest, second largest...
     * Time Complexity: O(n log n), Space Complexity: O(n)
     */
    public static void rearrangeSmallestLargest(int[] arr) {
        Arrays.sort(arr);
        
        int[] result = new int[arr.length];
        int left = 0, right = arr.length - 1;
        
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                result[i] = arr[left++];
            } else {
                result[i] = arr[right--];
            }
        }
        
        System.arraycopy(result, 0, arr, 0, arr.length);
    }
    
    /**
     * REARRANGE ARRAY TO DOUBLE THE SIZE
     * arr[i] = 2 * arr[i] if 2 * arr[i] exists in array
     * Time Complexity: O(n log n), Space Complexity: O(1)
     */
    public static void doubleArray(int[] arr) {
        Arrays.sort(arr);
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                int doubled = 2 * arr[i];
                
                // Find doubled value in remaining array
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] == doubled) {
                        arr[i] = doubled;
                        arr[j] = -1; // Mark as used
                        break;
                    }
                    if (arr[j] > doubled) {
                        break; // No point searching further
                    }
                }
            }
        }
        
        // Move all -1s to end
        moveNegativeOnesToEnd(arr);
    }
    
    /**
     * Helper method to move -1s to end
     */
    private static void moveNegativeOnesToEnd(int[] arr) {
        int writeIndex = 0;
        
        for (int readIndex = 0; readIndex < arr.length; readIndex++) {
            if (arr[readIndex] != -1) {
                arr[writeIndex++] = arr[readIndex];
            }
        }
        
        while (writeIndex < arr.length) {
            arr[writeIndex++] = -1;
        }
    }
    
    /**
     * SHUFFLE ARRAY
     * Fisher-Yates shuffle algorithm
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void shuffleArray(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            swap(arr, i, randomIndex);
        }
    }
    
    /**
     * Utility method to swap two elements
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
        System.out.println("=== Array Rearrangement Operations Demo ===\n");
        
        // 1. Reverse Array Demo
        System.out.println("1. REVERSE ARRAY:");
        int[] reverseArr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Original: " + Arrays.toString(reverseArr));
        reverseArray(reverseArr);
        System.out.println("Reversed: " + Arrays.toString(reverseArr));
        
        // Recursive reverse
        int[] reverseArr2 = {1, 2, 3, 4, 5, 6, 7, 8};
        reverseArrayRecursive(reverseArr2, 0, reverseArr2.length - 1);
        System.out.println("Recursive: " + Arrays.toString(reverseArr2));
        System.out.println();
        
        // 2. Positive-Negative Rearrangement Demo
        System.out.println("2. POSITIVE-NEGATIVE REARRANGEMENT:");
        int[] posNegArr = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        System.out.println("Original: " + Arrays.toString(posNegArr));
        
        int[] alternateResult = rearrangeAlternatePositiveNegative(posNegArr);
        System.out.println("Alternate (order preserved): " + Arrays.toString(alternateResult));
        
        int[] inPlaceArr = posNegArr.clone();
        rearrangePositiveNegativeInPlace(inPlaceArr);
        System.out.println("In-place (order may change): " + Arrays.toString(inPlaceArr));
        System.out.println();
        
        // 3. Move Zeros to End Demo
        System.out.println("3. MOVE ZEROS TO END:");
        int[] zeroArr = {0, 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9};
        System.out.println("Original: " + Arrays.toString(zeroArr));
        moveZerosToEnd(zeroArr);
        System.out.println("After moving zeros: " + Arrays.toString(zeroArr));
        System.out.println();
        
        // 4. Segregate Even-Odd Demo
        System.out.println("4. SEGREGATE EVEN-ODD:");
        int[] evenOddArr = {12, 34, 45, 9, 8, 90, 3};
        System.out.println("Original: " + Arrays.toString(evenOddArr));
        segregateEvenOdd(evenOddArr);
        System.out.println("Even-Odd segregated: " + Arrays.toString(evenOddArr));
        System.out.println();
        
        // 5. Max-Min Rearrangement Demo
        System.out.println("5. MAX-MIN REARRANGEMENT:");
        int[] maxMinArr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Original: " + Arrays.toString(maxMinArr));
        rearrangeMaxMin(maxMinArr);
        System.out.println("Max-Min arranged: " + Arrays.toString(maxMinArr));
        System.out.println();
        
        // 6. Array Index Rearrangement Demo
        System.out.println("6. ARRAY INDEX REARRANGEMENT (arr[i] = i):");
        int[] indexArr = {-1, -1, 6, 1, 9, 3, 2, -1, 4, -1};
        System.out.println("Original: " + Arrays.toString(indexArr));
        rearrangeArrayIndex(indexArr);
        System.out.println("Rearranged: " + Arrays.toString(indexArr));
        System.out.println();
        
        // 7. Wave Form Demo
        System.out.println("7. WAVE FORM ARRANGEMENT:");
        int[] waveArr = {10, 5, 6, 3, 2, 20, 100, 80};
        System.out.println("Original: " + Arrays.toString(waveArr));
        arrangeInWaveForm(waveArr);
        System.out.println("Wave form: " + Arrays.toString(waveArr));
        System.out.println();
        
        // 8. Dutch National Flag Demo
        System.out.println("8. DUTCH NATIONAL FLAG (0s, 1s, 2s):");
        int[] dutchArr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        System.out.println("Original: " + Arrays.toString(dutchArr));
        dutchNationalFlag(dutchArr);
        System.out.println("Segregated: " + Arrays.toString(dutchArr));
        System.out.println();
        
        // 9. Smallest-Largest Demo
        System.out.println("9. SMALLEST-LARGEST ARRANGEMENT:");
        int[] smallLargeArr = {5, 8, 1, 4, 2, 9, 3, 7, 6};
        System.out.println("Original: " + Arrays.toString(smallLargeArr));
        rearrangeSmallestLargest(smallLargeArr);
        System.out.println("Smallest-Largest: " + Arrays.toString(smallLargeArr));
        System.out.println();
        
        // 10. Double Array Demo
        System.out.println("10. DOUBLE ARRAY:");
        int[] doubleArr = {2, 1, 2, 4, 3, 6};
        System.out.println("Original: " + Arrays.toString(doubleArr));
        doubleArray(doubleArr);
        System.out.println("Doubled: " + Arrays.toString(doubleArr));
        System.out.println();
        
        // 11. Shuffle Demo
        System.out.println("11. SHUFFLE ARRAY:");
        int[] shuffleArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Original: " + Arrays.toString(shuffleArr));
        shuffleArray(shuffleArr);
        System.out.println("Shuffled: " + Arrays.toString(shuffleArr));
        System.out.println();
        
        System.out.println("=== Rearrangement Algorithm Characteristics ===");
        printRearrangementCharacteristics();
        
        System.out.println("\n=== End of Array Rearrangement Demo ===");
    }
    
    /**
     * Print characteristics of rearrangement algorithms
     */
    private static void printRearrangementCharacteristics() {
        System.out.println("Operation                | Time Complexity | Space Complexity | Stable | In-Place");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Reverse Array            | O(n)            | O(1)             | Yes    | Yes");
        System.out.println("Positive-Negative Alt    | O(n)            | O(n)             | Yes    | No");
        System.out.println("Positive-Negative InPlace| O(n)            | O(1)             | No     | Yes");
        System.out.println("Move Zeros to End        | O(n)            | O(1)             | Yes    | Yes");
        System.out.println("Segregate Even-Odd       | O(n)            | O(1)             | No     | Yes");
        System.out.println("Max-Min Arrangement      | O(n log n)      | O(n)             | No     | No");
        System.out.println("Wave Form                | O(n)            | O(1)             | No     | Yes");
        System.out.println("Dutch National Flag      | O(n)            | O(1)             | No     | Yes");
        System.out.println("Shuffle (Fisher-Yates)   | O(n)            | O(1)             | No     | Yes");
    }
}
