package tree.schema;

import java.util.Objects;

public class BinaryTreeNode {
    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    //only for the sibling questions
    private BinaryTreeNode sibling;

    public BinaryTreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.sibling = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public BinaryTreeNode getSibling() {
        return sibling;
    }

    public void setSibling(BinaryTreeNode sibling) {
        this.sibling = sibling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTreeNode that = (BinaryTreeNode) o;
        return data == that.data &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, left, right);
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "data=" + data +
                '}';
    }
}
