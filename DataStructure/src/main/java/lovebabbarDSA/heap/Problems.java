package lovebabbarDSA.heap;

import java.util.*;

public class Problems {

    //k largest elements

    int leftChild(int[] arr, int n, int i) {
        int left = 2 * i  + 1;
        if(left < 0 || left >= n)
            return -1;
        return left;
    }

    int rightChild(int[] arr, int n, int i) {
        int right = 2 * i  + 2;
        if(right < 0 || right >= n)
            return -1;
        return right;
    }

    void percolate(int[] arr, int n, int i) {
        int left = leftChild(arr, n, i);
        int right = rightChild(arr, n, i);

        int max = i;
        if(left != -1 && arr[max] < arr[left]) {
            max = left;
        }
        if(right != -1 && arr[max] < arr[right]) {
            max = right;
        }
        if(max != i) {
            int t = arr[max];
            arr[max] = arr[i];
            arr[i] = t;
            percolate(arr, n, max);
        }
    }

    void heapify(int[] arr, int n) {
        for(int i = (n - 2)/ 2; i >=0; i--) {
            percolate(arr, n, i);
        }
    }

    int getLargest(int[] arr, int n) {
        int largest = arr[0];
        int t = arr[0];
        arr[0] = arr[n - 1];
        arr[n - 1] = t;
        percolate(arr, n - 1, 0);
        return largest;
    }

    int[] kLargest(int[] arr, int n, int k) {
        int[] result = new int[k];
        heapify(arr, n);
        for(int i = 0; i < k; i++) {
            result[i] = getLargest(arr, n - i);
        }
        return result;
    }


