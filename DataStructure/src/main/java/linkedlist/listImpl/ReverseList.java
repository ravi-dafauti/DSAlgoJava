package linkedlist.listImpl;

import linkedlist.schema.ListNode;

import java.util.Stack;

public class ReverseList {

    public static ListNode reverseLinkedListRecursive(ListNode head) {

        if(head == null || head.getNext() == null)
            return head;

        ListNode temp = head.getNext();
        head.setNext(null);
        ListNode reversedList = reverseLinkedListRecursive(temp);
        temp.setNext(head);
        return reversedList;

    }

    public static ListNode reverseListIterative(ListNode head) {
        if(head == null || head.getNext() == null)
            return head;

        ListNode nextNode = null;
        ListNode itr = head;
        head = null;

        while (itr != null) {
            nextNode = itr.getNext();
            itr.setNext(head);
            head = itr;
            itr = nextNode;
        }

        return head;
    }


    public static ListNode reverseInPairs(ListNode head) {

        if(head == null || head.getNext() == null)
            return head;
        ListNode nextNode = head.getNext().getNext();
        ListNode temp = head.getNext();
        temp.setNext(head);
        head.setNext(reverseInPairs(nextNode));
        return temp;
    }



    public static ListNode reverseInPairsIterative(ListNode head) {

        if(head == null || head.getNext() == null)
            return head;
        ListNode p = head;
        ListNode tail = null;
        while (p != null && p.getNext() != null) {
            ListNode nextNode = p.getNext().getNext();
            ListNode temp = p.getNext();
            temp.setNext(p);
            p.setNext(nextNode);

            if(p == head) {
                head = temp;
            } else {
                tail.setNext(temp);
            }
            tail = p;
            p = nextNode;

        }

        return head;
    }

    public static ListNode reverseBlockOfKNodes(ListNode head, int K) {

        if(head == null || K == 0)
            return head;

        ListNode nextNode = head;
        ListNode reverse = null;
        ListNode prev = null;

        int i = 0;
        while (nextNode != null && i < K) {
            prev = nextNode;
            nextNode = nextNode.getNext();
            i++;
        }

        if(nextNode != null || i == K) {
            prev.setNext(null);
            ListNode tail = head;
            reverse = reverseLinkedListRecursive(head);
            tail.setNext(reverseBlockOfKNodes(nextNode, K));
        } else {
            return head;
        }


        return reverse;
    }

    public static ListNode reverseBlockOfKNodesIterative(ListNode head, int K) {


        ListNode p = head;
        ListNode prev = null;
        ListNode reverse = null;
        ListNode tail = null;

        while (p != null) {
            int i = 0;
            ListNode nextNode = p;
            while(nextNode != null && i < K) {
                prev = nextNode;
                nextNode = nextNode.getNext();
                i++;
            }

            if(nextNode != null || i == K) {
                prev.setNext(null);
                if(reverse == null)
                    reverse = reverseListIterative(p);
                else
                    tail.setNext(reverseListIterative(p));

                tail = p;
            } else {
                if(reverse == null)
                    reverse = p;
                else
                    tail.setNext(p);
            }

            p = nextNode;
        }

        return reverse;
    }

    /**
     * L1->L2->L3->L4->L5   ==>  L1->L5->L2->L4->L3
     */
    public static ListNode reorderList(ListNode head) {


        Stack<ListNode> s = new Stack<>();

        ListNode slowPtr, fastPtr;
        slowPtr = fastPtr = head;

        while(fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            if(fastPtr != null)
                slowPtr = slowPtr.getNext();
        }

        ListNode secondHalfList = slowPtr.getNext();
        slowPtr.setNext(null);


        while(secondHalfList != null) {
            s.push(secondHalfList);
            secondHalfList = secondHalfList.getNext();

        }

        ListNode contcatList = head;
        while (!s.isEmpty() && head != null) {
            ListNode listNode = s.pop();
            listNode.setNext(head.getNext());
            head.setNext(listNode);
            head = listNode.getNext();
        }

        return contcatList;
    }
}
