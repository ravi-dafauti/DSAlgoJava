package stack.stackimpl;

import java.util.Stack;

public class LargestAreaUnderHistogram {

    public static int getLargestAreaUnderHistogram(int histogram[]) {

        int maxArea = Integer.MIN_VALUE;


        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i < histogram.length) {

            if (stack.isEmpty() || histogram[i] >= histogram[stack.peek()]) {
                stack.push(i++);
            } else {
                int currentArea = histogram[stack.pop()] * (stack.empty() ? i : (i - stack.peek() - 1));
                if (currentArea > maxArea)
                    maxArea = currentArea;
            }

        }

        while (!stack.isEmpty()) {
            int currentArea = histogram[stack.pop()] * (stack.empty() ? i : (i - stack.peek() - 1));
            if (currentArea > maxArea)
                maxArea = currentArea;
        }

        return maxArea;
    }
}
