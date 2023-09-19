package lovebabbarDSA.tree;

import java.util.*;

public class Problems {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }


    //Diameter of a Binary Tre

    int diameter(Node root) {
        if(root == null)
            return 0;
        int[] dia = new int[1];
        dia[0] = 0;
        diameterUtil(root, dia);
        return dia[0];
    }

    int diameterUtil(Node root, int[] dia) {
        if(root == null)
            return 0;
        int left = diameterUtil(root.left, dia);
        int right = diameterUtil(root.right, dia);
        int height = Math.max(left, right);
        dia[0] = Math.max(dia[0], left + right + 1);
        return height + 1;
    }

    //Right View of Binary Tree
    // SAME for left view can be done via level order too.. but this is using recursion

    int maxLevel = 0;
    ArrayList<Integer> rightView(Node node) {
        ArrayList<Integer> s = new ArrayList<>();
        rightViewUtil(node, 1, s);
        return s;
    }

    void rightViewUtil(Node root, int level, ArrayList<Integer> s) {
        if(root == null)
            return;
        if(maxLevel < level) {
            maxLevel = level;
            s.add(root.data);
        }
        rightViewUtil(root.right, level + 1, s);
        rightViewUtil(root.left, level + 1, s);
    }

    //Top View of Binary Tree


    static class PairTop {
        Node node;
        int index;
        PairTop(Node node, int i) {
            this.node = node;
            this.index = i;
        }
    }

    static int leftMostTop = 0;

    static ArrayList<Integer> topView(Node root) {
        ArrayList<Integer> topView = new ArrayList<>();
        if(root == null)
            return topView;
        Map<Integer, Integer> map = new HashMap<>();
        Queue<PairTop> queue = new LinkedList<>();
        queue.offer(new PairTop(root, 0));
        while (!queue.isEmpty()) {
            PairTop pair = queue.poll();
            if(leftMostTop > pair.index)
                leftMostTop = pair.index;
            if(!map.containsKey(pair.index)) {
                map.put(pair.index, pair.node.data);
            }
            if(pair.node.left != null) {
                queue.offer(new PairTop(pair.node.left, pair.index - 1));
            }
            if(pair.node.right != null) {
                queue.offer(new PairTop(pair.node.right, pair.index + 1));
            }
        }
        while (map.containsKey(leftMostTop)) {
            topView.add(map.get(leftMostTop));
            leftMostTop++;
        }
        return topView;
    }


    //Bottom View of Binary Tree


    int leftMost = 0;

    class PairBottom {
        Node node;
        int index;
        PairBottom(Node node, int i) {
            this.node = node;
            this.index = i;
        }
    }
    public ArrayList <Integer> bottomView(Node root) {
        ArrayList<Integer> bottomView = new ArrayList<>();
        if(root == null)
            return bottomView;
        Map<Integer, Integer> map = new HashMap<>();
        Queue<PairBottom> queue = new LinkedList<>();
        queue.offer(new PairBottom(root, 0));
        while (!queue.isEmpty()) {
            PairBottom pair = queue.poll();
            if(leftMost > pair.index)
                leftMost = pair.index;
            map.put(pair.index, pair.node.data);
            if(pair.node.left != null) {
                queue.offer(new PairBottom(pair.node.left, pair.index - 1));
            }
            if(pair.node.right != null) {
                queue.offer(new PairBottom(pair.node.right, pair.index + 1));
            }
        }
        while (map.containsKey(leftMost)) {
            bottomView.add(map.get(leftMost));
            leftMost++;
        }
        return bottomView;
    }


    //ZigZag Tree Traversal

    ArrayList<Integer> zigZagTraversal(Node root)
    {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        boolean leftToRight = true;
        ArrayList<Integer> result = new ArrayList<>();
        while (!stack1.isEmpty()) {
            while (!stack1.isEmpty()) {
                Node node = stack1.pop();
                result.add(node.data);
                if(leftToRight) {
                    if(node.left != null) {
                        stack2.push(node.left);
                    }
                    if(node.right != null) {
                        stack2.push(node.right);
                    }
                } else {
                    if(node.right != null) {
                        stack2.push(node.right);
                    }
                    if(node.left != null) {
                        stack2.push(node.left);
                    }
                }
            }

            Stack<Node> temp = stack1;
            stack1 = stack2;
            stack2 = temp;
            leftToRight = !leftToRight;
        }
        return result;
    }

    //Check for Balanced Tree

    boolean isBalanced(Node root)
    {
        if(root == null)
            return true;
        boolean[] balanced = new boolean[1];
        balanced[0] = true;
        height(root, balanced);
        return balanced[0];
    }

    int height(Node root, boolean[] balanced) {
        if(root == null)
            return 0;
        int left = height(root.left, balanced);
        int right = height(root.right, balanced);
        if(Math.abs(left - right) > 1) {
            balanced[0] = false;
        }
        return Math.max(left, right) + 1;
    }

    //Diagonal Traversal of Binary Tree

    public ArrayList<Integer> diagonal(Node root)
    {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Node itr = root;
        while (itr != null) {
            result.add(itr.data);
            if(itr.left != null) {
                queue.offer(itr.left);
            }
            if(itr.right != null) {
                itr = itr.right;
            } else {
                if(!queue.isEmpty()) {
                    itr = queue.poll();
                } else {
                    itr = null;
                }
            }
        }
        return result;
    }

    //Boundary Traversal of binary tree

    ArrayList <Integer> boundary(Node node)
    {
        ArrayList<Integer> result = new ArrayList<>();
        if(node == null)
            return result;
        result.add(node.data);
        printLeftBoundary(node.left, result);
        printLeafs(node.left, result);
        printLeafs(node.right, result);
        printRightBoundary(node.right, result);
        return result;
    }

    void printLeftBoundary(Node root, ArrayList<Integer> result) {
        if(root == null)
            return;
        if(root.left != null || root.right != null)
            result.add(root.data);
        if(root.left != null)
            printLeftBoundary(root.left, result);
        else
            printLeftBoundary(root.right, result);

    }

    void printRightBoundary(Node root, ArrayList<Integer> result) {
        if(root == null)
            return;
        if(root.right != null) {
            printRightBoundary(root.right, result);
        } else {
            printRightBoundary(root.left, result);
        }
        if(root.left != null || root.right != null)
            result.add(root.data);
    }

    void printLeafs(Node root, ArrayList<Integer> result) {
        if(root == null)
            return;
        printLeafs(root.left, result);
        if(root.left == null && root.right == null)
            result.add(root.data);
        printLeafs(root.right, result);
    }

    //Check if Tree is Isomorphic

    boolean isIsomorphic(Node root1, Node root2)
    {
        if(root1 == null && root2 == null)
            return true;
        if(root1 == null || root2 == null)
            return false;
        return (root1.data == root2.data) && ((isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right)) || (isIsomorphic(root1.left, root2.right) && isIsomorphic(root1.right, root2.left)) );

    }

    //Binary Tree to DLL
    Node head = null;
    Node prev = null;
    Node bToDLL(Node root)
    {
        bToDLLUtil(root);
        return head;
    }

    void bToDLLUtil(Node root) {
        if(root == null)
            return;
        bToDLLUtil(root.left);
        if(prev == null) {
            head = prev = root;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        bToDLLUtil(root.right);
    }


    //Transform to Sum Tree
    public void toSumTree(Node root){
        sumTreeUtil(root);
    }

    public int sumTreeUtil(Node root) {
        if(root == null)
            return 0;
        int leftSum = sumTreeUtil(root.left);
        int rightSum = sumTreeUtil(root.right);
        int rootVal = root.data;
        root.data = leftSum + rightSum;
        return leftSum + rightSum + rootVal;
    }

    //Sum Tree


    boolean isLeaf(Node root) {
        if(root == null)
            return false;
        if(root.left == null && root.right == null)
            return true;
        return false;
    }

    boolean isSumTree(Node root)
    {
        if(root == null || isLeaf(root))
            return true;
        if(isSumTree(root.left) && isSumTree(root.right)) {
            int leftSum, rightSum;
            if(root.left == null)
                leftSum = 0;
            else if(isLeaf(root.left))
                leftSum = root.left.data;
            else
                leftSum = root.left.data * 2;

            if(root.right == null)
                rightSum = 0;
            else if(isLeaf(root.right))
                rightSum = root.right.data;
            else
                rightSum = root.right.data * 2;
            if(root.data == leftSum + rightSum)
                return true;
            return false;

        }
        return false;
    }


    //Leaf at same level

    boolean leafFound = false;
    int level = 0;

    boolean check(Node root)
    {
        return checkLeafLevel(root, 1);
    }

    boolean checkLeafLevel(Node root, int curLevel)
    {
        if(root == null)
            return true;
        if(root.left == null && root.right == null) {
            if(leafFound == false) {
                leafFound = true;
                level = curLevel;
                return true;
            } else {
                if(curLevel != level)
                    return false;
            }
        }
        return checkLeafLevel(root.left, curLevel + 1) && checkLeafLevel(root.right, curLevel + 1);
    }

    //Sum of nodes on the longest path from root to leaf node
    int highestLevel = -1;
    int maxSum = Integer.MIN_VALUE;
    public int sumOfLongRootToLeafPath(Node root)
    {
        if(root == null)
            return 0;
        sumOfLongRootToLeafPathUtil(root, 0, 0);
        return maxSum;
    }

    public void sumOfLongRootToLeafPathUtil(Node root, int level, int sum)
    {
        if(root == null)
            return;
        if(root.left == null && root.right == null) {
            if(level > highestLevel) {
                highestLevel = level;
                maxSum = sum + root.data;
            } else if(level == highestLevel) {
                maxSum = Math.max(maxSum, sum + root.data);
            }
            return;
        }
        sumOfLongRootToLeafPathUtil(root.left, level + 1, sum + root.data);
        sumOfLongRootToLeafPathUtil(root.right, level + 1, sum + root.data);
    }


    //K Sum Paths
    int res = 0;
    public int sumK(Node root,int k)
    {
        sumKUtil(root, k, k);
        return res;
    }

    public void sumKUtil(Node root, int k, int curSum) {
        if(root == null)
            return;
        if(root.data == curSum)
            res++;
        sumKUtil(root.left, k, k);
        sumKUtil(root.left, k, curSum - root.data);
        sumKUtil(root.right, k, k);
        sumKUtil(root.right, k, curSum - root.data);
    }


    //Kth Ancestor in a Tree

    int ans = -1;
    public int kthAncestor(Node root, int k, int node)
    {
        List<Integer> path = new ArrayList<>();
        kthAncestorUtil(root, k, node, path);
        return ans;
    }

    public void kthAncestorUtil(Node root, int k, int node, List<Integer> path)
    {
        if(root == null)
            return;
        if(root.data == node) {
            if(path.size() < k)
                return;
            ans = path.get(path.size() - k);
            return;
        }
        path.add(root.data);
        kthAncestorUtil(root.left, k, node, path);
        kthAncestorUtil(root.right, k, node, path);
        path.remove(path.size() - 1);
    }
    public static void main(String[] args) {

        Problems p = new Problems();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(-1);
        root.left.right.left = new Node(1);
        System.out.println(p.sumK(root, 3));
    }
}
