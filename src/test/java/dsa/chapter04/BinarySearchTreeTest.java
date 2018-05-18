package dsa.chapter04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeTest {

    @Test
    public void testBinaryTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        List<Integer> list = new ArrayList<>();
        for(int i=1; i < 11; i++) {
            list.add(i);
        }
        Collections.shuffle(list);


        System.out.println(list);
        for(Integer n: list) {
            tree.insert(n);
        }

        tree.printTree();

        int min = tree.findMin();
        System.out.println(min);
        int max = tree.findMax();

        assertEquals(min, 1);
        assertEquals(max, 10);

        tree.remove(10);
        assertEquals(false, tree.contains(10));

        Integer x = 20;
        tree.insert(x);
        assertEquals(tree.findMax(), x);
    }

}
