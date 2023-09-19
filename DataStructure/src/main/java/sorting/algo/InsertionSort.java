package sorting.algo;

public class InsertionSort {

    public static void sort(int[] arr) {

        for(int i = 0; i < arr.length ; i++) {
            int j = i;
            int temp = arr[i];
            while(j - 1 >= 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
