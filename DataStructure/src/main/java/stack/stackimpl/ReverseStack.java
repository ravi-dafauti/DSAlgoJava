package stack.stackimpl;

import java.util.Stack;

public class ReverseStack {

    public static <T> void reverse (Stack<T> stack) {

        if(stack.isEmpty())
            return;
        T top = stack.pop();
        reverse(stack);
        pushToBottom(stack,top);

    }

    private static <T> void pushToBottom(Stack<T> stack, T element) {

        if(stack.isEmpty()) {
            stack.push(element);
            return;
        }
        T top = stack.pop();
        pushToBottom(stack, element);
        stack.push(top);
    }
}
