package strings;

public class TernarySearchTree {

    private TstNode root;

    public TernarySearchTree() {
        this.root = null;
    }

    public void insert(String text) {
        root = insertUtil(root, text, 0);
    }

    public TstNode insertUtil(TstNode root , String text, int pos) {
        if(root == null) {
            TstNode temp =  new TstNode(text.charAt(pos));
            if(pos == text.length() - 1) {
                temp.setEndOfLine(true);
            } else {
               temp.setEqual(insertUtil(temp.getEqual(), text, pos + 1));
            }
            return temp;
        } else {
            if(root.getData() > text.charAt(pos)) {
                root.setLeft(insertUtil(root.getLeft(), text, pos));
            } else if(root.getData() < text.charAt(pos)) {
                root.setRight(insertUtil(root.getRight(), text, pos));
            } else {
                if(pos == text.length() - 1) {
                    if(!root.isEndOfLine()) {
                        root.setEndOfLine(true);
                    }
                } else {
                    root.setEqual(insertUtil(root.getEqual(), text, pos + 1));
                }

            }
        }
        return root;
    }

    public boolean search(String text) {
        if(this.root == null)
            return false;
        return searchUtil(root, text, 0);

    }

    public boolean searchUtil(TstNode root, String text, int pos) {

        if(root == null) {
            return false;
        }
        if(text.charAt(pos) == root.getData()) {
            if(pos == text.length() - 1) {
                return root.isEndOfLine() ? true : false;
            } else {
                return searchUtil(root.getEqual(), text, pos + 1);
            }
        } else if (text.charAt(pos) < root.getData()) {
            return searchUtil(root.getLeft(), text, pos);
        } else {
            return searchUtil(root.getRight(), text, pos);
        }
    }

    public static void main(String[] args) {

        TernarySearchTree ternarySearchTree = new TernarySearchTree();

        ternarySearchTree.insert("abc");
        ternarySearchTree.insert("ravinder");
        ternarySearchTree.insert("deepadafauti");

        System.out.println(ternarySearchTree.search("bc"));
    }
}


class TstNode {
    private char data;
    private boolean isEndOfLine;
    private TstNode left;
    private TstNode right;
    protected TstNode equal;

    public TstNode(char data) {
        this.data = data;
        isEndOfLine = false;
        left = right = equal = null;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public boolean isEndOfLine() {
        return isEndOfLine;
    }

    public void setEndOfLine(boolean endOfLine) {
        isEndOfLine = endOfLine;
    }

    public TstNode getLeft() {
        return left;
    }

    public void setLeft(TstNode left) {
        this.left = left;
    }

    public TstNode getRight() {
        return right;
    }

    public void setRight(TstNode right) {
        this.right = right;
    }

    public TstNode getEqual() {
        return equal;
    }

    public void setEqual(TstNode equal) {
        this.equal = equal;
    }
}