package greedy;


import java.util.*;

public class Problems {


    // huffman coding
    public static class BinaryTreeNode {
        private int count;
        private char c;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode(char c, int count) {
            this.c = c;
            this.count = count;
            this.left = null;
            this.right = null;

        }
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public char getC() {
            return c;
        }

        public void setC(char c) {
            this.c = c;
        }

        public BinaryTreeNode getLeft() {
            return left;
        }

        public void setLeft(BinaryTreeNode left) {
            this.left = left;
        }

        public BinaryTreeNode getRight() {
            return right;
        }

        public void setRight(BinaryTreeNode right) {
            this.right = right;
        }
    }

    public static void huffManCoding(char[] arr, int[] freq) {
        PriorityQueue<BinaryTreeNode> priorityQueue = new PriorityQueue<BinaryTreeNode>((a, b) -> ( a.getCount() - b.getCount()));
        for(int i = 0; i < arr.length; i++) {
            BinaryTreeNode t = new BinaryTreeNode(arr[i], freq[i]);
            priorityQueue.add(t);
        }
        while(priorityQueue.size() > 1) {
            BinaryTreeNode a = priorityQueue.poll();
            BinaryTreeNode b = priorityQueue.poll();
            BinaryTreeNode c = new BinaryTreeNode('x',a.getCount() + b. getCount());
            c.setLeft(a);
            c.setRight(b);
            priorityQueue.add(c);
        }
        print(priorityQueue.poll(), new ArrayList<>());
    }

    public static void print(BinaryTreeNode root, List<Integer> arrayList) {
        if(root == null)
            return;
        if(root.getLeft() == null && root.getRight() == null) {
            System.out.print(root.getC() + " : ");
            for(int i : arrayList) {
                System.out.print(i);
            }
            System.out.println("");
        }
        arrayList.add(0);
        print(root.getLeft(), arrayList);
        arrayList.remove(arrayList.size() - 1);
        arrayList.add(1);
        print(root.getRight(), arrayList);
        arrayList.remove(arrayList.size() - 1);
    }


    //fractional knapsack :

    //Given the weights and values of n items, we need to put these items in a knapsack of capacity W to get the maximum total value in the knapsack.


    public static class Item {
        int weight;
        int value;
        int cost;
        public Item(int w, int v) {
            weight = w;
            value = v;
            cost = value/weight;
        }
    }
    public static int getMaxValue(int[] weight, int[] value, int capacity) {

        Item[] items = new Item[weight.length];
        for(int i = 0; i < weight.length; i++) {
            items[i] = new Item(weight[i], value[i]);
        }
        Arrays.sort(items, (a, b) -> b.cost - a.cost );
        int maxValue = 0;
        int currentWeight = 0;

        for(int i = 0; currentWeight < capacity && i < items.length; i++) {

            if(currentWeight + items[i].weight < capacity) {
                maxValue += items[i].value;
                currentWeight+= items[i].weight;
            } else {
                maxValue = maxValue + (items[i].cost * (capacity - currentWeight));
                currentWeight = capacity;
            }
        }

        return maxValue;
    }

    /*
        Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei), find the minimum number of conference rooms required.
        Example 1:
        Input:
        [[0, 30],[5, 10],[15, 20]]
        Output:
         2
     */

    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }

    public int minMeetingRooms(Interval[] intervals) {

        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++) {
            startTimes[i] = intervals[i].start;
            endTimes[i] = intervals[i].end;
        }
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        int count, start, end, maxRoom;
        start = end = count = maxRoom = 0;
        while (start < startTimes.length) {
            if(startTimes[start] < endTimes[end]) {
                count++;
                start++;
            } else {
                count--;
                end++;
            }
            maxRoom = Math.max(maxRoom, count);
        }
        return maxRoom;
    }


    // 2nd approach for Meeting Room

    /*
    here we sort by start time and and see if any room is there where meeting will end before the current start , if yes then use the same room , else create
    new room. here no of elements in the PQ are the rooms.
     */

    public int minMeetingRoomsPQ(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<>((a, b) -> a.end - b.end);
        priorityQueue.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            Interval earlestFinish = priorityQueue.poll();
            if(intervals[i].start >= earlestFinish.end) {
                earlestFinish.end = intervals[i].end;
            } else {
                priorityQueue.offer(intervals[i]);
            }
            priorityQueue.offer(earlestFinish);
        }
        return priorityQueue.size();
    }

    // Leetcode 56. Merge Intervals

    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for(int i = 0; i < intervals.length;) {
            int[] currInterval = intervals[i];
            int j = i;
            while (j + 1 < intervals.length  && currInterval[1] >= intervals[j + 1][0]) {
                currInterval[1] = Math.max(currInterval[1], intervals[j + 1][1]);
                j++;
            }
            list.add(currInterval);
            i = j + 1;
        }
        int[][] result = new int[list.size()][];
        int i = 0;
        for(int[] arr : list) {
            result[i++] = arr;
        }
        return result;
    }

    // Leetcode 57. Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        boolean done = false;
        for(int i = 0; i < intervals.length;) {
            int[] currentInterval = intervals[i];

            if(newInterval[1] < currentInterval[0]) {
                list.add(newInterval);
                while (i < intervals.length) {
                    list.add(intervals[i++]);
                }
                done = true;
            } else if (newInterval[0] > currentInterval[1]) {
                list.add(currentInterval);
                i++;
            } else {
                newInterval[0] = Math.min(newInterval[0], currentInterval[0]);
                newInterval[1] = Math.max(newInterval[1], currentInterval[1]);
                i++;
            }

        }
        if(!done) {
            list.add(newInterval);
        }
        int[][] result = new int[list.size()][];
        int i = 0;
        for(int[] arr : list) {
            result[i++] = arr;
        }
        return result;
    }


    public List<Integer> partitionLabels(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        List<Integer> result = new ArrayList<>();
        int currentEnd = 0;
        int size = 0;
        for(int i = 0; i < s.length(); i++) {
            currentEnd = Math.max(currentEnd, map.get(s.charAt(i)));
            size++;
            if(currentEnd == i) {
                result.add(size);
                size = 0;
            }
        }
        return result;
    }
}
