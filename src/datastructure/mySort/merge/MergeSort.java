package datastructure.mySort.merge;

import datastructure.strategy.Strategy;

/**
 * Created by zhengjianhui on 17/2/15.
 *
 *
 * 归并排序
 */
public class MergeSort {

    /**
     * 合并算法
     * 输入
     *
     * @param arr 待合并数组arr， 待合并区间有[p - q] [q + 1 - r]
     */
    private static void merge(Object[] arr, int p, int q, int r, Strategy strategy) {
        Object[] newArr = new Object[r - p + 1];

        int s = p;          // 待合并区间 [p - q] 的初始角标
        int t = q + 1;      // 待合并区间 [q - r] 的初始角标
        int k = 0;          // 新数组的初始角标

        // [p - q] 区间的s 小于等于 q 则继续循环（s初始为p 循环过程中自增）
        // [q + 1 - r] 区间的 t 小于等于r 则继续循环（t初始为 q + 1 循环过程中自增）
        while (s <= q && t <= r) {

            // 比较[p - q] 和 [q + 1 - r] 区间的值
            if (strategy.compare(arr[s], arr[t]) < 0) {
                // 将较小的元素 放入新数组
                newArr[k++] = arr[s++];
            } else {
                newArr[k++] = arr[t++];
            }

        }


        // 由于几乎不可能 在上面你的循环中将 arr 中的元素全部移动到 newArr 只要一个区间移动玩就会停止循环
        // 此时要把剩下的元素 移动到 newArr 中
        while (s <= q) {
            newArr[k++] = arr[s++];
        }

        while (t <= r) {
            newArr[k++] = arr[t++];
        }

        // 将newArr的元素覆盖掉arr数组
        // 每次合并 lo 下标 到 r下标的元素（每次二分 都返回排序后的数组后）
        for (int i = 0; i < newArr.length; i++) {
            arr[p + i] = newArr[i];
        }
    }


    /**
     * @param arr      带排序数组
     * @param lo       排序最小边界
     * @param hi       排序最大边界
     * @param strategy 策略
     */
    public static void mergeSort(Object[] arr, int lo, int hi, Strategy strategy) {

        // recursion out
        if (lo < hi) {
            // 将[lo - hi] 区间的数组不拆分
            mergeSort(arr, lo, (lo + hi) / 2, strategy);
            // 将[(lo + hi) / 2 + 1 - hi] 区间的数组不拆分
            mergeSort(arr, (lo + hi) / 2 + 1, hi, strategy);

            // 合并算法
            // 每次合并 区间位数的元素到 原来的位置
            merge(arr, lo, (lo + hi) / 2, hi, strategy);
        }
    }
}
