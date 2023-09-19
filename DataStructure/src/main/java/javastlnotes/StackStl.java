package javastlnotes;

import java.util.Stack;

public class StackStl {

    public void usage() {
        Stack<Integer> stack = new Stack<>();

        Integer pushedElement = stack.push(1); //Pushes an item onto the top of this stack.
        /*
         * Removes the object at the top of this stack and returns that
         * object as the value of this function.
         *      * @throws  EmptyStackException  if this stack is empty.
         */
        Integer popedElement = stack.pop();

        /*
         * Looks at the object at the top of this stack without removing it
         * from the stack.
         *      * @throws  EmptyStackException  if this stack is empty.
         */
        Integer topElement = stack.peek();

        stack.isEmpty();
        stack.size();
    }
}
