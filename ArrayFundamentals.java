package com.misc.arrays;

import java.util.Arrays;

/**
 * Array Fundamentals - Comprehensive Guide for Interview Preparation
 * 
 * This program demonstrates essential array operations and concepts that are
 * frequently tested in technical interviews. It covers:
 * 
 * CORE CONCEPTS:
 * 1. Array declaration and initialization patterns
 * 2. Memory allocation and default values
 * 3. Array element access and modification
 * 4. Array length property and bounds checking
 * 5. Different traversal techniques
 * 6. Object arrays and reference handling
 * 7. Exception handling for array operations
 * 8. Method parameters and return values with arrays
 * 9. Performance characteristics and trade-offs
 * 
 * INTERVIEW FOCUS:
 * - Understanding array vs ArrayList differences
 * - Memory layout and cache performance
 * - Common pitfalls and edge cases
 * - Best practices for array manipulation
 * 
 * Prerequisites: Complete ArrayBasicConcepts.java before studying this class
 * Next: Study specialized array algorithm classes for advanced topics
 * 
 * @author Vishal.Kamaliya
 * @since 2025
 */
public class ArrayFundamentals {
    
    // =================================================================================
    // SECTION 1: DECLARATION AND INITIALIZATION PATTERNS
    // =================================================================================
    
    /**
     * Demonstrates different array declaration and initialization patterns
     * INTERVIEW TIP: Know the differences between these approaches
     */
    private static void demonstrateDeclarationPatterns() {
        System.out.println("=== DECLARATION AND INITIALIZATION PATTERNS ===");
        
        // Method 1: Declare then initialize (two-step process)
        int[] arr1;
        arr1 = new int[5]; // All elements initialized to 0
        System.out.println("Method 1 (two-step): " + Arrays.toString(arr1));
        
        // Method 2: Declare and initialize together
        int[] arr2 = new int[5]; // More common and preferred
        System.out.println("Method 2 (one-step): " + Arrays.toString(arr2));
        
        // Method 3: Array literal initialization (compile-time known values)
        int[] arr3 = {10, 20, 30, 40, 50};
        System.out.println("Method 3 (literal): " + Arrays.toString(arr3));
        
        // Method 4: Anonymous array (useful for method calls)
        int[] arr4 = new int[]{1, 2, 3, 4, 5};
        System.out.println("Method 4 (anonymous): " + Arrays.toString(arr4));
        
        // INTERVIEW QUESTION: What are default values for different types?
        boolean[] boolArray = new boolean[3]; // default: false
        char[] charArray = new char[3];       // default: '\u0000'
        String[] stringArray = new String[3]; // default: null
        
        System.out.println("Default boolean values: " + Arrays.toString(boolArray));
        System.out.println("Default char values: " + Arrays.toString(charArray));
        System.out.println("Default String values: " + Arrays.toString(stringArray));
        System.out.println();
    }
    
    // =================================================================================
    // SECTION 2: ARRAY ACCESS AND MODIFICATION
    // =================================================================================
    
