package priorityqueue;

import java.util.*;

public class Leetcode {

    public int findKthLargest(int[] nums, int k) {
        for(int i = (nums.length - 1) /2; i >= 0; i--) {
            percolateDown(nums, i, nums.length);
        }
        return kthLargest(nums, k);
    }


    public int leftChild(int i, int length) {
        int left = (2 * i) + 1;
        return left >= length ? -1 : left;
    }

    public int rightChild(int i, int length) {
        int right = (2 * i) + 2;
        return right >= length ? -1 : right;
    }

    public void percolateDown(int[] nums, int i, int length) {
        int left = leftChild(i, length);
        int right = rightChild(i, length);
        int max = i;
        if(left != -1 && nums[left] > nums[max])
            max = left;
        if(right != -1 && nums[right] > nums[max])
            max = right;
        if(max != i) {
            int temp = nums[max];
            nums[max] = nums[i];
            nums[i] = temp;
            percolateDown(nums, max, length);
        }
    }

    public int kthLargest(int[] nums, int k) {
        int result = nums[0];
        int length = nums.length;
        for(int i = 1; i <= k; i++) {
            result = nums[0];
            int t = nums[0];
            nums[0] = nums[length - 1];
            nums[length - 1] = t;
            length--;
            percolateDown(nums, 0, length);
        }
        return result;
    }


    class MedianFinder {
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;
        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a);
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(maxHeap.isEmpty() || maxHeap.peek() > num)
                maxHeap.add(num);
            else
                minHeap.add(num);

            if(maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            }
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.remove());
            }
        }

        public double findMedian() {
            if(maxHeap.size() == minHeap.size())
                return (maxHeap.peek() + minHeap.peek())/ 2;
            return maxHeap.peek();

        }
    }

    /**********/

    class Pair implements Comparable {
        int key;
        int count;
        Pair(int key, int c) {
            this.key = key;
            this.count = c;
        }
        @Override
        public int compareTo(Object o) {
            return ((Pair)o).count - this.count;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxHeap.add(new Pair(entry.getKey(), entry.getValue()));
        }
        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
            Pair p = maxHeap.remove();
            result[i] = p.key;
        }
        return result;
    }

    /*******/

    public class PairOfIndex {
        int i;
        int j;
        PairOfIndex(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PairOfIndex that = (PairOfIndex) o;
            return i == that.i &&
                    j == that.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        HashSet<PairOfIndex> visited = new HashSet<>();
        heap.add(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(new PairOfIndex(0, 0));

        List<List<Integer>> result = new ArrayList<>();
        while(k-- > 0 && !heap.isEmpty()) {
            int[] top = heap.poll();
            int i = top[1];
            int j = top[2];
            result.add(List.of(nums1[i], nums2[j]));
            if(i + 1 < nums1.length && !visited.contains(new PairOfIndex(i + 1, j))) {
                heap.add(new int[] {nums1[i + 1] + nums2[j], i + 1, j});
                visited.add(new PairOfIndex(i + 1, j));
            }
            if(j + 1 < nums2.length && !visited.contains(new PairOfIndex(i, j + 1))) {
                heap.add(new int[] {nums1[i] + nums2[j + 1], i, j + 1});
                visited.add(new PairOfIndex(i, j + 1));
            }
        }
        return result;
    }


    public class T {
        int c;
        int p;
        T(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<T> minHeap = new PriorityQueue<>((a, b) -> a.c - b.c);
        PriorityQueue<T> maxHeap = new PriorityQueue<>((a, b) -> b.p - a.p);
        for(int i = 0 ; i < profits.length; i++) {
            minHeap.add(new T(capital[i], profits[i]));
        }

        while (k-- > 0) {
            while (!minHeap.isEmpty() && minHeap.peek().c <= w) {
                maxHeap.add(minHeap.poll());
            }
            if(maxHeap.isEmpty())
                break;
            w += maxHeap.poll().p;
        }

        return w;
    }


    //2336. Smallest Number in Infinite Set

    class SmallestInfiniteSet {


        PriorityQueue<Integer> priorityQueue;
        int minimum;
        public SmallestInfiniteSet() {
            priorityQueue = new PriorityQueue<>();
            minimum = 1;
        }

        public int popSmallest() {
            if(priorityQueue.size() > 0) {
                return priorityQueue.poll();
            }
            minimum++;
            return minimum - 1;
        }

        public void addBack(int num) {
            if(num < minimum && !priorityQueue.contains(num)) {
                priorityQueue.offer(num);
            }
        }
    }


    //2462. Total Cost to Hire K Workers

    public long totalCost(int[] costs, int k, int candidates) {

        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>();
        int i = 0;
        int j = costs.length - 1;
        long minCost = 0;
        while (k-- > 0) {
            while (left.size() < candidates && i <= j) {
                left.offer(costs[i++]);
            }
            while (right.size() < candidates && i <= j) {
                right.offer(costs[j--]);
            }

            int leftCost = left.size() == 0 ? Integer.MAX_VALUE : left.peek();
            int rightCost = right.size() == 0 ? Integer.MAX_VALUE : right.peek();


            if(leftCost <= rightCost) {
                minCost+= leftCost;
                left.poll();
            } else {
                minCost += rightCost;
                right.poll();
            }
        }
        return minCost;
    }


    //2542. Maximum Subsequence Score
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;

        int[][] pairs = new int[n][2];
        for(int i = 0; i < n; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]);
        long sumNum1 = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i = 0; i < k; i++) {
            sumNum1 += pairs[i][0];
            priorityQueue.offer(pairs[i][0]);
        }
        long result = sumNum1 * pairs[k - 1][1];
        for(int i = k; i < n; i++) {
            sumNum1 += pairs[i][0] - priorityQueue.poll();
            priorityQueue.offer(pairs[i][0]);
            result = Math.max(result, sumNum1 * pairs[i][1]);
        }
        return result;
    }


}
