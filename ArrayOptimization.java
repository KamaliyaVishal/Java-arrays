package com.misc.arrays;

import java.util.*;

/**
 * ArrayOptimization - Advanced array problems focusing on space/time optimization
 * 
 * This class covers classic optimization problems:
 * 1. Kadane's Algorithm - Maximum Subarray Sum
 * 2. Two Pointer Technique problems
 * 3. Sliding Window problems
 * 4. Dutch National Flag variations
 * 5. Hash Map optimization problems
 * 6. Prefix Sum techniques
 * 7. Buy-Sell Stock problems
 * 8. Trapping Rain Water
 * 
 * Interview Focus:
 * - Time complexity optimization (O(n²) to O(n))
 * - Space complexity optimization (O(n) to O(1))
 * - Multiple solution approaches
 * - Edge case handling
 * 
 * @author Vishal.Kamaliya
 */
public class ArrayOptimization {
    
    /**
     * KADANE'S ALGORITHM - Maximum Subarray Sum
     * Time Complexity: O(n), Space Complexity: O(1)
     * Classic dynamic programming optimization
     */
    public static int maxSubarraySum(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    /**
     * KADANE'S ALGORITHM - Return the subarray indices
     * Returns [start, end, sum]
     */
    public static int[] maxSubarrayWithIndices(int[] arr) {
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0, end = 0, tempStart = 0;
        
        for (int i = 1; i < arr.length; i++) {
            if (maxEndingHere < 0) {
                maxEndingHere = arr[i];
                tempStart = i;
            } else {
                maxEndingHere += arr[i];
            }
            
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }
        
        return new int[]{start, end, maxSoFar};
    }
    
    /**
     * TWO SUM PROBLEM
     * Find two numbers that add up to target
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        
        return new int[]{-1, -1}; // Not found
    }
    
    /**
     * TWO SUM in SORTED ARRAY (Two Pointer approach)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int[] twoSumSorted(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[]{-1, -1}; // Not found
    }
    
    /**
     * THREE SUM PROBLEM
     * Find three numbers that add up to target (or zero)
     * Time Complexity: O(n²), Space Complexity: O(1)
     */
    public static List<List<Integer>> threeSum(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < arr.length - 2; i++) {
            // Skip duplicates
            if (i > 0 && arr[i] == arr[i - 1]) continue;
            
            int left = i + 1, right = arr.length - 1;
            
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                
                if (sum == target) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    
                    // Skip duplicates
                    while (left < right && arr[left] == arr[left + 1]) left++;
                    while (left < right && arr[right] == arr[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    /**
     * SLIDING WINDOW MAXIMUM
     * Find maximum in every window of size k
     * Time Complexity: O(n), Space Complexity: O(k)
     */
    public static int[] slidingWindowMaximum(int[] arr, int k) {
        if (arr.length == 0 || k == 0) return new int[0];
        
        int[] result = new int[arr.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices
        
        for (int i = 0; i < arr.length; i++) {
            // Remove indices outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove smaller elements from rear
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            // Add to result if window is complete
            if (i >= k - 1) {
                result[i - k + 1] = arr[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * MAXIMUM SUM of K CONSECUTIVE ELEMENTS (Sliding Window)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxSumKConsecutive(int[] arr, int k) {
        if (arr.length < k) return -1;
        
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    /**
     * LONGEST SUBARRAY WITH SUM K
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static int longestSubarrayWithSumK(int[] arr, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int sum = 0, maxLength = 0;
        
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            
            if (sum == k) {
                maxLength = i + 1;
            } else if (prefixSumMap.containsKey(sum - k)) {
                maxLength = Math.max(maxLength, i - prefixSumMap.get(sum - k));
            }
            
            // Store first occurrence only
            if (!prefixSumMap.containsKey(sum)) {
                prefixSumMap.put(sum, i);
            }
        }
        
        return maxLength;
    }
    
    /**
     * STOCK BUY SELL - Single Transaction
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxProfitSingleTransaction(int[] prices) {
        if (prices.length < 2) return 0;
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        
        return maxProfit;
    }
    
    /**
     * STOCK BUY SELL - Multiple Transactions
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxProfitMultipleTransactions(int[] prices) {
        int totalProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        
        return totalProfit;
    }
    
    /**
     * TRAPPING RAIN WATER
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int trapRainWater(int[] height) {
        if (height.length == 0) return 0;
        
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        
        return water;
    }
    
    /**
     * CONTAINER WITH MOST WATER
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int width = right - left;
            int currentArea = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, currentArea);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    /**
     * LONGEST INCREASING SUBSEQUENCE LENGTH
     * Time Complexity: O(n log n), Space Complexity: O(n)
     */
    public static int lengthOfLIS(int[] arr) {
        if (arr.length == 0) return 0;
        
        List<Integer> dp = new ArrayList<>();
        
        for (int num : arr) {
            int pos = Collections.binarySearch(dp, num);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            
            if (pos == dp.size()) {
                dp.add(num);
            } else {
                dp.set(pos, num);
            }
        }
        
        return dp.size();
    }
    
    /**
     * MISSING NUMBER in array 1 to n
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int findMissingNumber(int[] arr, int n) {
        // Using XOR operation
        int xor1 = 0, xor2 = 0;
        
        for (int i = 0; i < arr.length; i++) {
            xor2 ^= arr[i];
        }
        
        for (int i = 1; i <= n; i++) {
            xor1 ^= i;
        }
        
        return xor1 ^ xor2;
    }
    
    /**
     * FIND DUPLICATE NUMBER
     * Time Complexity: O(n), Space Complexity: O(1)
     * Floyd's Cycle Detection Algorithm
     */
    public static int findDuplicate(int[] arr) {
        int slow = arr[0];
        int fast = arr[0];
        
        // Find intersection point
        do {
            slow = arr[slow];
            fast = arr[arr[fast]];
        } while (slow != fast);
        
        // Find entrance to cycle
        fast = arr[0];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        
        return slow;
    }
    
    /**
     * MAJORITY ELEMENT (appears more than n/2 times)
     * Time Complexity: O(n), Space Complexity: O(1)
     * Boyer-Moore Voting Algorithm
     */
    public static int majorityElement(int[] arr) {
        int candidate = arr[0];
        int count = 1;
        
        // Find candidate
        for (int i = 1; i < arr.length; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }
        
        // Verify candidate (optional step if majority is guaranteed)
        count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
        
        return count > arr.length / 2 ? candidate : -1;
    }
    
    /**
     * NEXT GREATER ELEMENT
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static int[] nextGreaterElement(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        
        // Initialize result with -1
        Arrays.fill(result, -1);
        
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    /**
     * Demo and testing method
     */
    public static void main(String[] args) {
        System.out.println("=== Array Optimization Problems Demo ===\n");
        
        // 1. Kadane's Algorithm Demo
        System.out.println("1. KADANE'S ALGORITHM (Maximum Subarray Sum):");
        int[] kadaneArr = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Array: " + Arrays.toString(kadaneArr));
        int maxSum = maxSubarraySum(kadaneArr);
        System.out.println("Maximum subarray sum: " + maxSum);
        
        int[] indices = maxSubarrayWithIndices(kadaneArr);
        System.out.println("Subarray indices: [" + indices[0] + ", " + indices[1] + "], Sum: " + indices[2]);
        System.out.println();
        
        // 2. Two Sum Demo
        System.out.println("2. TWO SUM PROBLEM:");
        int[] twoSumArr = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Array: " + Arrays.toString(twoSumArr));
        System.out.println("Target: " + target);
        int[] twoSumResult = twoSum(twoSumArr, target);
        System.out.println("Indices: " + Arrays.toString(twoSumResult));
        System.out.println();
        
        // 3. Three Sum Demo
        System.out.println("3. THREE SUM PROBLEM:");
        int[] threeSumArr = {-1, 0, 1, 2, -1, -4};
        System.out.println("Array: " + Arrays.toString(threeSumArr));
        List<List<Integer>> threeSumResult = threeSum(threeSumArr, 0);
        System.out.println("Three sum triplets: " + threeSumResult);
        System.out.println();
        
        // 4. Sliding Window Maximum Demo
        System.out.println("4. SLIDING WINDOW MAXIMUM:");
        int[] windowArr = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Array: " + Arrays.toString(windowArr));
        System.out.println("Window size: " + k);
        int[] windowMaxResult = slidingWindowMaximum(windowArr, k);
        System.out.println("Sliding window maximums: " + Arrays.toString(windowMaxResult));
        System.out.println();
        
        // 5. Stock Trading Demo
        System.out.println("5. STOCK TRADING:");
        int[] stockPrices = {7, 1, 5, 3, 6, 4};
        System.out.println("Stock prices: " + Arrays.toString(stockPrices));
        int singleProfit = maxProfitSingleTransaction(stockPrices);
        int multipleProfit = maxProfitMultipleTransactions(stockPrices);
        System.out.println("Max profit (single transaction): " + singleProfit);
        System.out.println("Max profit (multiple transactions): " + multipleProfit);
        System.out.println();
        
        // 6. Trapping Rain Water Demo
        System.out.println("6. TRAPPING RAIN WATER:");
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Heights: " + Arrays.toString(heights));
        int waterTrapped = trapRainWater(heights);
        System.out.println("Water trapped: " + waterTrapped);
        System.out.println();
        
        // 7. Container with Most Water Demo
        System.out.println("7. CONTAINER WITH MOST WATER:");
        int[] containerHeights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Heights: " + Arrays.toString(containerHeights));
        int maxWater = maxArea(containerHeights);
        System.out.println("Maximum water area: " + maxWater);
        System.out.println();
        
        // 8. Missing Number Demo
        System.out.println("8. MISSING NUMBER:");
        int[] missingArr = {1, 2, 4, 6, 3, 7, 8};
        System.out.println("Array: " + Arrays.toString(missingArr));
        int missing = findMissingNumber(missingArr, 8);
        System.out.println("Missing number: " + missing);
        System.out.println();
        
        // 9. Majority Element Demo
        System.out.println("9. MAJORITY ELEMENT:");
        int[] majorityArr = {3, 2, 3, 3, 3, 2, 3};
        System.out.println("Array: " + Arrays.toString(majorityArr));
        int majority = majorityElement(majorityArr);
        System.out.println("Majority element: " + majority);
        System.out.println();
        
        // 10. Next Greater Element Demo
        System.out.println("10. NEXT GREATER ELEMENT:");
        int[] ngeArr = {4, 5, 2, 25, 7, 8};
        System.out.println("Array: " + Arrays.toString(ngeArr));
        int[] ngeResult = nextGreaterElement(ngeArr);
        System.out.println("Next greater elements: " + Arrays.toString(ngeResult));
        
        System.out.println("\n=== Optimization Techniques Summary ===");
        printOptimizationTechniques();
        
        System.out.println("\n=== End of Array Optimization Demo ===");
    }
    
    /**
     * Print optimization techniques summary
     */
    private static void printOptimizationTechniques() {
        System.out.println("Technique           | From Complexity | To Complexity   | Key Insight");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Kadane's Algorithm  | O(n³)          | O(n)            | DP with running sum");
        System.out.println("Two Pointers        | O(n²)          | O(n)            | Sorted array property");
        System.out.println("Sliding Window      | O(n*k)         | O(n)            | Reuse computation");
        System.out.println("Hash Map            | O(n²)          | O(n)            | Space-time tradeoff");
        System.out.println("Stack/Deque         | O(n²)          | O(n)            | Maintain useful info");
        System.out.println("Boyer-Moore Voting  | O(n) + O(n)    | O(n) + O(1)     | Clever counting");
        System.out.println("Floyd's Cycle       | O(n) + O(n)    | O(n) + O(1)     | Linked list property");
    }
}
