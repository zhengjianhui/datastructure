package datastructure.mySort.insert;

import org.junit.Test;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/13.
 */
public class InsertTest {

    @Test
    public void test1() {
        Integer[] arr = {1, 10, 5, 8, 9, 11, 20 ,30};

        Insert.directlyInsertSort(arr, 0, arr.length - 1, new Strategy() {
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
        Integer[] arr = {15, 10, 5, 8, 9, 11, 20 ,30, 22};

        Insert.binInsertSort(arr, 0, arr.length - 1, new Strategy() {
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
    public void test3() {
        Integer[] arr = {1, 10, 5, 8, 11, 9, 20 ,30, 22};
        int[] delta = {2, 3, 5, 1};

        Insert.shellSort(arr, 0, arr.length - 1, new Strategy() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Integer o1 = (Integer) obj1;
                Integer o2 = (Integer) obj2;
                return o1.compareTo(o2);
            }
        }, delta);


        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

}
