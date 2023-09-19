package hashtable.schema;

import hashtable.exception.HashTableException;


public class HashTable<K,V> {

    private HashTableListNode[] hashTable;
    private int loadFactor;
    private int hashTableSize;
    private int count;

    public HashTable() {
        // creating initial hashTable of size 10..
        this.hashTable = new HashTableListNode[10];
        loadFactor = 10; // keeping LF as 10
        hashTableSize = 10;
        count = 0;
    }

    private int hashFunction(K key) {
        return key.hashCode() % hashTableSize;
    }

    private void rehash() {

        hashTableSize = hashTableSize * 2;
        HashTableListNode[] newHashTable = new HashTableListNode[hashTableSize];
        for(int i = 0; i < hashTableSize / 2 ; i++) {
            HashTableListNode itr = hashTable[i];
            while (itr != null) {
                HashTableListNode temp = itr;
                itr = itr.getNext();
                hashTable[i].setNext(itr);
                temp.setNext(null);
                int position = hashFunction((K)temp.getKey());
                temp.setNext(newHashTable[position].getNext());
                newHashTable[position].setNext(temp);
            }
        }
        hashTable = newHashTable;
    }

    private Object searchInList(HashTableListNode head, K key) throws HashTableException{
        if(head == null)
            throw new HashTableException("key not present");
        while(head != null) {
            if(head.getKey() == key)
                return head.getValue();
            head = head.getNext();
        }
        throw new HashTableException("key not present");
    }

    private HashTableListNode deleteFromList(HashTableListNode head , K key) {

        HashTableListNode a, b;

        b = null;
        a = head;

        while (a != null && a.getKey() != key) {
            b = a;
            a = a.getNext();
        }

        if(a == null)
            return head;

        if(b == null) {
            HashTableListNode temp = head.getNext();
            head.setNext(null);
            head = temp;
        } else {
            b.setNext(a.getNext());
            a.setNext(null);
        }

        return head;

    }

    public void insert(K key, V value) throws HashTableException{
        if(key == null) {
            throw new HashTableException("cannot insert as key is null");
        }

        int position = hashFunction(key);
        HashTableListNode<K, V> node = new HashTableListNode<>(key, value);
        node.setNext(hashTable[position].getNext());
        hashTable[position].setNext(node);
        count++;
        if((count / hashTableSize) > loadFactor)
            rehash();
    }

    public V get(K key) throws HashTableException{
        return (V)searchInList(hashTable[hashFunction(key)].getNext(), key);
    }

    public void remove(K key){
        if(key == null)
            return;
        hashTable[hashFunction(key)].setNext(deleteFromList(hashTable[hashFunction(key)].getNext(), key));
        count--;
    }

}
