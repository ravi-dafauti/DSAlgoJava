package linkedlist.listImpl;

import linkedlist.schema.ListNode;

public class NthNodeFromEnd {

    static int count = 1;
    public static ListNode nthNodeFromEnd(ListNode head, int n) {

        ListNode nth, temp;

        if(head == null || n <= 0)
            return null;

        nth = temp = head;

        int i = 0;
        for(; i < n ; i++) {
            if(temp != null)
                temp = temp.getNext();
            else
                break;
        }

        if(i < n)
            return null;

        while (temp != null) {
            temp = temp.getNext();
            nth = nth.getNext();
        }

        return nth;
    }


    public static ListNode nthNodeFromEndRecur(ListNode head, int n) {

        if(head != null) {
            ListNode res = nthNodeFromEndRecur(head.getNext(), n);
            if(count ++  == n) {
                return head;
            } else {
                return res;
            }
        }

        return null;

    }
}
