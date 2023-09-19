package sorting.algo;

public class CountingSort {

    // Sorts if elements are in range of 0(inclusive) to K(exclusive)

    public static void sort(int[] arr, int K) {
        int count[] = new int[K];
        int result[] = new int[arr.length];

        for(int i : arr) {
            count[i]++;
        }

        for(int i = 1; i < K ; i++)
            count[i] = count[i] + count[i - 1];

        for(int i = arr.length - 1; i >= 0 ; i--) {
            result[count[arr[i]] - 1] = arr[i];
            count[arr[i]] = count[arr[i]] - 1;
        }

        for(int i = 0; i < arr.length ; i++) {
            arr[i] = result[i];
        }
    }
}
