package com.misc.arrays;

import java.util.*;

/**
 * ArrayMatrix - Comprehensive 2D array (matrix) operations and problems
 * 
 * This class covers:
 * 1. Matrix traversal patterns (row-wise, column-wise, diagonal, spiral)
 * 2. Matrix transformations (transpose, rotate, flip)
 * 3. Matrix search algorithms
 * 4. Matrix mathematical operations
 * 5. Path finding in matrix
 * 6. Matrix optimization problems
 * 7. Special matrix properties
 * 
 * Interview Topics:
 * - 2D array indexing and bounds checking
 * - Space-efficient matrix operations
 * - Matrix algorithms optimization
 * - Graph algorithms on grid/matrix
 * 
 * @author Vishal.Kamaliya
 */
public class ArrayMatrix {
    
    /**
     * SPIRAL MATRIX TRAVERSAL (Clockwise)
     * Time Complexity: O(m*n), Space Complexity: O(1)
     */
    public static List<Integer> spiralTraversal(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;
        
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // Traverse right
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;
            
            // Traverse down
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;
            
            // Traverse left (if still valid row)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }
            
            // Traverse up (if still valid column)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        
        return result;
    }
    
    /**
     * MATRIX TRANSPOSE
     * Time Complexity: O(m*n), Space Complexity: O(1) for square matrix
     */
    public static void transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        if (rows == cols) {
            // Square matrix - in-place transpose
            for (int i = 0; i < rows; i++) {
                for (int j = i + 1; j < cols; j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        } else {
            // Non-square matrix - need new matrix
            System.out.println("Non-square matrix transpose requires new matrix allocation");
        }
    }
    
    /**
     * ROTATE MATRIX 90 DEGREES CLOCKWISE
     * Time Complexity: O(n²), Space Complexity: O(1)
     */
    public static void rotateMatrix90Clockwise(int[][] matrix) {
        int n = matrix.length;
        
        // First transpose the matrix
        transpose(matrix);
        
        // Then reverse each row
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }
    }
    
    /**
     * ROTATE MATRIX 90 DEGREES COUNTERCLOCKWISE
     * Time Complexity: O(n²), Space Complexity: O(1)
     */
    public static void rotateMatrix90Counterclockwise(int[][] matrix) {
        int n = matrix.length;
        
        // First reverse each row
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }
        
