package strings;


public class Tries {

    private TrieNode root;

    public Tries() {
        root = new TrieNode('$');
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String text) {
        int pos = 0;
        TrieNode itr = root;
        while(true) {
            if(pos == text.length()) {
                if(!itr.isEndOfString()) {
                    itr.setEndOfString(true);
                }
                break;
            } else {
                if(itr.getChildren()[text.charAt(pos) - 'a'] == null) {
                    itr.getChildren()[text.charAt(pos)- 'a'] = new TrieNode(text.charAt(pos));
                }
                itr = itr.getChildren()[text.charAt(pos) - 'a'];
                pos++;
            }
        }
    }

    public boolean search(String text) {
        int pos = 0;
        TrieNode itr = root;
        while(true) {
            if(pos == text.length()) {
                if(itr.isEndOfString()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if(itr.getChildren()[text.charAt(pos) - 'a'] == null) {
                    return false;
                } else {
                    itr = itr.getChildren()[text.charAt(pos) - 'a'];
                    pos++;
                }
            }
        }
    }

    public static void main(String[] args) {


        Tries tries = new Tries();
        tries.insert("abc");
        tries.insert("banana");

        System.out.println(tries.search("abc"));
        System.out.println(tries.search("df"));
        System.out.println(tries.search("ravinder"));
        System.out.println(tries.search("banana"));

    }
}

class TrieNode {
    private char data;
    private boolean isEndOfString;
    private TrieNode[] children;

    public TrieNode(char data) {
        this.data = data;
        this.isEndOfString = false;
        children = new TrieNode[26];
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public boolean isEndOfString() {
        return isEndOfString;
    }

    public void setEndOfString(boolean endOfString) {
        isEndOfString = endOfString;
    }

    public TrieNode[] getChildren() {
        return children;
    }

    public void setChildren(TrieNode[] children) {
        this.children = children;
    }
}