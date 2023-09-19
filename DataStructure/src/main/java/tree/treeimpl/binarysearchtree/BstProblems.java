package tree.treeimpl.binarysearchtree;

import linkedlist.listImpl.SinglyLinkedList;
import linkedlist.schema.ListNode;
import tree.schema.BinaryTreeNode;

public class BstProblems {

    private static Integer prev = null;

    public static BinaryTreeNode findElement(BinaryTreeNode root, int data) {

        if(root == null)
            return null;
        if(root.getData() == data)
            return root;
        if(data < root.getData())
            return findElement(root.getLeft(), data);
        return findElement(root.getRight(), data);
    }

    public static BinaryTreeNode findIterative(BinaryTreeNode root, int data) {

        while(root != null) {
            if(root.getData() > data)
                root = root.getLeft();
            else if(root.getData() < data)
                root = root.getRight();
            else
                return root;
        }

        return null;
    }

    public static BinaryTreeNode findMinimum(BinaryTreeNode root) {

        if(root == null)
            return null;

        if(root.getLeft() == null)
            return root;

        return findMinimum(root.getLeft());
    }


    public static BinaryTreeNode findMaximum(BinaryTreeNode root) {

        if(root == null)
            return null;

        if(root.getRight() == null)
            return root;

        return findMaximum(root.getRight());
    }


    public static BinaryTreeNode insertInBst(BinaryTreeNode root, int data) {

        if(root == null) {
            return new BinaryTreeNode(data);
        }
        if(data < root.getData())
            root.setLeft(insertInBst(root.getLeft(), data));
        else if(data > root.getData())
            root.setRight(insertInBst(root.getRight(), data));

        return root;
    }


    public static BinaryTreeNode deleteFromBst(BinaryTreeNode root, int data) {

        if(root == null)
            return null;

        if(data < root.getData())
            root.setLeft(deleteFromBst(root.getLeft(), data));
        else if (data > root.getData())
            root.setRight(deleteFromBst(root.getRight(), data));
        else {
                if(root.getLeft() == null) {
                    root =  root.getRight();
                } else if(root.getRight() == null){
                    root =  root.getLeft();
                } else {
                    BinaryTreeNode maxInLeftSubtree = findMaximum(root.getLeft());
                    root.setData(maxInLeftSubtree.getData());
                    root.setLeft(deleteFromBst(root.getLeft(), root.getData()));
                }
        }
        return root;
    }


    public static BinaryTreeNode inorderSuccessor(BinaryTreeNode root, BinaryTreeNode node) {

        if(root == null || node == null)
            return null;

        BinaryTreeNode succcessor = null;

        while(root != null) {
            if(root.getData() == node.getData()) {
                if(root.getRight() != null)
                    succcessor = findMinimum(root.getRight());
                break;
            } else if(root.getData() > node.getData()) {
                succcessor = root;
                root = root.getLeft();
            } else if(root.getData() < node.getData()) {
                root = root.getRight();
            }

        }

        return succcessor;
    }


    public static BinaryTreeNode inorderPredecessor(BinaryTreeNode root, BinaryTreeNode node) {

        if(root == null || node == null)
            return null;

        BinaryTreeNode predecessor = null;

        while(root != null) {
            if(root.getData() == node.getData()) {
                if(root.getLeft() != null)
                    predecessor = findMaximum(root.getLeft());
                break;
            } else if(root.getData() > node.getData()) {
                root = root.getLeft();
            } else if(root.getData() < node.getData()) {
                predecessor = root;
                root = root.getRight();
            }

        }

        return predecessor;
    }



    public static boolean isBst(BinaryTreeNode root) {

        if(root == null)
            return true;

        if(!isBst(root.getLeft()))
            return false;

        if(prev != null) {
            if(prev > root.getData())
                return false;
            prev = root.getData();
        } else {
            prev = new Integer(root.getData());
        }

        return isBst(root.getRight());
    }

    public static BinaryTreeNode lca(BinaryTreeNode root, int a, int b) {

        if(root == null)
            return null;

        if(root.getData() == a || root.getData() == b)
            return root;

        if(Math.max(a, b) < root.getData())
            return lca(root.getLeft(), a , b);
        else if(Math.min(a, b) > root.getData())
            return lca(root.getRight(), a, b);

        return root;
    }

