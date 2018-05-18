package dsa.chapter04;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public BinaryNode(T element) {
            this(element, null, null);
        }
    }

    private BinaryNode<T> root;

    protected BinaryNode<T> getRoot() {
        return this.root;
    }

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        this.root = null;
    }


    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMax() {
        if(isEmpty())
            throw new IndexOutOfBoundsException();

        return findMax(root).element;
    }

    public T findMin() {
        if(isEmpty())
            throw new IndexOutOfBoundsException();

        return findMin(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if(t == null)
            return false;

        int compareResult = x.compareTo(t.element);
        if(compareResult > 0) {
            return contains(x, t.right);
        } else if(compareResult < 0) {
            return contains(x, t.left);
        }

        return true;
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if(t != null) {
            while (t.left != null)
                t = t.left;
        }
        return  t;
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if(t != null) {
            while(t.right != null)
                t = t.right;
        }
        return t;
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if(t == null)
            return new BinaryNode<T>(x, null, null);

        int compareResult = x.compareTo(t.element);
        if(compareResult > 0){
            t.right = insert(x, t.right);
        } else if(compareResult < 0) {
            t.left = insert(x, t.left);
        }

        return t;
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if(t == null)
            return t;

        int compareResult = x.compareTo(t.element);
        if(compareResult < 0) {
            t.left = remove(x, t.left);
        } else if(compareResult > 0) {
            t.right = remove(x, t.right);
        } else if(t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void preorder(BinaryNode<T> t) {
        if(t != null) {
            System.out.println(t.element + "");
            preorder(t.left);
            preorder(t.right);
        }
    }

    private void inorder(BinaryNode<T> t) {
        if(t != null) {
            inorder(t.left);
            System.out.println(t.element + " ");
            inorder(t.right);
        }
    }

    private void postOrder(BinaryNode<T> t) {
        if (t != null) {
            postOrder(t.left);
            postOrder(t.right);
            System.out.println(t.element + "");
        }
    }

    private void levelOrder(BinaryNode<T> t) {
        Queue<BinaryNode<T>> nodes = new LinkedList<>();
        nodes.add(t);

        while (!nodes.isEmpty()) {
            BinaryNode<T> n = nodes.remove();
            System.out.print(n.element + " ");
            if (n.left != null) {
                nodes.add(n.left);
            }
            if (n.right != null) {
                nodes.add(n.right);
            }
        }
        System.out.println();
    }

    public void printTree() {
        if(isEmpty())
            System.out.println("Empty tree");
        else
            // inorder(root);
            levelOrder(root);
    }

    /**
     * 求树的叶子树
     * @param t
     * @return
     */
    public int leaveCount(BinaryNode<T> t) {
        if(t == null)
            return 0;
        if(t.left == null && t.right == null)
            return 1;

        return leaveCount(t.left) + leaveCount(t.right);
    }

    public int depth(BinaryNode<T> t) {
        if(t == null)
            return 0;

        int leftDepth = depth(t.left) + 1;
        int rightDepth = depth(t.right) + 1;
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }

    public boolean isSameStruct(BinarySearchTree<T> other) {
        return isSameStruct(other.getRoot(), this.root);
    }

    /**
     * 两棵树的结构是否相同
     * @param x
     * @param y
     * @return
     */
    private boolean isSameStruct(BinaryNode<T> x, BinaryNode<T> y) {
        if (x == null && y == null)
            return true;
        else if (x == null || y == null)
            return false;
        return isSameStruct(x.left, y.left) && isSameStruct(x.right, y.right);
    }

    /**
     * 树的镜像树
     * @param t
     */
    public void mirror(BinaryNode<T> t) {
        if (t == null)
            return;

        BinaryNode<T> n = t.left;
        t.left = t.right;
        t.right = n;

        mirror(t.left);
        mirror(t.right);
    }
}
