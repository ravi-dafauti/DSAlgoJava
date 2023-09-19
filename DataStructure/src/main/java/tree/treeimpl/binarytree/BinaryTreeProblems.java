package tree.treeimpl.binarytree;

import tree.schema.BinaryTreeNode;

import java.util.*;

public class BinaryTreeProblems {


    public static BinaryTreeNode findMaximumElement(BinaryTreeNode root) {
        if(root == null || TraversalImpl.isLeaf(root))
            return root;

        BinaryTreeNode maxLeft = findMaximumElement(root.getLeft());
        BinaryTreeNode maxRight = findMaximumElement(root.getRight());
        BinaryTreeNode max;
        if(maxLeft == null)
            max = maxRight;
        else if(maxRight == null)
            max = maxLeft;
        else {
            max = maxLeft.getData() > maxRight.getData() ? maxLeft : maxRight;
        }
        return root.getData() > max.getData() ? root : max;
    }

    public static BinaryTreeNode findMaxElementIterative(BinaryTreeNode root) {

        if(root == null || TraversalImpl.isLeaf(root))
            return root;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        BinaryTreeNode max = null;

        while (!queue.isEmpty()) {
            BinaryTreeNode curr = queue.remove();

            if(max == null)
                max = curr;
            else {
                max = curr.getData() > max.getData()? curr : max;
            }
            if(curr.getLeft() != null)
                queue.add(curr.getLeft());
            if(curr.getRight() != null)
                queue.add(curr.getRight());
        }

        return max;
    }

    public static BinaryTreeNode search(BinaryTreeNode root, int data) {

        if(root == null || root.getData() == data)
            return root;
        BinaryTreeNode left = search(root.getLeft(), data);
        return left == null ? search(root.getRight(), data) : left;
    }

    public static BinaryTreeNode insertIntoBinaryTree(BinaryTreeNode root, int data) {

        if(root == null)
            return new BinaryTreeNode(data);

        if(root.getData() > data) {
            root.setLeft(insertIntoBinaryTree(root.getLeft(), data));
        } else {
            root.setRight((insertIntoBinaryTree(root.getRight(), data)));
        }

        return root;
    }

    public static int heightOrDepthOfTree(BinaryTreeNode root) {

        if(root == null)
            return 0;

        Queue<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(root);
        queue.add(null);

        int height = 0;

        while (!queue.isEmpty()) {
            BinaryTreeNode curr = queue.remove();

            if(curr != null) {
                if(curr.getLeft() != null)
                    queue.add(curr.getLeft());
                if(curr.getRight() != null)
                    queue.add(curr.getRight());
            } else {
                height++;
                if(!queue.isEmpty())
                    queue.add(null);
            }
        }

        return height;
    }


    public static BinaryTreeNode deleteElementFromTree(BinaryTreeNode root, int data) {


        if(TraversalImpl.isLeaf(root) && data == root.getData())
            return null;

        // 1. find element
        BinaryTreeNode element = search(root, data);
        if(element == null)
            return root;

        // 2. find deepest Node
        Queue<ParentChildData> queue = new LinkedList<>();
        ParentChildData deepestNode = null;
        queue.add(new ParentChildData(root, null));

        while (!queue.isEmpty()) {
            deepestNode = queue.remove();
            if(deepestNode.getChild() != null) {
                if(deepestNode.getChild().getLeft() != null)
                    queue.add(new ParentChildData(deepestNode.getChild().getLeft(), deepestNode.getChild()));
                if(deepestNode.getChild().getRight() != null)
                    queue.add(new ParentChildData(deepestNode.getChild().getRight(), deepestNode.getChild()));
            }
        }

        // 3. swap data
        int temp = element.getData();
        element.setData(deepestNode.getChild().getData());
        deepestNode.getChild().setData(temp);


        // 4. remove deepest node
        if(deepestNode.getParent().getLeft().getData() == data)
            deepestNode.getParent().setLeft(null);
        else
            deepestNode.getParent().setRight(null);


        return root;

    }

    public static class ParentChildData {

        private BinaryTreeNode child;
        private BinaryTreeNode parent;

        public ParentChildData(BinaryTreeNode child, BinaryTreeNode parent) {
            this.child = child;
            this.parent = parent;
        }

        public BinaryTreeNode getChild() {
            return child;
        }

        public void setChild(BinaryTreeNode child) {
            this.child = child;
        }

        public BinaryTreeNode getParent() {
            return parent;
        }

        public void setParent(BinaryTreeNode parent) {
            this.parent = parent;
        }
    }


