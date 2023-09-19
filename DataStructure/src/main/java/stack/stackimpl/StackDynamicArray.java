package stack.stackimpl;

import linkedlist.exception.CustomListException;
import stack.exception.CustomStackException;

import java.util.Arrays;


/**
 * amortized time for n push operations is O(1).
 *
 *
 * 1 + 2 + 4 + 8 + ...... n = 2*n = O(n)
 * @param <T>
 */
public class StackDynamicArray <T>{

    private int length;
    private int top = -1;
    private T stack[];

    public StackDynamicArray() {
        length = 0;
        top = -1;
        stack = (T[])new Object[1];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(T element) {

        if(stack.length == length)
            expand();
        stack[++top] = element;
        length++;
    }

    public T pop() throws CustomStackException {
        if (isEmpty())
            throw new CustomStackException("Stack is empty!!");
        T element = stack[top--];
        length--;
        if((stack.length)/2 > length)
            shrink();
        return element;

    }

    public T getTop() throws CustomStackException{
        if (isEmpty())
            throw new CustomStackException("Stack is empty!!");
        return stack[top];
    }

    private void expand() {
        T doubleStack[] = (T[]) new Object[stack.length * 2];
        for(int i = 0;i<length;i++) {
            doubleStack[i] = stack[i];
        }
        stack = doubleStack;
    }


    private void shrink() {
        T halfStack[] = (T[]) new Object[stack.length/2];
        for(int i = 0; i<length; i++) {
            halfStack[i] = stack[i];
        }
        stack = halfStack;
    }

    public int getLength() {
        return top + 1;
    }

    @Override
    public String toString() {
        return "StackDynamicArray{" +
                "stack=" + Arrays.toString(stack) +
                '}';
    }
}
