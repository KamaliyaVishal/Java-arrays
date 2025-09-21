package com.misc.arrays;

import java.util.Arrays;

/**
 * Comprehensive Array Basic Concepts for Interview Preparation This class demonstrates fundamental array concepts including: 1. Array declaration and initialization (all data types) 2. Array operations and modifications 3. Post-increment and assignment operations 4. Multi-dimensional array basics 5. Array utility methods and best practices Perfect for understanding array fundamentals before moving to advanced topics.
 *
 * @author Vishal.Kamaliya
 */
public class ArrayBasicConcepts
{

	// =================================================================================
	// SECTION 1: ARRAY DECLARATIONS FOR ALL PRIMITIVE TYPES
	// =================================================================================

	// Integer arrays - most commonly used in interviews
	private int[] intArray = new int[10];                              // Default initialization (all zeros)
	private int[] intArrayInitialized = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Literal initialization

	// Character arrays - useful for string manipulation problems
	private char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
	private char[] charArrayEmpty = new char[10];                      // Default initialization (null characters)

	// String arrays - common in text processing interviews
	private String[] stringArray = {
			"Hello", "World", "This", "Is", "An", "Array", "Of", "Strings"
	};

	// Boolean arrays - useful for marking/flagging problems
	private boolean[] booleanArray = {true, false, true, false, true};

