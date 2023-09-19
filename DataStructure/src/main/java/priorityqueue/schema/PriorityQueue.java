package priorityqueue.schema;

import priorityqueue.exception.PriorityQueueException;

// Min heap implementation
public class PriorityQueue {


    private int size;
    private int count;
    private int[] arr;


    public PriorityQueue() {
        this.size = 10; //taking initial size as 10
        this.count = 0;
        arr = new int[this.size];
    }

    private PriorityQueue(int size, int[] arr) {
        this.size = size;
        this.arr = arr;
        this.count = size;
    }

    private void expandSize() {
        int[] newArr = new int[size * 2];
        for(int i = 0; i < arr.length; i++)
            newArr[i] = arr[i];
        arr = newArr;
        size = size * 2;
    }

    private boolean isFull() {
        if(count == size)
            return true;
        return false;
    }

    public int getMin() {
        if(count == 0)
            throw new PriorityQueueException("Queue is Empty!!");
        return arr[0];
    }

    private int getLeftChildIndex(int parentIndex) {
        int leftChild = (2 * parentIndex) + 1;
        return leftChild >= count ? -1 : leftChild;
    }

    private int getRightChildIndex(int parentIndex) {
        int rightChild = (2 * parentIndex) + 2;
        return rightChild >= count ? -1 : rightChild;

    }

    private int getParentIndex(int childIndex) {
        int parentIndex = (childIndex - 1) / 2;
        return parentIndex < 0 ? -1 : parentIndex;
    }

    public void percolateDown(int index) {

        int leftChild = getLeftChildIndex(index);
        int rightChild = getRightChildIndex(index);

        int minIndex = index;
        if(leftChild != -1 && arr[leftChild] < arr[minIndex])
            minIndex = leftChild;

        if(rightChild != -1 && arr[rightChild] < arr[minIndex])
            minIndex = rightChild;

        if(minIndex != index) {
            int temp = arr[index];
            arr[index] = arr[minIndex];
            arr[minIndex] = temp;
            percolateDown(minIndex);
        }
    }


    public int getElementAtIndex(int index) {
        if(index <0 || index >= count)
            throw new PriorityQueueException("index out of range!!");

        return arr[index];
    }

    public void insert(int data) {

        if(count == size)
            expandSize();
        arr[count++] = data;

        int i = count - 1;
        while (getParentIndex(i) >= 0 && arr[getParentIndex(i)] > arr[i] ) {
            int temp = arr[i];
            arr[i] = arr[getParentIndex(i)];
            arr[getParentIndex(i)] = temp;
            i = getParentIndex(i);
        }
    }

    public int deleteMin() {
        if(count == 0)
            throw new PriorityQueueException("Queue is Empty!!");
        int data = arr[0];
        arr[0] = arr[count - 1];
        count--;
        percolateDown(0);
        return data;
    }


    public PriorityQueue buildHeap(int array[]) {

        PriorityQueue priorityQueue = new PriorityQueue(array.length, array);

        if(array.length > 1) {
            for(int i = (array.length - 2)/ 2; i >= 0 ; i--) {
                priorityQueue.percolateDown(i);
            }
        }
        return priorityQueue;

    }

    public int[] getArr(){
        return this.arr;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

}
