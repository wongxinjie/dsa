package dsa.chapter01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMaxDemoTest {

    @Test
    public void testFindMaxDemo() {
        String[] words = {"ZEBRA", "alligator", "crocodile"};
        String expected = "ZEBRA";
        String result = FindMaxDemo.findMax(words, new CaseInsensitiveCompare());

        assertEquals(expected, result);
    }
}