	// Floating-point arrays - numerical computation problems
	private Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};        // Wrapper class
	private float[] floatArray = {1.1f, 2.2f, 3.3f, 4.4f, 5.5f};      // Primitive type

	// Other numeric types - less common but important to know
	private byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	private short[] shortArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	private long[] longArray = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L};

	// =================================================================================
	// SECTION 2: MULTI-DIMENSIONAL ARRAYS (BASIC CONCEPTS)
	// =================================================================================

	// 2D arrays - matrix problems are very common in interviews
	private char[][] char2DArray = {
			{'a', 'b', 'c'},
			{'d', 'e', 'f'},
			{'g', 'h', 'i'}
	};

	// 3D arrays - less common but good to understand the concept
	private char[][][] char3DArray = {
			{
					{'a', 'b'},
					{'c', 'd'}
			},
			{
					{'e', 'f'},
					{'g', 'h'}
			}
	};

	// Dynamic allocation of multi-dimensional arrays
	private char[][][] char3DArrayDynamic = new char[2][2][2];

	// =================================================================================
	// SECTION 3: ARRAY MODIFICATION OPERATIONS
	// =================================================================================

	/**
	 * Demonstrates various array modification techniques including post-increment operations which are common in interview coding problems.
	 */
	public static void demonstrateArrayModifications()
	{
		System.out.println("=== Array Modification Operations ===");

		// Basic array modification
		double[] doubleArray = new double[5];
		doubleArray[0] = 10.5;
		System.out.println("Initial value: " + doubleArray[0]);

		// Post-increment operation (value used first, then incremented)
		System.out.println("Post-increment result: " + doubleArray[0]++); // Prints 10.5
		System.out.println("After post-increment: " + doubleArray[0]);     // Prints 11.5

		// Pre-increment operation (incremented first, then value used)
		System.out.println("Pre-increment result: " + ++doubleArray[0]);   // Prints 12.5

		// Compound assignment operations
		doubleArray[0] += 1.0;  // Add and assign
		doubleArray[0] -= 0.5;  // Subtract and assign
		doubleArray[0] *= 2;    // Multiply and assign
		doubleArray[0] /= 2;    // Divide and assign
		doubleArray[0] %= 3;    // Modulo and assign

		System.out.println("After compound operations: " + doubleArray[0]);

		// Array element swapping (very common in interviews)
		int[] arr = {1, 2, 3, 4, 5};
		System.out.println("Before swap: " + Arrays.toString(arr));
		swapElements(arr, 0, 4);
		System.out.println("After swap: " + Arrays.toString(arr));
	}

	/**
	 * Utility method to swap two elements in an array This is a fundamental operation used in many sorting and manipulation algorithms
	 */
	public static void swapElements(int[] arr, int i, int j)
	{
		if (i >= 0 && i < arr.length && j >= 0 && j < arr.length)
		{
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	// =================================================================================
	// SECTION 4: ARRAY TRAVERSAL PATTERNS
	// =================================================================================

	/**
	 * Demonstrates different ways to traverse arrays - essential for interviews
	 */
	public static void demonstrateTraversalPatterns()
	{
		System.out.println("\n=== Array Traversal Patterns ===");

		int[] array = {10, 20, 30, 40, 50};

		// 1. Traditional for loop with index access
		System.out.print("Traditional loop: ");
		for (int i = 0; i < array.length; i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.println();

		// 2. Enhanced for loop (for-each) - cleaner but no index access
		System.out.print("Enhanced loop: ");
		for (int element : array)
		{
			System.out.print(element + " ");
		}
		System.out.println();

		// 3. Reverse traversal - common in many algorithms
		System.out.print("Reverse traversal: ");
		for (int i = array.length - 1; i >= 0; i--)
		{
			System.out.print(array[i] + " ");
		}
		System.out.println();

		// 4. Two-pointer traversal - very important for interview problems
		System.out.print("Two-pointer traversal: ");
		for (int left = 0, right = array.length - 1; left <= right; left++, right--)
		{
			System.out.print("(" + array[left] + "," + array[right] + ") ");
		}
		System.out.println();
	}

	// =================================================================================
	// SECTION 5: MULTI-DIMENSIONAL ARRAY OPERATIONS
	// =================================================================================

	/**
	 * Demonstrates multi-dimensional array operations commonly asked in interviews
	 */
	public static void demonstrateMultiDimensionalOperations()
	{
		System.out.println("\n=== Multi-Dimensional Array Operations ===");

		// 2D array traversal
		int[][] matrix = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};

		System.out.println("2D Array traversal:");
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				System.out.printf("%3d ", matrix[i][j]);
			}
			System.out.println();
		}

		// Diagonal traversal (common interview pattern)
		System.out.print("Main diagonal: ");
		for (int i = 0; i < matrix.length; i++)
		{
			System.out.print(matrix[i][i] + " ");
		}
		System.out.println();

		System.out.print("Anti-diagonal: ");
		for (int i = 0; i < matrix.length; i++)
		{
			System.out.print(matrix[i][matrix.length - 1 - i] + " ");
		}
		System.out.println();
	}

	// =================================================================================
	// SECTION 6: ARRAY UTILITY METHODS
	// =================================================================================

	/**
	 * Demonstrates essential array utility methods using Arrays class
	 */
	public static void demonstrateArrayUtilities()
	{
		System.out.println("\n=== Array Utility Methods ===");

		int[] original = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};

		// Array to string conversion
		System.out.println("Original array: " + Arrays.toString(original));

		// Array copying
		int[] copy = Arrays.copyOf(original, original.length);
		System.out.println("Copied array: " + Arrays.toString(copy));

		// Array sorting
		Arrays.sort(copy);
		System.out.println("Sorted array: " + Arrays.toString(copy));

		// Binary search (only works on sorted arrays)
		int index = Arrays.binarySearch(copy, 5);
		System.out.println("Index of 5 in sorted array: " + index);

		// Array filling
		int[] filled = new int[5];
		Arrays.fill(filled, 42);
		System.out.println("Filled array: " + Arrays.toString(filled));

		// Array equality comparison
		int[] another = Arrays.copyOf(original, original.length);
		System.out.println("Arrays equal: " + Arrays.equals(original, another));
	}

	// =================================================================================
	// SECTION 7: COMMON INTERVIEW ARRAY OPERATIONS
	// =================================================================================

	/**
	 * Demonstrates basic operations frequently asked in coding interviews
	 */
	public static void demonstrateInterviewBasics()
	{
		System.out.println("\n=== Interview Basic Operations ===");

		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		// Find sum of all elements
		int sum = 0;
		for (int num : numbers)
		{
			sum += num;
		}
		System.out.println("Sum of elements: " + sum);

		// Find maximum element
		int max = numbers[0];
		for (int i = 1; i < numbers.length; i++)
		{
			if (numbers[i] > max)
			{
				max = numbers[i];
			}
		}
		System.out.println("Maximum element: " + max);

		// Find minimum element
		int min = numbers[0];
		for (int i = 1; i < numbers.length; i++)
		{
			if (numbers[i] < min)
			{
				min = numbers[i];
			}
		}
		System.out.println("Minimum element: " + min);

		// Check if array is sorted
		boolean isSorted = true;
		for (int i = 1; i < numbers.length; i++)
		{
			if (numbers[i] < numbers[i - 1])
			{
				isSorted = false;
				break;
			}
		}
		System.out.println("Array is sorted: " + isSorted);

		// Reverse array in-place
		int[] toReverse = Arrays.copyOf(numbers, numbers.length);
		for (int i = 0; i < toReverse.length / 2; i++)
		{
			swapElements(toReverse, i, toReverse.length - 1 - i);
		}
		System.out.println("Reversed array: " + Arrays.toString(toReverse));
	}

	// =================================================================================
	// MAIN METHOD - DEMONSTRATION
	// =================================================================================

	public static void main(String[] args)
	{
		System.out.println("╔════════════════════════════════════════════════════════════════╗");
		System.out.println("║               ARRAY BASIC CONCEPTS DEMONSTRATION               ║");
		System.out.println("║                Interview Preparation Guide                     ║");
		System.out.println("╚════════════════════════════════════════════════════════════════╝");

		ArrayBasicConcepts demo = new ArrayBasicConcepts();

		// Display all array types and their contents
		System.out.println("\n=== Array Type Demonstrations ===");

		// Populate the empty integer array
		for (int i = 0; i < demo.intArray.length; i++)
		{
			demo.intArray[i] = (i + 1) * 10;
		}

		// Display different array types
		System.out.println("Integer array (manual): " + Arrays.toString(demo.intArray));
		System.out.println("Integer array (literal): " + Arrays.toString(demo.intArrayInitialized));
		System.out.println("Character array: " + Arrays.toString(demo.charArray));
		System.out.println("String array: " + Arrays.toString(demo.stringArray));
		System.out.println("Boolean array: " + Arrays.toString(demo.booleanArray));
		System.out.println("Double array: " + Arrays.toString(demo.doubleArray));
		System.out.println("Float array: " + Arrays.toString(demo.floatArray));
		System.out.println("Byte array: " + Arrays.toString(demo.byteArray));
		System.out.println("Short array: " + Arrays.toString(demo.shortArray));
		System.out.println("Long array: " + Arrays.toString(demo.longArray));

		// Multi-dimensional arrays
		System.out.println("\n=== Multi-Dimensional Arrays ===");
		System.out.println("2D Character array:");
		for (char[] row : demo.char2DArray)
		{
			System.out.println(Arrays.toString(row));
		}

		System.out.println("3D Character array: " + Arrays.deepToString(demo.char3DArray));
		System.out.println("3D Dynamic array: " + Arrays.deepToString(demo.char3DArrayDynamic));

		// Run all demonstrations
		demonstrateArrayModifications();
		demonstrateTraversalPatterns();
		demonstrateMultiDimensionalOperations();
		demonstrateArrayUtilities();
		demonstrateInterviewBasics();

        System.out.println();
		System.out.println("╔════════════════════════════════════════════════════════════════╗");
		System.out.println("║  Next Steps: Study ArrayFundamentals.java for advanced topics  ║");
		System.out.println("╚════════════════════════════════════════════════════════════════╝");
	}
}
