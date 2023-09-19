package queue.queueImpl;

import queue.exception.CustomQueueException;

import java.util.Arrays;

public class DynamicArrayQueue {

    private int size;
    private int capacity;
    private int rear;
    private int front;
    int queue[];


    public DynamicArrayQueue() {
        this.size = 0;
        this.capacity = 1;
        this.rear = -1;
        this.front = -1;
        this.queue = new int[1];
    }

    public boolean isEmpty() {
        return this.front == -1;
    }

    private boolean isFull() {
        return this.size == this.capacity;
    }
    public void enqueue(int a) {
        if(isFull()) {
            expand();
        }
        if(isEmpty()) {
            front = rear = 0;
            queue[0] = a;
        } else {
            rear = (rear + 1) % capacity;
            queue[rear] = a;
        }

        size++;
    }

    public int dequeue() throws CustomQueueException {
        if(isEmpty())
            throw new CustomQueueException("Queue is Empty");

        int element = queue[front];

        if(front != rear) {
            front = (front + 1) % capacity;
        } else {
            front = rear = -1;
        }
        size--;

        if(size < (capacity/2)) {
            shrink();
        }
        return element;
    }

    private void expand() {

        int[] expandedQueue = new int[capacity * 2];

        int i = 0;

        while (front != rear) {
            expandedQueue[i++] = queue[front];
            front = (front + 1) % capacity;
        }

        if(front == rear) {
            expandedQueue[i++] = queue[front];
        }

        front = 0;
        rear = capacity - 1;

        capacity = capacity * 2;
        queue = expandedQueue;

    }


    private void shrink() {
        if(front == -1) {
            this.capacity = 1;
            this.queue = new int[1];
        } else {
            int[] shrinkedQueue = new int[capacity / 2];

            int i = 0;

            while (front != rear) {
                shrinkedQueue[i++] = queue[front];
                front = (front + 1) % capacity;
            }

            if(front == rear) {
                shrinkedQueue[i++] = queue[front];
            }

            front = 0;
            rear = size - 1;

            capacity = capacity / 2;
            queue = shrinkedQueue;
        }

    }

    @Override
    public String toString() {
        return "DynamicArrayQueue{" +
                "size=" + size +
                ", capacity=" + capacity +
                ", rear=" + rear +
                ", front=" + front +
                ", queue=" + Arrays.toString(queue) +
                '}';
    }
}
