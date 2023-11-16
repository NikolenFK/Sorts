import java.util.Random;
import java.util.Scanner;

/**
 * The Sorts class contains various sorting algorithms and a main method to test their performance.
 */
public class Sorts {

    /**
     * The size of the array to be sorted.
     */
    private static int ARRAY_SIZE;

    /**
     * The maximum random integer value for array elements.
     */
    private static int MAX_RANDOM_INT;

    /**
     * The scanner.
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * The main method that generates an array, performs different sorting algorithms,
     * and prints the execution time of each algorithm.
     *
     * @param args The command line arguments (not used in this program).
     */
    public static void main(String[] args) {

        System.out.print("Enter the size of the array: ");
        ARRAY_SIZE = SCANNER.nextInt();
        System.out.print("Enter an integer max: ");
        MAX_RANDOM_INT = SCANNER.nextInt();
        int[] array = generateArray(ARRAY_SIZE, MAX_RANDOM_INT);
        long startTime, endTime;

        // BUBBLE_SORT
        startTime = System.currentTimeMillis();
        bubbleSort(array.clone());
        endTime = System.currentTimeMillis();
        printResult("BubbleSort", endTime - startTime);

        // INSERTION_SORT
        startTime = System.currentTimeMillis();
        insertionSort(array.clone());
        endTime = System.currentTimeMillis();
        printResult("InsertionSort", endTime - startTime);

        // SHELL_SORT
        startTime = System.currentTimeMillis();
        shellSort(array.clone());
        endTime = System.currentTimeMillis();
        printResult("ShellSort", endTime - startTime);

        // MERGE_SORT
        startTime = System.currentTimeMillis();
        mergeSort(array.clone());
        endTime = System.currentTimeMillis();
        printResult("MergeSort", endTime - startTime);

        // SELECTION_SORT
        startTime = System.currentTimeMillis();
        selectionSort(array.clone());
        endTime = System.currentTimeMillis();
        printResult("SelectionSort", endTime - startTime);

        // QUICK_SORT
        startTime = System.currentTimeMillis();
        quickSort(array.clone(), 0, array.length - 1);
        endTime = System.currentTimeMillis();
        printResult("QuickSort", endTime - startTime);
    }

    /**
     * Prints the result of a sorting algorithm, including the algorithm name and execution time.
     *
     * @param sortName   The name of the sorting algorithm.
     * @param timeMillis The execution time of the sorting algorithm in milliseconds.
     */
    private static void printResult(String sortName, long timeMillis) {
        System.out.printf("%s: %d ms\n", sortName, timeMillis);
    }

    /**
     * Generates an array of random integers.
     *
     * @param size     The size of the array to generate.
     * @param maxValue The maximum random integer value for array elements.
     * @return The generated array.
     */
    private static int[] generateArray(int size, int maxValue) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(maxValue) + 1;

        return array;
    }

    /**
     * Sorts an array using the Bubble Sort algorithm.
     *
     * @param array The array to be sorted.
     */
    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts an array using the Insertion Sort algorithm.
     *
     * @param array The array to be sorted.
     */
    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = current;
        }
    }

    /**
     * Sorts an array using the Shell Sort algorithm.
     *
     * @param array The array to be sorted.
     */
    private static void shellSort(int[] array) {
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];

                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap)
                    array[j] = array[j - gap];

                array[j] = temp;
            }
        }
    }

    /**
     * Sorts an array using the Merge Sort algorithm.
     *
     * @param array The array to be sorted.
     */
    private static void mergeSort(int[] array) {
        int length = array.length;
        if (length <= 1) return;

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int left = 0;
        int right = 0;

        for (; left < length; left++) {
            if (left < middle) {
                leftArray[left] = array[left];
            } else {
                rightArray[right] = array[left];
                right++;
            }
        }

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    /**
     * Merges two subarrays during the merge step of the Merge Sort algorithm.
     *
     * @param leftArray  The left subarray.
     * @param rightArray The right subarray.
     * @param array      The array to merge into.
     */
    private static void merge(int[] leftArray, int[] rightArray, int[] array) {
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;

        int i = 0, left = 0, right = 0;

        while (left < leftSize && right < rightSize) {
            if (leftArray[left] < rightArray[right]) {
                array[i] = leftArray[left];
                i++;
                left++;
            } else {
                array[i] = rightArray[right];
                i++;
                right++;
            }
        }

        while (left < leftSize) {
            array[i] = leftArray[left];
            i++;
            left++;
        }

        while (right < rightSize) {
            array[i] = rightArray[right];
            i++;
            right++;
        }

    }

    /**
     * Sorts an array using the Selection Sort algorithm.
     *
     * @param array The array to be sorted.
     */
    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min_index = i;
            for (int j = i; j < array.length; j++)
                if (array[j] < array[min_index]) min_index = j;
            int temp = array[min_index];
            array[min_index] = array[i];
            array[i] = temp;
        }

    }

    /**
     * Sorts an array using the Quick Sort algorithm.
     *
     * @param array The array to be sorted.
     * @param start The starting index of the subarray to be sorted.
     * @param end   The ending index of the subarray to be sorted.
     */
    private static void quickSort(int[] array, int start, int end) {
        if (end <= start) return;

        int pivot = partition(array, start, end);

        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    /**
     * Partitions the array during the partition step of the Quick Sort algorithm.
     *
     * @param array The array to be partitioned.
     * @param start The starting index of the subarray to be partitioned.
     * @param end   The ending index of the subarray to be partitioned.
     * @return The index of the pivot after partitioning.
     */
    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start;

        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
            }
        }
        int temp = array[i];
        array[i] = array[end];
        array[end] = temp;

        return i;
    }
}
