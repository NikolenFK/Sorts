import java.util.Arrays;
import java.util.Random;

public class Sorts {

    private static final int ARRAY_SIZE = 15_000;
    private static final int MAX_RANDOM_INT = 100_000;

    public static void main(String[] args) {
        int[] arr = generateArray(ARRAY_SIZE, MAX_RANDOM_INT);
        long startTime, endTime;

        // BUBBLE SORT
        startTime = System.currentTimeMillis();
        bubbleSort(arr.clone());
        endTime = System.currentTimeMillis();
        printResult("Bubble Sort", endTime - startTime);

        // INSERTION SORT
        startTime = System.currentTimeMillis();
        insertionSort(arr.clone());
        endTime = System.currentTimeMillis();
        printResult("Insertion Sort", endTime - startTime);

        // MERGE SORT
        startTime = System.currentTimeMillis();
        mergeSort(arr.clone());
        endTime = System.currentTimeMillis();
        printResult("Merge Sort", endTime - startTime);

        // SELECTION SORT
        startTime = System.currentTimeMillis();
        selectionSort(arr.clone());
        endTime = System.currentTimeMillis();
        printResult("Selection Sort", endTime - startTime);

        // QUICK SORT
        startTime = System.currentTimeMillis();
        quickSort(arr.clone(), 0, arr.length - 1);
        endTime = System.currentTimeMillis();
        printResult("Quick Sort", endTime - startTime);
    }

    private static void printResult(String sortName, long timeMillis) {
        System.out.printf("%s: %d ms\n", sortName, timeMillis);
    }

    private static int[] generateArray(int size, int maxValue) {
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(maxValue) + 1;

        return array;
    }

    // BUBBLE SORT
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

    // INSERTION SORT
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

    // MERGE SORT
    private static void mergeSort(int[] array) {
        Arrays.sort(array);
    }

    // SELECTION SORT
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

    // QUICK SORT
    private static void quickSort(int[] array, int start, int end) {
        if (end <= start) return;

        int pivot = partition(array, start, end);

        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

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
