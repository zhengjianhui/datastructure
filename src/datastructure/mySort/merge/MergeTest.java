package datastructure.mySort.merge;

import org.junit.Test;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/15.
 */
public class MergeTest {

    @Test
    public void test() {
        Integer[] arr = {15, 10, 5, 8, 9, 11, 20 ,30, 22};

        MergeSort.mergeSort(arr, 0, arr.length - 1, new Strategy() {
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
