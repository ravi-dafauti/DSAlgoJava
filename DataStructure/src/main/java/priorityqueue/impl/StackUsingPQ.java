package priorityqueue.impl;


import java.util.PriorityQueue;

public class StackUsingPQ {

    class Pair implements Comparable<Pair>{
        int data;
        int priority;

        public Pair(int data, int priority) {
            this.data = data;
            this.priority = priority;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(Pair o) {
            return this.priority - o.getPriority() ;
        }
    }
    PriorityQueue<Pair> stack = new PriorityQueue();

    int count = Integer.MAX_VALUE;

    public void push(int data) {
        stack.add(new Pair(data, count--));
    }

    public int pop() {
        Pair p = stack.remove();
        return p.getData();
    }
}
