package dsa.chapter07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dsa.utils.Generator;

public class Sort {

    private static final int CUTOFF = 3;

    public static <T extends Comparable<? super T>> void insertionSort(T[] array) {
        int idx;
        for(int i=1; i < array.length; i++) {
            T x = array[i];
            for(idx = i; idx > 0 && x.compareTo(array[idx-1]) < 0; idx--)
                array[idx] = array[idx - 1];
            array[idx] = x;
        }
    }

    public static <T extends Comparable<? super T>> void shellSort(T[] array) {
        int idx;

        for(int gap = array.length / 2; gap > 0; gap /= 2) {
            for(int i = gap; i < array.length; i++) {
                T x = array[i];
                for(idx = i; idx >= gap && x.compareTo(array[idx - gap]) < 0; idx -= gap) {
                    array[idx] = array[idx - gap];
                }
                array[idx] = x;
            }
        }
    }


    private static <T extends Comparable<? super T>> void swapReferences(T[] array, int x, int y) {
        T value = array[x];
        array[x] = array[y];
        array[y] = value;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static <T extends Comparable<? super T>> void precDown(T[] array, int i, int n) {
        int child = i;
        T x = array[i];

        while (leftChild(i) < n) {
            child = leftChild(i);

            if(child != n -1 && array[child].compareTo(array[child+1]) < 0)
                child++;

            if (x.compareTo(array[child]) < 0)
                array[i] = array[child];
            else
                break;

            i = child;
        }
        array[i] = x;
    }

    public static <T extends Comparable<? super T>> void heapsort(T[] array) {

        for(int i=array.length / 2; i >= 0; i--) {
            precDown(array, i, array.length);
        }

        for(int i = array.length - 1; i > 0; i--) {
            swapReferences(array, 0, i);
            precDown(array, 0, i);
        }
    }


    private static <T extends Comparable<? super T>> void merge(T[] array, T[] tmpArray, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int pos = left;
        int elementNum = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if(array[left].compareTo(array[right]) <= 0)
                tmpArray[pos++] = array[left++];
            else
                tmpArray[pos++] = array[right++];
        }

        while(left <= leftEnd)
            tmpArray[pos++] = array[left++];

        while(right <= rightEnd)
            tmpArray[pos++] = array[right++];

        for(int i = 0; i < elementNum; i++, rightEnd--)
            array[rightEnd] = tmpArray[rightEnd];
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] array, T[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(array, tmpArray, left, center);
            mergeSort(array, tmpArray, center+1, right);

            merge(array, tmpArray, left, center+1, right);
        }
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] array) {
        T[] tmpArray = (T[]) new Comparable[array.length];
        mergeSort(array, tmpArray, 0, array.length -1);
    }


    private static <T extends Comparable<? super T>> T median3(T[] array, int left, int right) {
        int center = (left + right) / 2;

        if(array[center].compareTo(array[left]) < 0)
            swapReferences(array, left, center);
        if(array[right].compareTo(array[left]) < 0)
            swapReferences(array, left, right);
        if(array[right].compareTo(array[center]) < 0)
            swapReferences(array, center, right);

        swapReferences(array, center, right-1);
        return array[right - 1];
    }

    private static <T extends Comparable<? super T>> void quicksort(T[] array, int left, int right) {
        if (left + CUTOFF <= right) {
            T pivot = median3(array, left, right);

            int i = left, j = right - 1;
            while (true) {
                while (array[++i].compareTo(pivot) < 0) {}
                while (array[--j].compareTo(pivot) > 0) {}

                if(i < j)
                    swapReferences(array, i, j);
                else
                    break;
            }

            swapReferences(array, i, right - 1);

            quicksort(array, left, i -1);
            quicksort(array, i + 1, right);
        } else {
            insertionSort(array);
        }
    }

    public static <T extends Comparable<? super T>> void quicksort(T[] array) {
        quicksort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        List<Integer> numbers = Generator.randIntegerList(10);
        numbers.toArray(array);

        System.out.println(Arrays.asList(array));
        // insertionSort(array);
        // shellSort(array);
        // heapsort(array);
        // mergeSort(array);
        quicksort(array);
        System.out.println(Arrays.asList(array));
    }
}
