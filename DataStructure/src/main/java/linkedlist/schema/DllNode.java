package linkedlist.schema;

public class DllNode {
    private int data;
    private DllNode prev;
    private DllNode next;


    public DllNode(int data) {
        this.data = data;
        prev = null;
        next = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public DllNode getPrev() {
        return prev;
    }

    public void setPrev(DllNode prev) {
        this.prev = prev;
    }

    public DllNode getNext() {
        return next;
    }

    public void setNext(DllNode next) {
        this.next = next;
    }
}
