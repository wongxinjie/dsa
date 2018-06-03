package dsa.chapter06;

import java.util.Arrays;

public class LeftistHeap <T extends Comparable<? super T>> {

    public LeftistHeap() {
        root = null;
    }

    public void merge(LeftistHeap<T> r) {
        if (this == r) {
            return;
        }

        root = merge(root, r.root);
        r.root = null;
    }

    public void insert(T x) {
        root = merge(new Node<>(x), root);
    }

    public T findMin() {
        return root.element;
    }

    public T deleteMin() {
        if(isEmpty())
            return null;

        T min = root.element;
        root = merge(root.left, root.right);
        return min;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    private Node<T> merge(Node<T> h1, Node<T> h2) {
        if(h1 == null)
            return h2;
        if(h2 == null)
            return h1;

        if(h1.element.compareTo(h2.element) < 0)
            return merge1(h1, h2);
        else
            return merge1(h2, h1);
    }

    private Node<T> merge1(Node<T> h1, Node<T> h2) {
        if(h1.left == null)
            h1.left = h2;
        else {
            h1.right = merge(h1.right, h2);
            if(h1.left.npl < h1.right.npl)
                swapChildren(h1);
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private void swapChildren(Node<T> t) {
        Node<T> x = t.left;
        t.left = t.right;
        t.right = x;
    }


    private static class Node<T> {
        Node(T element) {
            this(element, null, null);
        }

        Node(T element, Node<T> left, Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.npl = 0;
        }

        T element;
        Node<T> left;
        Node<T> right;
        int npl;
    }

    private Node<T> root;

    public static void main(String[] args) {
        LeftistHeap<Integer> ranges = new LeftistHeap<>();

        Integer[] list = new Integer[10];
        for(int i=0; i < 10; i++) {
            list[i] = (int) (Math.random() * 9 + 1);
        }
        System.out.println(Arrays.asList(list));

        for (Integer x : list) {
            ranges.insert(x);
        }

        System.out.println(ranges.findMin());
        System.out.println(ranges.deleteMin());
        System.out.println(ranges.findMin());
    }
}
