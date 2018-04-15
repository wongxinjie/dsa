package dsa.chapter04;


public class AvlTree<T extends Comparable<? super T>>{

    private static class AvlNode<T> {
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        public AvlNode(T element) {
            this(element, null, null);
        }
    }

    private AvlNode<T> root;

    public AvlTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    private AvlNode<T> remove(T x, AvlNode<T> t) {
        //TODO: implement AVL remove action
        /*
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
        */

        return t;
    }

    private AvlNode<T> findMin(AvlNode<T> t) {
        if(t != null) {
            while (t.left != null)
                t = t.left;
        }
        return  t;
    }

    private AvlNode<T> findMax(AvlNode<T> t) {
        if(t != null) {
            while(t.right != null)
                t = t.right;
        }
        return t;
    }


    private boolean contains(T x, AvlNode<T> t) {
        if(t == null)
            return false;

        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            return contains(x, t.left);
        else if(compareResult > 0)
            return contains(x, t.right);

        return true;
    }


    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if(t == null)
            return new AvlNode<>(x);

        int compareResult = x.compareTo(t.element);
        if(compareResult < 0) {
            t.left = insert(x, t.left);
            if(height(t.left)  - height(t.right) == 2) {
                if(compare(x, t.left.element) < 0)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
            }
        } else if(compareResult > 0) {
            t.right = insert(x, t.right);
            if(height(t.right) - height(t.left) == 2) {
                if(compare(x, t.right.element) < 0)
                    t = doubleWithRightChild(t);
                else
                    t = rotateWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private int compare(T x, T y) {
        return x.compareTo(y);
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> node) {
        // LL
        AvlNode<T> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        leftChild.height = Math.max(height(leftChild.left), node.height) + 1;

        return leftChild;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> node) {
        // RR
        AvlNode<T> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rightChild.height = Math.max(height(rightChild.left), node.height) + 1;

        return rightChild;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> node) {
        // LR -> LL
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> node) {
        // RL -> RR
        node.right  = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }

}
