package datastructure.mySort.select;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/14.
 * 查找类排序
 */
public class SelectSort {

    /**
     * 外层每次 i 递增 1 起始以最小边界作为对比元素
     * 内层循环每次循环如果找到最小的元素，则将该元素作为新的对比元素
     * 外层没次循环后，如果当前最小的元素下标 != i 说明找到了比初始i角标更小的元素（最小）交换两个下标的值
     * 每次找到最小元素交换后 i 递增（这样就i 递增后 i 前的元素都是有序的）
     *
     * @param arr
     * @param lo
     * @param hi
     * @param strategy
     */
    public static void selectSort(Object[] arr, int lo, int hi, Strategy strategy) {

        for (int i = lo; i < hi; i++) { // arr的边界 - 1（最后次不需要比较）
            int min = i;

            for (int j = min + 1; j <= hi; j++) { // 起始位置为初始设置最小值的后一位

                // 如果找到更小的元素，就使新元素称为最新的对比对象
                if(strategy.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
                
            }

            if (min != i) { // 将最小的元素与 第 i 的下标交换位置
                Object temp = arr[i]; // 保存当前 i 次循序时对应下标的值
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
}
