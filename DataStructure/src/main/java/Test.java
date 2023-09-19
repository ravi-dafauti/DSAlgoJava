import linkedlist.listImpl.DoublyLinkedList;
import linkedlist.listImpl.SinglyLinkedList;
import linkedlist.schema.DllNode;
import linkedlist.schema.ListNode;
import tree.schema.BinaryTreeNode;

import static greedy.Problems.getMaxValue;
import static greedy.Problems.huffManCoding;
import static linkedlist.listImpl.ShiftList.shiftList;
import static searching.Problems.*;
import static selection.Problems.median;
import static tree.treeimpl.binarysearchtree.BstProblems.convertDllToBstNew;


public class Test {


    public static void main(String[] args) throws Exception {

        BinaryTreeNode dll = createDll();

        BinaryTreeNode bt = convertDllToBstNew(dll);

        inorder(bt);


    }

    public static void inorder(BinaryTreeNode root) {
        if(root != null) {
            inorder(root.getLeft());
            System.out.println(root.getData());
            inorder(root.getRight());
        }
    }

    public static BinaryTreeNode createDll(){

        BinaryTreeNode head = new BinaryTreeNode(1);
        head.setRight(new BinaryTreeNode(2));
        head.getRight().setLeft(head);
        head.getRight().setRight(new BinaryTreeNode(3));
        head.getRight().getRight().setLeft(head.getRight());
        return head;
    }


    public static DllNode createList1(){


        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.insertAtBegin(new DllNode(11));
        linkedList.insertAtBegin(new DllNode(10));
        linkedList.insertAtBegin(new DllNode(30));
        linkedList.insertAtBegin(new DllNode(2));
        linkedList.insertAtBegin(new DllNode(40));
        linkedList.insertAtBegin(new DllNode(0));

        return linkedList.getHead();

    }

    public static ListNode createList5(){


        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.insertAtBegin(new ListNode(11));

        linkedList.insertAtBegin(new ListNode(30));

        linkedList.insertAtBegin(new ListNode(40));

        return linkedList.getHead();

    }

    public static ListNode createList6(){
        SinglyLinkedList linkedList = new SinglyLinkedList();

        linkedList.insertAtBegin(new ListNode(10));
        linkedList.insertAtBegin(new ListNode(10));
        linkedList.insertAtBegin(new ListNode(10));
        linkedList.insertAtBegin(new ListNode(10));

        linkedList.getHead().getNext().getNext().getNext().setNext(linkedList.getHead());

        return linkedList.getHead();

    }



    public static ListNode createList7(){
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.insertAtBegin(new ListNode(100));
        linkedList.insertAtBegin(new ListNode(45));
        linkedList.insertAtBegin(new ListNode(15));
        linkedList.insertAtBegin(new ListNode(10));
        linkedList.insertAtBegin(new ListNode(5));
        return linkedList.getHead();

    }

    public static BinaryTreeNode createBst() {
        BinaryTreeNode root = new BinaryTreeNode(5);


        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(7));


        root.getLeft().setLeft(new BinaryTreeNode(1));
        root.getLeft().setRight(new BinaryTreeNode(4));

        root.getRight().setLeft(new BinaryTreeNode(6));
        root.getRight().setRight(new BinaryTreeNode(8));

        return root;
    }

    public static void traverseList(ListNode head) {

        while(head != null) {
            System.out.print(head.getData() + " ");
            head = head.getNext();
        }
    }

}