    public static int diameter(BinaryTreeNode root) {
        if(root == null)
            return 0;

        int diameterWithCurrentNode = heightOrDepthOfTree(root.getLeft()) + heightOrDepthOfTree(root.getRight()) + 1;
        int diameterLeft = diameter(root.getLeft());
        int diameterRight = diameter(root.getRight());

        int diameter = diameterLeft > diameterRight ? diameterLeft : diameterRight;

        return diameter > diameterWithCurrentNode ? diameter : diameterWithCurrentNode;
    }

    public static boolean ifPathExistsWithSum(BinaryTreeNode root, int sum) {

        if(root == null || root.getData() > sum)
            return false;

        return root.getData() == sum || ifPathExistsWithSum(root.getLeft(), root.getData() - sum) || ifPathExistsWithSum(root.getRight(), root.getData() - sum);
    }

    public static BinaryTreeNode mirror(BinaryTreeNode root) {

        if(root != null) {
            BinaryTreeNode leftMirror = mirror(root.getLeft());
            BinaryTreeNode rightMirror = mirror(root.getRight());
            root.setLeft(rightMirror);
            root.setRight(leftMirror);
        }
        return root;
    }

    public static boolean printAllAncestors(BinaryTreeNode root, int data) {

        if(root == null)
            return false;
        if(root.getData() == data || printAllAncestors(root.getLeft(), data) || printAllAncestors(root.getRight(), data)) {
            System.out.println(root.getData());
            return true;
        }
        return false;

    }

    public static BinaryTreeNode leastCommonAncestor(BinaryTreeNode root, BinaryTreeNode nodeA, BinaryTreeNode nodeB) {


        if(root == null)
            return null;
        if(nodeA == null && nodeB == null)
            return null;
        if(nodeA == null)
            return nodeB;
        if(nodeB == null)
            return nodeA;

        if(root.getData() == nodeA.getData() || root.getData() == nodeB.getData())
            return root;

        BinaryTreeNode left = leastCommonAncestor(root.getLeft(), nodeA, nodeB);
        BinaryTreeNode right = leastCommonAncestor(root.getRight(), nodeA, nodeB);

        if(left != null && right != null)
            return root;

        return left == null ? right : left;
    }


    public static void verticalSumOfBinaryTree(BinaryTreeNode root) {

        Map<Integer, Integer> verticalSum = new TreeMap<>();

        verticalSum(root, verticalSum, 0);
        for (Map.Entry<Integer, Integer> entry : verticalSum.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }

    }


    public static void verticalSum(BinaryTreeNode root, Map<Integer, Integer> map, int level) {
        if(root == null)
            return;
        if(map.get(level) == null)
            map.put(level, 0);
        map.put(level, map.get(level) + root.getData());
        verticalSum(root.getLeft(), map, level -1 );
        verticalSum(root.getRight(), map, level + 1);
    }

    public static BinaryTreeNode getLeftMostNode(BinaryTreeNode root) {

        if(root == null)
            return null;

        if(root.getLeft() != null)
            return root.getLeft();
        else if(root.getRight() != null)
            return root.getRight();
        else
            return getLeftMostNode(root.getSibling());
    }
    public static void connectSiblings(BinaryTreeNode root) {

        if(root == null)
            return;

        if(root.getLeft() != null) {
            if(root.getRight() == null)
                root.getLeft().setSibling(getLeftMostNode(root.getSibling()));
            else
                root.getLeft().setSibling(root.getRight());
        }
        if(root.getRight() != null) {
            root.getRight().setSibling(getLeftMostNode(root.getSibling()));
        }
        connectSiblings(root.getRight());
        connectSiblings(root.getLeft());
    }

    /**
     * given N arry tree in form of array P , where P[i] denotes parent of ith node. -1 for root.
     * Find height of tree.
     * @param tree
     * @return
     */
    public static int heightOfNaryTree(int tree[]) {

        int height = 0;
        int dp[] = new int[tree.length];
        for(int i = 0; i < dp.length ; i++) {
            dp[i] = -1;
        }
        for(int i = 0; i < tree.length; i++) {
            int j = i;
            int curHeight = 0;
            while(tree[j] != -1) {
                if(dp[tree[j]] != -1) {
                    curHeight = dp[tree[j]] + 1;
                    break;
                } else {
                    curHeight++;
                    j= tree[j];
                }
            }

            dp[i] = curHeight;

            if(curHeight > height)
                height = curHeight;

        }

        return height;
    }


    public static void printAllPathFromRootToLeave(BinaryTreeNode root) {
        Stack<Integer> stack = new Stack<>();
        print(root, stack);
    }

    private static void print(BinaryTreeNode root, Stack<Integer> stack) {

        if(root == null)
            return;

        if(TraversalImpl.isLeaf(root)) {
            stack.push(root.getData());
            System.out.println(stack);
            stack.pop();
        } else {
            stack.push(root.getData());
            print(root.getLeft(), stack);
            print(root.getRight(), stack);
            stack.pop();
        }
    }
}
