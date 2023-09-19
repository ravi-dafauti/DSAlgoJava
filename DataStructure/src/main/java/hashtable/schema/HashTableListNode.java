package hashtable.schema;

public class HashTableListNode <K,V> {

    private K key;
    private V value;
    private HashTableListNode next;

    public HashTableListNode() {

    }

    public HashTableListNode(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashTableListNode getNext() {
        return next;
    }

    public void setNext(HashTableListNode next) {
        this.next = next;
    }
}
