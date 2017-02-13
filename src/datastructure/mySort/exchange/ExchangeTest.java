package datastructure.mySort.exchange;

import org.junit.Test;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/13.
 */
public class ExchangeTest {

    @Test
    public void test1() {

        Integer[] arr = {55, 10, 5, 8, 11, 9, 20 ,30, 22, 1};
        Exchange.bubbleSort(arr, new Strategy() {
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

    @Test
    public void test2() {
        Integer[] arr = {55, 10, 5, 8, 11, 9, 20 ,30, 22, 1};
        Exchange.bubbleSort(arr, new Strategy() {
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
