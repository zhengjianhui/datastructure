package datastructure.mySort.select;

import org.junit.Test;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/14.
 */
public class SelectTest {

    @Test
    public void test() {
        Integer[] arr = {15, 10, 5, 8, 9, 11, 20 ,30, 22};

        SelectSort.selectSort(arr, 0, arr.length - 1, new Strategy() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Integer o1 = (Integer) obj1;
                Integer o2 = (Integer) obj2;
                return o1.compareTo(o2);
            }
        });


        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
