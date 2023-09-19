package lovebabbarDSA.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BSTProblems {

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

    //Predecessor and Successor
    static Node pre=null,suc=null;
    public static void findPreSuc(Node root, int key) {
        if(root == null)
            return;
        if(root.data == key) {
            if(root.left != null) {
                pre = max(root.left);
            }
            if(root.right != null) {
                suc = min(root.right);
            }
            return;
        } else if(key < root.data) {
            suc = root;
            findPreSuc(root.left, key);
        } else {
            pre = root;
            findPreSuc(root.right, key);
        }
    }

    public static Node max(Node root) {
        if(root == null)
            return null;
        while (root.right != null)
            root = root.right;
        return root;
    }

    public static Node min(Node root) {
        if(root == null)
            return null;
        while (root.left != null)
            root = root.left;
        return root;
    }

    //Construct BST from given preorder traversal


    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
     }

    //This is slow approach.
    public TreeNode bstFromPreorder(int[] preorder) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for(int i = 1; i < preorder.length; i++) {
            if(stack.isEmpty()) {
                stack.push(new TreeNode(preorder[i]));
            } else if(stack.peek().val >= preorder[i]) {
                TreeNode t = new TreeNode(preorder[i]);
                stack.peek().left = t;
                stack.push(t);
            } else {
                TreeNode t = null;
                while(!stack.isEmpty() && stack.peek().val < preorder[i]) {
                    t = stack.pop();
                }
                TreeNode n = new TreeNode(preorder[i]);
                t.right = n;
                stack.push(n);
            }
        }
        return root;
    }


    // good approach

    int index = 0;
    public TreeNode bstFromPreorderFast(int[] preorder) {
        return bstFromPreOrderUtil(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public TreeNode bstFromPreOrderUtil(int[] preorder, int start, int end){
        if(index >= preorder.length || preorder[index] < start || preorder[index] > end)
            return null;
        int val = preorder[index++];
        TreeNode treeNode = new TreeNode(val);
        treeNode.left = bstFromPreOrderUtil(preorder, start, val);
        treeNode.right = bstFromPreOrderUtil(preorder, val, end);
        return treeNode;
    }


    //Merge two BST 's
    Node prev = null;
    Node head1 = null;
    Node head2 = null;
    public List<Integer> merge(Node root1, Node root2)
    {
        prev = null;
        convertToDLL(root1, 1);
        prev = null;
        convertToDLL(root2, 2);
        List<Integer> result = new ArrayList<>();

        while (head1 != null && head2 != null) {
            if(head1.data <= head2.data) {
                result.add(head1.data);
                head1 = head1.right;
            } else {
                result.add(head2.data);
                head2 = head2.right;
            }
        }
        while (head1 != null) {
            result.add(head1.data);
            head1 = head1.right;
        }
        while (head2 != null) {
            result.add(head2.data);
            head2 = head2.right;
        }
        return result;
    }

    public void convertToDLL(Node root, int bstno) {
        if(root == null)
            return;
        convertToDLL(root.left, bstno);
        if(prev != null) {
            prev.right = root;
            root.left = prev;
        } else {
            if(bstno == 1)
                head1 = root;
            else
                head2 = root;
        }
        prev = root;
        convertToDLL(root.right, bstno);
    }

    //Brothers From Different Roots
    public static int countPairs(Node root1, Node root2, int x)
    {
        int[] count = new int[1];
        inorder(root1, root2, x, count);
        return count[0];
    }

    public static void inorder(Node root1, Node root2, int x, int[] count) {
        if(root1 == null)
            return;
        inorder(root1.left, root2, x, count);
        if(contains(root2, x - root1.data))
            count[0] = count[0] + 1;
        inorder(root1.right, root2, x, count);
    }
    public static boolean contains(Node root, int x) {
        if(root == null)
            return false;
        if(root.data == x)
            return true;
        if(x < root.data)
            return contains(root.left, x);
        return contains(root.right, x);
    }

    ///Median of BST


    public static void countNodes(Node root, int[] count) {
        count[0] = 0;
        if(root == null)
            return;
        while (root != null) {
            if(root.left == null) {
                count[0] = count[0] + 1;
                root = root.right;
            } else {
                Node maxInLeftSubTree = root.left;
                while (maxInLeftSubTree.right != null && maxInLeftSubTree.right != root)
                    maxInLeftSubTree = maxInLeftSubTree.right;
                if(maxInLeftSubTree.right == null) {
                    maxInLeftSubTree.right = root;
                    root = root.left;
                } else if(maxInLeftSubTree.right == root) {
                    maxInLeftSubTree.right = null;
                    count[0] = count[0] + 1;
                    root = root.right;
                }
            }
        }
    }

    public static float inOrder(Node root, int[] count) {
        if(root == null)
            return 0;
        int c = 0;
        Node prev = null;
        int med = (count[0] / 2) + 1;
        while (root != null) {
            if(root.left == null) {
                c++;
                if(c == med) {
                    if(count[0] % 2 != 0) {
                        return root.data;
                    } else {
                        return (root.data + prev.data)/(float)2;
                    }
                }
                prev = root;
                root = root.right;
            } else {
                Node maxInLeftSubTree = root.left;
                while (maxInLeftSubTree.right != null && maxInLeftSubTree.right != root)
                    maxInLeftSubTree = maxInLeftSubTree.right;
                if(maxInLeftSubTree.right == null) {
                    maxInLeftSubTree.right = root;
                    root = root.left;
                } else if(maxInLeftSubTree.right == root) {
                    maxInLeftSubTree.right = null;
                    c++;
                    if(c == med) {
                        if(count[0] % 2 != 0) {
                            return root.data;
                        } else {
                            return (root.data + prev.data)/(float)2;
                        }
                    }
                    prev = root;
                    root = root.right;
                }
            }
        }
        return 0;
    }
    public static float findMedian(Node root)
    {
        int[] count = new int[1];
        countNodes(root, count);
        return inOrder(root, count);
    }


//Given a binary tree. Find the size of its largest subtree that is a Binary Search Tree.

    public static int[] largestBSTBT(Node root)
    {
        // Base cases : When tree is empty or it has one
        // child.
        if (root == null)
            return new int[] { Integer.MAX_VALUE,
                    Integer.MIN_VALUE, 0 };
        if (root.left == null && root.right == null)
            return new int[] { root.data, root.data, 1 };

        // Recur for left subtree and right subtrees
        int[] left = largestBSTBT(root.left);
        int[] right = largestBSTBT(root.right);

        // Create a return variable and initialize its size.
        int[] ans = new int[3];

        // If whole tree rooted under current root is BST.
        if ((left[1] < root.data)
                && (right[0] > root.data)) {
            ans[0] = Math.min(left[0], root.data);
            ans[1] = Math.max(right[1],root.data);

            // Update answer for tree rooted under current
            // 'root'
            ans[2] = 1 + left[2] + right[2];
            return ans;
        }

        // If whole tree is not BST, return maximum of left
        // and right subtrees
        ans[0] = Integer.MIN_VALUE;
        ans[1] = Integer.MAX_VALUE;
        ans[2] = Math.max(left[2], right[2]);

        return ans;
    }


    static int largestBst(Node root)
    {
        return largestBSTBT(root)[2];
    }

    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.right = new Node(3);
        root1.right.left = new Node(2);


        Node root2 = new Node(3);
        root2.left = new Node(2);
        root2.right = new Node(4);
        root2.left.left = new Node(1);

        System.out.println(countPairs(root1, root2, 4));
    }
}
