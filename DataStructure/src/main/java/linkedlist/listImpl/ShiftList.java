package linkedlist.listImpl;

import linkedlist.schema.ListNode;

public class ShiftList {

    public static ListNode shiftList(ListNode head, int k) {

        if(head == null || k == 0)
            return head;
        ListNode t = head;
        while (k-- > 0) {
            t = t.next;
            if(t == null)
                t = head;
        }
        ListNode s = head;
        ListNode tail = t;
        t = t.next;
        while (t != null) {
            tail = t;
            s = s.next;
            t = t.next;
        }
        if(s.next == null)
            return head;
        ListNode res = s.next;
        tail.next = head;
        s.next = null;
        return res;
    }


}
