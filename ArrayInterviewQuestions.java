package com.misc.arrays;

import java.util.*;

/**
 * ArrayInterviewQuestions - Most common array interview questions and solutions
 * 
 * This class covers the most frequently asked array problems in technical interviews:
 * 1. Find duplicates and missing numbers
 * 2. Subarray problems (sum, products)
 * 3. Array manipulation and transformation
 * 4. Frequency and counting problems
 * 5. Merge and intersection problems
 * 6. Palindrome and pattern problems
 * 7. Game theory and strategy problems
 * 8. Advanced optimization problems
 * 
 * Company-wise popular questions:
 * - Google: Maximum subarray, merge intervals, rain water trapping
 * - Amazon: Two sum variations, rotate array, product except self
 * - Microsoft: Missing number, majority element, container with water
 * - Facebook: Move zeros, merge sorted arrays, valid anagram
 * - Apple: Pascal's triangle, plus one, remove duplicates
 * 
 * @author Vishal.Kamaliya
 */
public class ArrayInterviewQuestions {
    
    /**
     * 1. FIND ALL DUPLICATES IN ARRAY
     * Array contains integers 1 to n, some appear twice
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(Math.abs(nums[i]));
            } else {
                nums[index] = -nums[index];
            }
        }
        
        return result;
    }
    
    /**
     * 2. PRODUCT OF ARRAY EXCEPT SELF
     * Cannot use division operator
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Calculate left products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Calculate right products and multiply
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        
        return result;
    }
    
    /**
     * 3. MERGE INTERVALS
     * Time Complexity: O(n log n), Space Complexity: O(n)
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        
        for (int i = 1; i < intervals.length; i++) {
            if (current[1] >= intervals[i][0]) {
                // Overlapping intervals, merge them
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                // Non-overlapping, add current and move to next
                merged.add(current);
                current = intervals[i];
            }
        }
        
        merged.add(current); // Add the last interval
        return merged.toArray(new int[merged.size()][]);
    }
    
    /**
     * 4. MEETING ROOMS II
     * Minimum number of meeting rooms required
     * Time Complexity: O(n log n), Space Complexity: O(n)
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        // Create start and end time arrays
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int rooms = 0, maxRooms = 0;
        int startPtr = 0, endPtr = 0;
        
        while (startPtr < intervals.length) {
            if (starts[startPtr] < ends[endPtr]) {
                rooms++;
                maxRooms = Math.max(maxRooms, rooms);
                startPtr++;
            } else {
                rooms--;
                endPtr++;
            }
        }
        
        return maxRooms;
    }
    
    /**
     * 5. BEST TIME TO BUY AND SELL STOCK WITH COOLDOWN
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxProfitWithCooldown(int[] prices) {
        if (prices.length <= 1) return 0;
        
        int hold = -prices[0]; // Max profit when holding stock
        int sold = 0;          // Max profit when just sold
        int rest = 0;          // Max profit when resting
        
        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;
            int prevSold = sold;
            int prevRest = rest;
            
            hold = Math.max(prevHold, prevRest - prices[i]);
            sold = prevHold + prices[i];
            rest = Math.max(prevRest, prevSold);
        }
        
        return Math.max(sold, rest);
    }
    
    /**
     * 6. FIRST MISSING POSITIVE
     * Find smallest positive integer not in array
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // Place each positive integer i at index i-1
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap nums[i] with nums[nums[i] - 1]
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        
        // Find first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        
        return n + 1;
    }
    
    /**
     * 7. SUBARRAY SUM EQUALS K
     * Count number of subarrays with sum equal to k
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Empty subarray
        
        int sum = 0, count = 0;
        
        for (int num : nums) {
            sum += num;
            
            if (prefixSumCount.containsKey(sum - k)) {
                count += prefixSumCount.get(sum - k);
            }
            
            prefixSumCount.put(sum, prefixSumCount.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * 8. LARGEST NUMBER FROM ARRAY
     * Arrange numbers to form largest possible number
     * Time Complexity: O(n log n), Space Complexity: O(n)
     */
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        // Custom comparator: if a+b > b+a, then a should come before b
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        
        // Handle edge case: all zeros
        if (strs[0].equals("0")) return "0";
        
