package stack.stackimpl;

import java.util.Stack;

public class SymbolsAndNotations {

    /**
     * checks is the given expression has balanced symbols
     * @param expression
     * @return
     */
    public static boolean isExpressionBalanced(String expression) {

        if(expression == null || expression.isEmpty())
            return true;

        Stack<Character> stack = new Stack<Character>();
        boolean isBalanced = true;
        char[] expressionArr = expression.toCharArray();
        for(int i = 0; i< expressionArr.length; i++) {
            if(isOpeningSymbol(expressionArr[i])) {
                stack.push(expressionArr[i]);
            } else if(isClosingSymbol(expressionArr[i])) {
                if(stack.isEmpty() || !match(stack.pop(),expressionArr[i])) {
                    isBalanced = false;
                    break;
                }
            }
        }
        return stack.isEmpty() ? isBalanced : false;
    }

    private static boolean isOpeningSymbol(char c) {
        return (Character.compare(c, '(') == 0 || Character.compare(c, '[') == 0 || Character.compare(c, '{') == 0);
    }

    private static boolean isClosingSymbol(char c) {
        return (Character.compare(c, ')') == 0 || Character.compare(c, ']') == 0 || Character.compare(c, '}') == 0);
    }

    private static boolean match(char a, char b) {
        return ((Character.compare(a, '(') == 0 && Character.compare(b, ')') == 0) || (Character.compare(a, '[') == 0 && Character.compare(b, ']') == 0) || (Character.compare(a, '{') == 0 && Character.compare(b, '}') == 0));
    }


    private static boolean isRightParenthesis(char c) {
        return Character.compare(c, ')') == 0;
    }

    private static boolean isLeftParenthesis(char c) {
        return Character.compare(c, '(') == 0;
    }

    private static boolean isOperator(char c) {
        return ((Character.compare(c, '+') == 0) || (Character.compare(c, '-') == 0) || (Character.compare(c, '*') == 0) || (Character.compare(c, '/') == 0));
    }

    private static boolean isOperand(char c) {
        return (!isRightParenthesis(c) && !isLeftParenthesis(c) && !isOperator(c));
    }

    private static boolean hasLowPriority(char operatorToCheck, char checkAgainst) {
        if((operatorToCheck == '*' || operatorToCheck == '/')&& (checkAgainst == '*' || checkAgainst == '/'))
            return true;
        if((operatorToCheck == '+' || operatorToCheck == '-') && (checkAgainst == '*' || checkAgainst == '/' ||checkAgainst == '+' ||checkAgainst == '-' ))
            return true;
        return false;
    }

    public static String infixToPostFix(String infix) {

        if(infix == null || infix.isEmpty())
            return infix;

        char infixArr[] = infix.toCharArray();

        Stack<Character> stack = new Stack<>();
        StringBuilder postFix = new StringBuilder();

        for(int i=0; i<infix.length(); i++) {
            if(isLeftParenthesis(infixArr[i])) {
                stack.push(infixArr[i]);
            } else if(!stack.isEmpty() && isRightParenthesis(infixArr[i])) {
                while (!isLeftParenthesis(stack.peek())) {
                    postFix.append(stack.pop());
                }
                stack.pop();
            } else if(isOperand(infixArr[i])) {
                postFix.append(infixArr[i]);
            } else if(isOperator(infixArr[i])) {
                while (!stack.isEmpty() && !isLeftParenthesis(stack.peek()) && hasLowPriority(infixArr[i], stack.peek())){
                    postFix.append(stack.pop());
                }
                stack.push(infixArr[i]);
            }

        }

        while (!stack.isEmpty()) {
            postFix.append(stack.pop());
        }
        return postFix.toString();
    }

    public static String evalPostFix(String postfix) {

        if(postfix == null || postfix.isEmpty())
            return "0";

        char postfixArr[] = postfix.toCharArray();
        Stack<String> stack = new Stack<>();

        for(int i = 0;i < postfixArr.length; i++) {
            if(isOperand(postfixArr[i]))
                stack.push(String.valueOf(postfixArr[i]));
            else if(isOperator(postfixArr[i])) {
                String a = stack.pop();
                String b = stack.pop();
                stack.push(calculateResult(b, a, String.valueOf(postfixArr[i])));
            }
        }
        return stack.peek();
    }

    private static String calculateResult(String a, String b, String operator) {
        int aNum = Integer.valueOf(a);
        int bNum = Integer.valueOf(b);
        if("+".equals(operator)) {
            return String.valueOf(aNum + bNum);
        } else if("-".equals(operator)) {
            return String.valueOf(aNum - bNum);
        } else if("*".equals(operator)) {
            return String.valueOf(aNum * bNum);
        } else if("/".equals(operator)) {
            return String.valueOf(aNum / bNum);
        }

        return "0";
    }


    public static String evaluateInfixExpression(String infix) {

        Stack<String> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        char[] infixExpression = infix.toCharArray();

        for(int i = 0; i < infixExpression.length ; i++) {

            if (isOperand(infixExpression[i])) {
                operandStack.push(String.valueOf(infixExpression[i]));
            } else if (isLeftParenthesis(infixExpression[i])) {
                operatorStack.push(String.valueOf(infixExpression[i]));
            } else if (isRightParenthesis(infixExpression[i])) {
                while (!"(".equals(operatorStack.peek())) {
                    String a = operandStack.pop();
                    String b = operandStack.pop();
                    operandStack.push(calculateResult(b,a,operatorStack.pop()));
                }
                operatorStack.pop();
            } else if(isOperator(infixExpression[i])) {

                while (!operatorStack.empty() && !"(".equals(operatorStack.peek()) && hasLowPriority(infixExpression[i], operatorStack.peek().charAt(0))) {
                    String a = operandStack.pop();
                    String b = operandStack.pop();
                    operandStack.push(calculateResult(b,a,operatorStack.pop()));
                }
                operatorStack.push(String.valueOf(infixExpression[i]));
            }
        }

        while (!operatorStack.empty()) {
            String a = operandStack.pop();
            String b = operandStack.pop();
            operandStack.push(calculateResult(b,a,operatorStack.pop()));
        }

        return operandStack.peek();
    }
}