    /**
     * Demonstrates array access and modification operations
     * INTERVIEW TIP: Always consider bounds checking in real applications
     */
    private static void demonstrateAccessAndModification() {
        System.out.println("=== ACCESS AND MODIFICATION OPERATIONS ===");
        
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        // Safe element access with bounds checking
        int index = 2;
        if (index >= 0 && index < numbers.length) {
            System.out.println("Element at index " + index + ": " + numbers[index]);
            numbers[index] = 99; // Modification
            System.out.println("After modification: " + Arrays.toString(numbers));
        }
        
        // Array length property (final field, not method)
        System.out.println("Array length: " + numbers.length);
        
        // Bulk modification
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] *= 2; // Double all values
        }
        System.out.println("After doubling: " + Arrays.toString(numbers));
        System.out.println();
    }
    
    // =================================================================================
    // SECTION 3: TRAVERSAL TECHNIQUES
    // =================================================================================
    
    /**
     * Demonstrates different array traversal techniques
     * INTERVIEW TIP: Choose the right loop based on your needs
     */
    private static void demonstrateTraversalTechniques() {
        System.out.println("=== TRAVERSAL TECHNIQUES ===");
        
        int[] array = {1, 2, 3, 4, 5};
        
        // Traditional for loop - use when you need index
        System.out.print("Traditional for loop: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + i + "]=" + array[i] + " ");
        }
        System.out.println();
        
        // Enhanced for loop - use when you only need values
        System.out.print("Enhanced for loop: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        // Reverse traversal - common in many algorithms
        System.out.print("Reverse traversal: ");
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        
        // While loop traversal
        System.out.print("While loop: ");
        int i = 0;
        while (i < array.length) {
            System.out.print(array[i] + " ");
            i++;
        }
        System.out.println();
        
        // Two-pointer technique - very important for interviews
        System.out.print("Two-pointer technique: ");
        for (int left = 0, right = array.length - 1; left <= right; left++, right--) {
            if (left == right) {
                System.out.print("[" + array[left] + "] ");
            } else {
                System.out.print("(" + array[left] + "," + array[right] + ") ");
            }
        }
        System.out.println();
        System.out.println();
    }
    
    // =================================================================================
    // SECTION 4: OBJECT ARRAYS AND REFERENCES
    // =================================================================================
    
    /**
     * Student class to demonstrate object arrays
     * Important for understanding reference vs primitive arrays
     */
    static class Student {
        public int rollNo;
        public String name;
        
        Student(int rollNo, String name) {
            this.rollNo = rollNo;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return "Student{rollNo=" + rollNo + ", name='" + name + "'}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Student student = (Student) obj;
            return rollNo == student.rollNo && name.equals(student.name);
        }
    }
    
    /**
     * Demonstrates object arrays and reference handling
     * INTERVIEW TIP: Understand the difference between primitive and reference arrays
     */
    private static void demonstrateObjectArrays() {
        System.out.println("=== OBJECT ARRAYS AND REFERENCES ===");
        
        // Create array of Student objects
        Student[] students = new Student[3];
        System.out.println("Empty object array: " + Arrays.toString(students));
        
        // Populate with objects
        students[0] = new Student(101, "Alice");
        students[1] = new Student(102, "Bob");
        students[2] = new Student(103, "Charlie");
        
        System.out.println("\nPopulated object array:");
        for (int i = 0; i < students.length; i++) {
            System.out.println("students[" + i + "] = " + students[i]);
        }
        
        // Reference sharing demonstration
        Student[] studentsCopy = students; // Shallow copy - same references
        studentsCopy[0].name = "Alice Updated";
        System.out.println("\nAfter modifying through copy reference:");
        System.out.println("Original: " + students[0]);
        System.out.println("Copy: " + studentsCopy[0]);
        
        // Deep copy demonstration
        Student[] studentsDeepCopy = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            studentsDeepCopy[i] = new Student(students[i].rollNo, students[i].name);
        }
        studentsDeepCopy[0].name = "Alice Deep Copy";
        System.out.println("\nAfter deep copy modification:");
        System.out.println("Original: " + students[0]);
        System.out.println("Deep copy: " + studentsDeepCopy[0]);
        System.out.println();
    }
    
    // =================================================================================
    // SECTION 5: ERROR HANDLING AND EDGE CASES
    // =================================================================================
    
    /**
     * Demonstrates error handling and edge cases
     * INTERVIEW TIP: Always discuss error handling in your solutions
     */
    private static void demonstrateErrorHandling() {
        System.out.println("=== ERROR HANDLING AND EDGE CASES ===");
        
        int[] array = {1, 2, 3, 4, 5};
        
        // ArrayIndexOutOfBoundsException handling
        try {
            System.out.println("Attempting to access index 10 in array of length " + array.length);
            int value = array[10]; // This will throw exception
            System.out.println("Value: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        
        // Null array handling
        int[] nullArray = null;
        try {
            System.out.println("Attempting to access null array length");
            int length = nullArray.length; // This will throw NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught exception: Null array access");
        }
        
        // Safe array operations
        System.out.println("\nSafe array operations:");
        System.out.println("Safe access result: " + safeArrayAccess(array, 2));
        System.out.println("Safe access result: " + safeArrayAccess(array, 10));
        System.out.println("Safe access result: " + safeArrayAccess(null, 0));
        
        // Edge case: Empty array
        int[] emptyArray = new int[0];
        System.out.println("Empty array length: " + emptyArray.length);
        System.out.println("Empty array: " + Arrays.toString(emptyArray));
        System.out.println();
    }
    
    /**
     * Safe array access method
     */
    private static String safeArrayAccess(int[] array, int index) {
        if (array == null) {
            return "Array is null";
        }
        if (index < 0 || index >= array.length) {
            return "Index out of bounds";
        }
        return "Value at index " + index + ": " + array[index];
    }
    
    // =================================================================================
    // SECTION 6: METHOD PARAMETERS AND RETURN VALUES
    // =================================================================================
    
    /**
     * Demonstrates arrays as method parameters and return values
     * INTERVIEW TIP: Understand pass-by-reference for arrays
     */
    private static void demonstrateMethodArrays() {
        System.out.println("=== METHOD PARAMETERS AND RETURN VALUES ===");
        
        int[] original = {1, 2, 3, 4, 5};
        System.out.println("Original array: " + Arrays.toString(original));
        
        // Arrays are passed by reference
        modifyArray(original);
        System.out.println("After method call: " + Arrays.toString(original));
        
        // Method returning an array
        int[] generated = generateArray(5, 10);
        System.out.println("Generated array: " + Arrays.toString(generated));
        
        // Method with array parameter and return
        int[] doubled = createDoubledArray(original);
        System.out.println("Doubled array: " + Arrays.toString(doubled));
        
        // Varargs demonstration
        int sum1 = calculateSum(1, 2, 3, 4, 5);
        int sum2 = calculateSum(original);
        System.out.println("Sum using varargs: " + sum1);
        System.out.println("Sum using array: " + sum2);
        
        // Array as return value from method
        int[] fibonacci = generateFibonacci(8);
        System.out.println("Fibonacci sequence: " + Arrays.toString(fibonacci));
        System.out.println();
    }
    
    /**
     * Method that modifies the passed array
     */
    private static void modifyArray(int[] arr) {
        if (arr != null && arr.length > 0) {
            arr[0] = 999; // This change will be visible to caller
        }
    }
    
    /**
     * Method that returns a new array
     */
    private static int[] generateArray(int size, int multiplier) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (i + 1) * multiplier;
        }
        return result;
    }
    
    /**
     * Method that creates a new array from existing one
     */
    private static int[] createDoubledArray(int[] source) {
        if (source == null) return null;
        
        int[] result = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i] * 2;
        }
        return result;
    }
    
    /**
     * Varargs method (variable arguments)
     */
    private static int calculateSum(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
    
    /**
     * Generate Fibonacci sequence as array
     */
    private static int[] generateFibonacci(int count) {
        if (count <= 0) return new int[0];
        if (count == 1) return new int[]{0};
        if (count == 2) return new int[]{0, 1};
        
        int[] fib = new int[count];
        fib[0] = 0;
        fib[1] = 1;
        
        for (int i = 2; i < count; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        
        return fib;
    }
    
    // =================================================================================
    // SECTION 7: PERFORMANCE ANALYSIS
    // =================================================================================
    
    /**
     * Demonstrates performance characteristics
     * INTERVIEW TIP: Be prepared to discuss time/space complexity
     */
    private static void demonstratePerformanceAnalysis() {
        System.out.println("=== PERFORMANCE ANALYSIS ===");
        
        int[] largeArray = new int[1000];
        
        // Initialize array - O(n) time
        long startTime = System.nanoTime();
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2;
        }
        long endTime = System.nanoTime();
        System.out.println("Array initialization time: " + (endTime - startTime) + " nanoseconds");
        
        // Random access - O(1) time
        startTime = System.nanoTime();
        int value = largeArray[500]; // Direct index access
        endTime = System.nanoTime();
        System.out.println("Random access time: " + (endTime - startTime) + " nanoseconds");
        
        // Linear search - O(n) time
        startTime = System.nanoTime();
        int target = 500;
        int foundIndex = -1;
        for (int i = 0; i < largeArray.length; i++) {
            if (largeArray[i] == target) {
                foundIndex = i;
                break;
            }
        }
        endTime = System.nanoTime();
        System.out.println("Linear search time: " + (endTime - startTime) + " nanoseconds");
        
        System.out.println("\nPerformance Characteristics:");
        System.out.println("• Access by index: O(1) - Constant time");
        System.out.println("• Search (unsorted): O(n) - Linear time");
        System.out.println("• Insertion at end: O(1) - if space available");
        System.out.println("• Insertion in middle: O(n) - due to shifting");
        System.out.println("• Deletion: O(n) - due to shifting");
        System.out.println("• Space complexity: O(n) - linear space");
        
        System.out.println("\nArray vs ArrayList:");
        System.out.println("• Array: Fixed size, primitive types, better performance");
        System.out.println("• ArrayList: Dynamic size, objects only, more methods");
        System.out.println();
    }
    
    // =================================================================================
    // SECTION 8: COMMON INTERVIEW OPERATIONS
    // =================================================================================
    
    /**
     * Demonstrates common array operations asked in interviews
     */
    private static void demonstrateInterviewOperations() {
        System.out.println("=== COMMON INTERVIEW OPERATIONS ===");
        
        int[] numbers = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        System.out.println("Sample array: " + Arrays.toString(numbers));
        
        // Find sum of all elements
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Sum of elements: " + sum);
        
        // Find maximum and minimum
        int max = numbers[0], min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            max = Math.max(max, numbers[i]);
            min = Math.min(min, numbers[i]);
        }
        System.out.println("Maximum: " + max + ", Minimum: " + min);
        
        // Check if array contains duplicates
        boolean hasDuplicates = false;
        for (int i = 0; i < numbers.length && !hasDuplicates; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    hasDuplicates = true;
                    break;
                }
            }
        }
        System.out.println("Has duplicates: " + hasDuplicates);
        
        // Reverse array in-place
        int[] toReverse = Arrays.copyOf(numbers, numbers.length);
        for (int i = 0; i < toReverse.length / 2; i++) {
            int temp = toReverse[i];
            toReverse[i] = toReverse[toReverse.length - 1 - i];
            toReverse[toReverse.length - 1 - i] = temp;
        }
        System.out.println("Reversed array: " + Arrays.toString(toReverse));
        
        // Find second largest element
        int largest = Integer.MIN_VALUE, secondLargest = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }
        System.out.println("Second largest: " + (secondLargest == Integer.MIN_VALUE ? "Not found" : secondLargest));
        System.out.println();
    }
    
    // =================================================================================
    // MAIN METHOD - COMPREHENSIVE DEMONSTRATION
    // =================================================================================
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    ARRAY FUNDAMENTALS GUIDE                    ║");
        System.out.println("║              Essential Concepts for Interviews                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        // Run all demonstrations in logical order
        demonstrateDeclarationPatterns();
        demonstrateAccessAndModification();
        demonstrateTraversalTechniques();
        demonstrateObjectArrays();
        demonstrateErrorHandling();
        demonstrateMethodArrays();
        demonstratePerformanceAnalysis();
        demonstrateInterviewOperations();
        
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  Mastery Check: Can you explain each concept to an interviewer?║");
        System.out.println("║  Next: Study MultiDimensionalArrayAdvanced.java for matrices   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}