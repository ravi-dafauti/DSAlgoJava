package tree.treeimpl;

import tree.schema.AvlTreeNode;

public class AvlTree {

    AvlTreeNode root;

    public AvlTree() {
        this.root = null;
    }

    public int getHeight() {
        if(root == null)
            return -1;
        return root.getHeight();
    }

    private int height(AvlTreeNode node) {
        if(node == null)
            return -1;
        return node.getHeight();
    }

    private AvlTreeNode lLRotation(AvlTreeNode w) {
        AvlTreeNode x = w.getLeft();
        w.setLeft(x.getRight());
        x.setRight(w);
        w.setHeight(Math.max(height(w.getLeft()), height(w.getRight())) + 1);
        x.setHeight(Math.max(w.getHeight(), height(w.getLeft())) + 1);
        return x;
    }

    private AvlTreeNode rRRotation(AvlTreeNode x) {
        AvlTreeNode w = x.getRight();
        x.setRight(w.getLeft());
        w.setLeft(x);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        w.setHeight(Math.max(x.getHeight(), height(w.getRight())) + 1);
        return w;
    }

    private AvlTreeNode lRRotation(AvlTreeNode y) {
        y.setLeft(rRRotation(y.getLeft()));
        return lLRotation(y);
    }

    private AvlTreeNode rLRotation(AvlTreeNode y) {
        y.setRight(lLRotation(y.getRight()));
        return rRRotation(y);
    }

    public void insert(int data) {
        this.root = insertToAvl(this.root, data);
    }

    private AvlTreeNode insertToAvl(AvlTreeNode root, int data) {

        if(root == null) {
            return new AvlTreeNode(data);
        }

        if(data < root.getData()) {
            root.setLeft(insertToAvl(root.getLeft(), data));

            if(height(root.getLeft()) - height(root.getRight()) > 1) {
                if(data < root.getLeft().getData()) {
                    root = lLRotation(root);
                } else if (data > root.getLeft().getData()) {
                    root = lRRotation(root);
                }
            }

        } else if(data > root.getData()) {
            root.setRight(insertToAvl(root.getRight(), data));

            if(height(root.getRight()) - height(root.getLeft()) > 1) {
                if(data < root.getRight().getData()) {
                    root = rLRotation(root);
                } else if (data > root.getRight().getData()) {
                    root = rRRotation(root);
                }
            }
        }

        root.setHeight(Math.max(height(root.getRight()), height(root.getLeft())) + 1);
        return root;
    }

    public void deleteNode(int data) {
        this.root = deleteFromAvl(this.root, data);
    }

    public AvlTreeNode max(AvlTreeNode root) {
        if(root == null)
            return null;
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    private AvlTreeNode deleteFromAvl(AvlTreeNode root, int data) {
        if(root != null) {
            if(root.getData() > data) {
                root.setLeft(deleteFromAvl(root.getLeft(), data));
                if(Math.abs(height(root.getLeft()) - height(root.getRight())) == 2) {
                    if(root.getLeft().getData() < data) {
                        root = lLRotation(root);
                    } else if (root.getLeft().getData() > data) {
                        root = lRRotation(root);
                    }
                }
            } else if(root.getData() < data) {
                root.setRight(deleteFromAvl(root.getRight(), data));
                if(Math.abs(height(root.getLeft()) - height(root.getRight())) == 2) {
                    if(root.getRight().getData() > data) {
                        root = rRRotation(root);
                    } else if (root.getRight().getData() < data) {
                        root = rLRotation(root);
                    }
                }
            }  else {
                if(root.getLeft() != null && root.getRight() != null) {
                    AvlTreeNode max = max(root.getLeft());
                    root.setData(max.getData());
                    max.setData(data);
                    root.setLeft(deleteFromAvl(root.getLeft(), data));
                } else if (root.getLeft() == null) {
                    root = root.getRight();
                } else if(root.getRight() == null) {
                    root = root.getLeft();
                }
            }
            root.setHeight(Math.max(height(root.getRight()), height(root.getLeft())) + 1);
        }
        return root;
    }
}
