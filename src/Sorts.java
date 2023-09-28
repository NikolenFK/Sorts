import java.util.Random;

public class Sorts {

    private static final int ARRAY_SIZE = 15_000;
    private static final int MAX_RANDOM_INT = 100_000;

    public static void main(String[] args) {
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

    // BUBBLE_SORT
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

    // INSERTION_SORT
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

    // SHELL_SORT
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

    // MERGE_SORT
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

    // SELECTION_SORT
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

    // QUICK_SORT
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
