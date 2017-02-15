package datastructure.mySort.select;

import org.junit.Test;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/15.
 */
public class HeapTest {


    @Test
    public void test() {
        Integer[] arr = {0, 28, 26, 17, 36, 20, 42, 11 ,53};

        HeapSort.heapSort(arr, new Strategy() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Integer o1 = (Integer) obj1;
                Integer o2 = (Integer) obj2;
                return o1.compareTo(o2);

                // 调整排序策略焕发你大顶堆
                // return o1 > o2 ? -1 : (o1 < o2) ? 1 : 0;
            }
        });

        for (Integer i : arr) {
            System.out.println(i);
        }
    }

    /**
     *
     *  结果
     *
     *                11
     *          17          20
     *      26     28   36      42
     * 53
     *
     *
     */
}
