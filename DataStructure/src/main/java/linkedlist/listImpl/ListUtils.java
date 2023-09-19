package linkedlist.listImpl;

import linkedlist.schema.ListNode;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static int length(ListNode head) {
        int length = 0;
        ListNode iterator = head;
        while (iterator != null) {
            length++;
            iterator = iterator.getNext();
        }
        return length;
    }

    public static void traverseList(ListNode head) {
        ListNode iterator = head;

        List<Integer> list = new ArrayList<>();
        while (iterator != null) {
            list.add(iterator.getData());
            iterator = iterator.getNext();
        }

        System.out.println(list);
    }
}
