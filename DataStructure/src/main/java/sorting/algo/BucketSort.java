package sorting.algo;

public class BucketSort {

    // Sorts if elements are in range of 0(inclusive) to K(exclusive)

    public static void sort(int[] arr, int K) {
        int buckets[] = new int[K]; // create K buckets

        for(int i : arr) {
            buckets[i]++;
        }

        for(int i = 0, j = 0; i < K ; i++) {
            while(buckets[i] > 0) {
                arr[j++] = i;
                buckets[i]--;
            }
        }
    }
}
