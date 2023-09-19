package linkedlist.listImpl;

import linkedlist.schema.ListNode;

public class SortedList {

    public static ListNode insertInSortedList(ListNode head, ListNode nodeToInsert) {

        if(head == null) {
            head = nodeToInsert;
        } else if(nodeToInsert.getData() < head.getData()) {
            nodeToInsert.setNext(head);
            head = nodeToInsert;
        } else {
             ListNode ptr = head;
             while (ptr.getNext() != null && ptr.getNext().getData() < nodeToInsert.getData()) {
                 ptr = ptr.getNext();
             }
             nodeToInsert.setNext(ptr.getNext());
             ptr.setNext(nodeToInsert);
        }

        return head;
    }
}