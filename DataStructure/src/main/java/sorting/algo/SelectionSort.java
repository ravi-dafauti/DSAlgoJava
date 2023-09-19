package sorting.algo;

public class SelectionSort {

    public static void sort(int[] arr) {

        for(int i = 0; i < arr.length - 1; i++) {

            int min = i;
            int j = i + 1;
            while(j < arr.length) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
                j++;
            }

            if(min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }

    }
}