    private static BinaryTreeNode findTail(BinaryTreeNode head) {

        if(head == null)
            return null;

        BinaryTreeNode tail = head;
        while (tail.getRight() !=  null)
            tail = tail.getRight();

        return tail;
    }

    public static BinaryTreeNode convertBstToCircularDll(BinaryTreeNode root) {

        if(root == null)
            return null;
        BinaryTreeNode list = convertBstToDll(root);
        BinaryTreeNode tail = findTail(list);
        tail.setRight(list);
        list.setLeft(tail);
        return list;
    }

    /**
     * O(NLogN)
     * @param root
     * @return
     */
    public static BinaryTreeNode convertBstToDll(BinaryTreeNode root) {

        if(root == null)
            return null;

        BinaryTreeNode leftList = convertBstToDll(root.getLeft());

        BinaryTreeNode leftListTail = findTail(leftList);
        BinaryTreeNode rightList = convertBstToDll(root.getRight());

        root.setRight(rightList);
        root.setLeft(leftListTail);
        if(rightList != null)
            rightList.setLeft(root);
        if(leftList != null)
            leftListTail.setRight(root);

        return leftList == null ? root : leftList;

    }


    public static BinaryTreeNode head = null;
    private static BinaryTreeNode prevList = null;

    /**
     * O(N)
     * @param root
     */
    public static void convertBstToDllNew(BinaryTreeNode root) {

        if(root == null)
            return;

        convertBstToDll(root.getLeft());

        root.setLeft(prevList);

        if(prevList == null)
            head = root;
        else
            prevList.setRight(root);

        prevList = root;


        convertBstToDll(root.getRight());
    }


    public static BinaryTreeNode getMedian(BinaryTreeNode head) {


        if(head == null || head.getRight() == null)
            return head;

        BinaryTreeNode slowPtr, fastPtr;
        slowPtr = fastPtr = head;

        while (fastPtr != null && fastPtr.getRight() != null) {

            fastPtr = fastPtr.getRight().getRight();

            if(fastPtr == null) {
                return slowPtr;
            } else {
                slowPtr = slowPtr.getRight();
            }
        }

        return slowPtr;
    }

    public static BinaryTreeNode convertDllToBst(BinaryTreeNode head) {

        if(head == null)
            return null;

        BinaryTreeNode mid = getMedian(head);

        if(mid.getLeft() != null) {
            mid.getLeft().setRight(null);
            mid.setLeft(convertDllToBst(head));
        }


        if(mid.getRight() != null)
            mid.getRight().setLeft(null);

        mid.setRight(convertDllToBst(mid.getRight()));

        return mid;

    }

    /**
     * O(N)
     * @param head
     * @return
     */
    static BinaryTreeNode headOfDll;
    public static BinaryTreeNode convertDllToBstNew(BinaryTreeNode head) {
        if(head == null)
            return null;
        int n = 0;
        BinaryTreeNode itr = head;
        while(itr != null) {
            n++;
            itr = itr.getRight();
        }
        headOfDll = head;
        return dlltoBst(n);

    }

    public static BinaryTreeNode dlltoBst(int n) {
        if(n <= 0)
            return null;
        BinaryTreeNode left = dlltoBst( n/2);
        BinaryTreeNode root = headOfDll;
        headOfDll = headOfDll.getRight();
        root.setLeft(left);
        root.setRight(dlltoBst(n - n/2 - 1));
        return root;
    }


    private static int count = 0;
    public static BinaryTreeNode kthSmallest(BinaryTreeNode root, int k) {

        if(root == null)
            return null;

        BinaryTreeNode left = kthSmallest(root.getLeft(), k);


        if(left != null)
            return left;

        if(++count == k)
            return root;

        return kthSmallest(root.getRight(), k);
    }


    /**
     * create a BST in O(n) 
     * @param linkedList
     * @return
     */
    public static BinaryTreeNode convertSinglyListToBst(SinglyLinkedList linkedList) {

        int n = linkedList.getLength();

        return convertSlToBst(linkedList, n);
    }

    private static BinaryTreeNode convertSlToBst(SinglyLinkedList linkedList, int n) {

        if(n <= 0)
            return null;

        BinaryTreeNode left = convertSlToBst(linkedList, n/2);

        BinaryTreeNode root = new BinaryTreeNode(linkedList.getHead().getData());

        root.setLeft(left);

        linkedList.setHead(linkedList.getHead().getNext());

        root.setRight(convertSlToBst(linkedList, n- n/2 - 1));

        return root;

    }





}
