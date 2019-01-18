package hash.hashmap;

import hash.hashmap.impl.IHashMap;

/**
 * Created by zhengjianhui on 17/10/22.
 */
public class MapTest {

    public static void main(String[] args) {
        IMap<Integer, String> test = new IHashMap<>();


        test.put(new Integer(1), "aa");
        test.put(new Integer(2), "bb");
        test.put(new Integer(17), "123");

        System.out.println(test.get(new Integer(1)));
        System.out.println(test.get(new Integer(2)));
        System.out.println(test.get(new Integer(17)));
        System.out.println(test.size());
    }
}
