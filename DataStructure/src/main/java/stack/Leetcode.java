package stack;

import java.util.Stack;

public class Leetcode {

    public static void main(String[] args) {
        Leetcode l = new Leetcode();
        Leetcode.MinStack m = new Leetcode().new MinStack();
        l.simplifyPath("/../");
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if(stack.isEmpty()){
                    return false;
                } else {
                    if(s.charAt(i) == ')') {
                        if(stack.peek() == '(')
                            stack.pop();
                        else
                            return false;
                    } else if(s.charAt(i) == ']') {
                        if(stack.peek() == '[')
                            stack.pop();
                        else
                            return false;
                    } else if (s.charAt(i) == '}') {
                        if(stack.peek() == '{')
                            stack.pop();
                        else
                            return false;
                    }
                }
            }
        }
        if(!stack.isEmpty())
            return false;
        return true;
    }

    class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;


        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            if(stack.isEmpty()) {
                stack.push(val);
                minStack.push(val);
            } else {
                if(val <= minStack.peek()) {
                    minStack.push(val);
                }
                stack.push(val);
            }
        }

        public void pop() {
            if(stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    class Solution {
        public int evalRPN(String[] tokens) {

            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < tokens.length; i++) {
                if(!isOperator(tokens[i])) {
                    stack.push(Integer.valueOf(tokens[i]));
                } else {
                    int two = stack.pop();
                    int one = stack.pop();
                    if("+".equals(tokens[i])) {
                        stack.push(one + two);
                    } else if("-".equals(tokens[i])) {
                        stack.push(one - two);
                    } else if("*".equals(tokens[i])) {
                        stack.push(one * two);
                    } else if("/".equals(tokens[i])) {
                        stack.push(one/two);
                    }
                }
            }
            return stack.peek();
        }

        public boolean isOperator(String token) {
            if("*".equals(token) || "+".equals(token) || "-".equals(token) || "/".equals(token))
                return true;
            return false;
        }
    }


    public int calculate(String s) {
        s = s.trim();
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (isOperator(s.charAt(i))) {
                if(!stack.isEmpty() && isOperator(stack.peek())) {
                    postfix.append(stack.peek());
                    stack.pop();
                }
                stack.push(s.charAt(i));
            } else  if (s.charAt(i) == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.peek());
                    stack.pop();
                }
                stack.pop();
            } else if(s.charAt(i) != ' ') {
                postfix.append(s.charAt(i));
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.peek());
            stack.pop();
        }

        return evalRPN(postfix.toString());

    }

    public int evalRPN(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < postfix.length(); i++) {
            if(!isOperator(postfix.charAt(i))) {
                stack.push(Integer.valueOf(String.valueOf(postfix.charAt(i))));
            } else {
                int two = stack.pop();
                int one = stack.pop();
                if('+' == (postfix.charAt(i))) {
                    stack.push(one + two);
                } else if('-' == (postfix.charAt(i))) {
                    stack.push(one - two);
                }
            }
        }
        if(stack.size() > 1) {
            return Integer.valueOf(postfix);
        }
        return stack.peek();
    }

    public boolean isOperator(char token) {
        if('+' == token || '-' == token)
            return true;
        return false;
    }


    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(']' != s.charAt(i)) {
                stack.push(s.charAt(i));
            } else {
                StringBuilder st = new StringBuilder();
                while (stack.peek() != '[') {
                    st.append(stack.peek());
                    stack.pop();
                }
                st.reverse();
                stack.pop();
                StringBuilder mul = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    mul.append(stack.peek());
                    stack.pop();
                }
                mul.reverse();
                int multiple = Integer.valueOf(mul.toString());
                StringBuilder offset = new StringBuilder(st);
                for(int j = 1; j < multiple; j++) {
                    st.append(offset);
                }
                for(int k = 0; k < st.length(); k++) {
                    stack.push(st.charAt(k));
                }
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.peek());
            stack.pop();
        }
        result.reverse();
        return result.toString();
    }


    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        int j = -1;
        int i;
        for (i = 0; i < heights.length; i++) {
            if(stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int length = heights[stack.peek()];
                    stack.pop();
                    int width = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;
                    int currArea = length * width;
                    if(currArea > maxArea)
                        maxArea = currArea;
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            int length = heights[stack.peek()];
            stack.pop();
            int width = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;
            int currArea = length * width;
            if(currArea > maxArea)
                maxArea = currArea;
        }

        return maxArea;
    }


    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < temperatures.length; i++) {
            if(stack.isEmpty() || temperatures[stack.peek()] > temperatures[i]){
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    int j = stack.peek();
                    stack.pop();
                    result[j] = i - j;
                }
                stack.push(i);
            }
        }
        return result;
    }


    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        for(int i = 0; i < path.length(); i++) {
            if(path.charAt(i) == '/')
                continue;
            String temp = "";
            while(i < path.length() && path.charAt(i) != '/'){
                temp += path.charAt(i);
                i++;
            }
            if(".".equals(temp)){
                continue;
            } else if("..".equals(temp.toString())) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(temp);
            }

        }

        String result = "";
        while(!stack.isEmpty()) {
            result = "/" + stack.peek() + result;
            stack.pop();
        }

        if(result.length() == 0)
            return "/";
        return result;

    }


    //901. Online Stock Span


    class StockSpanner {

        class Span {
            int val;
            int span;
            Span(int val, int span) {
                this.val = val;
                this.span = span;
            }
        }

        Stack<Span> stack;
        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price) {

            String s = new String(new char[]{}, 1, 2);
            int span = 1;
            while (!stack.isEmpty() && stack.peek().val <= price) {
                span += stack.pop().span;
            }
            stack.push(new Span(price, span));
            return span;
        }
    }

    //735. Asteroid Collision

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i < asteroids.length) {
            if(stack.isEmpty() || isSameDirection(stack.peek(), asteroids[i]) || (stack.peek() < 0 && asteroids[i] > 0)) {
                stack.push(asteroids[i++]);
            } else {
                int t = stack.pop();
                if(Math.abs(t) == Math.abs(asteroids[i])) {
                    i++;
                } else if(Math.abs(t) > Math.abs(asteroids[i])) {
                    asteroids[i] = t;
                }
            }
        }

        int[] result = new int[stack.size()];
        i = result.length - 1;
        while (!stack.isEmpty()) {
            result[i--] = stack.pop();
        }
        return result;
    }

    public boolean isSameDirection(int a , int b) {
        if((a < 0 && b < 0) || (a > 0 && b > 0))
            return true;
        return false;
    }
}
