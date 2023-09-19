package linkedlist.listImpl;

import linkedlist.exception.CustomListException;
import linkedlist.schema.DllNode;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList {

    private DllNode head;
    private DllNode tail;
    private int length;

    public DoublyLinkedList() {
        length = 0;
        tail = null;
        head = null;
    }
    public DllNode getHead() {
        return head;
    }

    public void setHead(DllNode head) {
        this.head = head;
    }

    public DllNode getTail() {
        return tail;
    }

    public void setTail(DllNode tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void insertAtBegin(DllNode dllNode) {
        if(dllNode == null)
            return;
        if(head == null) {
            head = dllNode;
            tail = dllNode;
        } else {
            dllNode.setNext(head);
            head.setPrev(dllNode);
            head = dllNode;
        }

        length ++;

    }

    public void insertAtEnd(DllNode dllNode) {

        if(dllNode == null)
            return;

        if(head == null) {
            head = dllNode;
            tail = dllNode;
        } else {
            tail.setNext(dllNode);
            dllNode.setPrev(tail);
            tail = dllNode;
        }

        length ++;
    }


    public void insertAtPosition(int data, int position) throws CustomListException {

        if(position < 0 || position > length)
            throw new CustomListException("invalid position");

        DllNode dllNode = new DllNode(data);

        if(head == null) {
            head = dllNode;
            tail = dllNode;
            length++;
        } else if (position == 0) {
            insertAtBegin(dllNode);
        } else if(position == length) {
            insertAtEnd(dllNode);
        } else {
            DllNode itr = head;
            int pos = 0;

            while (pos < position && pos + 1 != position) {
                itr = itr.getNext();
                pos++;
            }
            dllNode.setNext(itr.getNext());
            dllNode.getNext().setPrev(dllNode);
            itr.setNext(dllNode);
            dllNode.setPrev(itr);
            length ++;
        }
    }


    public void removeAtBegin() throws CustomListException {
        if(head == null)
            throw new CustomListException("List is empty!!");

        if (head == tail){
            head = null;
            tail = null;
        } else {
            DllNode p = head.getNext();
            p.setPrev(null);
            head.setNext(null);
            head = p;
        }

        length--;
    }

    public void removeAtEnd() throws CustomListException {
        if(head == null)
            throw new CustomListException("List is empty!!");

        if (head == tail){
            head = null;
            tail = null;
        } else {
            DllNode p = tail.getPrev();
            p.setNext(null);
            tail.setPrev(null);
            tail = p;
        }
        length--;
    }

    public void removeAtPos(int position) throws CustomListException {

        if(position < 0 || position >= length)
            throw new CustomListException("invalid position");

        if(position == 0) {
            removeAtBegin();
        } else if(position + 1 == length) {
            removeAtEnd();
        } else {
            DllNode itr = head;
            int pos = 0;
            while(pos  != position) {
                itr = itr.getNext();
                pos++;
            }
            itr.getNext().setPrev(itr.getPrev());
            itr.getPrev().setNext(itr.getNext());
            itr.setNext(null);
            itr.setPrev(null);
            length--;
        }
    }

    @Override
    public String toString() {

        List<Integer> list = new ArrayList<>();
        DllNode p = head;
        while (p != null) {

            list.add(p.getData());
            p = p.getNext();

        }
        return list.toString();
    }
}
