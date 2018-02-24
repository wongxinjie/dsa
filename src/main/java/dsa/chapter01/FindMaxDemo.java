package dsa.chapter01;

import java.util.Comparator;

class CaseInsensitiveCompare implements Comparator<String> {
    public int compare(String ls, String rs) {
        return ls.compareToIgnoreCase(rs);
    }
}

public class FindMaxDemo {

    public static <T> T findMax(T[] arr, Comparator<? super T> comparator) {
        int maxIndex = 0;

        for(int i=1; i < arr.length; i++) {
            if(comparator.compare(arr[i], arr[maxIndex]) > 0)
                maxIndex = i;
        }

        return arr[maxIndex];
    }

    public static void main(String[] args) {
        String[] words = {"ZEBRA", "alligator", "crocodile"};
        System.out.println(findMax(words, new CaseInsensitiveCompare()));
    }
}
