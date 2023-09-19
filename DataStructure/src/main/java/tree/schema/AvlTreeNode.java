package tree.schema;

public class AvlTreeNode {

    private AvlTreeNode left;
    private AvlTreeNode right;
    private int height;
    private int data;

    public AvlTreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
        height = 0;
    }

    public AvlTreeNode getLeft() {
        return left;
    }

    public void setLeft(AvlTreeNode left) {
        this.left = left;
    }

    public AvlTreeNode getRight() {
        return right;
    }

    public void setRight(AvlTreeNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
