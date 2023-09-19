package linkedlist.listImpl;

import linkedlist.exception.CustomListException;
import linkedlist.schema.ListNode;

import java.util.ArrayList;
import java.util.List;

public class CircularLinkedList {

    private ListNode head;
    private int length;

    public CircularLinkedList() {
        length = 0;
        head = null;
    }
    public ListNode getHead() {
        return head;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    public ListNode findTailNode(ListNode head) {

        if(head == null)
            return null;

        ListNode itr = head;

        do {
            itr = itr.getNext();
        } while (itr.getNext() != head);

        return itr;
    }
    public void insertAtBegin(ListNode listNode) {

        if (head == null) {
            listNode.setNext(listNode);
            head = listNode;
        } else {
            ListNode tail = findTailNode(head);
            listNode.setNext(head);
            tail.setNext(listNode);
            head = listNode;
        }

        length++;
    }

    public void insertAtEnd(ListNode listNode) {
        if (head == null) {
            listNode.setNext(listNode);
            head = listNode;
        } else {
            ListNode tail = findTailNode(head);
            tail.setNext(listNode);
            listNode.setNext(head);
        }

        length++;
    }


    public void insertAtPosition(int data, int position) throws CustomListException {

        if(position < 0 || position > length)
            throw new CustomListException("invalid position");

        ListNode listNode = new ListNode(data);

        if(position == 0) {
            insertAtBegin(listNode);
        } else if(position == length) {
            insertAtEnd(listNode);
        } else {
            ListNode itr = head;
            int pos = 0;
            while(pos + 1 != position) {
                itr = itr.getNext();
                pos++;
            }
            listNode.setNext(itr.getNext());
            itr.setNext(listNode);
            length++;
        }
    }


    public void removeAtBegin() throws CustomListException {

        if(head == null)
            throw new CustomListException("list is empty!!");

        if(head.getNext() == head)
            head = null;
        else {
            ListNode tail = findTailNode(head);
            tail.setNext(head.getNext());
            head.setNext(null);
            head = tail.getNext();
        }

        length--;

    }

    public void removeAtEnd() throws CustomListException {

        if(head == null)
            throw new CustomListException("list is empty!!");

        if(head.getNext() == head)
            head = null;
        else {

            ListNode p, q;
            p = head;

            do {
                q = p;
                p = p.getNext();
            } while (p.getNext() != head);

            p.setNext(null);
            q.setNext(head);

        }

        length--;
    }

    public void removeAtPosition(int position) throws CustomListException {

        if(head == null)
            throw new CustomListException("List is empty");

        if(position < 0 || position >= length)
            throw new CustomListException("invalid position.");

        if(position == 0) {
            removeAtBegin();
        } else if(position + 1 == length) {
            removeAtEnd();
        } else {
            ListNode itr = head;
            int pos = 0;
            while(pos < position && pos + 1 != position) {
                itr = itr.getNext();
                pos++;
            }
            itr.setNext(itr.getNext().getNext());
            length--;
        }
    }

    @Override
    public String toString() {

        List<Integer> list = new ArrayList<>();

        if(head != null) {
            ListNode itr = head;

            do {
                list.add(itr.getData());
                itr = itr.getNext();
            } while (itr != head);
        }

        return list.toString();
    }

    public void clearList() {
        head = null;
        length = 0;
    }
}
