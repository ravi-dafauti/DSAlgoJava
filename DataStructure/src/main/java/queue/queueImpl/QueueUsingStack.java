package queue.queueImpl;

import queue.exception.CustomQueueException;

import java.util.Queue;
import java.util.Stack;

public class QueueUsingStack<T> {

    private Stack<T> stack1;
    private Stack<T> stack2;

    public QueueUsingStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }


    public void enqueue(T a) {
        stack1.push(a);
    }

    public T dequeue() throws CustomQueueException {

        if(stack1.empty() && stack2.empty())
            throw new CustomQueueException("Queue is Empty");

        if(stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();

    }

    public int size() {
        return stack1.size() + stack2.size();
    }
}