    //Merge k Sorted Arrays


    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K)
    {
        class Element {
            int row;
            int col;
            int val;
            Element(int row, int col, int val) {
                this.row = row;
                this.col = col;
                this.val = val;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Element> priorityQueue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(int i = 0; i < K; i++) {
            priorityQueue.offer(new Element(i, 0, arr[i][0]));
        }

        while (!priorityQueue.isEmpty()) {
            Element element = priorityQueue.poll();
            result.add(element.val);
            if(element.col + 1 < K) {
                priorityQueue.offer(new Element(element.row, element.col + 1, arr[element.row][element.col + 1]));
            }
        }
        return result;
    }


    //Merge two binary Max heaps

    public int[] mergeHeaps(int[] a, int[] b, int n, int m) {
        int[] mergedHeap = new int[n + m];
        int k = 0;
        for (int i = 0; i < n;) {
            mergedHeap[k++] = a[i++];
        }
        for(int i = 0; i < m;)
            mergedHeap[k++] = b[i++];
        heapify(mergedHeap, n + m);
        return mergedHeap;
    }


    //Merge “K” Sorted Linked Lists [V.IMP]
/*    class Node  {
        int data;
        Node next;

        Node(int key)
        {
            data = key;
            next = null;
        }
    }*/

/*    Node mergeKList(Node[]arr,int K)
    {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.data - b.data);
        Node result = new Node(0);
        Node resultTail = result;
        for(int i = 0; i < K; i++) {
            if(arr[i] != null)
                pq.offer(arr[i]);
        }
        while (!pq.isEmpty()) {
            resultTail.next = pq.poll();
            resultTail = resultTail.next;
            if(resultTail.next != null) {
                pq.offer(resultTail.next);
                resultTail.next = null;
            }
        }
        return result.next;
    }*/


    //Find median in a stream

    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

    //Function to insert heap.
    public static void insertHeap(int x)
    {
        if(maxHeap.isEmpty() || x <= maxHeap.peek()) {
            maxHeap.offer(x);
        } else {
            minHeap.offer(x);
        }
        balanceHeaps();
    }

    //Function to balance heaps.
    public static void balanceHeaps()
    {
        if(maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        if(minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    //Function to return Median.
    public static double getMedian()
    {
        if(minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek())/ (double)2;
        } else {
            return maxHeap.peek();
        }
    }









    //Is Binary Tree Heap

    class Node{
        int data;
        Node left,right;
        Node(int d){
            data=d;
            left=right=null;
        }
    }

    boolean isHeap(Node tree) {
        if(tree == null)
            return true;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(tree);
        boolean leafFound = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                if(node.left != null) {
                    if(leafFound || node.data < node.left.data)
                        return false;
                    queue.offer(node.left);
                } else {
                    if(node.right != null)
                        return false;
                }
                if(node.right != null) {
                    if(leafFound || node.data < node.right.data)
                        return false;
                    queue.offer(node.right);
                } else {
                    leafFound = true;
                }
            }
        }
       return true;
    }


    //Minimum Cost of ropes
    long minCost(long arr[], int n)
    {
        long result = 0;
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            priorityQueue.offer(arr[i]);
        }
        while (!priorityQueue.isEmpty()) {
            long x = priorityQueue.poll();
            if(!priorityQueue.isEmpty()) {
                long y = priorityQueue.poll();
                result += x + y;
                priorityQueue.offer(x + y);
            }
        }
        return result;
    }


    //Minimum sum

    String solve(int[] arr, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            pq.offer(arr[i]);
        }
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        while (!pq.isEmpty()) {
            a.append(pq.poll());
            if(!pq.isEmpty()) {
                b.append(pq.poll());
            }
        }
        StringBuilder sum = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >=0 && j >=0 ) {
            int s = Integer.valueOf(a.substring(i, i+ 1)) + Integer.valueOf(b.substring(j, j + 1)) + carry;
            sum.append(s % 10);
            carry = s / 10;
            i--;
            j--;
        }
        while (i >=0) {
            int s = Integer.valueOf(a.substring(i, i+ 1))+ carry;
            sum.append(s % 10);
            carry = s / 10;
            i--;
        }
        while (j >=0 ) {
            int s =  Integer.valueOf(b.substring(j, j + 1)) + carry;
            sum.append(s % 10);
            carry = s / 10;
            j--;
        }
        if(carry != 0)
            sum.append(carry);


        sum.reverse();
        int k = 0;
        while (k < sum.length() && sum.charAt(k) == '0')
            k++;
        String res = sum.substring(k, sum.length());
        return res.length() == 0 ? "0" : res;
    }

    //767. Reorganize String

    class Pair {
        char c;
        int count;
        Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    public String reorganizeString(String s) {

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.count - a.count);
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            if(result.length() == 0 ) {
                result.append(pair.c);
                pair.count--;
                if(pair.count > 0)
                    pq.offer(pair);
            } else  {
                if(result.charAt(result.length() - 1) != pair.c) {
                    result.append(pair.c);
                    pair.count--;
                    if(pair.count > 0)
                        pq.offer(pair);
                } else {
                    if(pq.isEmpty()) {
                        return "";
                    } else {
                        Pair pair2 = pq.poll();
                        result.append(pair2.c);
                        pair2.count--;
                        if(pair2.count > 0)
                            pq.offer(pair2);
                        if(pair.count > 0)
                            pq.offer(pair);
                    }
                }
            }

        }

        return result.toString();
    }


    //K-th Largest Sum Contiguous Subarray
    public static int kthLargest(int N, int K, int[] Arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int j = i; j < N; j++) {
                sum += Arr[j];
                if(priorityQueue.isEmpty() || priorityQueue.size() < K) {
                    priorityQueue.offer(sum);
                } else {
                    if(priorityQueue.peek() < sum) {
                        priorityQueue.poll();
                        priorityQueue.offer(sum);
                    }
                }
            }
        }
        return priorityQueue.peek();
    }


    //Smallest range in K lists

    static class RangePair{
        int val;
        int row;
        int col;
        RangePair(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    static int[] findSmallestRange(int[][] KSortedArray,int n,int k) {
        int[] range = new int[2];
        range[0] = -1;
        range[1] = -1;
        int minRange = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        PriorityQueue<RangePair> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(int i = 0; i < k; i++) {
            pq.offer(new RangePair(KSortedArray[i][0], i, 0));
            max = Math.max(max, KSortedArray[i][0]);
        }
        while (!pq.isEmpty()) {
            RangePair node = pq.poll();
            if(minRange > max - node.val) {
                minRange = max - node.val;
                range[0] = node.val;
                range[1] = max;
            }
            if(node.col + 1 < n) {
                pq.offer(new RangePair(KSortedArray[node.row][node.col + 1], node.row, node.col + 1));
                max = Math.max(max,KSortedArray[node.row][node.col + 1]);
            } else {
                break;
            }
        }
        return range;
    }

    public static void main(String[] args) {

        Problems p = new Problems();
        //p.solve(new int[]{1 3 8 7 4 2 7 7 9 3 1 9 8 6 5 0 2 8 6 0 2 4}, 22);

    }
}
