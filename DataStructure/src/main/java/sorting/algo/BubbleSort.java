package sorting.algo;

public class BubbleSort {

    public static void sort(int[] arr) {

        boolean swapped = true;
        for(int i = 0; i < arr.length - 1 && swapped; i++) {
            swapped = false;
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
        }
    }
}
