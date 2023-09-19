package queue.queueImpl;

import queue.exception.CustomQueueException;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue<T> {

    private Queue<T> queue1;
    private Queue<T> queue2;

    public StackUsingQueue() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(T a) {
        if(queue1.isEmpty())
            queue2.offer(a);
        else
            queue1.offer(a);
    }

    public T pop()  throws CustomQueueException {
        if(queue1.isEmpty() && queue2.isEmpty())
            throw new CustomQueueException("Stack is empty!!");
        if(!queue1.isEmpty()) {
            while (queue1.size() != 1) {
                queue2.offer(queue1.poll());
            }

            return queue1.remove();
        }
        while (queue2.size() != 1) {
            queue1.offer(queue2.poll());
        }
        return queue2.remove();
    }

    public int size() {
        return queue1.isEmpty() ? queue2.size() : queue1.size();
    }
}
