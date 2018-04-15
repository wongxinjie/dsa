package dsa.chapter04;

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

    public void printTree() {
        if(isEmpty())
            System.out.println("Empty tree");
        else
            inorder(root);
    }
}
