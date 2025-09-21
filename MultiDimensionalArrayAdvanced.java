package com.misc.arrays;

import java.util.Scanner;

/**
 * Multi-Dimensional Arrays - Advanced Interview Preparation
 * 
 * This class provides comprehensive coverage of multi-dimensional arrays,
 * which are essential for matrix problems, dynamic programming, and graph algorithms
 * commonly asked in technical interviews.
 * 
 * COVERAGE:
 * 1. 2D Arrays (Matrices) - Declaration, initialization, traversal
 * 2. 3D Arrays - Concept understanding and practical usage
 * 3. Jagged Arrays - Arrays with different row lengths
 * 4. Matrix Operations - Common interview patterns
 * 5. Performance Analysis - Memory layout and access patterns
 * 6. Real-world Applications - When to use multi-dimensional arrays
 * 
 * INTERVIEW FOCUS:
 * - Matrix traversal patterns (row-wise, column-wise, diagonal)
 * - Space complexity analysis for 2D arrays
 * - Dynamic allocation vs static initialization
 * - Common matrix algorithms (transpose, rotation, search)
 * 
 * Prerequisites: Complete ArrayBasicConcepts.java and ArrayFundamentals.java
 * Next: Study ArrayMatrix.java for advanced matrix algorithms
 * 
 * @author Vishal.Kamaliya
 */
public class MultiDimensionalArrayAdvanced {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    MULTI-DIMENSIONAL ARRAYS                   ║");
        System.out.println("║                Matrix & 3D Array Mastery Guide                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");
        
        // Section 1: Basic 2D Array Operations
        demonstrate2DArrayBasics();
        
        // Section 2: Advanced 2D Array Patterns
        demonstrate2DArrayPatterns();
        
        // Section 3: 3D Array Concepts
        demonstrate3DArrays();
        
        // Section 4: Jagged Arrays
        demonstrateJaggedArrays();
        
        // Section 5: Performance Analysis
        demonstratePerformanceAnalysis();
        
