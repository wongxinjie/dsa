package dsa.chapter04;

public class AvlTreeTest {

    public void testAvlTree(){
        AvlTree<Integer> avl = new AvlTree<>();
        // test "insert"
        avl.insert(7);
        avl.insert(2);
        avl.insert(1);
        avl.insert(1);
        avl.insert(5);
        avl.insert(3);
        avl.insert(6);
        avl.insert(4);
        avl.insert(9);
        avl.insert(8);
        avl.insert(11);
        avl.insert(11);
        avl.insert(10);
        avl.insert(12);
        avl.print();

        System.out.println(avl.contains(2));
        System.out.println(avl.contains(100));

        avl.remove(1);
        avl.print();

        avl.remove(9);
        avl.print();
    }

    public static void main(String[] args) {
        new AvlTreeTest().testAvlTree();
    }
}
