package priorityqueue.impl;

import linkedlist.schema.ListNode;
import priorityqueue.exception.PriorityQueueException;
import priorityqueue.schema.HeapFactory;
import priorityqueue.schema.PriorityQueue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class PriorityQueueQuestions {

    public static int kthSmallestElement(PriorityQueue priorityQueue, int k) {

        int min = Integer.MIN_VALUE;
        try {
            for(int i = 1; i <= k ; i++) {
                min = priorityQueue.deleteMin();
            }
        } catch (PriorityQueueException e) {
            throw new PriorityQueueException("size of queue is less than k");
        }
        return min;
    }


    public static void dynamicMedian(int arr[]) {

        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());


        for(int i = 0 ; i < arr.length ; i++) {

            if(maxHeap.isEmpty() || arr[i] < maxHeap.peek()) {
                maxHeap.add(arr[i]);
            } else {
                minHeap.add(arr[i]);
            }

            if(maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            }
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.remove());
            }

            if(maxHeap.size() == minHeap.size()) {
                System.out.println("Median : " + (maxHeap.peek() + minHeap.peek()) / 2);
            } else {
                System.out.println("Median : " + maxHeap.peek());
            }

        }
    }


    public static void maxInSlidingWindow(int arr[], int windowSize) {

        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>(Collections.reverseOrder());
        if(windowSize > arr.length)
            return;
        for(int i = 0; i < windowSize; i++)
            maxHeap.add(arr[i]);
        System.out.println(maxHeap.peek());
        for(int i = windowSize ; i < arr.length ; i++){
            int j = i - windowSize;
            maxHeap.remove(arr[j]);
            maxHeap.add(arr[i]);
            System.out.println(maxHeap.peek());

        }
    }

    public static void  maxInSlidingWindowOptimized(int arr[], int windowSize) {

        if(windowSize > arr.length) {
            return;
        }

        Deque<Integer> deque = new LinkedList<>();

        for(int i = 0; i < windowSize; i++) {

            while (!deque.isEmpty() && arr[deque.getLast()] < arr[i])
                deque.removeLast();

            deque.addLast(i);
        }

        System.out.println("Maximum : " + arr[deque.getFirst()]);

        for (int i = windowSize; i < arr.length ; i++) {

            while (!deque.isEmpty() && arr[deque.getLast()] < arr[i])
                deque.removeLast();

            while (!deque.isEmpty() && deque.getFirst() <= i - windowSize)
                deque.removeFirst();

            deque.addLast(i);
            System.out.println("Maximum : " + arr[deque.getFirst()]);
        }

    }


    public static ListNode mergeKSortedList(ListNode[] arrayOfList) {

        java.util.PriorityQueue<ListNode> pq = new java.util.PriorityQueue<ListNode>((a, b) -> { return a.getData() - b.getData();});

        for(int i = 0; i < arrayOfList.length; i++) {
            pq.add(arrayOfList[i]);
        }

        ListNode head, cur;
        head = cur = null;

        while (!pq.isEmpty()) {
            ListNode temp = pq.remove();
            if(head == null) {
                head = temp;
                cur = head;
            } else {
                cur.setNext(temp);
                cur = temp;
            }
            if(cur.getNext() != null)
                pq.add(cur.getNext());
        }

        return head;
    }



}
