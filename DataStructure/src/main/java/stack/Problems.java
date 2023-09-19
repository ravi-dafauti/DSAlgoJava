package stack;

import java.util.Stack;

public class Problems {

    /*
        Given a stack of integers, write a function pairWiseConsecutive() that checks whether numbers in the stack are pairwise consecutive or not. The pairs can be increasing or decreasing, and if the stack has an odd number of elements, the element at the top is left out of a pair. The function should retain the original stack content.
        Only following standard operations are allowed on stack.

        push(X): Enter a element X on top of stack.
        pop(): Removes top element of the stack.
        empty(): To check if stack is empty.
        Examples:

        Input : stack = [4, 5, -2, -3, 11, 10, 5, 6, 20]
        Output : Yes
        Each of the pairs (4, 5), (-2, -3), (11, 10) and
        (5, 6) consists of consecutive numbers.

        Input : stack = [4, 6, 6, 7, 4, 3]
        Output : No
        (4, 6) are not consecutive.
     */


    static boolean pairWiseConsecutive(Stack<Integer> s) {
        boolean result = true;
        Stack<Integer> auxStack = new Stack<>();
        while (!s.isEmpty()) {
            auxStack.push(s.pop());
        }
        while (auxStack.size() > 1) {
            int num1 = auxStack.pop();
            int num2 = auxStack.pop();
            if(Math.abs(num1 - num2) != 1)
                result = false;
            s.push(num1);
            s.push(num2);
        }
        if(auxStack.size() == 1) {
            s.push(auxStack.pop());
        }
        return result;
    }
}
