package linkedlist.listImpl;

import linkedlist.schema.ListNode;

import java.util.HashSet;

public class DetectLoop {

    public static boolean isLoopPresent(ListNode head) {
        ListNode slowPtr, fastPtr;
        slowPtr = fastPtr = head;

        while (fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();

            if(fastPtr == slowPtr)
                return true;
        }
        return false;
    }

    public static boolean isLoopPresentHash(ListNode head) {
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode itr = head;
        while(itr != null) {
            if(hashSet.contains(itr)) {
                return true;
            } else {
                hashSet.add(itr);
                itr = itr.getNext();
            }
        }
        return false;
    }

    public static ListNode getStartOfLoop(ListNode head) {

        boolean isLoopPresent = false;

        ListNode slowPtr, fastPtr;
        slowPtr = fastPtr = head;


        while(fastPtr != null && fastPtr.getNext() != null) {

            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();

            if(fastPtr == slowPtr) {
                isLoopPresent = true;
                break;
            }
        }

        if(isLoopPresent) {

            slowPtr = head;
            while(slowPtr != fastPtr) {
                slowPtr = slowPtr.getNext();
                fastPtr = fastPtr.getNext();
            }

            return slowPtr;
        }

        return null;
    }

    public static ListNode getMedian(ListNode head) {


        if(head == null || head.getNext() == null)
            return head;

        ListNode slowPtr, fastPtr;
        slowPtr = fastPtr = head;

        while (fastPtr != null && fastPtr.getNext() != null) {

            fastPtr = fastPtr.getNext().getNext();

            if(fastPtr == null) {
                return slowPtr;
            } else {
                slowPtr = slowPtr.getNext();
            }
        }

        return slowPtr;
    }
}
