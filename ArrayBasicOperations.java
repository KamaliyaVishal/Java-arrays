package com.misc.arrays;

import java.util.*;

/**
 * ArrayBasicOperations - Fundamental array operations and utility methods
 * This class covers basic array operations that serve as building blocks: 1. Array creation and initialization 2. Element access and modification 3. Array copying and cloning 4. Array comparison and equality 5. Array conversion operations 6. Statistical operations (min, max, sum, average) 7. Array validation and bounds checking 8. Array utility methods
 * Foundation for: - Understanding array fundamentals - Building more complex algorithms - Interview preparation basics
 *
 * @author Vishal.Kamaliya
 * @since 2025
 */
public class ArrayBasicOperations
{

	/**
	 * CREATE AND INITIALIZE ARRAYS Demonstrates different ways to create arrays
	 */
	public static void arrayCreationDemo()
	{
		System.out.println("=== Array Creation Methods ===");

		// Method 1: Declaration and initialization separately
		int[] arr1;
		arr1 = new int[5];
		System.out.println("Empty array: " + Arrays.toString(arr1));

		// Method 2: Declaration and initialization together
		int[] arr2 = new int[5];

		// Method 3: Array literal
		int[] arr3 = {1, 2, 3, 4, 5};
		System.out.println("Literal array: " + Arrays.toString(arr3));

		// Method 4: Anonymous array
		int[] arr4 = new int[] {10, 20, 30, 40, 50};
		System.out.println("Anonymous array: " + Arrays.toString(arr4));

		// Method 5: Using Arrays.fill()
		int[] arr5 = new int[5];
		Arrays.fill(arr5, 7);
		System.out.println("Filled array: " + Arrays.toString(arr5));

		// Method 6: Using Collections and streams
		int[] arr6 = Collections.nCopies(5, 3).stream().mapToInt(i -> i).toArray();
		System.out.println("Stream array: " + Arrays.toString(arr6));
	}

	/**
	 * ARRAY ELEMENT ACCESS AND MODIFICATION Safe array operations with bounds checking
	 */
	public static boolean safeSet(int[] arr, int index, int value)
	{
		if (isValidIndex(arr, index))
		{
			arr[index] = value;
			return true;
		}
		return false;
	}

	public static Integer safeGet(int[] arr, int index)
	{
		if (isValidIndex(arr, index))
		{
			return arr[index];
		}
		return null; // or throw exception
	}

	public static boolean isValidIndex(int[] arr, int index)
	{
		return arr != null && index >= 0 && index < arr.length;
	}

	/**
	 * ARRAY COPYING METHODS Different approaches to copy arrays
	 */
	public static int[] shallowCopy(int[] original)
	{
		return original.clone();
	}

	public static int[] deepCopyUsingSystemArraycopy(int[] original)
	{
		int[] copy = new int[original.length];
		System.arraycopy(original, 0, copy, 0, original.length);
		return copy;
	}

	public static int[] deepCopyUsingArraysCopyOf(int[] original)
	{
		return Arrays.copyOf(original, original.length);
	}

	public static int[] copyRange(int[] original, int from, int to)
	{
		return Arrays.copyOfRange(original, from, to);
	}

	/**
	 * ARRAY COMPARISON METHODS
	 */
	public static boolean areEqual(int[] arr1, int[] arr2)
	{
		return Arrays.equals(arr1, arr2);
	}

	public static boolean areEqualIgnoreOrder(int[] arr1, int[] arr2)
	{
		if (arr1.length != arr2.length)
			return false;

		int[] sorted1 = Arrays.copyOf(arr1, arr1.length);
		int[] sorted2 = Arrays.copyOf(arr2, arr2.length);

		Arrays.sort(sorted1);
		Arrays.sort(sorted2);

		return Arrays.equals(sorted1, sorted2);
	}

	public static int compareArrays(int[] arr1, int[] arr2)
	{
		return Arrays.compare(arr1, arr2);
	}

