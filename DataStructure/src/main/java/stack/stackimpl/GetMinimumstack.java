package stack.stackimpl;

import stack.exception.CustomStackException;

import java.util.Stack;

public class GetMinimumstack {

    private Stack<Integer> stack;
    private int min;

    public GetMinimumstack() {
        stack = new Stack<>();
        min = Integer.MIN_VALUE;
    }

    public void push(int a) {
        if(stack.isEmpty()) {
            stack.push(a);
            min = a;
        } else {
            if(a < min) {
                stack.push(2*a - min);
                min = a;
            } else {
                stack.push(a);
            }
        }

    }

    public int pop() throws CustomStackException {
        if(stack.isEmpty())
            throw new CustomStackException("Stack is empty!!");
        int poped = stack.pop();
        int toReturn;
        if(poped < min) {
            toReturn = min;
            min = (2 * min) - poped;
            return toReturn;
        }
        if (stack.isEmpty())
            min = Integer.MIN_VALUE;
        return poped;
    }

    public int peek() {
        if(stack.peek() < min) {
            return min;
        }
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "GetMinimumstack{" +
                "stack=" + stack +
                ", min=" + min +
                '}';
    }
}
