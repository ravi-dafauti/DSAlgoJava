package stack.stackimpl;

import java.util.Stack;

public class SortStack {

    public static Stack<Integer> sort(Stack<Integer> stack) {
        Stack<Integer> stackAux = new Stack<>();
        while(!stack.isEmpty()) {
            int element = stack.pop();
            while (!stackAux.isEmpty() && stackAux.peek() > element) {
                stack.push(stackAux.pop());
            }
            stackAux.push(element);
        }
        return stackAux;
    }
}
