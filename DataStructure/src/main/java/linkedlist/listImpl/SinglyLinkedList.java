package linkedlist.listImpl;

import linkedlist.exception.CustomListException;
import linkedlist.schema.ListNode;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList {

    private int length;

    ListNode head;

    public SinglyLinkedList() {
        length = 0;
    }

    private void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public ListNode getHead() {
        return head;
    }


    public void insertAtBegin(ListNode listNode) {
        listNode.setNext(head);
        head = listNode;
        length++;
    }

    public void insertAtEnd(ListNode listNode) {

        if(head == null) {
            head = listNode;
        } else {
            ListNode itr = head;
            while(itr.getNext() != null)
                itr = itr.getNext();
            itr.setNext(listNode);
        }
        length++;
    }

    public void insertAtPosition(int data, int position) throws CustomListException {

        if(position < 0 || position > length)
            throw new CustomListException("invalid position");

        ListNode listNode = new ListNode(data);
        if(position == 0)
            insertAtBegin(listNode);
        else if(position == length )
            insertAtEnd(listNode);
        else {
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

    public void deleteFromBegin() throws CustomListException {

        if(head == null)
            throw new CustomListException("no data to delete. Empty List.");
        ListNode listNode = head;
        head = head.getNext();
        listNode.setNext(null);
        listNode = null; // make garbage collection eligible
        length--;

    }

    public void deleteFromEnd() throws CustomListException {
        if(head == null) {
            throw new CustomListException("no data to delete. Empty List.");
        } else if(head.getNext() == null) {
            head = null;
        } else {
            ListNode itr = head;
            while (itr.getNext().getNext() != null)
                itr = itr.getNext();

            itr.setNext(null);
        }
        length--;
    }

    public void deleteAtPosition(int position) throws CustomListException {

        if (position < 0 || position >= length)
            throw new CustomListException("invalid position");

        if (position == 0)
            deleteFromBegin();
        else if (position + 1 == length)
            deleteFromEnd();
        else {
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

    public void deleteMatchedNode(ListNode listNode) throws CustomListException {

        if(listNode == null) {
            if(head == null)
                return;
            throw new CustomListException("node not found");
        }

        if(listNode.equals(head))
            deleteFromBegin();
        else {
            ListNode p, q;
            p = head;
            q = null;
            while(p != null && !p.equals(listNode)) {
                q = p;
                p = p.getNext();
            }
            if (p == null)
                throw new CustomListException("node not found");
            else {
                q.setNext(p.getNext());
                length--;
            }
        }
    }

    public int getPosition(int data) {
        ListNode itr = head;
        int pos = 0;
        while (itr != null) {
            if(itr.getData() == data)
                return pos;
            pos++;
            itr = itr.getNext();
        }

        return -1;
    }
    public void clearList() {
        head = null;
        length = 0;
    }


    public ListNode findTailNode(ListNode head) {

        if(head == null)
            return null;

        ListNode itr = head;
        while (itr.getNext() != null)
            itr = itr.getNext();

        return itr;
    }

    @Override
    public String toString() {

        List<Integer> list = new ArrayList<>();
        ListNode listNode = head;
        while (listNode != null) {

            list.add(listNode.getData());
            listNode = listNode.getNext();

        }
        return list.toString();
    }
}
