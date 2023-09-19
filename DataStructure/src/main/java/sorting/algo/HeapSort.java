package sorting.algo;

import priorityqueue.schema.HeapFactory;
import priorityqueue.schema.PriorityQueue;

public class HeapSort {

    public static void sort(int[] arr) {

        PriorityQueue q = HeapFactory.getMinHeap();
        q = q.buildHeap(arr);
        int[] heapArr = q.getArr();
        for(int i = 0; i < arr.length; i++) {
            int temp = heapArr[0];
            heapArr[0] = heapArr[q.getCount() - 1];
            heapArr[q.getCount() - 1] = temp;
            q.setCount(q.getCount() - 1);
            q.percolateDown(0);
        }

        for(int i = 0 ; i< arr.length; i++) {
            arr[i] = heapArr[i];
        }

    }
}
