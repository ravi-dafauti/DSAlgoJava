package sorting.algo;

public class MergeSort {

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int s, int l) {
        if(s >= l)
            return;
        int m = s + (l - s)/2;
        mergeSort(arr, s, m);
        mergeSort(arr, m + 1, l);
        merge(arr, s , l);
    }

    private static void merge(int[] arr, int s, int l) {

        int[] aux = new int[l - s + 1];
        int m = s + (l - s) / 2;
        int i = 0;
        int j = s;
        int k = m + 1;
        while (j <= m && k <= l) {
            aux[i++] = (arr[j] < arr[k]) ? arr[j++] : arr[k++];
        }
        while (j <= m)
            aux[i++] = arr[j++];
        while (k <= l)
            aux[i++] = arr[k++];

        i = 0;
        j = s;
        while (j <= l)
            arr[j++] = aux[i++];
    }











/*    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length/2 - 1, arr.length - 1);
    }

    private static void mergeSort(int arr[], int start, int mid , int end) {

        if(start >= end)
            return;

        int[] leftArray = new int[mid - start + 1];
        int[] rightArray = new int[end - mid];

        for(int i = start, j = 0; i <= mid; i++, j++) {
            leftArray[j] = arr[i];
        }

        for(int i = mid + 1, j = 0; i <= end; i++, j++) {
            rightArray[j] = arr[i];
        }

        mergeSort(leftArray, 0, leftArray.length/2 - 1, leftArray.length - 1);
        mergeSort(rightArray, 0, rightArray.length/2 -1, rightArray.length - 1);
        merge(arr, leftArray, rightArray);

    }


    private static void merge(int[] arr, int[] left, int[] right) {

        int i, j, k;
        i = j = k = 0;


        while(i < left.length && j < right.length) {
            if(left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i< left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }

    }*/


}