	/**
	 * STATISTICAL OPERATIONS
	 */
	public static int findMin(int[] arr)
	{
		if (arr == null || arr.length == 0)
		{
			throw new IllegalArgumentException("Array cannot be null or empty");
		}

		int min = arr[0];
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i] < min)
			{
				min = arr[i];
			}
		}
		return min;
	}

	public static int findMax(int[] arr)
	{
		if (arr == null || arr.length == 0)
		{
			throw new IllegalArgumentException("Array cannot be null or empty");
		}

		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i] > max)
			{
				max = arr[i];
			}
		}
		return max;
	}

	public static long sum(int[] arr)
	{
		long total = 0;
		for (int num : arr)
		{
			total += num;
		}
		return total;
	}

	public static double average(int[] arr)
	{
		if (arr == null || arr.length == 0)
		{
			throw new IllegalArgumentException("Array cannot be null or empty");
		}
		return (double) sum(arr) / arr.length;
	}

	public static int[] findMinMax(int[] arr)
	{
		if (arr == null || arr.length == 0)
		{
			throw new IllegalArgumentException("Array cannot be null or empty");
		}

		int min = arr[0], max = arr[0];
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];
		}
		return new int[] {min, max};
	}

	/**
	 * ARRAY CONVERSION OPERATIONS
	 */
	public static List<Integer> arrayToList(int[] arr)
	{
		List<Integer> list = new ArrayList<>();
		for (int num : arr)
		{
			list.add(num);
		}
		return list;
	}

	public static int[] listToArray(List<Integer> list)
	{
		return list.stream().mapToInt(i -> i).toArray();
	}

	public static String arrayToString(int[] arr)
	{
		return Arrays.toString(arr);
	}

	public static String arrayToString(int[] arr, String delimiter)
	{
		if (arr == null || arr.length == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arr.length; i++)
		{
			sb.append(arr[i]);
			if (i < arr.length - 1)
			{
				sb.append(delimiter);
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * ARRAY RESIZING OPERATIONS
	 */
	public static int[] resize(int[] arr, int newSize)
	{
		return Arrays.copyOf(arr, newSize);
	}

	public static int[] insertElement(int[] arr, int index, int element)
	{
		if (index < 0 || index > arr.length)
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arr.length);
		}

		int[] newArr = new int[arr.length + 1];
		System.arraycopy(arr, 0, newArr, 0, index);
		newArr[index] = element;
		System.arraycopy(arr, index, newArr, index + 1, arr.length - index);

		return newArr;
	}

	public static int[] removeElement(int[] arr, int index)
	{
		if (index < 0 || index >= arr.length)
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arr.length);
		}

		int[] newArr = new int[arr.length - 1];
		System.arraycopy(arr, 0, newArr, 0, index);
		System.arraycopy(arr, index + 1, newArr, index, arr.length - index - 1);

		return newArr;
	}

	/**
	 * FREQUENCY AND COUNTING OPERATIONS
	 */
	public static Map<Integer, Integer> countFrequency(int[] arr)
	{
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		for (int num : arr)
		{
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
		}
		return frequencyMap;
	}

	public static int countOccurrences(int[] arr, int target)
	{
		int count = 0;
		for (int num : arr)
		{
			if (num == target)
			{
				count++;
			}
		}
		return count;
	}

	public static boolean contains(int[] arr, int target)
	{
		for (int num : arr)
		{
			if (num == target)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * ARRAY VALIDATION METHODS
	 */
	public static boolean isEmpty(int[] arr)
	{
		return arr == null || arr.length == 0;
	}

	public static boolean isSorted(int[] arr)
	{
		return isSortedAscending(arr);
	}

	public static boolean isSortedAscending(int[] arr)
	{
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i] < arr[i - 1])
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isSortedDescending(int[] arr)
	{
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i] > arr[i - 1])
			{
				return false;
			}
		}
		return true;
	}

	public static boolean hasDistinctElements(int[] arr)
	{
		Set<Integer> seen = new HashSet<>();
		for (int num : arr)
		{
			if (!seen.add(num))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * ARRAY TRANSFORMATION METHODS
	 */
	public static int[] removeDuplicates(int[] arr)
	{
		Set<Integer> uniqueSet = new LinkedHashSet<>();
		for (int num : arr)
		{
			uniqueSet.add(num);
		}
		return uniqueSet.stream().mapToInt(i -> i).toArray();
	}

	public static int[] filter(int[] arr, java.util.function.IntPredicate predicate)
	{
		return Arrays.stream(arr).filter(predicate).toArray();
	}

	public static int[] map(int[] arr, java.util.function.IntUnaryOperator mapper)
	{
		return Arrays.stream(arr).map(mapper).toArray();
	}

	public static int reduce(int[] arr, int identity, java.util.function.IntBinaryOperator accumulator)
	{
		return Arrays.stream(arr).reduce(identity, accumulator);
	}

	/**
	 * ARRAY PRINTING UTILITIES
	 */
	public static void printArray(int[] arr)
	{
		System.out.println(Arrays.toString(arr));
	}

	public static void printArrayWithIndices(int[] arr)
	{
		System.out.println("Index: " + Arrays.toString(java.util.stream.IntStream.range(0, arr.length).toArray()));
		System.out.println("Value: " + Arrays.toString(arr));
	}

	public static void printArrayStats(int[] arr)
	{
		if (isEmpty(arr))
		{
			System.out.println("Array is empty");
			return;
		}

		System.out.println("Array: " + Arrays.toString(arr));
		System.out.println("Length: " + arr.length);
		System.out.println("Min: " + findMin(arr));
		System.out.println("Max: " + findMax(arr));
		System.out.println("Sum: " + sum(arr));
		System.out.println("Average: " + String.format("%.2f", average(arr)));
		System.out.println("Is Sorted: " + isSorted(arr));
		System.out.println("Has Duplicates: " + !hasDistinctElements(arr));
	}

	/**
	 * Demo and testing method
	 */
	public static void main(String[] args)
	{
		System.out.println("=== Array Basic Operations Demo ===\n");

		// 1. Array Creation Demo
		arrayCreationDemo();
		System.out.println();

		// 2. Array Operations Demo
		System.out.println("=== Array Operations ===");
		int[] testArr = {5, 2, 8, 1, 9, 3};
		System.out.println("Test Array: " + Arrays.toString(testArr));

		// Safe operations
		System.out.println("Safe set index 2 to 100: " + safeSet(testArr, 2, 100));
		System.out.println("After modification: " + Arrays.toString(testArr));
		System.out.println("Safe get index 2: " + safeGet(testArr, 2));
		System.out.println("Safe get invalid index 10: " + safeGet(testArr, 10));
		System.out.println();

		// 3. Array Copying Demo
		System.out.println("=== Array Copying ===");
		int[] original = {1, 2, 3, 4, 5};
		System.out.println("Original: " + Arrays.toString(original));

		int[] copy1 = shallowCopy(original);
		int[] copy2 = deepCopyUsingSystemArraycopy(original);
		int[] copy3 = deepCopyUsingArraysCopyOf(original);
		int[] copy4 = copyRange(original, 1, 4);

		System.out.println("Shallow copy: " + Arrays.toString(copy1));
		System.out.println("Deep copy (System.arraycopy): " + Arrays.toString(copy2));
		System.out.println("Deep copy (Arrays.copyOf): " + Arrays.toString(copy3));
		System.out.println("Range copy [1,4): " + Arrays.toString(copy4));
		System.out.println();

		// 4. Array Comparison Demo
		System.out.println("=== Array Comparison ===");
		int[] arr1 = {1, 2, 3, 4, 5};
		int[] arr2 = {1, 2, 3, 4, 5};
		int[] arr3 = {5, 4, 3, 2, 1};

		System.out.println("Array 1: " + Arrays.toString(arr1));
		System.out.println("Array 2: " + Arrays.toString(arr2));
		System.out.println("Array 3: " + Arrays.toString(arr3));
		System.out.println("arr1 equals arr2: " + areEqual(arr1, arr2));
		System.out.println("arr1 equals arr3: " + areEqual(arr1, arr3));
		System.out.println("arr1 equals arr3 (ignore order): " + areEqualIgnoreOrder(arr1, arr3));
		System.out.println();

		// 5. Statistical Operations Demo
		System.out.println("=== Statistical Operations ===");
		int[] statsArr = {10, 5, 8, 3, 12, 7, 1, 9};
		printArrayStats(statsArr);
		System.out.println();

		// 6. Array Conversion Demo
		System.out.println("=== Array Conversions ===");
		int[] convArr = {1, 2, 3, 4, 5};
		System.out.println("Array: " + Arrays.toString(convArr));

		List<Integer> list = arrayToList(convArr);
		System.out.println("To List: " + list);

		int[] backToArray = listToArray(list);
		System.out.println("Back to Array: " + Arrays.toString(backToArray));

		System.out.println("Custom delimiter: " + arrayToString(convArr, " | "));
		System.out.println();

		// 7. Array Resizing Demo
		System.out.println("=== Array Resizing ===");
		int[] resizeArr = {1, 2, 3, 4, 5};
		System.out.println("Original: " + Arrays.toString(resizeArr));

		int[] inserted = insertElement(resizeArr, 2, 99);
		System.out.println("Insert 99 at index 2: " + Arrays.toString(inserted));

		int[] removed = removeElement(inserted, 2);
		System.out.println("Remove element at index 2: " + Arrays.toString(removed));

		int[] resized = resize(resizeArr, 8);
		System.out.println("Resize to 8: " + Arrays.toString(resized));
		System.out.println();

		// 8. Frequency Operations Demo
		System.out.println("=== Frequency Operations ===");
		int[] freqArr = {1, 2, 3, 2, 1, 3, 1, 4};
		System.out.println("Array: " + Arrays.toString(freqArr));

		Map<Integer, Integer> frequency = countFrequency(freqArr);
		System.out.println("Frequency map: " + frequency);
		System.out.println("Count of 1: " + countOccurrences(freqArr, 1));
		System.out.println("Contains 5: " + contains(freqArr, 5));
		System.out.println();

		// 9. Array Validation Demo
		System.out.println("=== Array Validation ===");
		int[] sortedArr = {1, 2, 3, 4, 5};
		int[] unsortedArr = {3, 1, 4, 1, 5};

		System.out.println("Sorted array: " + Arrays.toString(sortedArr));
		System.out.println("Is sorted: " + isSorted(sortedArr));
		System.out.println("Has distinct elements: " + hasDistinctElements(sortedArr));

		System.out.println("Unsorted array: " + Arrays.toString(unsortedArr));
		System.out.println("Is sorted: " + isSorted(unsortedArr));
		System.out.println("Has distinct elements: " + hasDistinctElements(unsortedArr));
		System.out.println();

		// 10. Array Transformation Demo
		System.out.println("=== Array Transformations ===");
		int[] transformArr = {1, 2, 2, 3, 3, 3, 4, 5};
		System.out.println("Original: " + Arrays.toString(transformArr));

		int[] unique = removeDuplicates(transformArr);
		System.out.println("Remove duplicates: " + Arrays.toString(unique));

		int[] evens = filter(transformArr, x -> x % 2 == 0);
		System.out.println("Filter evens: " + Arrays.toString(evens));

		int[] doubled = map(transformArr, x -> x * 2);
		System.out.println("Double all: " + Arrays.toString(doubled));

		int product = reduce(transformArr, 1, (a, b) -> a * b);
		System.out.println("Product of all: " + product);

		System.out.println("\n=== End of Array Basic Operations Demo ===");
	}
}