        // Then transpose the matrix
        transpose(matrix);
    }
    
    /**
     * SEARCH IN ROW-WISE AND COLUMN-WISE SORTED MATRIX
     * Time Complexity: O(m + n), Space Complexity: O(1)
     */
    public static boolean searchInSortedMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
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
     * SET MATRIX ZEROS
     * If element is 0, set entire row and column to 0
     * Time Complexity: O(m*n), Space Complexity: O(1)
     */
    public static void setMatrixZeros(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean firstRowZero = false, firstColZero = false;
        
        // Check if first row has zero
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        
        // Check if first column has zero
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        
        // Use first row and column as markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // Set zeros based on markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Set first row to zero if needed
        if (firstRowZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
        
        // Set first column to zero if needed
        if (firstColZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    
    /**
     * MATRIX MULTIPLICATION
     * Time Complexity: O(n³), Space Complexity: O(n²)
     */
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;
        
        if (colsA != B.length) {
            throw new IllegalArgumentException("Matrix dimensions don't match for multiplication");
        }
        
        int[][] result = new int[rowsA][colsB];
        
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        return result;
    }
    
    /**
     * DIAGONAL TRAVERSAL OF MATRIX
     * Time Complexity: O(m*n), Space Complexity: O(1)
     */
    public static List<Integer> diagonalTraversal(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // Traverse upper diagonals
        for (int k = 0; k < cols; k++) {
            int i = 0, j = k;
            while (i < rows && j >= 0) {
                result.add(matrix[i][j]);
                i++;
                j--;
            }
        }
        
        // Traverse lower diagonals
        for (int k = 1; k < rows; k++) {
            int i = k, j = cols - 1;
            while (i < rows && j >= 0) {
                result.add(matrix[i][j]);
                i++;
                j--;
            }
        }
        
        return result;
    }
    
    /**
     * FIND PEAK ELEMENT IN 2D MATRIX
     * Time Complexity: O(n log m), Space Complexity: O(1)
     */
    public static int[] findPeakIn2D(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int maxRow = findMaxInColumn(matrix, mid);
            
            boolean isLeftBig = (mid - 1 >= 0) && (matrix[maxRow][mid - 1] > matrix[maxRow][mid]);
            boolean isRightBig = (mid + 1 < cols) && (matrix[maxRow][mid + 1] > matrix[maxRow][mid]);
            
            if (!isLeftBig && !isRightBig) {
                return new int[]{maxRow, mid};
            } else if (isLeftBig) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return new int[]{-1, -1};
    }
    
    /**
     * WORD SEARCH IN MATRIX
     * Time Complexity: O(m*n*4^L), Space Complexity: O(L) where L = word length
     */
    public static boolean wordSearch(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfsWordSearch(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean dfsWordSearch(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return true;
        
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
            board[row][col] != word.charAt(index)) {
            return false;
        }
        
        char temp = board[row][col];
        board[row][col] = '#'; // Mark as visited
        
        boolean found = dfsWordSearch(board, word, row + 1, col, index + 1) ||
                       dfsWordSearch(board, word, row - 1, col, index + 1) ||
                       dfsWordSearch(board, word, row, col + 1, index + 1) ||
                       dfsWordSearch(board, word, row, col - 1, index + 1);
        
        board[row][col] = temp; // Restore
        return found;
    }
    
    /**
     * NUMBER OF ISLANDS (Connected Components)
     * Time Complexity: O(m*n), Space Complexity: O(1) if modifying input
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsMarkIsland(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    private static void dfsMarkIsland(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||
            grid[row][col] != '1') {
            return;
        }
        
        grid[row][col] = '0'; // Mark as visited
        
        // Explore all 4 directions
        dfsMarkIsland(grid, row + 1, col);
        dfsMarkIsland(grid, row - 1, col);
        dfsMarkIsland(grid, row, col + 1);
        dfsMarkIsland(grid, row, col - 1);
    }
    
    /**
     * MINIMUM PATH SUM
     * Time Complexity: O(m*n), Space Complexity: O(1) if modifying input
     */
    public static int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Fill first row
        for (int j = 1; j < cols; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        
        // Fill first column
        for (int i = 1; i < rows; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        
        // Fill rest of the matrix
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        
        return grid[rows - 1][cols - 1];
    }
    
    /**
     * MAXIMUM RECTANGLE IN BINARY MATRIX
     * Time Complexity: O(m*n), Space Complexity: O(n)
     */
    public static int maxRectangleArea(int[][] matrix) {
        if (matrix.length == 0) return 0;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        
        for (int i = 0; i < rows; i++) {
            // Update heights array
            for (int j = 0; j < cols; j++) {
                heights[j] = matrix[i][j] == 0 ? 0 : heights[j] + 1;
            }
            
            // Find max rectangle in histogram
            maxArea = Math.max(maxArea, largestRectangleInHistogram(heights));
        }
        
        return maxArea;
    }
    
    private static int largestRectangleInHistogram(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int index = 0;
        
        while (index < heights.length) {
            if (stack.isEmpty() || heights[index] >= heights[stack.peek()]) {
                stack.push(index++);
            } else {
                int top = stack.pop();
                int area = heights[top] * (stack.isEmpty() ? index : index - stack.peek() - 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = heights[top] * (stack.isEmpty() ? index : index - stack.peek() - 1);
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
    
    /**
     * Utility method to find max element in a column
     */
    private static int findMaxInColumn(int[][] matrix, int col) {
        int maxRow = 0;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][col] > matrix[maxRow][col]) {
                maxRow = i;
            }
        }
        return maxRow;
    }
    
    /**
     * Utility method to reverse a row
     */
    private static void reverseRow(int[] row) {
        int left = 0, right = row.length - 1;
        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }
    
    /**
     * Utility method to print matrix
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    /**
     * Utility method to print char matrix
     */
    public static void printCharMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    /**
     * Demo and testing method
     */
    public static void main(String[] args) {
        System.out.println("=== Matrix Operations Demo ===\n");
        
        // Test matrix
        int[][] matrix = {
            {1,  2,  3,  4},
            {5,  6,  7,  8},
            {9,  10, 11, 12},
            {13, 14, 15, 16}
        };
        
        System.out.println("Original Matrix:");
        printMatrix(matrix);
        System.out.println();
        
        // 1. Spiral Traversal Demo
        System.out.println("1. SPIRAL TRAVERSAL:");
        List<Integer> spiral = spiralTraversal(matrix);
        System.out.println("Spiral order: " + spiral);
        System.out.println();
        
        // 2. Matrix Transpose Demo
        System.out.println("2. MATRIX TRANSPOSE:");
        int[][] transposeMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original:");
        printMatrix(transposeMatrix);
        transpose(transposeMatrix);
        System.out.println("Transposed:");
        printMatrix(transposeMatrix);
        System.out.println();
        
        // 3. Matrix Rotation Demo
        System.out.println("3. MATRIX ROTATION:");
        int[][] rotateMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original:");
        printMatrix(rotateMatrix);
        rotateMatrix90Clockwise(rotateMatrix);
        System.out.println("After 90° clockwise rotation:");
        printMatrix(rotateMatrix);
        System.out.println();
        
        // 4. Search in Sorted Matrix Demo
        System.out.println("4. SEARCH IN SORTED MATRIX:");
        int[][] sortedMatrix = {
            {1,  4,  7,  11},
            {2,  5,  8,  12},
            {3,  6,  9,  16},
            {10, 13, 14, 17}
        };
        System.out.println("Sorted Matrix:");
        printMatrix(sortedMatrix);
        int target = 5;
        boolean found = searchInSortedMatrix(sortedMatrix, target);
        System.out.println("Search for " + target + ": " + (found ? "Found" : "Not found"));
        System.out.println();
        
        // 5. Set Matrix Zeros Demo
        System.out.println("5. SET MATRIX ZEROS:");
        int[][] zeroMatrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        System.out.println("Original:");
        printMatrix(zeroMatrix);
        setMatrixZeros(zeroMatrix);
        System.out.println("After setting zeros:");
        printMatrix(zeroMatrix);
        System.out.println();
        
        // 6. Matrix Multiplication Demo
        System.out.println("6. MATRIX MULTIPLICATION:");
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);
        int[][] product = multiplyMatrices(A, B);
        System.out.println("A × B:");
        printMatrix(product);
        System.out.println();
        
        // 7. Diagonal Traversal Demo
        System.out.println("7. DIAGONAL TRAVERSAL:");
        int[][] diagMatrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Matrix:");
        printMatrix(diagMatrix);
        List<Integer> diagonal = diagonalTraversal(diagMatrix);
        System.out.println("Diagonal traversal: " + diagonal);
        System.out.println();
        
        // 8. Word Search Demo
        System.out.println("8. WORD SEARCH:");
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        System.out.println("Board:");
        printCharMatrix(board);
        String word = "ABCCED";
        boolean wordFound = wordSearch(board, word);
        System.out.println("Word '" + word + "' found: " + wordFound);
        System.out.println();
        
        // 9. Number of Islands Demo
        System.out.println("9. NUMBER OF ISLANDS:");
        char[][] grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println("Grid:");
        printCharMatrix(grid);
        int islands = numIslands(grid);
        System.out.println("Number of islands: " + islands);
        System.out.println();
        
        // 10. Minimum Path Sum Demo
        System.out.println("10. MINIMUM PATH SUM:");
        int[][] pathMatrix = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println("Grid:");
        printMatrix(pathMatrix);
        int minSum = minPathSum(pathMatrix);
        System.out.println("Minimum path sum: " + minSum);
        
        System.out.println("\n=== Matrix Algorithm Characteristics ===");
        printMatrixCharacteristics();
        
        System.out.println("\n=== End of Matrix Operations Demo ===");
    }
    
    /**
     * Print characteristics of matrix algorithms
     */
    private static void printMatrixCharacteristics() {
        System.out.println("Operation              | Time Complexity | Space Complexity | Notes");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Spiral Traversal       | O(m*n)          | O(1)             | Four-pointer technique");
        System.out.println("Matrix Transpose       | O(n²)           | O(1)             | Square matrix only");
        System.out.println("Matrix Rotation        | O(n²)           | O(1)             | Transpose + reverse");
        System.out.println("Search Sorted Matrix   | O(m+n)          | O(1)             | Start top-right");
        System.out.println("Set Matrix Zeros       | O(m*n)          | O(1)             | Use first row/col");
        System.out.println("Matrix Multiplication  | O(n³)           | O(n²)            | Standard algorithm");
        System.out.println("Word Search            | O(m*n*4^L)      | O(L)             | DFS with backtrack");
        System.out.println("Number of Islands      | O(m*n)          | O(1)             | DFS marking");
        System.out.println("Min Path Sum           | O(m*n)          | O(1)             | DP modification");
    }
}