        StringBuilder result = new StringBuilder();
        for (String str : strs) {
            result.append(str);
        }
        
        return result.toString();
    }
    
    /**
     * 9. MAXIMUM PRODUCT SUBARRAY
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                // Swap max and min when current number is negative
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }
            
            maxEndingHere = Math.max(nums[i], maxEndingHere * nums[i]);
            minEndingHere = Math.min(nums[i], minEndingHere * nums[i]);
            
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    /**
     * 10. PASCALS TRIANGLE
     * Generate first numRows of Pascal's triangle
     * Time Complexity: O(n²), Space Complexity: O(n²)
     */
    public static List<List<Integer>> generatePascalsTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int value = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    row.add(value);
                }
            }
            
            triangle.add(row);
        }
        
        return triangle;
    }
    
    /**
     * 11. VALID ANAGRAM
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] count = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        for (int c : count) {
            if (c != 0) return false;
        }
        
        return true;
    }
    
    /**
     * 12. GROUP ANAGRAMS
     * Time Complexity: O(n * k log k), Space Complexity: O(n * k)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    /**
     * 13. TOP K FREQUENT ELEMENTS
     * Time Complexity: O(n log k), Space Complexity: O(n)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            heap.offer(new int[]{entry.getKey(), entry.getValue()});
            if (heap.size() > k) {
                heap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll()[0];
        }
        
        return result;
    }
    
    /**
     * 14. SLIDING WINDOW MEDIAN
     * Time Complexity: O(n log k), Space Complexity: O(k)
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        
        // Use two heaps to maintain median
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Add to appropriate heap
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.offer(nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }
            
            // Balance heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
            
            // Calculate median when window is complete
            if (i >= k - 1) {
                if (k % 2 == 1) {
                    result[i - k + 1] = maxHeap.size() > minHeap.size() ? 
                                       maxHeap.peek() : minHeap.peek();
                } else {
                    result[i - k + 1] = ((long)maxHeap.peek() + minHeap.peek()) / 2.0;
                }
                
                // Remove element going out of window
                int toRemove = nums[i - k + 1];
                if (toRemove <= maxHeap.peek()) {
                    maxHeap.remove(toRemove);
                } else {
                    minHeap.remove(toRemove);
                }
                
                // Rebalance
                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.offer(maxHeap.poll());
                } else if (minHeap.size() > maxHeap.size() + 1) {
                    maxHeap.offer(minHeap.poll());
                }
            }
        }
        
        return result;
    }
    
    /**
     * 15. JUMP GAME II
     * Minimum number of jumps to reach end
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static int jump(int[] nums) {
        int jumps = 0, currentEnd = 0, farthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        
        return jumps;
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
        System.out.println("=== Array Interview Questions Demo ===\n");
        
        // 1. Find Duplicates Demo
        System.out.println("1. FIND ALL DUPLICATES:");
        int[] duplicateArr = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Array: " + Arrays.toString(duplicateArr));
        List<Integer> duplicates = findDuplicates(duplicateArr.clone());
        System.out.println("Duplicates: " + duplicates);
        System.out.println();
        
        // 2. Product Except Self Demo
        System.out.println("2. PRODUCT OF ARRAY EXCEPT SELF:");
        int[] productArr = {1, 2, 3, 4};
        System.out.println("Array: " + Arrays.toString(productArr));
        int[] productResult = productExceptSelf(productArr);
        System.out.println("Product except self: " + Arrays.toString(productResult));
        System.out.println();
        
        // 3. Merge Intervals Demo
        System.out.println("3. MERGE INTERVALS:");
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Intervals: " + Arrays.deepToString(intervals));
        int[][] merged = mergeIntervals(intervals);
        System.out.println("Merged: " + Arrays.deepToString(merged));
        System.out.println();
        
        // 4. Meeting Rooms Demo
        System.out.println("4. MEETING ROOMS II:");
        int[][] meetings = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Meetings: " + Arrays.deepToString(meetings));
        int rooms = minMeetingRooms(meetings);
        System.out.println("Minimum meeting rooms: " + rooms);
        System.out.println();
        
        // 5. First Missing Positive Demo
        System.out.println("5. FIRST MISSING POSITIVE:");
        int[] missingArr = {3, 4, -1, 1};
        System.out.println("Array: " + Arrays.toString(missingArr));
        int firstMissing = firstMissingPositive(missingArr.clone());
        System.out.println("First missing positive: " + firstMissing);
        System.out.println();
        
        // 6. Subarray Sum K Demo
        System.out.println("6. SUBARRAY SUM EQUALS K:");
        int[] sumArr = {1, 1, 1};
        int k = 2;
        System.out.println("Array: " + Arrays.toString(sumArr));
        System.out.println("Target sum: " + k);
        int subarrayCount = subarraySum(sumArr, k);
        System.out.println("Number of subarrays: " + subarrayCount);
        System.out.println();
        
        // 7. Largest Number Demo
        System.out.println("7. LARGEST NUMBER:");
        int[] numberArr = {10, 2};
        System.out.println("Array: " + Arrays.toString(numberArr));
        String largest = largestNumber(numberArr);
        System.out.println("Largest number: " + largest);
        System.out.println();
        
        // 8. Maximum Product Subarray Demo
        System.out.println("8. MAXIMUM PRODUCT SUBARRAY:");
        int[] maxProductArr = {2, 3, -2, 4};
        System.out.println("Array: " + Arrays.toString(maxProductArr));
        int maxProd = maxProduct(maxProductArr);
        System.out.println("Maximum product: " + maxProd);
        System.out.println();
        
        // 9. Pascal's Triangle Demo
        System.out.println("9. PASCAL'S TRIANGLE:");
        int numRows = 5;
        System.out.println("Number of rows: " + numRows);
        List<List<Integer>> pascal = generatePascalsTriangle(numRows);
        System.out.println("Pascal's triangle: " + pascal);
        System.out.println();
        
        // 10. Group Anagrams Demo
        System.out.println("10. GROUP ANAGRAMS:");
        String[] anagramStrs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Strings: " + Arrays.toString(anagramStrs));
        List<List<String>> groupedAnagrams = groupAnagrams(anagramStrs);
        System.out.println("Grouped anagrams: " + groupedAnagrams);
        System.out.println();
        
        // 11. Top K Frequent Demo
        System.out.println("11. TOP K FREQUENT ELEMENTS:");
        int[] freqArr = {1, 1, 1, 2, 2, 3};
        int topK = 2;
        System.out.println("Array: " + Arrays.toString(freqArr));
        System.out.println("K: " + topK);
        int[] topKResult = topKFrequent(freqArr, topK);
        System.out.println("Top " + topK + " frequent: " + Arrays.toString(topKResult));
        System.out.println();
        
        // 12. Jump Game II Demo
        System.out.println("12. JUMP GAME II:");
        int[] jumpArr = {2, 3, 1, 1, 4};
        System.out.println("Array: " + Arrays.toString(jumpArr));
        int minJumps = jump(jumpArr);
        System.out.println("Minimum jumps: " + minJumps);
        
        System.out.println("\n=== Interview Question Categories ===");
        printInterviewCategories();
        
        System.out.println("\n=== End of Array Interview Questions Demo ===");
    }
    
    /**
     * Print interview question categories and difficulty
     */
    private static void printInterviewCategories() {
        System.out.println("Category                | Difficulty | Key Technique           | Companies");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Two Pointers           | Easy       | Sort + Two pointers     | Amazon, Google");
        System.out.println("Sliding Window         | Medium     | Window expansion        | Microsoft, FB");
        System.out.println("Hash Map               | Easy       | Frequency counting      | All companies");
        System.out.println("Prefix Sum             | Medium     | Cumulative sum          | Google, Apple");
        System.out.println("Dynamic Programming    | Hard       | Optimal substructure    | Google, FB");
        System.out.println("Greedy                 | Medium     | Local optimal choice    | Amazon, MS");
        System.out.println("Binary Search          | Medium     | Divide and conquer      | All companies");
        System.out.println("Stack/Queue            | Medium     | LIFO/FIFO properties    | Amazon, Apple");
        System.out.println("Heap                   | Hard       | Priority-based sorting  | Google, FB");
        System.out.println("Graph on Grid          | Hard       | DFS/BFS traversal       | FB, Google");
    }
}
