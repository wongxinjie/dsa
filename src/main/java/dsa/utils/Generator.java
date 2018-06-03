package dsa.utils;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    public static List<Integer> randIntegerList(int size) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i < size; i++) {
            int x = (int) (Math.random() * 99 + 1);
            list.add(x);
        }
        return list;
    }
}
