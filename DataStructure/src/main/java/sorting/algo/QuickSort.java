package sorting.algo;

import java.util.Random;

public class QuickSort {

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int start, int end) {

        if(start >= end)
            return;
        //partition
        int part = partition(arr, start, end);
        quickSort(arr, start, part -1);
        quickSort(arr, part + 1, end);

    }


    private static int partition(int[] arr, int start, int end) {

        Random r = new Random();
        int pivot = r.nextInt(end - start + 1) + start;
        swap(arr, pivot, end);
        int part = start;
        for(int i = start; i < end; i++) {
            if(arr[i] < arr[end]) {
                swap(arr, part, i);
                part++;
            }
        }
        swap(arr, end, part);
        return part;
    }


    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