        // Section 6: Interview Common Patterns
        demonstrateInterviewPatterns();

        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║         Ready for matrix problems? Study ArrayMatrix.java!    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Demonstrates basic 2D array operations
     * INTERVIEW TIP: Master these fundamentals before attempting complex matrix problems
     */
    private static void demonstrate2DArrayBasics() {
        System.out.println("=== 2D ARRAY BASICS ===");
        
        // 1. Different initialization methods
        System.out.println("1. Initialization Methods:");
        
        // Static initialization with known values
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Static initialization:");
        print2DArray(matrix1);
        
        // Dynamic initialization with dimensions
        int[][] matrix2 = new int[3][4]; // 3 rows, 4 columns
        System.out.println("\nDynamic initialization (default zeros):");
        print2DArray(matrix2);
        
        // Manual population
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                matrix2[i][j] = (i + 1) * 10 + (j + 1);
            }
        }
        System.out.println("\nAfter manual population:");
        print2DArray(matrix2);
        
        // 2. Array properties
        System.out.println("\n2. Array Properties:");
        System.out.println("matrix1.length (rows): " + matrix1.length);
        System.out.println("matrix1[0].length (columns): " + matrix1[0].length);
        System.out.println("Total elements: " + (matrix1.length * matrix1[0].length));
        System.out.println();
    }
    
    /**
     * Demonstrates advanced 2D array traversal patterns
     * INTERVIEW TIP: These patterns are essential for matrix algorithm interviews
     */
    private static void demonstrate2DArrayPatterns() {
        System.out.println("=== 2D ARRAY TRAVERSAL PATTERNS ===");
        
        int[][] matrix = {
            {1,  2,  3,  4},
            {5,  6,  7,  8},
            {9,  10, 11, 12},
            {13, 14, 15, 16}
        };
        
        System.out.println("Sample Matrix:");
        print2DArray(matrix);
        
        // Row-wise traversal (most common)
        System.out.println("\nRow-wise traversal:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
        
        // Column-wise traversal
        System.out.println("\nColumn-wise traversal:");
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
        
        // Diagonal traversals (main diagonal)
        System.out.println("\nMain diagonal:");
        for (int i = 0; i < Math.min(matrix.length, matrix[0].length); i++) {
            System.out.print(matrix[i][i] + " ");
        }
        System.out.println();
        
        // Anti-diagonal
        System.out.println("\nAnti-diagonal:");
        for (int i = 0; i < matrix.length; i++) {
            int j = matrix[0].length - 1 - i;
            if (j >= 0) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
        
        // Spiral traversal (clockwise)
        System.out.println("\nSpiral traversal (clockwise):");
        spiralTraversal(matrix);
        System.out.println();
    }
    
    /**
     * Spiral traversal algorithm - common in interviews
     */
    private static void spiralTraversal(int[][] matrix) {
        if (matrix.length == 0) return;
        
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // Traverse top row
            for (int j = left; j <= right; j++) {
                System.out.print(matrix[top][j] + " ");
            }
            top++;
            
            // Traverse right column
            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--;
            
            // Traverse bottom row (if exists)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    System.out.print(matrix[bottom][j] + " ");
                }
                bottom--;
            }
            
            // Traverse left column (if exists)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left++;
            }
        }
    }
    
    /**
     * Demonstrates 3D array concepts
     * INTERVIEW TIP: Less common but good to understand the principle
     */
    private static void demonstrate3DArrays() {
        System.out.println("\n=== 3D ARRAY CONCEPTS ===");
        
        // Think of 3D array as array of 2D arrays (matrices)
        int[][][] cube = {
            {{1, 2}, {3, 4}},     // First 2D array
            {{5, 6}, {7, 8}},     // Second 2D array
            {{9, 10}, {11, 12}}   // Third 2D array
        };
        
        System.out.println("3D Array Structure (3x2x2):");
        print3DArray(cube);
        
        // Dynamic 3D array creation
        int[][][] dynamicCube = new int[2][3][4]; // 2 blocks, 3 rows, 4 columns
        
        // Fill with sequential values
        int value = 1;
        for (int i = 0; i < dynamicCube.length; i++) {
            for (int j = 0; j < dynamicCube[i].length; j++) {
                for (int k = 0; k < dynamicCube[i][j].length; k++) {
                    dynamicCube[i][j][k] = value++;
                }
            }
        }
        
        System.out.println("\nDynamic 3D Array (2x3x4):");
        print3DArray(dynamicCube);
        
        System.out.println("\n3D Array Properties:");
        System.out.println("Depth (blocks): " + dynamicCube.length);
        System.out.println("Height (rows): " + dynamicCube[0].length);
        System.out.println("Width (columns): " + dynamicCube[0][0].length);
        System.out.println("Total elements: " + (dynamicCube.length * dynamicCube[0].length * dynamicCube[0][0].length));
        System.out.println();
    }
    
    /**
     * Demonstrates jagged arrays (arrays with different row lengths)
     * INTERVIEW TIP: Common in problems involving triangular structures or variable data
     */
    private static void demonstrateJaggedArrays() {
        System.out.println("=== JAGGED ARRAYS ===");
        
        // Jagged array - each row can have different length
        int[][] jaggedArray = {
            {1},
            {2, 3},
            {4, 5, 6},
            {7, 8, 9, 10}
        };
        
        System.out.println("Jagged Array (Triangle pattern):");
        for (int i = 0; i < jaggedArray.length; i++) {
            for (int j = 0; j < jaggedArray[i].length; j++) {
                System.out.printf("%3d ", jaggedArray[i][j]);
            }
            System.out.println();
        }
        
        // Pascal's Triangle using jagged array
        System.out.println("\nPascal's Triangle (5 rows):");
        int[][] pascal = generatePascalsTriangle(5);
        for (int[] row : pascal) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
        
        // Memory analysis
        System.out.println("\nMemory Usage Comparison:");
        System.out.println("Regular 4x4 matrix: " + (4 * 4) + " elements");
        int jaggedElements = 0;
        for (int[] row : jaggedArray) {
            jaggedElements += row.length;
        }
        System.out.println("Jagged array: " + jaggedElements + " elements (60% space saving)");
        System.out.println();
    }
    
    /**
     * Generates Pascal's Triangle using jagged array
     */
    private static int[][] generatePascalsTriangle(int rows) {
        int[][] triangle = new int[rows][];
        
        for (int i = 0; i < rows; i++) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = 1; // First element is always 1
            triangle[i][i] = 1; // Last element is always 1
            
            // Fill middle elements
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
            }
        }
        
        return triangle;
    }
    
    /**
     * Demonstrates performance characteristics of multi-dimensional arrays
     */
    private static void demonstratePerformanceAnalysis() {
        System.out.println("=== PERFORMANCE ANALYSIS ===");
        
        int size = 1000;
        int[][] largeMatrix = new int[size][size];
        
        // Row-major vs Column-major access patterns
        long startTime, endTime;
        
        // Row-major access (cache-friendly)
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                largeMatrix[i][j] = i * j;
            }
        }
        endTime = System.nanoTime();
        long rowMajorTime = endTime - startTime;
        
        // Column-major access (cache-unfriendly)
        startTime = System.nanoTime();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                largeMatrix[i][j] = i * j;
            }
        }
        endTime = System.nanoTime();
        long columnMajorTime = endTime - startTime;
        
        System.out.println("Performance Comparison (" + size + "x" + size + " matrix):");
        System.out.println("Row-major access: " + rowMajorTime + " nanoseconds");
        System.out.println("Column-major access: " + columnMajorTime + " nanoseconds");
        System.out.println("Performance ratio: " + String.format("%.2f", (double)columnMajorTime / rowMajorTime));
        
        System.out.println("\nMemory Layout Insights:");
        System.out.println("• 2D arrays are stored as arrays of arrays in Java");
        System.out.println("• Row-major access is more cache-friendly");
        System.out.println("• Each row is a separate object with overhead");
        System.out.println("• Space complexity: O(rows × cols)");
        System.out.println();
    }
    
    /**
     * Demonstrates common interview patterns with multi-dimensional arrays
     */
    private static void demonstrateInterviewPatterns() {
        System.out.println("=== COMMON INTERVIEW PATTERNS ===");
        
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        System.out.println("Sample Matrix:");
        print2DArray(matrix);
        
        // Pattern 1: Count elements with specific property
        int evenCount = 0;
        for (int[] row : matrix) {
            for (int val : row) {
                if (val % 2 == 0) evenCount++;
            }
        }
        System.out.println("\nPattern 1 - Count even numbers: " + evenCount);
        
        // Pattern 2: Find maximum in each row
        System.out.println("\nPattern 2 - Maximum in each row:");
        for (int i = 0; i < matrix.length; i++) {
            int max = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                max = Math.max(max, matrix[i][j]);
            }
            System.out.println("Row " + i + " max: " + max);
        }
        
        // Pattern 3: Matrix transpose
        System.out.println("\nPattern 3 - Matrix transpose:");
        int[][] transposed = transposeMatrix(matrix);
        print2DArray(transposed);
        
        // Pattern 4: Search in matrix
        int target = 7;
        int[] position = searchInMatrix(matrix, target);
        System.out.println("\nPattern 4 - Search for " + target + ":");
        if (position != null) {
            System.out.println("Found at position: [" + position[0] + ", " + position[1] + "]");
        } else {
            System.out.println("Not found");
        }
        
        System.out.println("\nInterview Tips:");
        System.out.println("• Always check for null and empty arrays");
        System.out.println("• Consider edge cases (single row/column)");
        System.out.println("• Discuss time/space complexity");
        System.out.println("• Think about in-place vs extra space solutions");
    }
    
    /**
     * Transposes a matrix (swap rows and columns)
     */
    private static int[][] transposeMatrix(int[][] matrix) {
        if (matrix.length == 0) return new int[0][0];
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        
        return result;
    }
    
    /**
     * Searches for a target value in matrix
     * Returns [row, col] if found, null otherwise
     */
    private static int[] searchInMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    /**
     * Utility method to print 2D array with proper formatting
     */
    static void print2DArray(int[][] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Empty or null array");
            return;
        }
        
        for (int[] row : arr) {
            for (int val : row) {
                System.out.printf("%4d ", val);
            }
            System.out.println();
        }
    }
    
    /**
     * Utility method to print 3D array with clear block separation
     */
    static void print3DArray(int[][][] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Empty or null 3D array");
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Block " + i + ":");
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    System.out.printf("%4d ", arr[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
