package tree.treeimpl.binarytree;

import tree.exception.CustomTreeException;
import tree.schema.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TraversalImpl {


    public static void preOrderRecursive(BinaryTreeNode root) {
        if(root != null) {
            System.out.print(root.getData() + " ");
            preOrderRecursive(root.getLeft());
            preOrderRecursive(root.getRight());
        }
    }

    public static void preOrderIterative(BinaryTreeNode root) {
        if(root != null) {

            Stack<BinaryTreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                BinaryTreeNode temp = stack.pop();
                System.out.print(temp.getData() + " ");
                if(temp.getRight() != null)
                    stack.push(temp.getRight());
                if(temp.getLeft() != null)
                    stack.push(temp.getLeft());
            }
        }
    }

    public static void inOrderRecursive(BinaryTreeNode root) {
        if(root != null) {
            inOrderRecursive(root.getLeft());
            System.out.print(root.getData() + " ");
            inOrderRecursive(root.getRight());
        }
    }


    public static boolean isLeaf(BinaryTreeNode binaryTreeNode) {

        if (binaryTreeNode == null)
            return false;
        return binaryTreeNode.getLeft() == null && binaryTreeNode.getRight() == null;
    }
    public static void inOrderIterative(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode currentNode = root;
        boolean done = false;

        while (!done) {
            if (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            } else {
                if(stack.isEmpty()) {
                    done = true;
                } else {
                    currentNode = stack.pop();
                    System.out.print(currentNode.getData() + " ");
                    currentNode = currentNode.getRight();
                }
            }
        }

    }

    public static void postOrderRecursive(BinaryTreeNode root) {
        if(root != null) {
            postOrderRecursive(root.getLeft());
            postOrderRecursive(root.getRight());
            System.out.print(root.getData() + " ");
        }
    }


    public static void postOrderIterative(BinaryTreeNode root) {
        if(root != null) {
            Stack<BinaryTreeNode> stack = new Stack<>();

            boolean done = false;
            BinaryTreeNode currentNode = root;
            BinaryTreeNode prevNode = null;
            while (!done) {

                if(currentNode != null) {
                    stack.push(currentNode);
                    currentNode = currentNode.getLeft();
                } else {
                    if(stack.isEmpty()) {
                        done = true;
                    } else {
                        currentNode = stack.pop();
                        if(currentNode.getRight() == null || (prevNode != null && currentNode.getRight().equals(prevNode))) {
                            System.out.print(currentNode.getData() + " ");
                            prevNode = currentNode;
                            currentNode = null;
                        } else {
                            stack.push(currentNode);
                            currentNode = currentNode.getRight();
                        }
                    }
                }

            }
        }
    }


    public static void levelOrderTraversal(BinaryTreeNode root) {

        int levels = 0;
        if(root != null) {

            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null);

            while (!queue.isEmpty()) {
                BinaryTreeNode current = queue.remove();
                if(current != null) {
                    System.out.print(current.getData() + " ");
                    if(current.getLeft() != null)
                        queue.add(current.getLeft());
                    if(current.getRight() != null)
                        queue.add(current.getRight());
                } else {
                    levels++;
                    if(!queue.isEmpty())
                        queue.add(null);
                }
            }

        }

        System.out.println("");
        System.out.println("Levels : " + levels);
    }

    public static void zigZagTraversal(BinaryTreeNode root) {
        if(root != null) {

            Stack<BinaryTreeNode> currentStack = new Stack<>();
            Stack<BinaryTreeNode> auxStack = new Stack<>();
            boolean leftToRight = true;

            currentStack.push(root);
            while (!currentStack.isEmpty()) {
                BinaryTreeNode curr = currentStack.pop();
                System.out.print(curr.getData() + " ");
                if(leftToRight) {
                    if(curr.getLeft() != null)
                        auxStack.push(curr.getLeft());
                    if(curr.getRight() != null)
                        auxStack.push(curr.getRight());
                } else {
                    if(curr.getRight() != null)
                        auxStack.push(curr.getRight());
                    if(curr.getLeft() != null)
                        auxStack.push(curr.getLeft());
                }

                if(currentStack.isEmpty()) {
                    Stack<BinaryTreeNode> temp = currentStack;
                    currentStack = auxStack;
                    auxStack = temp;
                    leftToRight = !leftToRight;
                }
            }

        }
    }
}
