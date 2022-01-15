package mylink.mylink;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    Map<Integer, String> hm = new HashMap<>();

    @Test
    void put() {
        hm.put(1, "A");
        hm.put(2, "B");
        hm.put(3, "C");
        hm.put(4, "D");
        System.out.println(hm.keySet());
        System.out.println(hm.values());
        System.out.println(hm.get(1));
        //4번 삭제
        hm.remove(4);
        System.out.println(hm.keySet());
        System.out.println(hm.values());

    }

}
