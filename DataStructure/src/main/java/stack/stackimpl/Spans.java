package stack.stackimpl;

import java.util.Stack;

public class Spans {

    public static int[] getSpans(int arr[]) {

        int[] span = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        int p;
        for(int i = 0; i < arr.length ; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                p = -1;
            } else {
                p = stack.peek();
            }
            span[i] = i - p;
            stack.push(i);
        }
        return span;
    }
}
